package cn.northpark.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.util.Base64Decoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

	  
	/**
	 * @author Bruce
	 *
	 */
	/**
	 * @author Bruce
	 *
	 */
	public class FileUtils {  
		
		private static final Logger LOGGER = Logger.getLogger(FileUtils.class);
		
		 /**
		 * 头像
		 */
		 public static final String suffix_head = "heads";
		 
		 
		 /**
		 * 专辑
		 */
		public static final String suffix_album = "album";
		
		 /**
		 * 文件
		 */
		public static final String suffix_upload = "upload";
		 
		 //-------------以下为northpark公用上传 、下载 、删除相关方法-------------------------------------------
		
		/**
		 * 以下为northpark文件删除方法
		 * @param oldpath
		 * @param file
		 */
		public static void removeOldFile(String oldpath, MultipartFile[] file) {
			if (file.length >= 1) {
				LOGGER.info(file[0].getOriginalFilename()+ "------------------------------------------------》》");
				
				Properties prop = System.getProperties();

				String os = prop.getProperty("os.name");
				String path = "/mnt/apk/";
		         if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
		        	 path = "e:/bruce/";
		         }else{
		        	 path = "/mnt/apk/";
		         }
				if (StringUtils.isNotEmpty(file[0].getOriginalFilename()) && StringUtils.isNotEmpty(oldpath)) {// 新上传了图片才把以前的删除
						File f = new File(path+oldpath);
						LOGGER.info("要删除文件的绝对路径是：" + f.getAbsolutePath());
						if (f.exists()) {
							f.delete();
						} else {
							LOGGER.error("文件已经丢失!");
						}
				}
			}
		}
		 
		 
		 /**
		  * 以下为northpark上传upload相关方法
		 * @param file
		 * @param suffix
		 * @return 保存的路径数值集合
		 */
		public static List<String> commonUpload(MultipartFile[] file ,String suffix)  {
		    LOGGER.info("-------------------------------------->开始");  
		    
		    List<String> list = new ArrayList<String>();
		    Properties prop = System.getProperties();

			String os = prop.getProperty("os.name");
			String path = "/mnt/apk/";
			String fileName="";
			String newName="";
	         if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
	        	 path = "e:/bruce/";
	         }else{
	        	 path = "/mnt/apk/";
	         }
	         
	         String pre_path   =path+suffix+"/"; //["/mnt/apk/heads/"]
	         
	         for (int i = 0; i < file.length; i++) {
	        	String tail_path   ="";         //["/heads/100101.ext"数据库保存的数值]
	        	
	        	fileName = file[i].getOriginalFilename();  
	        	//fileName为空时证明用户没有上传文件
	        	if(StringUtils.isNotEmpty(fileName)){
	        		String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()); 
			        newName = String.valueOf(System.currentTimeMillis())+"."+ext;
			        File targetFile = new File(pre_path, newName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }  
			        //保存  
			        try {  
			            file[i].transferTo(targetFile);  
			        } catch (Exception e) {  
			            LOGGER.error("上传文件异常------->", e);
			            continue;
			        }  
			        tail_path = "/"+suffix+"/"+newName;
			       
			        list.add(tail_path);
	        	}
		        
			}
	         System.gc();
	         LOGGER.info("-------------------------------------->结束");
			return list;
		 
		 }
		 
		 
		 
		 //-------------以下为爬虫相关方法-------------------------------------------
	    /** 
	     * 获得网络图片地址。或者图片地址 
	     * @param url 
	     * @return 
	     */  
	    public static String getContentFromWeb(String url)  
	    {  
	        String filecontent="";  
	        InputStream is=null;  
	        BASE64Encoder base=new BASE64Encoder();  
	        if(url.startsWith("http"))  
	        {  
	            try {  
	                HttpURLConnection urlconn=(HttpURLConnection)new URL(url).openConnection();  
	                is=urlconn.getInputStream();  
	            } catch (MalformedURLException e) {  
	                // TODO Auto-generated catch block  
	                LOGGER.error("------->", e);;  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                LOGGER.error("------->", e);;  
	            }  
	        }  
	        int n=0;  
	        byte[] b=null;  
	        try {  
	            while((n=is.available())>0)  
	            {  
	            	n=is.read(b);  
	                if(n==-1)break;  
	                filecontent=filecontent+base.encode(b);  
	                  
	            }  
	            is.close();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            LOGGER.error("------->", e);;  
	        }  
	        return filecontent;  
	    }  
	      
	    /** 
	     * 将图片内容用post方式发送到url中 
	     * @param url 
	     * @param postcontent 
	     */  
	      
	    public static void sendImgbyPost(String url,String postcontent)  
	    {  
	        try {  
	            HttpURLConnection huc=(HttpURLConnection)new URL(url).openConnection();  
	            huc.setDoInput(true);  
	            huc.setDoOutput(true);  
	            huc.setRequestMethod("POST");  
	              
	            PrintWriter pw=new PrintWriter(new OutputStreamWriter(huc.getOutputStream()));  
	            pw.print(postcontent);  
	            pw.close();  
	              
	            BufferedReader br=new BufferedReader(new InputStreamReader(huc.getInputStream()));  
	            String content="";  
	            String line=br.readLine();  
	            while(line!=null)  
	            {  
	                content=content+line;  
	                line=br.readLine();  
	                  
	            }  
	              
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            LOGGER.error("------->", e);;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            LOGGER.error("------->", e);;  
	        }  
	          
	    }  
	    /** 
	     * 在服务器端获取发送过来的内容 
	     * @param request 
	     * @return 
	     */  
	    public static String receiveContent(HttpServletRequest request)  
	    {  
	         byte[] b = new byte[4096];  
	        String result="";  
	        try  
	        {  
	         ServletInputStream sis=request.getInputStream();  
	        int line=sis.readLine(b, 0, b.length);  
	        while(line!=-1)  
	        {  
	        result=result+new String(b,0,line);  
	        line=sis.readLine(b, 0, b.length);  
	        }  
	        }  
	        catch(Exception e)  
	        {  
	        LOGGER.error("------->", e);;  
	        }  
	        return  result;  
	    }  
	      
	    /** 
	     * 将接受过来的信息生成文件 
	     * @param request 
	     * @param filename 
	     */  
	    public static void createFile(HttpServletRequest request,String filename)  
	    {  
	        File file=new File(filename);  
	        try {  
	            FileOutputStream fos=new FileOutputStream(file);  
	            String content=receiveContent(request);  
	            BASE64Decoder base=new BASE64Decoder();  
	            byte[] b = base.decodeBuffer(content);
	            fos.write(b);  
	            fos.close();  
	              
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            LOGGER.error("------->", e);;  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            LOGGER.error("------->", e);;  
	        }  
	          
	    }  
	    
	 // 写文件
	    public void writerTxt() {
			BufferedWriter fw = null;
			try {
				File file = new File("D://text.txt");
				fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
				fw.append("我写入的内容");
				fw.newLine();
				fw.append("我又写入的内容");
				fw.flush(); // 全部写入缓存中的内容
			} catch (Exception e) {
				LOGGER.error("------->", e);;
			} finally {
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						LOGGER.error("------->", e);;
					}
				}
			}
		}

		// 读文件
		public static void readTxt() {
			String filePath = "/Users/zhangyang/Downloads/start.txt"; // 文件和该类在同个目录下
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
				String str = null;
				while ((str = reader.readLine()) != null) {
					long lo = Long.valueOf(str.trim());
					String time = TimeUtils.longToString(lo);
					LOGGER.info(str+"------->"+time);
				}
			} catch (FileNotFoundException e) {
				LOGGER.error("------->", e);;
			} catch (IOException e) {
				LOGGER.error("------->", e);;
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					LOGGER.error("------->", e);;
				}
			}
		}
	    
		 public static List<String> showAllFiles(File dir) throws Exception{
			 List<String> filelist = new ArrayList<String>();
			  File[] fs = dir.listFiles();
			  for(int i=0; i<fs.length; i++){
			    //LOGGER.info(fs[i].getAbsolutePath());
			    if(fs[i].isDirectory()){
			    	try{
			    		showAllFiles(fs[i]);
			    	}catch(Exception e){

			    	}
			    }
			    filelist.add(fs[i].getAbsolutePath());
			  }
			return filelist;
		 }
		 public static final String pic1 = "A7D82362B79C043782DC04FC8120036A";
		 /**
		  * 替换某文件夹下面所有默认图片
		 * @throws Exception
		 */
		public static void replaceFiles() throws Exception{
			
			File root = new File("/mnt/apk/album");
			List<String> flist = showAllFiles(root);
			for (int i = 0; i < flist.size(); i++) {
				if(MD5Utils.encoding(new FileInputStream(flist.get(i))).equals(pic1)){//替换图片
					LOGGER.info(i+"---"+flist.get(i));
					String new_pic = getRandomPic(flist);
					
					writeFile(flist, i, new_pic);  
				}
			}
		 }

		/**
		 * @param flist
		 * @param i
		 * @param new_pic
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		public static void writeFile(List<String> flist, int i, String new_pic)
				throws FileNotFoundException, IOException {
			//写入文件
			FileInputStream in = new FileInputStream(new_pic);
			FileOutputStream bos = new FileOutputStream(flist.get(i)); 
			byte[] buffer = new byte[1024];  
			int len = 0;  
			while (-1 != (len = in.read(buffer, 0, 1024))) {  
				bos.write(buffer, 0, len);  
			}
			try {
				in.close();
				bos.close();
			} catch (Exception e) {
				// TODO: handle exception
				LOGGER.error("------->", e);;
			}
		}

		 
		/**
		 * @desc 随机取出一个数【size 为  10 ，取得类似0-9的区间数】
		 * @return
		 */
		public static Integer getRandomOne(List<?> list){
			
			
			Random ramdom =  new Random();
			int number = -1;
			int max = list.size();
			
			//size 为  10 ，取得类似0-9的区间数
			number = Math.abs(ramdom.nextInt() % max  );
			
			return number;
	    
		}
		
		/**
		 * 取得一张不是默认图的图片地址
		 * @param list
		 * @return
		 */
		public static String getRandomPic(List<String> list){
			String path ="";
			try{
			   path = list.get(getRandomOne(list));
			   if(MD5Utils.encoding(new FileInputStream(path)).equals(pic1)){
				   getRandomPic(list);
			   }
			}catch(Exception e){
				LOGGER.error("------->", e);;
			}
			return path;
		}
	    
	    
		/**
		 * 读取文件返回string
		 * @param Path
		 * @return
		 */
		public static List<String> ReadFile(String Path){
			 File file = new File(Path);
			 	List<String> list = new ArrayList<String>();
		        Scanner scanner = null;
		        try {
		            scanner = new Scanner(file, "utf-8");
		            while (scanner.hasNextLine()) {
		            	StringBuilder buffer = new StringBuilder();
		                buffer.append(scanner.nextLine());
		                list.add(buffer.toString());
		            }
		 
		        } catch (FileNotFoundException e) {
		            // TODO Auto-generated catch block  
		 
		        } finally {
		            if (scanner != null) {
		                scanner.close();
		            }
		        }
		         
				return list;
		}




	}  

