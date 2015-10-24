package pl.tb.myApp.service;

import pl.tb.myApp.model.User;
import pl.tb.myApp.model.exception.MyAppException;

import java.util.List;

public interface UserService {

    List<User> findAll() throws MyAppException;

    List<User> findUser(User user) throws MyAppException;

    User findByEmail(String email) throws MyAppException;

    void deleteUser(Long userId) throws MyAppException;

    void addUser(User User) throws MyAppException;

    User updateUser(User user) throws MyAppException;

    User getUser(Long userId) throws MyAppException;

}
