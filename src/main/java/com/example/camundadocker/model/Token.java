package com.example.camundadocker.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Integer tokenid;
  
    @Column(unique = true)
    public String token;
  
    @Enumerated(EnumType.STRING)
    @Builder.Default
    public TokenType tokentype = TokenType.BEARER;
  
    public boolean revoked;
  
    public boolean expired;
  
    public long user_id;


       public boolean isExpired() {
        return expired;
    }

    public boolean isRevoked() {
        return expired;
    }
}
