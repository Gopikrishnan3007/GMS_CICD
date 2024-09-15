package com.micro.grievance.repositoryimpl;

import com.micro.grievance.model.Feedback;
import com.micro.grievance.repository.FeedbackRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class FeedbackRepositoryImpl implements FeedbackRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Feedback findById(int id) {
        return entityManager.find(Feedback.class, id);
    }

    @Override
    public List<Feedback> findAll() {
        return entityManager.createQuery("SELECT f FROM Feedback f", Feedback.class).getResultList();
    }

    @Override
    public Feedback save(Feedback feedback) {
        entityManager.persist(feedback);
        return feedback;
    }

    @Override
    public Feedback update(Feedback feedback) {
        return entityManager.merge(feedback);
    }

    @Override
    public void delete(int id) {
        Feedback feedback = findById(id);
        if (feedback != null) {
            entityManager.remove(feedback);
        }
    }
}
