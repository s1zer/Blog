package com.michal.blog.controller;

import com.michal.blog.model.dto.PostDto;
import com.michal.blog.model.mapper.PostMapper;
import com.michal.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("api/posts")
public class PostController {

    PostService postService;
    PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping("")
    Set<PostDto> getPosts(@RequestParam(required = false) String title) {
        return postService.readAll(title);
    }

    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return postService.readById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
        if (postDto.getId() == null) {
            return ResponseEntity.ok(postService.create(postDto));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Object id has to be empty.");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody PostDto postDto) {
        if (id.equals(postDto.getId())) {
            PostDto updatedPostDto = postService.update(postDto);
            return ResponseEntity.ok(updatedPostDto);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Id post to update is not the same in address URL");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Long> delete(@PathVariable Long id) {
        if (postService.readById(id).isPresent()) {
            postService.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post has not been found");
        }
    }

}
