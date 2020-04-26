package vo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import util.ConvertUtil;

@Data
public class IndexVo {

	
	
	private String keyName;
	
	private List<ColumnVo> columns;
	
	public String getClassName(){
		if(StringUtils.isBlank(keyName)){
			return null;
		}
		return ConvertUtil.underlineToCamel(keyName);
	}
	
	public String getInstanceName(){
		if(StringUtils.isBlank(keyName)){
			return null;
		}
		return ConvertUtil.lowercaseFirstLetter(ConvertUtil.underlineToCamel(keyName));
	}
}
