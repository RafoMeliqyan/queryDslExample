package com.example.demo.service;

import com.example.demo.model.BlogPost;
import com.example.demo.model.QBlogPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    @PersistenceContext
    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("com.baeldung.querydsl.intro");
    EntityManager em = emf.createEntityManager();
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    public BlogPost getById(Long id) {
        QBlogPost blogPost = QBlogPost.blogPost;

        return queryFactory.selectFrom(blogPost)
                .where(blogPost.id.eq(id))
                .fetchOne();
    }

    public BlogPost getByTitle(String title) {
        QBlogPost blogPost = QBlogPost.blogPost;

        return queryFactory.selectFrom(blogPost)
                .where(blogPost.title.eq(title))
                .fetchOne();
    }

    public BlogPost getByBody(String body) {
        QBlogPost blogPost = QBlogPost.blogPost;

        return queryFactory.selectFrom(blogPost)
                .where(blogPost.body.eq(body))
                .fetchOne();
    }

    public BlogPost getByUserID(Long userId) {
        QBlogPost blogPost = QBlogPost.blogPost;

        return queryFactory.selectFrom(blogPost)
                .where(blogPost.user.id.eq(userId))
                .fetchOne();
    }

    public void updateBlogPost(BlogPost blogPost, Long id) {
        QBlogPost qBlogPost = QBlogPost.blogPost;

        queryFactory.update(qBlogPost)
                .where(qBlogPost.id.eq(id))
                .set(qBlogPost.title , blogPost.getTitle())
                .set(qBlogPost.body , blogPost.getBody())
                .set(qBlogPost.user , blogPost.getUser())
                .execute();
    }

    public void deleteBlogPost(Long id) {
        QBlogPost blogPost = QBlogPost.blogPost;

        queryFactory.delete(blogPost)
                .where(blogPost.id.eq(id))
                .execute();
    }

}
