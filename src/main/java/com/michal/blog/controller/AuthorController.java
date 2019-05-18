package com.michal.blog.controller;

import com.michal.blog.model.dto.AuthorDto;
import com.michal.blog.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    Set<AuthorDto> getAuthors(@RequestParam(required = false) String firstName) {
        return authorService.getAuthors(firstName);
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    ResponseEntity<AuthorDto> save(@RequestBody AuthorDto authorDto) {
        if (authorDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Object's id has to be empty.");
        } else {
            AuthorDto savedAuthorDto = authorService.saveAuthor(authorDto);
            return ResponseEntity.ok(savedAuthorDto);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<AuthorDto> update(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        if (id.equals(authorDto.getId())) {
            AuthorDto updatedAuthorDto = authorService.updateAuthor(authorDto);
            return ResponseEntity.ok(updatedAuthorDto);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Id user to update is not the same in address URL.");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Long> delete(@PathVariable Long id) {
        if (authorService.getAuthorById(id).isPresent()) {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author has not been found.");
        }
    }

}
