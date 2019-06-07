package com.michal.blog.service;

import com.michal.blog.model.Author;
import com.michal.blog.model.dto.AuthorDto;
import com.michal.blog.model.mapper.AuthorMapper;
import com.michal.blog.model.mapper.AuthorPostMapper;
import com.michal.blog.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;
    @Mock
    private AuthorPostMapper authorPostMapper;

    @Test
    void getAllAuthorsIfSearchFilterIsNull() {
        //given
        List<Author> authors = prepareAuthorsData();
        given(authorRepository.findAll()).willReturn(authors);

        //when
        Set<AuthorDto> authorsFromDatabase = authorService.readAll(null);

        //then
        verify(authorRepository, times(1)).findAll();
        assertThat(authorsFromDatabase, hasSize(5));

    }

    @Test
    void getAllAuthorsByFirstName() {
        //given
        Author author = new Author();
        author.setFirstName("Author");

        given(authorRepository.findAllByFirstNameIgnoreCase(anyString())).willReturn(Optional.of(author));

        //when
        Set<AuthorDto> authors = authorService.readAll(anyString());

        //then
        verify(authorRepository, times(1)).findAllByFirstNameIgnoreCase(anyString());
    }

    private List<Author> prepareAuthorsData() {
        Author author1 = new Author();
        author1.setFirstName("first");
        Author author2 = new Author();
        author2.setFirstName("second");
        Author author3 = new Author();
        author3.setFirstName("third");
        Author author4 = new Author();
        author4.setFirstName("fourth");
        Author author5 = new Author();
        author5.setFirstName("fifth");

        return Arrays.asList(author1, author2, author3, author4, author5);
    }

}
