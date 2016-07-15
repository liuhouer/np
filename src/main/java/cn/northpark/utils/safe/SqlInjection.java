package cn.northpark.utils.safe;

public class SqlInjection{
	
  public String escapeSql(String content){
	  String flt ="'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|; |or|-|+|,"; 
	  String filter[] = flt.split("|"); 
	  for(int i=0; i <filter.length;i++){
		  content.replace(filter[i], ""); 
	  }
	  return content; 
  }
}