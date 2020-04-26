package service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DBDao;
import main.App;
import service.DBService;
import vo.ColumnVo;
import vo.IndexVo;
import vo.TableVo;

@Service
public class DBServiceImpl implements DBService {

	@Autowired
	private DBDao tableDao;
	
	@Override
	public List<TableVo> selectTable(String db){
		List<Map> list = tableDao.selectTable(App.formatSchemaOrTable(db));
		if(CollectionUtils.isEmpty(list)){
			return Collections.emptyList();
		}
		
		List<TableVo> tableVos = new ArrayList<>(list.size());
		for(Map<String,String> m:list){
			TableVo tableVo = new TableVo();
			tableVo.setTableName(m.get("Tables_in_"+db));
			tableVos.add(tableVo);
		}
		
		return tableVos;
	}
	
	@Override
	public List<ColumnVo> selectColumn(String db,String table){
		List<Map> list = tableDao.selectColumn(App.formatSchemaOrTable(db),App.formatSchemaOrTable(table));
		if(CollectionUtils.isEmpty(list)){
			return Collections.emptyList();
		}
		
		List<ColumnVo> columnVos = new ArrayList<>(list.size());
		for(Map<String,String> m:list){
			ColumnVo columnVo = new ColumnVo();
			columnVos.add(columnVo);
			
			columnVo.setField(m.get("Field"));
			columnVo.setType(m.get("Type"));
			columnVo.setKey(m.get("Key"));
			columnVo.setComment(m.get("Comment"));
			columnVo.setDefaultValue(m.get("Default"));
			columnVo.setNullValue(m.get("Null"));
			columnVo.setExtra(m.get("Extra"));	
			if(columnVo.getExtra().contains("on update CURRENT_TIMESTAMP")) {
				columnVo.setDefaultValue("CURRENT_TIMESTAMP");
			}
			if(columnVo.getExtra().equals("auto_increment")) {
				columnVo.setDefaultValue("0");
			}
			if(columnVo.getType().contains("char") || columnVo.getType().contains("text")) {
				if(columnVo.getNullValue().equalsIgnoreCase("NO") ) {
					columnVo.setDefaultValue("'"+columnVo.getDefaultValue()+"'");
				}
			}
		}
		
		return columnVos;
	}
	
	@Override
	public List<IndexVo> selectIndex(String db,String table){
		List<ColumnVo> columnVos = selectColumn(db, table);
		Map<String, ColumnVo> columnVoMap = new HashMap<>(columnVos.size());
		for(ColumnVo vo:columnVos){
			columnVoMap.put(vo.getField(), vo);
		}
		
		List<Map> list = tableDao.selectIndex(App.formatSchemaOrTable(db),App.formatSchemaOrTable(table));
		if(CollectionUtils.isEmpty(list)){
			return Collections.emptyList();
		}
		
		Map<String, IndexVo> indexVoMap = new LinkedHashMap<>(list.size());
		
		for(Map<String,String> m:list){
			String keyName = m.get("Key_name");
			IndexVo indexVo = indexVoMap.get(keyName);
			if(indexVo==null){
				indexVo = new IndexVo();
				indexVo.setKeyName(keyName);
				indexVoMap.put(keyName, indexVo);
			}
			
			List<ColumnVo> columnsInIndex = indexVo.getColumns();
			if(columnsInIndex==null){
				columnsInIndex = new ArrayList<>();
				indexVo.setColumns(columnsInIndex);
			}
			columnsInIndex.add(columnVoMap.get(m.get("Column_name")));
		}
		
		return new ArrayList<>(indexVoMap.values());
	}
	
	@Override
	public List<ColumnVo> selectIndexColumn(String db,String table){
		List<IndexVo> indexVos = selectIndex(db, table);
		Map<String, ColumnVo> columnVoMap = new HashMap<>();
		for(IndexVo indexVo:indexVos){
			for(ColumnVo columnVo:indexVo.getColumns()){
				columnVoMap.put(columnVo.getField(), columnVo);
			}
		}
		return new ArrayList<ColumnVo>(columnVoMap.values());
	}
}
