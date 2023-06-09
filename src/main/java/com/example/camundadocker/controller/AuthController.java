package com.example.camundadocker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.camundadocker.dto.AuthenticationRequest;
import com.example.camundadocker.dto.AuthenticationResponse;
import com.example.camundadocker.dto.RegisterRequest;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.camundadocker.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class AuthController {
    @Autowired
    private  final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterRequest request) {
        try {     
            log.info("OAuth Controller: Register post :" + request.toString(), request);       
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {    
            log.info("OAuth Controller: Authenticate post :" + request.toString(), request);        
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
                log.info("OAuth Controller: refreshToken post ", request);   
                service.refreshToken(request, response);
    }


}
