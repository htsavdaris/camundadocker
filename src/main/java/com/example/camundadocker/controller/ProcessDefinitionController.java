package com.example.camundadocker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.camundadocker.service.camProcessDefinition;
@RestController
@RequestMapping("/processdefinitioncontroller")
public class ProcessDefinitionController {

    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {            
            //camProcessDefinition oProcessDefinition =  new camProcessDefinition();            
            return new ResponseEntity<>(camProcessDefinition.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findByID(@PathVariable String id) {
        try {
            
            return new ResponseEntity<>(camProcessDefinition.getId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/key/{key}")
    public ResponseEntity<?> findByKey(@PathVariable String key) {
        try {
            
            return new ResponseEntity<>(camProcessDefinition.getId(key), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // @PostMapping()
    // public ResponseEntity<?> create(@RequestBody Dto dto) {
    //     try {
    //         //TODO Implement Your Logic To Save Data And Return Result Through ResponseEntity
    //         return new ResponseEntity<>("Create Result", HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @PutMapping()
    // public ResponseEntity<?> update(@RequestBody Dto dto) {
    //     try {
    //         //TODO Implement Your Logic To Update Data And Return Result Through ResponseEntity
    //         return new ResponseEntity<>("Update Result", HttpStatus.OK);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            //TODO Implement Your Logic To Destroy Data And Return Result Through ResponseEntity
            return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
