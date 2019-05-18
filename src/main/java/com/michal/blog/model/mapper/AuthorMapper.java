package com.michal.blog.model.mapper;

import com.michal.blog.model.Author;
import com.michal.blog.model.dto.AuthorDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    public AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setAge(author.getAge());
        return authorDto;
    }

    public Author toEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setAge(authorDto.getAge());
        return author;
    }
}
