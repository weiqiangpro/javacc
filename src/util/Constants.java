package util;

import java.io.Closeable;
import java.io.IOException;


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
}
