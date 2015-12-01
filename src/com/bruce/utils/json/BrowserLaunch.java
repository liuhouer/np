package com.bruce.utils.json;

import java.io.IOException;

public class BrowserLaunch {
	public static void openURL(String url){
		browse(url);
	}
	public static void browse(String url){
		String osName = System.getProperty("os.name","");
		if(osName.startsWith("Windows")){
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
