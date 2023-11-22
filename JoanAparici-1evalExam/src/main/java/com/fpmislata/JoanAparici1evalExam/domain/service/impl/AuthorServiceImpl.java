package com.fpmislata.JoanAparici1evalExam.domain.service.impl;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Author;
import com.fpmislata.JoanAparici1evalExam.domain.repository.AuthorRepository;
import com.fpmislata.JoanAparici1evalExam.domain.service.AuthorService;
import com.fpmislata.JoanAparici1evalExam.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    AuthorRepository authorRepository;
    @Override
    public Author find(int id) {
        return authorRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }
}
