package com.qa.data;

/**
 * 
 */
public class Users {
	int id;
	String first_name;
    String createdAt;

	public Users() {
		
	}
	
	public Users(String name) {
		this.first_name = name;
		
	}

    public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	

	
} 
	
