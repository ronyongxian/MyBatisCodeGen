package a.vo;

import java.util.Collection;

public class TIndexLastModifyTimeWhere{
	private Collection<Integer> ids;
	private Integer idGt,idLt;
	
	private Integer pageNo,pageSize;
	public Integer getOffset(){
		return (pageNo-1)*pageSize;
	}
	
	public void setId(Collection<Integer> ids){
		this.ids = ids;
	}
	public Collection<Integer> getIds(){
		return this.ids;
	}
	public void setIdGt(Integer idGt){
		this.idGt = idGt;
	}
	public Integer getIdGt(){
		return this.idGt;
	}
	public void setIdLt(Integer idLt){
		this.idLt = idLt;
	}
	public Integer getIdLt(){
		return this.idLt;
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
