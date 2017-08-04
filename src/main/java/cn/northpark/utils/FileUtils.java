package cn.northpark.utils;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
				System.out.println(file[0].getOriginalFilename()
						+ "------------------------------------------------》》");
				
				Properties prop = System.getProperties();

				String os = prop.getProperty("os.name");
				String path = "/mnt/apk/";
		         if(os.startsWith("win") || os.startsWith("Win") ){// windows操作系统
		        	 path = "e:/bruce/";
		         }else{
		        	 path = "/mnt/apk/";
		         }
				if (StringUtils.isNotEmpty(file[0].getOriginalFilename())) {// 新上传了图片才把以前的删除
					if (StringUtils.isNotEmpty(oldpath)) {
						File f = new File(path+oldpath);
						System.out.println("要删除文件的绝对路径是：" + f.getAbsolutePath());
						if (f.exists()) {
							f.delete();
						} else {
							System.out.println("文件已经丢失!");
						}
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
		    System.out.println("-------------------------------------->开始");  
		    
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
			            e.printStackTrace();  
			            continue;
			        }  
			        tail_path = "/"+suffix+"/"+newName;
			       
			        list.add(tail_path);
	        	}
		        
			}
	         
	         System.out.println("-------------------------------------->结束");
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
	                e.printStackTrace();  
	            } catch (IOException e) {  
	                // TODO Auto-generated catch block  
	                e.printStackTrace();  
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
	            e.printStackTrace();  
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
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	          
	    }  
	    /** 
	     * 在服务器端获取发送过来的内容 
	     * @param request 
	     * @return 
	     */  
	    public static String receiveContent(HttpServletRequest request)  
	    {  
	        int a = 0;  
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
	        e.printStackTrace();  
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
	            byte[] b=base.decodeBuffer(content);  
	            fos.write(b);  
	            fos.close();  
	              
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	          
	    }  
	    
	 // 写文件
		private void writerTxt() {
			BufferedWriter fw = null;
			try {
				File file = new File("D://text.txt");
				fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
				fw.append("我写入的内容");
				fw.newLine();
				fw.append("我又写入的内容");
				fw.flush(); // 全部写入缓存中的内容
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// 读文件
		private static void readTxt() {
			String filePath = "/Users/zhangyang/Downloads/start.txt"; // 文件和该类在同个目录下
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
				String str = null;
				while ((str = reader.readLine()) != null) {
					long lo = Long.valueOf(str.trim());
					String time = TimeUtils.longToString(lo);
					System.out.println(str+"------->"+time);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	    
		 public static List<String> showAllFiles(File dir) throws Exception{
			 List<String> filelist = new ArrayList<String>();
			  File[] fs = dir.listFiles();
			  for(int i=0; i<fs.length; i++){
			    //System.out.println(fs[i].getAbsolutePath());
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
					System.out.println(i+"---"+flist.get(i));
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
			int count = 0 ;
			byte[] buffer = new byte[1024];  
			int len = 0;  
			while (-1 != (len = in.read(buffer, 0, 1024))) {  
				bos.write(buffer, 0, len);  
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
				e.printStackTrace();
			}
			return path;
		}
	    
		
	
		
		
//			   	 File root = new File("/mnt/apk/album");
//		   	     showAllFiles(root);
		
		//replaceFiles();
		
		
		
		
		//写入文件
//				FileInputStream in = new FileInputStream("/Users/zhangyang/Documents/lvzheng_pc.access.log.2016-04-25");
//				FileOutputStream bos = new FileOutputStream("/mnt/apk/album/1449928675112.jpg"); 
//				int count = 0 ;
//				byte[] buffer = new byte[1024];  
//				int len = 0;  
//				while (-1 != (len = in.read(buffer, 0, 1024))) {  
//					bos.write(buffer, 0, len);  
//				}
//	    		
//	    		System.out.println(MD5Utils.encoding( new FileInputStream("/mnt/apk/album/1449928592939.jpg")));
//	    		System.out.println(MD5Utils.encoding( new FileInputStream("/mnt/apk/album/1449928750567.jpg")));
		
		
		
		
//	    public static void main(String[] args) {
//	    	try{
//	    		
//				List<trackVO> tlist = new ArrayList<trackVO>();
//				List<String> list = ReadFile("/Users/zhangyang/Documents/lvzheng_pc.access.log.2016-04-25");
//				//System.out.println(JsonContext);
//				for (int i = 0; i < list.size(); i++) {
//					
//					//System.out.println(list.get(i));
//					trackVO model =  JsonUtil.jsonUtil.jsonToModel(list.get(i), trackVO.class);
//					tlist.add(model);
//					
//					
//				}
//				
//				
//				 //实现统计逻辑
//				 
//				 Map<String, Integer> m = getCount(tlist,"pageUrl");
//				 
//				 
//				 //打印统计结果|发送邮件
//				 String rs = printMap(m);
//				 
//				 
//				 EmailUtils.emailUtil.analyseLog("duqingxiang92@163.com", rs);
//				 
//	    		
//	    		
//	    	}catch(Exception e){
//	    		e.printStackTrace();
//	    	}
//		}
	    
	    
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


		/**
		 * 实现统计逻辑
		 * @param tlist
		 */
		public static Map<String,Integer> getCount(List<trackVO> tlist,String column) {
			Map<String,Integer> m = new HashMap<String,Integer>();
			    //用word初使化m，m中包含了所有不重复的单词
			    for(int i=0;i<tlist.size();i++){
			    	String word  = "";
			    	word = caseColumn(tlist, column, i, word);
			    	m.put(word,0);
			    }
			    Set<String> set = m.keySet(); 
			    //用word中的每个单词与m中的单词比较，发现相同的就统计一次    
			    for(int i=0;i<tlist.size();i++){
			    	    String word = "";
			    	    word = caseColumn(tlist, column, i, word);
				        Iterator<String> it = set.iterator();
				        while(it.hasNext()){
			              String k = it.next();
			              if(word.equals(k)){
			                    int c = m.get(k);                  
			                    c++;
			                    m.put(word,c);
			                }
			            }                          
			    }
			    m = sortMap(m);
			   
			    //System.out.println(m);
			    return m;
		}


		/**
		 * 根据column判断字段的取值
		 * @param tlist
		 * @param column
		 * @param i
		 * @param word
		 * @return
		 */
		public static String caseColumn(List<trackVO> tlist, String column, int i, String word) {
			if("cookieId".equals(column)){
				word = tlist.get(i).getCookieId();
			}else if("currentTime".equals(column)){
				word = tlist.get(i).getCurrentTime();
			}else if("from".equals(column)){
				word = tlist.get(i).getFrom();
			}else if("ip".equals(column)){
				word = tlist.get(i).getIp();
			}else if("moudle".equals(column)){
				word = tlist.get(i).getMoudle();
			}else if("pageUrl".equals(column)){
				word = tlist.get(i).getPageUrl();
			}else if("spider".equals(column)){
				word = tlist.get(i).getSpider();
			}else if("userId".equals(column)){
				word = tlist.get(i).getUserId();
			}
			return word;
		}
		
		
		
		//Map根据value排序
		public static Map<String, Integer> sortMap(Map<String, Integer> map) {
		        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		        Comparator<Map.Entry<String, Integer>> c = new Comparator<Map.Entry<String, Integer>>() {
		            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		                return -(o1.getValue() - o2.getValue()); //倒序排列
		            }
		        };
		        Collections.sort(list, c);
		        Map<String, Integer> result = new LinkedHashMap<String, Integer>();

		        for (Iterator<Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
		            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
		            result.put(entry.getKey(), entry.getValue());
		        }
		        return result;
		}
		
		//遍历Map并且返回排版好的String字符串
		public static String printMap(Map<String, Integer> map) {
			StringBuilder sb = new StringBuilder();
			sb.append("<html><body><p style=\"margin-left: 30px;\"><font size=\"5\" color=\"rgb(216,206,178)\" >日志分析</font></p>");
			
			sb.append("<p style=\"background-color:rgb(163,210,202);margin-left: 30px;\">");
			sb.append("统计"+"||||"+"次<br>");
			sb.append("<br>");
			Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Integer> entry = iterator.next();
				sb.append(entry.getKey()+"||||"+entry.getValue().toString());
				sb.append("<br>");
				//System.out.println(entry.getKey() + "---->" + entry.getValue().toString());
			}
			sb.append("----analysed by bruce----<br/><br/>" + "</p>" + "</body></html>");
			
			return sb.toString();
		}
	  
		
		
		/** 
	     * 添加水印图  
	     * @param originalImagePath     需打水印的原图片路径 
	     * @param watermarkImagePath    水印后的图片路径 
	     * @param alpha                 透明度 
	     * @param x                     距x轴的距离 
	     * @param y                     距y轴的距离 
	     * @return 
	     */  
	    public static byte[] imageWatermarkProcess(String originalImagePath,String watermarkImagePath, float alpha, int x, int y) {  
	        try {  
	            // 原图  
	            Image original = Toolkit.getDefaultToolkit().getImage(originalImagePath);  
	            original = new ImageIcon(original).getImage();  
	            int width = original.getWidth(null);  
	            int height = original.getHeight(null);  
	            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	            Graphics2D graphics2d = bufferedImage.createGraphics();  
	            graphics2d.drawImage(original, 0, 0, width, height, null);  
	            // 水印图  
	            Image watermark = Toolkit.getDefaultToolkit().getImage(watermarkImagePath);  
	            watermark = new ImageIcon(watermark).getImage();  
	            int watermarkWidth = watermark.getWidth(null);  
	            int watermarkHeight = watermark.getHeight(null);  
	            graphics2d.setComposite(AlphaComposite.getInstance(10, alpha));  
	            graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);  
	            int widthDiff = width - watermarkWidth;  
	            int heightDiff = height - watermarkHeight;  
	            // 若水印图尺寸大于原图，等比例缩小1/4  
	            if (widthDiff <= 0 || heightDiff <= 0) {  
	                watermarkWidth /= 4;  
	                watermarkHeight /= 4;  
	                widthDiff = width - watermarkWidth;  
	                heightDiff = height - watermarkHeight;  
	            }  
	            // 保证水印图全部出现在原图中  
	            if (x < 0)  
	                x = widthDiff / 2;  
	            else if (x > widthDiff) {  
	                x = widthDiff;  
	            }  
	            if (y < 0)  
	                y = heightDiff / 2;  
	            else if (y > heightDiff) {  
	                y = heightDiff;  
	            }  
	            graphics2d.drawImage(watermark, x, y, watermarkWidth,watermarkHeight, null);  
	            graphics2d.dispose();  
	            String fileType = originalImagePath.substring(originalImagePath.lastIndexOf(".") + 1);  
	            ByteArrayOutputStream baops = new ByteArrayOutputStream();  
	            ImageIO.write(bufferedImage, fileType, baops);  
	            return baops.toByteArray();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  

	    /** 
	     * 调用图片添加水印的方法：
	     */  
	    public static String mergeWaterMark(String imagePath,String logoPath) {  
	        if (null == imagePath || -1 != imagePath.indexOf("-merge-") || null == logoPath) {  
	            return "ERROR";  
	        }  
	        String watermarkPath = imagePath.substring(0,imagePath.lastIndexOf('/')+1);  
	        // 添加随机4位的数字目的是为了避免切换其他logo合成水印后，页面图片依然显示第一次logo水印合成图片（缓存的原因）  
	        String watermarkImagePath = imagePath.substring(0,imagePath.lastIndexOf("."))+"-merge-"+ new Random().nextInt(9999) +".jpg";  
	        FileOutputStream fos = null;  
	        BufferedOutputStream bos = null;  
	        File imgDir = new File(watermarkPath);  
	        if(!imgDir.exists()){  
	            imgDir.mkdirs();  
	        }  
	        File fileImg = new File(watermarkImagePath);  
	        try {  
	            fos = new FileOutputStream(fileImg);  
	            bos = new BufferedOutputStream(fos);  
	            bos.write(imageWatermarkProcess(imagePath, logoPath, 1.0F,10,10));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return "ERROR";  
	        } finally {  
	            try {  
	                if(null != bos){  
	                    bos.close();  
	                }  
	                if(null != fos){  
	                    fos.close();  
	                }  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return watermarkImagePath;  
	    }  
	    
	    /**
	     * 
	     * 清除图片水印方法：
	     * 
	     */
		public static String clearWaterMark(String imagePath){  
	        String clearName = imagePath.substring(imagePath.lastIndexOf("-merge-"),imagePath.lastIndexOf('.'));  
	        String originalImagePath = imagePath.replace(clearName,"");  
	        // 清除水印后删除水印图片  
	        File fileImg = new File(imagePath);  
	        if (fileImg.isFile()) {  
	            fileImg.delete();  
	        }  
	        // 返回原图地址  
	        return originalImagePath;  
	    }  
		
		
		//==========================================================================
		
		private static List<File> fileList = new ArrayList<File>();
		
		private static void convertAllImages(String dir, String saveDir) {
	        File dirFile = new File(dir);
	        File saveDirFile = new File(saveDir);
	        dir = dirFile.getAbsolutePath();
	        saveDir = saveDirFile.getAbsolutePath();
	        loadImages(new File(dir));
	        for (File file : fileList) {
	            String filePath = file.getAbsolutePath();
	 
	            String dstPath = saveDir + filePath.substring(filePath.indexOf(dir) + dir.length(), filePath.length());
	            System.out.println("converting: " + filePath);
	            replaceColor(file.getAbsolutePath(), dstPath);
	        }
	    }
	 
	    public static void loadImages(File f) {
	        if (f != null) {
	            if (f.isDirectory()) {
	                File[] fileArray = f.listFiles();
	                if (fileArray != null) {
	                    for (int i = 0; i < fileArray.length; i++) {
	                        //递归调用
	                        loadImages(fileArray[i]);
	                    }
	                }
	            } else {
	                String name = f.getName();
	                if (name.endsWith("png") || name.endsWith("jpg")) {
	                    fileList.add(f);
	                }
	            }
	        }
	    }
	 
	    private static void replaceFolderImages(String dir) {
	        File dirFile = new File(dir);
	        File[] files = dirFile.listFiles(new FileFilter() {
	            public boolean accept(File file) {
	                String name = file.getName();
	                if (name.endsWith("png") || name.endsWith("jpg")) {
	                    return true;
	                }
	                return false;
	            }
	        });
	        for (File img : files) {
	            replaceColor(img.getAbsolutePath(), img.getAbsolutePath());
	        }
	    }
	 
	    private static void replaceColor(String srcFile, String dstFile) {
	        try {
	            Color color = new Color(255, 195, 195);
	            replaceImageColor(srcFile, dstFile, color, Color.WHITE);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    public static void replaceImageColor(String file, String dstFile, Color srcColor, Color targetColor) throws IOException {
	        URL http;
	        if (file.trim().startsWith("https")) {
	            http = new URL(file);
	            HttpsURLConnection conn = (HttpsURLConnection) http.openConnection();
	            conn.setRequestMethod("GET");
	        } else if (file.trim().startsWith("http")) {
	            http = new URL(file);
	            HttpURLConnection conn = (HttpURLConnection) http.openConnection();
	            conn.setRequestMethod("GET");
	        } else {
	            http = new File(file).toURI().toURL();
	        }
	        BufferedImage bi = ImageIO.read(http.openStream());
	        if(bi == null){
	            return;
	        }
	 
	        Color wColor = new Color(255, 255, 255);
	        for (int i = 0; i < bi.getWidth(); i++) {
	            for (int j = 0; j < bi.getHeight(); j++) {
	                //System.out.println(bi.getRGB(i, j));
	                int color = bi.getRGB(i, j);
	                Color oriColor = new Color(color);
	                int red = oriColor.getRed();
	                int greed = oriColor.getGreen();
	                int blue = oriColor.getBlue();
	                //粉色
	                if (greed < 190 || blue < 190) {
	 
	                } else {
	                    if (red == 255 && greed > 180 && blue > 180) {
	                        bi.setRGB(i, j, wColor.getRGB());
	                    }
	                }
	            }
	        }
	        String type = file.substring(file.lastIndexOf(".") + 1, file.length());
	        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName(type);
	        ImageWriter writer = it.next();
	        File f = new File(dstFile);
	        f.getParentFile().mkdirs();
	        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
	        writer.setOutput(ios);
	        writer.write(bi);
	        bi.flush();
	        ios.flush();
	        ios.close();
	    }
	  //==========================================================================
		
		
		public static void main(String[] args) {
//				  String path="/Users/zhangyang";
//				  File file=new File(path);
//				  File[] tempList = file.listFiles();
//				  System.out.println("该目录下对象个数："+tempList.length);
//				  for (int i = 0; i < tempList.length; i++) {
//				   if (tempList[i].isFile()) {
//				    System.out.println("文     件："+tempList[i]);
//				   }
//				   if (tempList[i].isDirectory()) {
//				    System.out.println("文件夹："+tempList[i]);
//				   }
//				  }
			
			
			//去掉水印测试
			//D:\BZ\soft\2017-5-20\Justinmind6.jpg
			String imagePath = "D:/BZ/soft/2017-5-20/Justinmind6.jpg";
			
			mergeWaterMark(imagePath, "D:/BZ/sdfz.jpg");
			clearWaterMark(imagePath);
		        
		        
		        
		}
	}  

