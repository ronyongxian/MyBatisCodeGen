<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageVo.daoPackage}.${tableVo.mapperName}">

	<sql id="table">${tableVo.tableName}</sql>

	<insert id="insert">
		insert into <include refid="table" /> (
		<trim suffixOverrides=",">
		<#list columnVos as column>
		${column.field},
		</#list>
		</trim>
		) values
		<foreach collection="collection" index="index" item="item" open=""
			separator="," close="">
			(
			<trim suffixOverrides=",">
			<#list columnVos as column>
			<#if column.defaultValue??>
			<if test="item.${column.instanceName}==null">${column.defaultValue},</if>
			<if test="item.${column.instanceName}!=null">${r'#{'}item.${column.instanceName}},</if>
			<#else>
			${r'#{'}item.${column.instanceName}},
			</#if>
			</#list>
			</trim>
			)
		</foreach>
	</insert>
	
	<insert id="insertOrUpdate">
		insert into <include refid="table" /> (
		<trim suffixOverrides=",">
		<#list columnVos as column>
		${column.field},
		</#list>
		</trim>
		) values
		<foreach collection="collection" index="index" item="item" open=""
			separator="," close="">
			(
			<trim suffixOverrides=",">
			<#list columnVos as column>
			<#if column.defaultValue??>
			<if test="item.${column.instanceName}==null">${column.defaultValue},</if>
			<if test="item.${column.instanceName}!=null">${r'#{'}item.${column.instanceName}},</if>
			<#else>
			${r'#{'}item.${column.instanceName}},
			</#if>
			</#list>
			</trim>
			)
		</foreach>
		ON DUPLICATE KEY UPDATE
		<trim suffixOverrides=",">
		<#list columnVos as column>
		<#if (column.primary==false)>
		${column.field}=values(${column.field}),
		</#if>
		</#list>
		</trim>
	</insert>
	
	<delete id="delete">
		delete from <include refid="table" />
		<include refid="where"/>
	</delete>
	
	<sql id="where">
		<where>
			<#list indexColumnVos as column>
				<if test="condition.${column.instanceName}s != null and condition.${column.instanceName}s.size>0">
				and ${column.field} in
				<foreach collection="condition.${column.instanceName}s" index="index" item="item" open="("
					separator="," close=")">
					${r'#{'}item}
				</foreach>
				</if>
				<if test="condition.${column.instanceName} != null">
				and ${column.field} = ${r'#{'}condition.${column.instanceName}}
				</if>
				<#if (column.str==false)>
				<if test="condition.${column.instanceName}Gt != null">
				and ${column.field} &gt; ${r'#{'}condition.${column.instanceName}Gt}
				</if>
				<if test="condition.${column.instanceName}Lt != null">
				and ${column.field} &lt; ${r'#{'}condition.${column.instanceName}Lt}
				</if>
				<if test="condition.${column.instanceName}Gte != null">
				and ${column.field} &gt;= ${r'#{'}condition.${column.instanceName}Gte}
				</if>
				<if test="condition.${column.instanceName}Lte != null">
				and ${column.field} &lt;= ${r'#{'}condition.${column.instanceName}Lte}
				</if>
				</#if>
			</#list>
			<#list columnVos as column>
			<#if (column.tinyint==true)>
				<if test="condition.${column.instanceName}s != null and condition.${column.instanceName}s.size>0">
				and ${column.field} in
				<foreach collection="condition.${column.instanceName}s" index="index" item="item" open="("
					separator="," close=")">
					${r'#{'}item}
				</foreach>
				</if>
				<if test="condition.${column.instanceName} != null">
				and ${column.field} = ${r'#{'}condition.${column.instanceName}}
				</if>
			</#if>
			</#list>
		</where>
		<if test="condition.pageNo!=null and condition.pageSize!=null">
		limit ${r'#{'}condition.offset},${r'#{'}condition.pageSize}
		</if>
	</sql>
	
	<update id="update">
		update <include refid="table" />
	    <set>
	    	<#list columnVos as column>
	        <if test="update.${column.instanceName}!=null">
			${column.field} = ${r'#{'}update.${column.instanceName}},
			</if>
			</#list>
		</set>
	    <include refid="where"/>
	</update>
	
	<select id="select" resultType="${packageVo.voPackage}.${tableVo.entityName}">
	    select * from <include refid="table" />
	    <include refid="where"/>
	</select>
	
	<select id="count" resultType="java.lang.Long">
		select count(*) from <include refid="table" />
		<include refid="where"/>
	</select>

</mapper>
  

  
	