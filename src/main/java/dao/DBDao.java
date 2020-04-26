package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DBDao {

	public List<Map> selectTable(@Param("db") String db);
	
	public List<Map> selectIndex(@Param("db") String db,@Param("table") String table);
	
	public List<Map> selectColumn(@Param("db") String db,@Param("table") String table);
}
