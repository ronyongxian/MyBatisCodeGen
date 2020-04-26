package vo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import util.ConvertUtil;

@Data
public class TableVo {
	
	public TableVo(){}
	
	public TableVo(String tableName,List<ColumnVo> columnVos){
		this.tableName = tableName;
		for(ColumnVo vo:columnVos){
			if(vo.getExtra().equals("auto_increment")){
				this.autoIncrementColumn = vo;
			}
		}
	}

	private String tableName;
	
	private ColumnVo autoIncrementColumn;
	
	public String getClassName(){
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		return ConvertUtil.underlineToCamel(tableName);
	}
	
	public String getEntityName(){
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		return ConvertUtil.underlineToCamel(tableName)+"Entity";
	}
	
	public String getInstanceName(){
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		return ConvertUtil.lowercaseFirstLetter(ConvertUtil.underlineToCamel(tableName));
	}
	
	public String getWhereName(){
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		return ConvertUtil.underlineToCamel(tableName)+"Condition";
	}
	
	public String getMapperName(){
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		return ConvertUtil.underlineToCamel(tableName)+"Mapper";
	}
}

