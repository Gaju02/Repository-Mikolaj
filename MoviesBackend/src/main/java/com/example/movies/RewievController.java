package com.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/rewievs")
public class RewievController {
    @Autowired
    private RewievService rewievService;

    @PostMapping
    public ResponseEntity<Rewiev> createRewiev(@RequestBody Map<String,String> payload){
        return new ResponseEntity<Rewiev>(rewievService.createReviewBy(payload.get("rewievBody"), payload.get("imdbId")), HttpStatus.OK);

    }
}
