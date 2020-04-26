package ${packageVo.voPackage};

import java.util.Collection;
import java.util.Date;

public class ${tableVo.whereName}{
	<#list indexColumnVos as column>
	private Collection<${column.javaType}> ${column.instanceName}s;
	<#if (column.str==false)>
	private ${column.javaType} ${column.instanceName}Gt,${column.instanceName}Lt;
	</#if>
	</#list>
	<#list columnVos as column>
	<#if (column.tinyint==true)>
	private Collection<${column.javaType}> ${column.instanceName}s;
	</#if>
	</#list>
	
	private Integer pageNo,pageSize;
	public Integer getOffset(){
		if(pageNo!=null && pageSize!=null) {
			return (pageNo-1)*pageSize;
		}
		return null;
	}
	
	<#list indexColumnVos as column>
	public ${tableVo.whereName} set${column.className}s(Collection<${column.javaType}> ${column.instanceName}s){
		this.${column.instanceName}s = ${column.instanceName}s;
		return this;
	}
	public Collection<${column.javaType}> get${column.className}s(){
		return this.${column.instanceName}s;
	}
	<#if (column.str==false)>
	public ${tableVo.whereName} set${column.className}Gt(${column.javaType} ${column.instanceName}Gt){
		this.${column.instanceName}Gt = ${column.instanceName}Gt;
		return this;
	}
	public ${column.javaType} get${column.className}Gt(){
		return this.${column.instanceName}Gt;
	}
	public ${tableVo.whereName} set${column.className}Lt(${column.javaType} ${column.instanceName}Lt){
		this.${column.instanceName}Lt = ${column.instanceName}Lt;
		return this;
	}
	public ${column.javaType} get${column.className}Lt(){
		return this.${column.instanceName}Lt;
	}
	</#if>
	</#list>
	
	<#list columnVos as column>
	<#if (column.tinyint==true)>
	public ${tableVo.whereName} set${column.className}s(Collection<${column.javaType}> ${column.instanceName}s){
		this.${column.instanceName}s = ${column.instanceName}s;
		return this;
	}
	public Collection<${column.javaType}> get${column.className}s(){
		return this.${column.instanceName}s;
	}
	</#if>
	</#list>
	
	public ${tableVo.whereName} setPageNo(Integer pageNo){
		this.pageNo = pageNo;
		return this;
	}
	public Integer getPageNo(){
		return this.pageNo;
	}
	public ${tableVo.whereName} setPageSize(Integer pageSize){
		this.pageSize = pageSize;
		return this;
	}
	public Integer getPageSize(){
		return this.pageSize;
	}
	
}