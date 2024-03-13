package org.example.tddboard.controller;

import lombok.RequiredArgsConstructor;
import org.example.tddboard.dto.PostRequest;
import org.example.tddboard.dto.PostResponse;
import org.example.tddboard.service.PostService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public PostResponse readPost(@PathVariable Long id){
        return postService.findById(id);
    }

    @PostMapping
    public Long createPost(@RequestBody PostRequest postRequest){
        return postService.save(postRequest);
    }



}
