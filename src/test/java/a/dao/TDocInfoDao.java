package a.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import a.vo.TDocInfo;
import a.vo.TDocInfoWhere;


@Repository
public interface TDocInfoDao {

	public int batchInsert(Collection<TDocInfo> collection);
	
	public int delete(@Param("where") TDocInfoWhere where);
	
	public int update(@Param("update") TDocInfo update,@Param("where") TDocInfoWhere where);
	
	public List<TDocInfo> select(@Param("where") TDocInfoWhere where);
	
	public long count(@Param("where") TDocInfoWhere where);
	
	
}

