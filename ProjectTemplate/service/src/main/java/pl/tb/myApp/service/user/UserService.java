package pl.tb.myApp.service.user;

import pl.tb.myApp.model.user.entity.User;
import pl.tb.myApp.model.util.exception.MyAppException;

import java.util.List;

public interface UserService {

    User add(User User) throws MyAppException;

    User deleteById(Long userId) throws MyAppException;

    User update(User user) throws MyAppException;

    List<User> findAll() throws MyAppException;

    User findByEmail(String email) throws MyAppException;

    User findById(Long userId) throws MyAppException;

}
