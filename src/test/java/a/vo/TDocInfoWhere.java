package a.vo;

import java.util.Collection;

public class TDocInfoWhere{
	private Collection<Integer> docids;
	private Integer docidGt,docidLt;
	
	private Integer pageNo,pageSize;
	public Integer getOffset(){
		return (pageNo-1)*pageSize;
	}
	
	public void setDocid(Collection<Integer> docids){
		this.docids = docids;
	}
	public Collection<Integer> getDocids(){
		return this.docids;
	}
	public void setDocidGt(Integer docidGt){
		this.docidGt = docidGt;
	}
	public Integer getDocidGt(){
		return this.docidGt;
	}
	public void setDocidLt(Integer docidLt){
		this.docidLt = docidLt;
	}
	public Integer getDocidLt(){
		return this.docidLt;
	}
	public void setPageNo(Integer pageNo){
		this.pageNo = pageNo;
	}
	public Integer getPageNo(){
		return this.pageNo;
	}
	public void setPageSize(Integer pageSize){
		this.pageSize = pageSize;
	}
	public Integer getPageSize(){
		return this.pageSize;
	}
	
}
