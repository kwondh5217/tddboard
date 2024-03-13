package org.example.tddboard.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.tddboard.dto.PostRequest;
import org.example.tddboard.dto.PostResponse;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    public PostResponse of(){
        return PostResponse.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
