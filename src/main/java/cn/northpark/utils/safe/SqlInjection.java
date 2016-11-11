package cn.northpark.utils.safe;

public class SqlInjection{

 public static SqlInjection instance = new SqlInjection();
	
  public  String escapeSql(String str){
	  str = str.isEmpty() ? "" : str.replaceAll("'", "''");
      str = str.replaceAll("(?i)exec",  "&#101;xec");
      str = str.replaceAll("(?i)xp_cmdshell",  "&#120;p_cmdshell");
      str = str.replaceAll("(?i)select",  "&#115;elect");
      str = str.replaceAll("(?i)insert",  "&#105;nsert");
      str = str.replaceAll("(?i)update",  "&#117;pdate");
      str = str.replaceAll("(?i)delete",  "&#100;elete");
      str = str.replaceAll("(?i)drop",  "&#100;rop");
      str = str.replaceAll("(?i)create",  "&#99;reate");
      str = str.replaceAll("(?i)rename",  "&#114;ename");
      str = str.replaceAll("(?i)truncate",  "&#116;runcate");
      str = str.replaceAll("(?i)alter",  "&#97;lter");
      str = str.replaceAll("(?i)exists",  "&#101;xists");
      str = str.replaceAll("(?i)master.",  "&#109;aster.");
      str = str.replaceAll("(?i)restore",  "&#114;estore");
      str = str.replaceAll("(?i)where",  "&#117;here");
      
      System.out.println("sql_inj_handle-------->"+str);
      return str;
  }
  
  public static void main(String[] args) {
	  instance.escapeSql("select * from bc_movies where 1=1");
  }
  
  
}