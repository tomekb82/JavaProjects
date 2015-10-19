package pl.tb.myApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.tb.myApp.model.User;
import pl.tb.myApp.model.exception.MyAppException;
import pl.tb.myApp.model.validation.ErrorMessage;
import pl.tb.myApp.repository.UserRepository;
import pl.tb.myApp.service.util.BasicService;
import pl.tb.myApp.service.validation.ServiceValidator;

import javax.persistence.OptimisticLockException;
import java.util.List;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends BasicService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUser(User user) throws MyAppException {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) throws MyAppException {
        try {
            return userRepository.findByEmail(email);
        }catch (Exception e){
            throw new MyAppException(ServiceValidator.getErrorMessage("Error in finding user (by email):" + e.toString(), null));
        }
    }

    @Override
    public void deleteUser(Long userId) throws MyAppException {
        if (userId == null) {
            throw new MyAppException(ServiceValidator.getErrorMessage("userId", ErrorMessage.IDENTIFIER_REQUIRED));
        }
        User user = new User(userId);
        try {
            userRepository.delete(user);
        }catch (Exception e){
            throw new MyAppException(ServiceValidator.getErrorMessage("Error deleting the user:" + e.toString(), null));
        }
    }

    @Override
    public void addUser(User user) throws MyAppException {
        if(user.getName()==null){
            throw new MyAppException(ServiceValidator.getErrorMessage("name", ErrorMessage.FIELD_REQUIRED));
        }
        try{
            User savedUser = userRepository.save(user);
            if(savedUser.getId()==null) {
                throw new MyAppException(ServiceValidator.getErrorMessage("Error creating the user.", ErrorMessage.IDENTIFIER_REQUIRED));
            }
        }catch (OptimisticLockException e){
            throw new MyAppException(ServiceValidator.getErrorMessage("", ErrorMessage.DATA_CHANAGED_BY_ANOTER_USER));
        }
    }

    @Override
    public User updateUser(User user) throws MyAppException {
        if (user.getId() == null) {
            throw new MyAppException(ServiceValidator.getErrorMessage("id", ErrorMessage.IDENTIFIER_REQUIRED));
        }
        if(user.getVersion()==null){
            throw new MyAppException(ServiceValidator.getErrorMessage("version", ErrorMessage.FIELD_REQUIRED));
        }
        if(user.getName()==null){
            throw new MyAppException(ServiceValidator.getErrorMessage("name", ErrorMessage.FIELD_REQUIRED));
        }
        if(user.getCreatedDate()==null){
            throw new MyAppException(ServiceValidator.getErrorMessage("createdDate", ErrorMessage.FIELD_REQUIRED));
        }
        try{
            return userRepository.save(user);
        }catch (OptimisticLockException e){
            throw new MyAppException(ServiceValidator.getErrorMessage("", ErrorMessage.DATA_CHANAGED_BY_ANOTER_USER));
        }
    }

    @Override
    public User getUser(Long userId) throws MyAppException {
        if (userId == null) {
            throw new MyAppException(ServiceValidator.getErrorMessage("userId", ErrorMessage.IDENTIFIER_REQUIRED));
        }
        return userRepository.findOne(userId);
    }
}
