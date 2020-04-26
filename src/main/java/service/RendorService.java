package service;

import java.util.List;

import vo.ColumnVo;
import vo.IndexVo;
import vo.TableVo;

public interface RendorService {

	void rendorDao(String projectRootPath, TableVo tableVo);

	void rendorVo(String projectRootPath, TableVo tableVo, List<ColumnVo> columnVos);
	
	void rendorMapper(String projectRootPath, TableVo tableVo, List<IndexVo> indexVos, List<ColumnVo> columnVos,
			List<ColumnVo> indexColumnVos);

	void rendorController(String projectRootPath, TableVo tableVo, List<IndexVo> indexVos, List<ColumnVo> columnVos,
			List<ColumnVo> indexColumnVos);

	void rendorWhereVo(String projectRootPath, TableVo tableVo, List<IndexVo> indexVos, List<ColumnVo> indexColumnVos,
			List<ColumnVo> columnVos);


}
