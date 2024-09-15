package com.micro.grievance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departmentId", nullable = false)
    private Department department;

    @Column(nullable = false, length = 255)
    private String categoryName;

	public Category() {	
		super();
	}

	public Category(Integer categoryId, Department department, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.department = department;
		this.categoryName = categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", department=" + department + ", categoryName=" + categoryName
				+ "]";
	}

    
}
