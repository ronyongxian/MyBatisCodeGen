package vo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import util.ConvertUtil;
import util.ExtractUtil;

@Data
public class ColumnVo {

	private String field;
	private String type;
	private String key;
	private String comment;
	private String defaultValue;
	private String nullValue;
	private String extra;
	
	public String getAnnotation(){
		if(StringUtils.isBlank(comment)){
			return "";
		}
		String annotation = (String)JSON.parseObject(comment, Map.class).get("annotation");
		if(annotation==null){
			return "";
		}
		return annotation;
	}
	
	
	
	public String getJavaType(){
		if(type.contains("bigint")){
			return "Long";
		}else if(type.contains("int")){
			return "Integer";
		}else if(type.contains("char") || type.contains("text")){
			return "String";
		}else if(type.contains("timestamp") || type.contains("date")){
			return "Date";
		}else if(type.contains("double") || type.contains("decimal") || type.contains("float")){
			return "Double";
		}
		throw new RuntimeException("不支持的mysql数据类型");
	}
	
	
	public boolean getPrimary(){
		return key.equalsIgnoreCase("pri");
	}
	
	public String getInstanceName(){
		return ConvertUtil.lowercaseFirstLetter(ConvertUtil.underlineToCamel(field));
	}
	
	public String getClassName(){
		return ConvertUtil.underlineToCamel(field);
	}
	
	public Integer getStringLength(){
		if(type.contains("char")){
			return Integer.valueOf(ExtractUtil.getByPattern(type, ".+\\(([^\\)]+)"));
		}
		return 0;
	}
	
	public boolean getStr() {
		return type.contains("char") || type.contains("text");
	}
	
	public boolean getTinyint() {
		return type.contains("tinyint");
	}
	
}
