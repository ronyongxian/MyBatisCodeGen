package ${packageVo.voPackage};

import java.util.Date;

public class ${tableVo.entityName} {
	<#list columnVos as column>
	//${column.comment}
	private ${column.javaType} ${column.instanceName};
	</#list>	
	<#list columnVos as column>
	public ${tableVo.entityName} set${column.className}(${column.javaType} ${column.instanceName}){
		this.${column.instanceName} = ${column.instanceName};
		return this;
	}
	public ${column.javaType} get${column.className}(){
		return this.${column.instanceName};
	}
	</#list>
}
