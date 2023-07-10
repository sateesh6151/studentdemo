package com.java.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.demo.model.StudentEntity;
 
@Repository
public interface StudentRepository
        extends JpaRepository<StudentEntity, Long> {
 
}
