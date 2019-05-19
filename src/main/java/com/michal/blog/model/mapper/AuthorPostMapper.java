package com.michal.blog.model.mapper;

import com.michal.blog.model.Post;
import com.michal.blog.model.dto.AuthorPostDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorPostMapper {

    public AuthorPostDto toDto(Post post) {
        AuthorPostDto authorPostDto = new AuthorPostDto();
        authorPostDto.setPostId(post.getId());
        authorPostDto.setTitle(post.getTitle());
        authorPostDto.setPublishDate(post.getPublishDate());
        return authorPostDto;
    }
}
