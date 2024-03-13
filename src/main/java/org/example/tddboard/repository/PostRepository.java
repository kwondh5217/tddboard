package org.example.tddboard.repository;

import org.example.tddboard.domain.Post;
import org.example.tddboard.dto.PostRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
