package util;

import org.apache.commons.lang3.StringUtils;

public class ConvertUtil {

	//下划线转驼峰
	public static String underlineToCamel(String column){  
		if(StringUtils.isBlank(column)){
			return column;
		}
		
		int len=column.length();  
		StringBuilder sb=new StringBuilder(len);
		for (int i = 0; i < len; i++) {  
			char c=column.charAt(i);  
			if(i==0){
				sb.append(Character.toUpperCase(c)); 
			}else if (c=='_'){  
				if (++i<len){  
					sb.append(Character.toUpperCase(column.charAt(i)));  
				}  
			}else{  
				sb.append(Character.toLowerCase(c));  
			}  
		}  
		return sb.toString();  
	}  
	
	//首字母小写
	public static String lowercaseFirstLetter(String str){
		if(StringUtils.isBlank(str)){
			return str;
		}
		
		char firstLetter = str.charAt(0);
		if(Character.isUpperCase(firstLetter)){
			StringBuilder sb=new StringBuilder(str.length()); 
			sb.append(Character.toLowerCase(firstLetter));
			sb.append(str.substring(1));
			return sb.toString();
		}
		return str;
	}
}
