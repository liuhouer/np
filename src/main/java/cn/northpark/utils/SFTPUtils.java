package cn.northpark.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SFTPUtils {

	
	private final static Logger logs = Logger.getLogger(SFTPUtils.class);
	
	private static ChannelSftp sftp;
	
	public static List<Map<String,String>> finalfilelist  =  new ArrayList<Map<String,String>>() ;
	
	public static String dirname = "";
	
	public static ChannelSftp connect(String host , int port ,String username ,String password){
		if (sftp != null) 
			return sftp;
		
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			logs.info("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logs.info("Session connected.");
			logs.info("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			logs.info("Connected to " + host + ".");
		} catch (Exception e) {
			logs.error("connect Exception", e);
		}
		return sftp;
		
	}
	
	
	
	public static boolean fileExist(String dir,String fileName) {
		try {
			Vector v = sftp.ls(dir);
			if (v == null || v.size() <= 0)
				return false;
			for (Object o : v) {
				if (o.toString().indexOf(fileName) > -1) {
					return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public static void download(String directory, String downloadFile, String saveFile,String dateStr,ChannelSftp sftp) {
		try {
			logs.info("download start.");
			sftp.cd(directory);
			File file = new File(saveFile);
			
			//先判断带日期的日志是否存在
			boolean exist = fileExist(directory,downloadFile+dateStr);
			if (exist) {
				downloadFile = downloadFile + dateStr ;
			} else {
				//判断不带日期的日志在不在
				exist = fileExist(directory,downloadFile);
			}
			
			//文件存在，则下载文件
			if (exist) {
				sftp.get(downloadFile, new FileOutputStream(file));
			} else {
				System.out.println("--->文件不存在！");
			}
			
			logs.info("download finish.");
		} catch (Exception e) {
			//logs.error("download Exception", e);
			e.printStackTrace();
		}
	}
	
	public static void closeSftp(){
		if (sftp == null)
			return;
		try {
			sftp.disconnect();
			sftp.exit();
			sftp = null;
		} catch (Exception e) {
			logs.error("close Excepton",e);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
	
		
		ChannelSftp sftp = connect("123.57.10.193", 22, "root", "Jix141118");
		try {
			String path  = "/opt/statics/audios/";
			
			
			List<Map<String, String>> fileList = getFileList(sftp, path);
			
			for (int i = 0; i < fileList.size(); i++) {
				Map<String, String> map  = fileList.get(i);
				
				String fileurl = map.get("fileurl").replaceAll("/opt/statics/", "http://static.lvzheng.com/");

				System.out.println(fileurl);
			        Clip clip = AudioSystem.getClip();
			        AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));
			        clip.open(ais);
			        System.out.println( clip.getMicrosecondLength() / 1000000D + " s" );//获取音频文件时长
				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeSftp();
		}
		
	}



	/**
	 * 递归获取文件列表
	 * @param sftp
	 * @param path
	 * @throws SftpException
	 */
	public static List<Map<String,String>> getFileList(ChannelSftp sftp, String path) throws SftpException {
		Vector filelist = sftp.ls(path);

		
		String filename = "";
		String fileurl = "";
		for (Object o : filelist) {
			LsEntry model = (LsEntry) o;
			
			SftpATTRS mattr = model.getAttrs();
			Map map = new HashMap<String,String>();
			//是文件夹
			if(mattr.isDir()){
				String path1 = path+model.getFilename();
//				System.out.println("path1--"+path1);
				if(!path1.endsWith(".")){
					dirname = model.getFilename();
					
					getFileList(sftp, path1);
				}
			}else{//不是文件夹
				filename = model.getFilename();
				fileurl = path+"/"+model.getFilename();
				map.put("filename", filename );
				map.put("dirname", dirname);
				map.put("fileurl", fileurl);
				
				finalfilelist.add(map);
//				System.out.println(dirname+"    "+fileurl+"----->");
			}
		}
		return finalfilelist;
	}
}
