package com.example.apiconsume.pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserList{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("data")
	private List<User> data;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setData(List<User> data){
		this.data = data;
	}

	public List<User> getData(){
		return data;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	@Override
	public String toString() {
		return "UserList{" +
				"perPage=" + perPage +
				", total=" + total +
				", data=" + data +
				", page=" + page +
				", totalPages=" + totalPages +
				'}';
	}
}