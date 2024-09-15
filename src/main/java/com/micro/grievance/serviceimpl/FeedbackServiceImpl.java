package com.micro.grievance.serviceimpl;

import com.micro.grievance.model.Feedback;
import com.micro.grievance.repository.FeedbackRepository;
import com.micro.grievance.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteFeedbackById(int id) {
        feedbackRepository.delete(id);
    }

    @Override
    public Feedback updateFeedback(int id, Feedback feedback) {
        if (feedbackRepository.findById(id) != null) {
            feedback.setFeedbackId(id);
            return feedbackRepository.update(feedback);
        }
        return null;
    }
}
