package com.micro.grievance.repository;

import com.micro.grievance.model.Feedback;

import java.util.List;

public interface FeedbackRepository {
    Feedback findById(int id);
    List<Feedback> findAll();
    Feedback save(Feedback feedback);
    Feedback update(Feedback feedback);
    void delete(int id);
}
