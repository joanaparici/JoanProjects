package com.fpmislata.JoanAparici1evalExam.domain.service.impl;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Publisher;
import com.fpmislata.JoanAparici1evalExam.domain.repository.PublisherRepository;
import com.fpmislata.JoanAparici1evalExam.domain.service.PublisherService;
import com.fpmislata.JoanAparici1evalExam.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public Publisher find(int id) {
        return publisherRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + id));
    }
}
