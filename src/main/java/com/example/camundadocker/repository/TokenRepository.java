package com.example.camundadocker.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.camundadocker.model.*;

public interface TokenRepository extends CrudRepository<Token, Integer> {
    
   
    @Query (value = "select * from token where token.user_id = :user_id and (token.expired = false or token.revoked = false)", nativeQuery = true)
    List<Token> findAllValidTokenByUser(@Param("user_id") Long user_id);
   

    Optional<Token> findBytoken(String token);
}
