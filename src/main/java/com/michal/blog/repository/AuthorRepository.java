package com.michal.blog.repository;

import com.michal.blog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAllByFirstNameIgnoreCase(String firstName);
}
