package com.micro.grievance.service;

import com.micro.grievance.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback saveFeedback(Feedback feedback);
    Feedback getFeedbackById(int id);
    List<Feedback> getAllFeedbacks();
    void deleteFeedbackById(int id);
    Feedback updateFeedback(int id, Feedback feedback);
}
