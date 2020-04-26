package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import util.CheckUtil;
import util.ExtractUtil;
import vo.common.ResultVo;

import dao.${tableVo.className}Dao;
import vo.${tableVo.className}Vo;
import vo.${tableVo.className}WhereVo;

@Controller
@RequestMapping("/${tableVo.instanceName}")
public class ${tableVo.className}Controller {
	
	@Autowired
	private ${tableVo.className}Dao ${tableVo.instanceName}Dao;

	@RequestMapping("/insert")
	public @ResponseBody ResultVo<Object> insert(@RequestBody List<${tableVo.className}Vo> ${tableVo.instanceName}Vos){
		CheckUtil.checkObject(${tableVo.instanceName}Vos,"${tableVo.instanceName}Vos",true);
		
		StringBuilder builder = new StringBuilder();
		for(int i=0,l=${tableVo.instanceName}Vos.size();i<l;i++){
			String checkMsg = ${tableVo.instanceName}Vos.get(i).check(false,true);
			if(checkMsg.length()>0){
				builder.append(i+1).append(":").append(checkMsg);
			}
		}
		if(builder.length()>0){
			throw new RuntimeException(builder.toString());
		}

		${tableVo.instanceName}Dao.insert(${tableVo.instanceName}Vos);
		<#if (tableVo.autoIncrementColumn??)>
		List<${tableVo.autoIncrementColumn.javaType}> ${tableVo.autoIncrementColumn.instanceName}s = ExtractUtil.getFieldList(${tableVo.instanceName}Vos, "${tableVo.autoIncrementColumn.instanceName}", ${tableVo.autoIncrementColumn.javaType}.class);
		return ResultVo.getSuccessResult(${tableVo.autoIncrementColumn.instanceName}s);
		<#else>
		return ResultVo.getSuccessResult();
		</#if>
	}
	
	@RequestMapping("/delete")
	public @ResponseBody ResultVo<Object> delete(@RequestBody ${tableVo.className}WhereVo where){
		CheckUtil.checkField(where,"where");
		int deleteNum = ${tableVo.instanceName}Dao.delete(where);
		return ResultVo.getSuccessResult(deleteNum);		
	}
	
	@RequestMapping("/update")
	public @ResponseBody ResultVo<Object> update(@RequestBody Map<String,Object> map){
		CheckUtil.checkObject(map,"map",true);
		${tableVo.className}Vo update = ((JSONObject) map.get("update")).toJavaObject(${tableVo.className}Vo.class);
		${tableVo.className}WhereVo where = ((JSONObject) map.get("where")).toJavaObject(${tableVo.className}WhereVo.class);
		CheckUtil.checkField(update,"update");
		update.check(true,false);
		CheckUtil.checkField(where,"where");
		int updateNum = ${tableVo.instanceName}Dao.update(update, where);
		return ResultVo.getSuccessResult(updateNum);
	}
	
	@RequestMapping("/list")
	public @ResponseBody ResultVo<Object> list(@RequestBody ${tableVo.className}WhereVo where){
		CheckUtil.checkObject(where,"where",true);
		CheckUtil.checkObject(where.getPageNo(),"where.pageNo",true);
		CheckUtil.checkObject(where.getPageSize(),"where.pageSize",true);
		CheckUtil.checkNumberRange(where.getPageNo(), "pageNo", 1, 50);
		CheckUtil.checkNumberRange(where.getPageSize(), "pageSize", 1, 500);
		Map<String, Object> map = new HashMap<>(2);
		map.put("list", ${tableVo.instanceName}Dao.select(where));
		map.put("total", ${tableVo.instanceName}Dao.count(where));
		return ResultVo.getSuccessResult(map);
	}
	
}
