package com.tavisca.workshops.todolist.com.tavisca.workshops.todolist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class TodoItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String details;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "ToDoList [id=" + id + ", name=" + name + ", details=" + details + "]";
	}
	public TodoItem(Integer id, String name, String details) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
	}
	
	public TodoItem() {
		
	}
	

}
