package com.michal.blog.model.mapper;

import com.michal.blog.model.Post;
import com.michal.blog.model.dto.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {

    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setPublishDate(post.getPublishDate());
        return postDto;
    }

    public Post toEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPublishDate(postDto.getPublishDate());
        return post;
    }
}
