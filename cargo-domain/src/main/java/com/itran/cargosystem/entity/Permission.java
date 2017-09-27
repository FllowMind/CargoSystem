package com.itran.cargosystem.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "PERMISSION")
public class Permission {
	
	@Id
	protected String id;
	
	protected String name;
	
	protected String percode;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}
	

}
