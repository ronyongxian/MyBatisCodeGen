package a.vo;

import java.util.Date;

public class TDocInfo {
	private Integer docid;
	private String terms;
	private String positive;
	private Long version;
	private Date createTime;
	private Date updateTime;
	public void setDocid(Integer docid){
		this.docid = docid;
	}
	public Integer getDocid(){
		return this.docid;
	}
	public void setTerms(String terms){
		this.terms = terms;
	}
	public String getTerms(){
		return this.terms;
	}
	public void setPositive(String positive){
		this.positive = positive;
	}
	public String getPositive(){
		return this.positive;
	}
	public void setVersion(Long version){
		this.version = version;
	}
	public Long getVersion(){
		return this.version;
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
