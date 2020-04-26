package main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import service.DBService;
import service.RendorService;
import util.SpringUtil;
import vo.ColumnVo;
import vo.IndexVo;
import vo.PackageVo;
import vo.TableVo;

public class App {
	
	private static DBService dbService;
	private static RendorService rendorService;
	
	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	
	public static PackageVo packageVo;
	private static String schema;
	public static String[] tables = null;
	private static String projectRoot;
	private static AtomicInteger success = new AtomicInteger(0);
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		try {
			init(args[0]);
			
			if(tables==null) {
				genAll();
			}else {
				genSpecific(tables);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(success.get()==tables.length?
				"generate success":"generate fail");
		System.exit(0);
	}
	
	public static void init(String fileName) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		propertiesPersister.load(props, new FileInputStream(new File(fileName)) );
		
		packageVo = new PackageVo();
		packageVo.setDaoPackage(props.getProperty("package.mapper"));
		packageVo.setVoPackage(props.getProperty("package.entity"));
		
		schema = props.getProperty("db.schema");
		if(!StringUtils.isBlank(props.getProperty("db.tables"))) {
			tables = props.getProperty("db.tables").split(",");
		}
		projectRoot = props.getProperty("project.root");
		
		System.setProperty("db.url", props.getProperty("db.url"));
		System.setProperty("db.username", props.getProperty("db.username"));
		System.setProperty("db.password", props.getProperty("db.password"));

		dbService = SpringUtil.getBean(DBService.class);
		rendorService = SpringUtil.getBean(RendorService.class);
		
		
		
	
	}
	
	public static String formatSchemaOrTable(String str) {
		if(!str.contains("`")) {
			return "`"+str+"`";
		}
		return str;
	}

	public static void genAll(){
		List<TableVo> tableVos = dbService.selectTable(schema);
		tables = new String[tableVos.size()];
		for(int i=0,l=tableVos.size();i<l;i++){
			tables[i] = tableVos.get(i).getTableName();
		}
		
		genSpecific(tables);
	}
	
	public static void genSpecific(String[] tables){
		for(String table:tables){
			if(StringUtils.isBlank(table)) {
				continue;
			}
			List<IndexVo> indexVos = dbService.selectIndex(schema, table);
			List<ColumnVo> columnVos = dbService.selectColumn(schema, table);
			List<ColumnVo> indexColumnVos = dbService.selectIndexColumn(schema, table);
			
			TableVo tableVo = new TableVo(table,columnVos);
			
			//rendorService.rendorController(projectRoot, tableVo, indexVos,columnVos,indexColumnVos);
			rendorService.rendorDao(projectRoot, tableVo);
			rendorService.rendorVo(projectRoot, tableVo, columnVos);
			rendorService.rendorWhereVo(projectRoot, tableVo, indexVos,indexColumnVos,columnVos);
			rendorService.rendorMapper(projectRoot, tableVo,indexVos,columnVos,indexColumnVos);
			success.incrementAndGet();
		}
	}
	
	
}
