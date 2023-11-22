package com.fpmislata.JoanAparici1evalExam.domain.repository;


import com.fpmislata.JoanAparici1evalExam.domain.entity.Publisher;

import java.util.Optional;

public interface PublisherRepository {
    Optional<Publisher> find(int id);
    Optional<Publisher> findPublisherByIsbn(String isbn);

}
