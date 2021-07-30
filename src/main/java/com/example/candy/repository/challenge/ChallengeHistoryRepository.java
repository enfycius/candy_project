package com.example.candy.repository.challenge;

import com.example.candy.domain.challenge.ChallengeHistory;
import com.example.candy.domain.user.User;
import com.example.candy.enums.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChallengeHistoryRepository {

    private final EntityManager em;

    @Transactional
    public void save(ChallengeHistory challengeHistory) {
        em.persist(challengeHistory);

    }

    @Transactional(readOnly = true)
    public List<ChallengeHistory> findAllByUser(User user) {
        return em.createQuery("select c from ChallengeHistory c join c.user u" +
                " join fetch c.challenge ch" +
                " where u.id = :id", ChallengeHistory.class)
                .setParameter("id", user.getId())
                .getResultList();
    }


    @Transactional(readOnly = true)
    public List<ChallengeHistory> findAllByUserAndCategory(User user, Category category) {
        return em.createQuery("select c from ChallengeHistory c join c.user u" +
                " join fetch c.challenge ch" +
                " where u.id = :id " +
                " and ch.category = :category", ChallengeHistory.class)
                .setParameter("id", user.getId())
                .setParameter("category", category)
                .getResultList();
    }
}
