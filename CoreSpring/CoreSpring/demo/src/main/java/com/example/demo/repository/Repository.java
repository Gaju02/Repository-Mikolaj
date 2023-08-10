package com.example.demo.repository;


import com.example.demo.model.Content;
import com.example.demo.model.Status;
import com.example.demo.model.Type;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class Repository {

    private final List<Content> content = new ArrayList<>();

    public Repository(){}

    public List<Content> findAll(){
        return content;
    }

    public Optional<Content> findById(Integer id) {
        return content.stream().filter(content1 -> content1.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init(){
        Content c = new Content(1,"My first Blog Post","My first Blog post", Status.IDEA,
                Type.ARTICLE,LocalDateTime.now(),null,"");
        content.add(c);
    }

    public void save(Content c) {
        content.removeIf(content->content.id().equals(content.id()));
        content.add(c);
    }

    public boolean existsById(Integer id) {
        return content.stream().filter(content1 -> content1.id().equals(id)).count() ==1;
    }

    public void delete(Integer id) {
        content.removeIf(content->content.id().equals(id));
    }
}
