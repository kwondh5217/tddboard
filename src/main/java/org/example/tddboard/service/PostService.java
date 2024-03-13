package org.example.tddboard.service;

import lombok.RequiredArgsConstructor;
import org.example.tddboard.dto.PostRequest;
import org.example.tddboard.dto.PostResponse;
import org.example.tddboard.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostRequest postRequest){
        return postRepository.save(postRequest.toEntity()).getId();
    }

    public PostResponse findById(Long id){
        return postRepository.findById(id).orElseThrow().of();
    }
}
