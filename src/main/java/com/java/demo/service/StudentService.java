package com.java.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.demo.exception.RecordNotFoundException;
import com.java.demo.model.StudentEntity;
import com.java.demo.repository.StudentRepository;
 
@Service
public class StudentService {
     
    @Autowired
    StudentRepository repository;
     
    public List<StudentEntity> getAllStudents()
    {
        List<StudentEntity> studentList = repository.findAll();
         
        if(studentList.size() > 0) {
            return studentList;
        } else {
            return new ArrayList<StudentEntity>();
        }
    }
     
    public StudentEntity getStudentById(Long id) throws RecordNotFoundException
    {
        Optional<StudentEntity> student = repository.findById(id);
         
        if(student.isPresent()) {
            return student.get();
        } else {
            throw new RecordNotFoundException("No student record exist for given id");
        }
    }
     
    public StudentEntity createOrUpdateEmployee(StudentEntity entity) throws RecordNotFoundException
    {
    	System.out.println("entity.getId():"+entity.getId());
        Optional<StudentEntity> student = repository.findById(entity.getId());
         
        if(student.isPresent())
        {
            StudentEntity newEntity = student.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFullName(entity.getFullName());
            newEntity.setTelephone(entity.getTelephone());
            newEntity.setCourses(entity.getCourses());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteStudentById(Long id) throws RecordNotFoundException
    {
        Optional<StudentEntity> student = repository.findById(id);
         
        if(student.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No student record exist for given id");
        }
    }
}