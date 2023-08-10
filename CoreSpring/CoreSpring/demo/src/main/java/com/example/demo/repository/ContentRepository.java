package com.example.demo.repository;

import com.example.demo.model.Content;
import com.example.demo.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ContentRepository extends Repository {
    List<Content> findAllByTitleContains(String keyword);
    List<Content> listByStatus(Status status);


}
