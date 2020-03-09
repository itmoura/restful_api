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

import br.com.b2ml.restful_api.entity.Teacher;
import br.com.b2ml.restful_api.repository.TeacherRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class TeacherController {
    @Autowired
    private TeacherRepository _teacherRepository;

    // LIST FULL
    @CrossOrigin
    @GetMapping("/teacher")
    public List<Teacher> Get() {
        return _teacherRepository.findAll();
    }

    // LIST ID ESPEC.
    @CrossOrigin
    @GetMapping("/teacher/{id}")
    public ResponseEntity<Teacher> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Teacher> teacher = _teacherRepository.findById(id);
        if(teacher.isPresent())
            return new ResponseEntity<Teacher>(teacher.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // REGISTER
    @CrossOrigin
    @PostMapping("/teacher")
    public Teacher Post(@Valid @RequestBody Teacher teacher)
    {
        return _teacherRepository.save(teacher);
    }

    // UPDATE
    @CrossOrigin
    @PutMapping("/teacher/{id}")
    public ResponseEntity<Teacher> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Teacher newTeacher)
    {
        Optional<Teacher> oldTeacher = _teacherRepository.findById(id);
        if(oldTeacher.isPresent()){
            Teacher teacher = oldTeacher.get();
            teacher.setName(newTeacher.getName());
            teacher.setTitration(newTeacher.getTitration());
            _teacherRepository.save(teacher);
            return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @CrossOrigin
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Teacher> teacher = _teacherRepository.findById(id);
        if(teacher.isPresent()){
            _teacherRepository.delete(teacher.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}