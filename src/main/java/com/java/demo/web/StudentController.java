package com.java.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.java.demo.exception.RecordNotFoundException;
import com.java.demo.model.StudentEntity;
import com.java.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService service;

	@GetMapping
	public ResponseEntity<List<StudentEntity>> getAllStudents() {
		List<StudentEntity> list = service.getAllStudents();

		return new ResponseEntity<List<StudentEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getStudentById(@PathVariable("id") Long id) throws RecordNotFoundException {
		StudentEntity entity = service.getStudentById(id);
		Gson gson = new Gson();
		return ResponseEntity.ok(gson.toJson(entity));

		// return new ResponseEntity<StudentEntity>(entity, new HttpHeaders(),
		// HttpStatus.OK);
	}

	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<StudentEntity> updateStudent(@PathVariable("id") long id, @RequestBody StudentEntity student)
			throws RecordNotFoundException {
		student.setId(id);
		StudentEntity updated = service.createOrUpdateEmployee(student);
		return new ResponseEntity<StudentEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student)
			throws RecordNotFoundException {
		StudentEntity updated = service.createOrUpdateEmployee(student);
		return new ResponseEntity<StudentEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteStudentById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteStudentById(id);
		return HttpStatus.FORBIDDEN;
	}

	@GetMapping("/test")
	public String testService() {
		return "Test service";
	}

}