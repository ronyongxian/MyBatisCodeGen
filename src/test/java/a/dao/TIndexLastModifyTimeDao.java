package a.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import a.vo.TIndexLastModifyTime;
import a.vo.TIndexLastModifyTimeWhere;


@Repository
public interface TIndexLastModifyTimeDao {

	public int batchInsert(Collection<TIndexLastModifyTime> collection);
	
	public int delete(@Param("where") TIndexLastModifyTimeWhere where);
	
	public int update(@Param("update") TIndexLastModifyTime update,@Param("where") TIndexLastModifyTimeWhere where);
	
	public List<TIndexLastModifyTime> select(@Param("where") TIndexLastModifyTimeWhere where);
	
	public long count(@Param("where") TIndexLastModifyTimeWhere where);
	
	
}

