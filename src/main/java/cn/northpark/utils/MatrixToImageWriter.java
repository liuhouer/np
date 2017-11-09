//package cn.northpark.utils;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Hashtable;
//
//import javax.imageio.ImageIO;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.common.BitMatrix;
//
//	public final class MatrixToImageWriter { 
//	    
//		   private static final int BLACK = 0xFF000000; 
//		   private static final int WHITE = 0xFFFFFFFF; 
//		    
//		   private MatrixToImageWriter() {} 
//		    
//		      
//		   public static BufferedImage toBufferedImage(BitMatrix matrix) { 
//		     int width = matrix.getWidth(); 
//		     int height = matrix.getHeight(); 
//		     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
//		     for (int x = 0; x < width; x++) { 
//		       for (int y = 0; y < height; y++) { 
//		         image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE); 
//		       } 
//		     } 
//		     return image; 
//		   } 
//		    
//		      
//		   public static void writeToFile(BitMatrix matrix, String format, File file) 
//		       throws IOException { 
//		     BufferedImage image = toBufferedImage(matrix); 
//		     if (!ImageIO.write(image, format, file)) { 
//		       throw new IOException("Could not write an image of format " + format + " to " + file); 
//		     } 
//		   } 
//		    
//		      
//		   public static BufferedImage writeToStream(BitMatrix matrix, String format, OutputStream stream) 
//		       throws IOException { 
//		     BufferedImage image = toBufferedImage(matrix); 
//		     if (!ImageIO.write(image, format, stream)) { 
//		       throw new IOException("Could not write an image of format " + format); 
//		     } 
//		     return image;
//		   } 
//
//		   
//		   public static BufferedImage writeToStreams() throws Exception { 
//			   String text = "http://www.baidu.com"; 
//		        int width = 300; 
//		        int height = 300; 
//		        //二维码的图片格式 
//		        String format = "gif"; 
//		        Hashtable hints = new Hashtable(); 
//		        //内容所使用编码 
//		        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
//		        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, 
//		                BarcodeFormat.QR_CODE, width, height, hints); 
//		        
//		        //生成二维码 
//		        OutputStream ot = System.out;
//		        		
//		        MatrixToImageWriter.writeToStream(bitMatrix, format, new FileOutputStream(new File("")));
//				return null;
//			   } 
//		   
//		   /**
//		     * @param args
//		     * @throws Exception 
//		     */ 
//		    public static void main(String[] args) throws Exception { 
//		        String text = "http://www.baidu.com"; 
//		        int width = 300; 
//		        int height = 300; 
//		        //二维码的图片格式 
//		        String format = "gif"; 
//		        Hashtable hints = new Hashtable(); 
//		        //内容所使用编码 
//		        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
//		        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, 
//		                BarcodeFormat.QR_CODE, width, height, hints); 
//		        //生成二维码 
//		        File outputFile = new File("/Users/zhangyang/Downloads/"+File.separator+"new.gif"); 
//		        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile); 
//		        
//		        //生成二维码 
//		        OutputStream ot = System.out;
//		        		
//		      //  MatrixToImageWriter.writeToStream(bitMatrix, format, ot);
//		   
//		        BufferedImage im =  MatrixToImageWriter.writeToStream(bitMatrix, format, new FileOutputStream(new File("11.gif")));
//		        System.out.println(im);
//		    } 
//}
