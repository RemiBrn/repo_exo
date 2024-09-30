package org.example.exercice_student.service;

import jakarta.servlet.http.HttpSession;
import org.example.exercice_student.dao.UserRepository;
import org.example.exercice_student.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user){
        return userRepository.save(user);
    }

    public boolean login(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user.getPassword().equals(password)){
            httpSession.setAttribute("username", user.getUsername());
            httpSession.setAttribute("login", "OK");
            return true;
        }
        return false;
    }

    public boolean isLogged(){
        try{
            String isLogged = httpSession.getAttribute("login").toString();
            return isLogged.equals("OK");
        } catch (Exception ex){
            return false;
        }
    }

    public void logout(){
        httpSession.invalidate();
    }
}
