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

import br.com.b2ml.restful_api.entity.ClassT;
import br.com.b2ml.restful_api.repository.ClassRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ClassController {
    @Autowired
    private ClassRepository _repository;

    final String map = "/class";
    final String mapId = "/class/{id}";

    // LIST FULL
    @CrossOrigin
    @GetMapping(map)
    public List<ClassT> Get() {
        return _repository.findAll();
    }

    // LIST ID ESPEC.
    @CrossOrigin
    @GetMapping(mapId)
    public ResponseEntity<ClassT> GetById(@PathVariable(value = "id") long id)
    {
        Optional<ClassT> opt = _repository.findById(id);
        if(opt.isPresent())
            return new ResponseEntity<ClassT>(opt.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // REGISTER
    @CrossOrigin
    @PostMapping(map)
    public ClassT Post(@Valid @RequestBody ClassT classT)
    {
        return _repository.save(classT);
    }

    // UPDATE
    @CrossOrigin
    @PutMapping(mapId)
    public ResponseEntity<ClassT> Put(@PathVariable(value = "id") long id, @Valid @RequestBody ClassT newClassT)
    {
        Optional<ClassT> oldOpt = _repository.findById(id);
        if(oldOpt.isPresent()){
            ClassT classT = oldOpt.get();
            classT.setCode(newClassT.getCode());
            classT.setRoom(newClassT.getRoom());
            classT.setDateOppening(newClassT.getDateOppening());
            classT.setDateEnding(newClassT.getDateEnding());
            classT.setTeacherId(newClassT.getTeacherId());
            _repository.save(classT);
            return new ResponseEntity<ClassT>(classT, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @CrossOrigin
    @DeleteMapping(mapId)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<ClassT> opt = _repository.findById(id);
        if(opt.isPresent()){
            _repository.delete(opt.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}