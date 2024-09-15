package com.micro.grievance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

//    (fetch = FetchType.LAZY)
    
    @ManyToOne
    @JoinColumn(name = "grievanceId", nullable = false)
    private Grievance grievance;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer rating;

    @Column(length = 2000)
    private String comments;

    @Column(nullable = false)
    private Boolean isResolved = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

	public Feedback() {
		super();
	}

	public Feedback(Integer feedbackId, Grievance grievance, User user, Integer rating, String comments,
			Boolean isResolved, LocalDateTime createdAt) {
		super();
		this.feedbackId = feedbackId;
		this.grievance = grievance;
		this.user = user;
		this.rating = rating;
		this.comments = comments;
		this.isResolved = isResolved;
		this.createdAt = createdAt;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Grievance getGrievance() {
		return grievance;
	}

	public void setGrievance(Grievance grievance) {
		this.grievance = grievance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Boolean getIsResolved() {
		return isResolved;
	}

	public void setIsResolved(Boolean isResolved) {
		this.isResolved = isResolved;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", grievance=" + grievance + ", user=" + user + ", rating="
				+ rating + ", comments=" + comments + ", isResolved=" + isResolved + ", createdAt=" + createdAt + "]";
	}

	
}
