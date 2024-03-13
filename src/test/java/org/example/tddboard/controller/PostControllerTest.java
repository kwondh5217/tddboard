package org.example.tddboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tddboard.dto.PostRequest;
import org.example.tddboard.dto.PostResponse;
import org.example.tddboard.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean(name = "postService")
    PostService postService;

    @Test
    @DisplayName("post가 생성된다")
    void create() throws Exception {

        //given
        Long id = 1L;
        PostRequest postRequest = PostRequest.builder()
                .title("test")
                .content("content")
                .build();

        given(postService.save(any(PostRequest.class))).willReturn(id);

        //when
        ResultActions resultActions = mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postRequest)));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    @DisplayName("post를 조회한다")
    void read() throws Exception {
        PostResponse postResponse = PostResponse
                .builder()
                .id(1L)
                .title("title")
                .content("test content")
                .build();
        given(postService.findById(any(Long.class))).willReturn(postResponse);

        mockMvc.perform(get("/posts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").value(postResponse.getContent()));
    }

    @Test
    @DisplayName("post를 수정한다")
    void update() throws Exception {
        PostResponse postResponse = PostResponse
                .builder()
                .id(1L)
                .title("title")
                .content("test content")
                .build();
        given(postService.update(1, )).willReturn(postResponse);
    }
}
