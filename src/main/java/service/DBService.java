package service;

import java.util.List;

import vo.ColumnVo;
import vo.IndexVo;
import vo.TableVo;

public interface DBService {

	List<TableVo> selectTable(String db);

	List<ColumnVo> selectColumn(String db, String table);

	List<IndexVo> selectIndex(String db, String table);

	List<ColumnVo> selectIndexColumn(String db, String table);

}
