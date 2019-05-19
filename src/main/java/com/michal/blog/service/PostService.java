package com.michal.blog.service;

import com.michal.blog.model.Post;
import com.michal.blog.model.dto.PostDto;
import com.michal.blog.model.mapper.PostMapper;
import com.michal.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService implements GenericDao<PostDto, Long, String> {

    private PostRepository postRepository;
    private PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDto create(PostDto postDto) {
        Post postToSave = postMapper.toEntity(postDto);
        Post savedPost = postRepository.save(postToSave);
        return postMapper.toDto(savedPost);
    }

    @Override
    public Optional<PostDto> readById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toDto);
    }

    @Override
    public Set<PostDto> readAll(String title) {
        if (title != null) {
            return postRepository.findByTitleIgnoreCase(title)
                    .stream()
                    .map(postMapper::toDto)
                    .collect(Collectors.toSet());
        } else {
            return postRepository.findAll()
                    .stream()
                    .map(postMapper::toDto)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public PostDto update(PostDto postDto) {
        if (postDto.getTitle() != null) {
            Post postToUpdate = postMapper.toEntity(postDto);
            Post updatedPost = postRepository.save(postToUpdate);
            return postMapper.toDto(updatedPost);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title can not be empty.");
        }
    }

    @Override
    public void delete(PostDto postDto) {

    }

    @Override
    public void deleteById(Long id) {
        Optional<Post> postToDelete = postRepository.findById(id);
        postToDelete.ifPresent(p -> postRepository.delete(p));
    }
}
