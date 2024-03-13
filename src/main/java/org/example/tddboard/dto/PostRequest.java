package org.example.tddboard.dto;

import lombok.*;
import org.example.tddboard.domain.Post;

@Builder
@Data
public class PostRequest {
    private String title;
    private String content;

    public Post toEntity(){
        return Post.builder().
                title(title)
                .content(content)
                .build();
    }
}
