package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {

	public static String readFileInJar(String fileClassPath) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(
				FileUtil.class.getResourceAsStream(fileClassPath),"UTF-8"));  
        StringBuilder builder = new StringBuilder();
		String s="";  
        while((s=br.readLine())!=null) {
        	builder.append(s).append("\n");
        }  
        return builder.toString();   
    
	}
}
