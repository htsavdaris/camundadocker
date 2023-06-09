package com.example.camundadocker.service;

import com.example.camundadocker.dto.AuthenticationRequest;
import com.example.camundadocker.dto.AuthenticationResponse;
import com.example.camundadocker.dto.RegisterRequest;
import com.example.camundadocker.model.Token;
import com.example.camundadocker.model.TokenType;
import com.example.camundadocker.model.cam_user;
import com.example.camundadocker.repository.TokenRepository;
import com.example.camundadocker.repository.UserRepository;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = cam_user.builder()
        .login(request.getLogin())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    log.info("AuthenticationService : authenticate is called");
    log.info("AuthenticationService: Login " + request.getLogin());    
    log.info("AuthenticationService: Password " + request.getPassword()); 
    var AuthToken = new UsernamePasswordAuthenticationToken(
      request.getLogin(),
      request.getPassword());
    log.info("AuthenticationService: Token " + AuthToken.toString()); 
    authenticationManager.authenticate(AuthToken);
    var user = repository.findByLogin(request.getLogin())
        .orElseThrow();
    log.info("AuthenticationService: User " + user.getLogin());
    var jwtToken = jwtService.generateToken(user);
    log.info("AuthenticationService: jwtToken " + jwtToken.toString());
    var refreshToken = jwtService.generateRefreshToken(user);
    //revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    log.info("AuthenticationService: saveUserToken ");
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(cam_user user, String jwtToken) {
    log.info("AuthenticationService: saveUserToken " + jwtToken.toString()); 
    var token = Token.builder()
        .user_id(user.getUser_id())
        .token(jwtToken)
        .tokentype(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(cam_user user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUser_id());
     
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
    
  }
  //
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userName;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userName = jwtService.extractUsername(refreshToken);
    if (userName != null) {
      var user = this.repository.findByLogin(userName)
          .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
        try {
          new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
        } catch (StreamWriteException e) {
          // Auto-generated catch block
          e.printStackTrace();
        } catch (DatabindException e) {
          // Auto-generated catch block
          e.printStackTrace();
        } catch (java.io.IOException e) {
          // Auto-generated catch block
          e.printStackTrace();
        }

      }
    }
  }
}
