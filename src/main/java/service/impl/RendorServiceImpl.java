package service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import main.App;
import service.RendorService;
import util.FileUtil;
import util.FreemakerUtil;
import vo.ColumnVo;
import vo.IndexVo;
import vo.TableVo;

@Service
public class RendorServiceImpl implements RendorService{
	
	@Override
	public void rendorController(String projectRootPath,TableVo tableVo,List<IndexVo> indexVos,List<ColumnVo> columnVos,List<ColumnVo> indexColumnVos){
		String javaFilePath = projectRootPath+"/controller/"+tableVo.getClassName()+"Controller.java";

		String ftlContent = null;
		try {
			ftlContent = FileUtil.readFileInJar("/template/controller/Controller.java.ftl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		Map data = new HashMap<>();
		data.put("tableVo", tableVo);
		data.put("indexVos", indexVos);
		data.put("columnVos", columnVos);
		data.put("indexColumnVos", indexColumnVos);
		
		String javaContent = FreemakerUtil.render("Controller.java.ftl", ftlContent, data);
		
		try {
			FileUtils.writeStringToFile(new File(javaFilePath), javaContent, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void rendorDao(String projectRootPath,TableVo tableVo){
		String javaFilePath = projectRootPath+"/mapper/"+tableVo.getMapperName()+".java";
		
		String ftlContent = null;
		try {
			ftlContent = FileUtil.readFileInJar("/template/dao/Mapper.java.ftl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		Map data = new HashMap<>();
		data.put("tableVo", tableVo);
		data.put("packageVo", App.packageVo);
		
		String javaContent = FreemakerUtil.render("Mapper.java.ftl", ftlContent, data);
		
		try {
			FileUtils.writeStringToFile(new File(javaFilePath), javaContent, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void rendorVo(String projectRootPath,TableVo tableVo,List<ColumnVo> columnVos){
		String javaFilePath = projectRootPath+"/entity/"+tableVo.getEntityName()+".java";
		
		String ftlContent = null;
		try {
			ftlContent = FileUtil.readFileInJar("/template/vo/Vo.java.ftl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		Map data = new HashMap<>();
		data.put("tableVo", tableVo);
		data.put("columnVos", columnVos);
		data.put("packageVo", App.packageVo);
		
		String javaContent = FreemakerUtil.render("Vo.java.ftl", ftlContent, data);
		
		try {
			FileUtils.writeStringToFile(new File(javaFilePath), javaContent, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void rendorWhereVo(String projectRootPath,TableVo tableVo,
			List<IndexVo> indexVos,List<ColumnVo> indexColumnVos,List<ColumnVo> columnVos){

		String javaFilePath = projectRootPath+"/entity/"+tableVo.getWhereName()+".java";
		
		String ftlContent = null;
		try {
			ftlContent = FileUtil.readFileInJar("/template/vo/ConditionVo.java.ftl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Map data = new HashMap<>();
		data.put("tableVo", tableVo);
		data.put("indexVos", indexVos);
		data.put("indexColumnVos", indexColumnVos);
		data.put("packageVo", App.packageVo);
		data.put("columnVos", columnVos);

		String javaContent = FreemakerUtil.render("WhereVo.java.ftl", ftlContent, data);

		try {
			FileUtils.writeStringToFile(new File(javaFilePath), javaContent, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void rendorMapper(String projectRootPath,TableVo tableVo,
			List<IndexVo> indexVos,List<ColumnVo> columnVos,List<ColumnVo> indexColumnVos){
		String javaFilePath = projectRootPath+"/mapper/"+tableVo.getMapperName()+".xml";
		
		String ftlContent = null;
		try {
			ftlContent = FileUtil.readFileInJar("/template/mapper/Mapper.xml.ftl");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		Map data = new HashMap<>();
		data.put("tableVo", tableVo);
		data.put("indexVos", indexVos);
		data.put("columnVos", columnVos);
		data.put("indexColumnVos", indexColumnVos);
		data.put("packageVo", App.packageVo);
		
		String javaContent = FreemakerUtil.render("Mapper.xml.ftl", ftlContent, data);
		
		try {
			FileUtils.writeStringToFile(new File(javaFilePath), javaContent, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
