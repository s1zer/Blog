package com.michal.blog.service;

import com.michal.blog.model.Author;
import com.michal.blog.model.dto.AuthorDto;
import com.michal.blog.model.mapper.AuthorMapper;
import com.michal.blog.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService implements GenericDao<AuthorDto, Long, String> {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Optional<Author> duplicateAuthor = authorRepository.findAllByFirstNameIgnoreCase(authorDto.getFirstName());
        duplicateAuthor.ifPresent(
                a -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Object already exists");
                }
        );
        Author authorToSave = authorMapper.toEntity(authorDto);
        Author savedAuthor = authorRepository.save(authorToSave);
        return authorMapper.toDto(savedAuthor);
    }

    @Override
    public Optional<AuthorDto> readById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDto);
    }

    @Override
    public Set<AuthorDto> readAll(String firstName) {
        if (firstName != null) {
            return authorRepository.findAllByFirstNameIgnoreCase(firstName)
                    .stream()
                    .map(authorMapper::toDto)
                    .collect(Collectors.toSet());
        } else {
            return authorRepository.findAll()
                    .stream()
                    .map(authorMapper::toDto)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        if (authorDto.getFirstName() != null && authorDto.getLastName() != null) {
            Author authorToUpdate = authorMapper.toEntity(authorDto);
            Author updatedAuthor = authorRepository.save(authorToUpdate);
            return authorMapper.toDto(updatedAuthor);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name and last name can not be empty.");
        }
    }

    @Override
    public void delete(AuthorDto authorDto) {

    }

    @Override
    public void deleteById(Long id) {
        Optional<Author> authorToDelete = authorRepository.findById(id);
        authorToDelete.ifPresent(a -> authorRepository.delete(a));
    }
}
