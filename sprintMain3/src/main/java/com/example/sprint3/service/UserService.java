package com.example.sprint3.service;

import com.example.sprint3.model.User;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface UserService {
    
    User getUserByLogin(String login) throws JAXBException;
    List<User> getAllUsers() throws JAXBException;
    void createUser(User user) throws JAXBException;
}
