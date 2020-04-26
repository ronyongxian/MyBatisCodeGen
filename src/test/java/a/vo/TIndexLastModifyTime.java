package a.vo;

import java.util.Date;

public class TIndexLastModifyTime {
	private Integer id;
	private Date lastModify;
	private Date createTime;
	private Date updateTime;
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setLastModify(Date lastModify){
		this.lastModify = lastModify;
	}
	public Date getLastModify(){
		return this.lastModify;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Date getUpdateTime(){
		return this.updateTime;
	}
}
