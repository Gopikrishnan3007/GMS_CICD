//package com.micro.grievance.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Entity
//@Table(name = "grievances")
//public class Grievance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer grievanceId;
//
//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "departmentId", nullable = false)
//    private Department department;
//
//    @ManyToOne
//    @JoinColumn(name = "categoryId", nullable = false)
//    private Category category;
//
//    @Column(nullable = false, length = 255)
//    private String branchName;
//
//    @Column(nullable = false, columnDefinition = "TEXT")
//    private String description;
//
//    @Lob
//    @Column(columnDefinition = "LONGBLOB")
//    private byte[] attachment;
//
//    @Column(nullable = false, length = 20)
//    private String status;
//
//    @ManyToOne
//    @JoinColumn(name = "assignedEmployeeId")
//    private Employee assignedEmployee;
//
//    @Column(nullable = false)
//    private Boolean closeConfirmation = false;
//
//    @Column(nullable = false)
//    private LocalDateTime createdAt;
//
//    @Column(nullable = false)
//    private LocalDateTime updatedAt;
//
//	public Grievance() {
//		super();
//	}
//
//	public Grievance(Integer grievanceId, User user, Department department, Category category, String branchName,
//			String description, byte[] attachment, String status,
//			Employee assignedEmployee, Boolean closeConfirmation, LocalDateTime createdAt, LocalDateTime updatedAt) {
//		super();
//		this.grievanceId = grievanceId;
//		this.user = user;
//		this.department = department;
//		this.category = category;
//		this.branchName = branchName;
//		this.description = description;
//		this.attachment = attachment;
//		this.status = status;
//		this.assignedEmployee = assignedEmployee;
//		this.closeConfirmation = closeConfirmation;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
//
//	public Integer getGrievanceId() {
//		return grievanceId;
//	}
//
//	public void setGrievanceId(Integer grievanceId) {
//		this.grievanceId = grievanceId;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Department getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(Department department) {
//		this.department = department;
//	}
//
//	public Category getCategory() {
//		return category;
//	}
//
//	public void setCategory(Category category) {
//		this.category = category;
//	}
//
//	public String getBranchName() {
//		return branchName;
//	}
//
//	public void setBranchName(String branchName) {
//		this.branchName = branchName;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public byte[] getAttachment() {
//		return attachment;
//	}
//
//	public void setAttachment(byte[] attachment) {
//		this.attachment = attachment;
//	}
//
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Employee getAssignedEmployee() {
//		return assignedEmployee;
//	}
//
//	public void setAssignedEmployee(Employee assignedEmployee) {
//		this.assignedEmployee = assignedEmployee;
//	}
//
//	public Boolean getCloseConfirmation() {
//		return closeConfirmation;
//	}
//
//	public void setCloseConfirmation(Boolean closeConfirmation) {
//		this.closeConfirmation = closeConfirmation;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	@Override
//	public String toString() {
//		return "Grievance [grievanceId=" + grievanceId + ", user=" + user + ", department=" + department + ", category="
//				+ category + ", branchName=" + branchName + ", description=" + description + ", attachment="
//				+ Arrays.toString(attachment) + ", status=" + status
//				+ ", assignedEmployee=" + assignedEmployee + ", closeConfirmation=" + closeConfirmation + ", createdAt="
//				+ createdAt + ", updatedAt=" + updatedAt + "]";
//	}
//}
//
//

package com.micro.grievance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "grievances")
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer grievanceId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "departmentId", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @Column(nullable = false, length = 255)
    private String branchName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] attachment;

    @Lob
    @Column(name = "resolved_attachment", columnDefinition = "LONGBLOB")
    private byte[] resolvedAttachment; // New field

    @Column(nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "assignedEmployeeId")
    private Employee assignedEmployee;

    @Column(nullable = false)
    private Boolean closeConfirmation = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Grievance() {
        super();
    }

    public Grievance(Integer grievanceId, User user, Department department, Category category, String branchName,
                     String description, byte[] attachment, byte[] resolvedAttachment, String status,
                     Employee assignedEmployee, Boolean closeConfirmation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super();
        this.grievanceId = grievanceId;
        this.user = user;
        this.department = department;
        this.category = category;
        this.branchName = branchName;
        this.description = description;
        this.attachment = attachment;
        this.resolvedAttachment = resolvedAttachment;
        this.status = status;
        this.assignedEmployee = assignedEmployee;
        this.closeConfirmation = closeConfirmation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public Integer getGrievanceId() {
		return grievanceId;
	}

	public void setGrievanceId(Integer grievanceId) {
		this.grievanceId = grievanceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public byte[] getResolvedAttachment() {
		return resolvedAttachment;
	}

	public void setResolvedAttachment(byte[] resolvedAttachment) {
		this.resolvedAttachment = resolvedAttachment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getAssignedEmployee() {
		return assignedEmployee;
	}

	public void setAssignedEmployee(Employee assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}

	public Boolean getCloseConfirmation() {
		return closeConfirmation;
	}

	public void setCloseConfirmation(Boolean closeConfirmation) {
		this.closeConfirmation = closeConfirmation;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Grievance [grievanceId=" + grievanceId + ", user=" + user + ", department=" + department + ", category="
				+ category + ", branchName=" + branchName + ", description=" + description + ", attachment="
				+ Arrays.toString(attachment) + ", resolvedAttachment=" + Arrays.toString(resolvedAttachment)
				+ ", status=" + status + ", assignedEmployee=" + assignedEmployee + ", closeConfirmation="
				+ closeConfirmation + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

    
}
