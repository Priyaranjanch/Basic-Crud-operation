package com.example.datacrud.repo;

import com.example.datacrud.model.Lapstore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Storerepo extends JpaRepository<Lapstore, Long> {

}
