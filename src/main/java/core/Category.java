package core;

import java.util.Arrays;

public class Category {
	private int id;
	private String name;
	private String note;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String detail) {
		this.note = detail;
	}
	
	public Category(int id, String name, String detail) {
		this.id = id;
		this.name = name;
		this.note = detail;
	}
	
	public Category() {
		this.id = 0;
		this.name = "";
		this.note = "";
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", note=" + note + "]";
	}
	
	
}
