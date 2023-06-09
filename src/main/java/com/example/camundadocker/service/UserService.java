package com.example.camundadocker.service;
import com.example.camundadocker.model.cam_user;
import com.example.camundadocker.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserRepository repository;

    // public UserService() {
    //     {
        
    //    }; 
    // }

    public Optional<cam_user> findByID(Long id) {
        try {
            return repository.findById(id);    
        } catch (Exception e) {
            System.out.print("Error" +e.getMessage());
            return null;
        }
       
    }

    public List<cam_user> findAll() {
        try {
            return (List<cam_user>) repository.findAll();    
        } catch (Exception e) {
            System.out.print("Error" +e.getMessage());
            return null;
        }
        
    }

    public Optional<cam_user> findByLogin(String login) {
        try {
            return repository.findByLogin(login);    
        } catch (Exception e) {
            System.out.print("Error findByLogin: " + e.getMessage());
            return null;
        }
       
    }

    public Boolean saveUser(cam_user user )
    {
        try {
            repository.save(user);    
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public Boolean Authenticate(String login, String password)
    {
        try {
            Optional<cam_user> user =  findByLogin(login);
            if (user.isPresent())
            {
                if (user.get().getPassword() == password) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;

        }
    }


    public Boolean ChangePassword(String login, String oldPassword,  String newPassword)
    {
        try {
            Optional<cam_user> user =  findByLogin(login);
            if (user.isPresent())
            {
                if (user.get().getPassword() == oldPassword) {
                    

                } else {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;

        }
    }




    
}
