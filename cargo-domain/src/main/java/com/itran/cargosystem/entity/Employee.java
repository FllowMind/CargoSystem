package com.itran.cargosystem.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Table(name="EMPLOYEE")
public class Employee {
	@Id
	private String id;
	
	private String name;//值班员
	
	private String substitute;//换班人
	
	private int position;//列号
	
	private String date;//日期

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

	public String getSubstitute() {
		return substitute;
	}

	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	

}
