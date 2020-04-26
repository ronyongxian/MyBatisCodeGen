package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExtractUtil {

	public static <T,K> List<T> getFieldList(List<K> list,String fieldName,Class<T> klass){
		try {
			Field field = list.get(0).getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			List<T> ts = new ArrayList<>(list.size());
			for(K k:list){
				ts.add((T)field.get(k));
			}
			return ts;
		} catch ( NoSuchFieldException| SecurityException|IllegalAccessException e) {
			throw new RuntimeException(e);
		}

	}

	public static String getByPattern(String str,String pattern){

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return m.group(1);            
		} 

		return null;
	}

}
