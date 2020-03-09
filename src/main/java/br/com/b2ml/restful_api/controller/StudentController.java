package br.com.b2ml.restful_api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2ml.restful_api.entity.Student;
import br.com.b2ml.restful_api.repository.StudentRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository _repository;

    final String map = "/student";
    final String mapId = "/student/{id}";

    // LIST FULL
    @CrossOrigin
    @GetMapping(map)
    public List<Student> Get() {
        return _repository.findAll();
    }

    // LIST ID ESPEC.
    @CrossOrigin
    @GetMapping(mapId)
    public ResponseEntity<Student> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Student> opt = _repository.findById(id);
        if(opt.isPresent())
            return new ResponseEntity<Student>(opt.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // REGISTER
    @CrossOrigin
    @PostMapping(map)
    public Student Post(@Valid @RequestBody Student student)
    {
        return _repository.save(student);
    }

    // UPDATE
    @CrossOrigin
    @PutMapping(mapId)
    public ResponseEntity<Student> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Student newStudent)
    {
        Optional<Student> oldOpt = _repository.findById(id);
        if(oldOpt.isPresent()){
            Student student = oldOpt.get();
            student.setName(newStudent.getName());
            student.setRegistration(newStudent.getRegistration());
            student.setClassId(newStudent.getClassId());
            _repository.save(student);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @CrossOrigin
    @DeleteMapping(mapId)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Student> opt = _repository.findById(id);
        if(opt.isPresent()){
            _repository.delete(opt.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}