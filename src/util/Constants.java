package util;

import java.io.Closeable;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Constants {
	public static String mes = "";
	   public static void close(Closeable... closeables) {
	        if (closeables == null) {
	            return;
	        }
	        for (Closeable closeable : closeables) {
	            try {
	                closeable.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	   public static boolean isNum(String str) {
			Pattern pattern = Pattern.compile("[0-9]*");
	        Matcher isNum = pattern.matcher(str);
	        if( !isNum.matches() ){
	            return false;
	        }
	        
	        return true;
		}
}
