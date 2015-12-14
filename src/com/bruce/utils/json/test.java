package com.bruce.utils.json;

import java.io.File;



public class test {
        
        
	 final static void showAllFiles(File dir) throws Exception{
		  File[] fs = dir.listFiles();
		  for(int i=0; i<fs.length; i++){
		   System.out.println(fs[i].getAbsolutePath());
		   if(fs[i].isDirectory()){
		    try{
		     showAllFiles(fs[i]);
		    }catch(Exception e){}
		   }
		  }
	 }
        public static void main(String[] args) {
                try {
                        //readfile("mnt/apk/album");
                	 File root = new File("/mnt/apk/album");
                	  showAllFiles(root);
                } catch (Exception ex) {
                	ex.printStackTrace();
                }
        }

}



 
