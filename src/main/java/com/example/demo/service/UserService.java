package com.example.demo.service;

import com.example.demo.model.QUser;
import com.example.demo.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @PersistenceContext
    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("com.queryDsl.example");
    EntityManager em = emf.createEntityManager();
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    public User getById(Long id) {
        QUser user = QUser.user;

        return queryFactory.selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    public User getByName(String name) {
        QUser user = QUser.user;

        return queryFactory.selectFrom(user)
                .where(user.name.eq(name))
                .fetchOne();
    }

    public List<User> getByDisabled(boolean disabled) {
        QUser user = QUser.user;
        return queryFactory.selectFrom(user)
                .where(user.disabled.eq(disabled))
                .fetch();
    }

    public void updateUser(User user, Long id) {
        QUser qUser = QUser.user;

        queryFactory.update(qUser)
                .where(qUser.id.eq(id))
                .set(qUser.name , user.getName())
                .set(qUser.disabled , user.getDisabled())
                .set(qUser.blogPosts , user.getBlogPosts())
                .execute();
    }

    public void deleteUser(Long id) {
        QUser user = QUser.user;

        queryFactory.delete(user)
                .where(user.id.eq(id))
                .execute();
    }

}
