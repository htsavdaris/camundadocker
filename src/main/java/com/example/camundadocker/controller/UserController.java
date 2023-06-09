package com.example.camundadocker.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.camundadocker.model.cam_user;
import com.example.camundadocker.service.UserService;

import lombok.extern.slf4j.Slf4j;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private  UserService userService;

    //private static final Logger logger  = LogManager.getLogger("com.example");

    @GetMapping()
    public ResponseEntity<List<cam_user>> findAll() {
        try {
            log.info("/user Controller: findAll is called");            
            List<cam_user> users = userService.findAll();
            return ResponseEntity.ok(users);
            
        } catch (Exception e) {
            log.error("/user findAll exception"); 
            return new ResponseEntity<List<cam_user>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("{id}")
    public ResponseEntity<Optional<cam_user>> findById(@PathVariable("id") long id) {
        try {   
            log.info("/user Controller:  findById is called");            
            Optional<cam_user> camUser = userService.findByID(id);
            return ResponseEntity.ok(camUser);
        } catch (Exception e) {
            return new ResponseEntity<Optional<cam_user>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
  
}
