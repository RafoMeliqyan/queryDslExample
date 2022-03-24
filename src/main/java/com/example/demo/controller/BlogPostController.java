package com.example.demo.controller;

import com.example.demo.model.BlogPost;
import com.example.demo.model.User;
import com.example.demo.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BlogPostController {

    private final BlogPostService blogPostService;

    @GetMapping("/getBlogPostById/{id}")
    public BlogPost byId(@PathVariable("id") Long id) {
        return blogPostService.getById(id);
    }

    @GetMapping("/getBlogPostByTitle/{title}")
    public BlogPost byTitle(@PathVariable("title") String title) {
        return blogPostService.getByTitle(title);
    }

    @GetMapping("/getBlogPostByBody/{body}")
    public BlogPost byBody(@PathVariable("body") String body) {
        return blogPostService.getByBody(body);
    }

    @GetMapping("/getBlogPostByUserId/{userId}")
    public BlogPost byUserId(@PathVariable("userId") Long userId) {
        return blogPostService.getByUserID(userId);
    }

    @PutMapping("/updateBlogPost/{id}")
    public void updateBlogPost(@RequestBody BlogPost blogPost, @PathVariable("id") Long id) {
        blogPostService.updateBlogPost(blogPost, id);
    }

    @DeleteMapping("/deleteBlogPost/{id}")
    public void deleteBlogPost(@PathVariable("id") Long id) {
        blogPostService.deleteBlogPost(id);
    }

}
