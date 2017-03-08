package cn.northpark.test;

import java.text.NumberFormat;
import java.text.ParseException;

public class BBB {

	public static String getNumKb(Double s) throws ParseException{  
		NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
		return numberFormat1.format(s);
	} 
	
	public static void main(String[] args) {
		try {
			System.out.println(getNumKb(123996.22));
			NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
			System.out.println(numberFormat1.format(11122.33)); //结果是11,122.33
			
			                      
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
