package ${packageVo.daoPackage};

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import ${packageVo.voPackage}.${tableVo.entityName};
import ${packageVo.voPackage}.${tableVo.whereName};


public interface ${tableVo.mapperName} {

	public int insert(@Param("collection") Collection<${tableVo.entityName}> collection);
	
	public int insertOrUpdate(@Param("collection") Collection<${tableVo.entityName}> collection);
	
	public int delete(@Param("condition") ${tableVo.whereName} condition);
	
	public int update(@Param("update") ${tableVo.entityName} update,@Param("condition") ${tableVo.whereName} condition);
	
	public List<${tableVo.entityName}> select(@Param("condition") ${tableVo.whereName} condition);
	
	public long count(@Param("condition") ${tableVo.whereName} condition);
	
	
}

