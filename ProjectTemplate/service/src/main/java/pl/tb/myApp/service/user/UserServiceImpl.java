package pl.tb.myApp.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.tb.myApp.model.user.entity.User;
import pl.tb.myApp.model.util.exception.MyAppException;
import pl.tb.myApp.model.util.validation.ErrorMessage;
import pl.tb.myApp.repository.user.UserRepository;
import pl.tb.myApp.service.util.BasicService;
import pl.tb.myApp.service.util.validation.ServiceValidator;

import javax.persistence.OptimisticLockException;
import java.util.List;


@Service
//@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends BasicService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Transactional
    @Override
    public User add(User user) throws MyAppException {
        LOGGER.debug("Adding a new to-do entry with information: {}", user);

        if(user.getName()==null){
            throw new MyAppException(ServiceValidator.getErrorMessage("name", ErrorMessage.FIELD_REQUIRED));
        }
        try{
            User savedUser = userRepository.save(user);
            /*if(savedUser==null || savedUser.getId()==null) {
                throw new MyAppException(ServiceValidator.getErrorMessage("Error creating the user.", ErrorMessage.IDENTIFIER_REQUIRED));
            }*/
        }catch (OptimisticLockException e){
            throw new MyAppException(ServiceValidator.getErrorMessage("", ErrorMessage.DATA_CHANAGED_BY_ANOTER_USER));
        }
        return user;
    }

    @Transactional(rollbackFor = {MyAppException.class})
    @Override
    public User update(User user) throws MyAppException {
        LOGGER.debug("Updating contact with information: {}", user);
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
            User updated = userRepository.save(user);
            return updated;
        }catch (OptimisticLockException e){
            throw new MyAppException(ServiceValidator.getErrorMessage("", ErrorMessage.DATA_CHANAGED_BY_ANOTER_USER));
        }
    }

    @Transactional(rollbackFor = {MyAppException.class})
    @Override
    public User deleteById(Long userId) throws MyAppException {
        LOGGER.debug("Deleting a to-do entry with id: {}", userId);
        if (userId == null) {
            throw new MyAppException(ServiceValidator.getErrorMessage("userId", ErrorMessage.IDENTIFIER_REQUIRED));
        }
        User user = findById(userId);
        LOGGER.debug("Deleting to-do entry: {}", user);
        userRepository.delete(user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() throws MyAppException {
        LOGGER.debug("Finding all to-do entries");
        return (List<User>)userRepository.findAll();
    }

    @Transactional(readOnly = true, rollbackFor = {MyAppException.class})
    @Override
    public User findById(Long userId) throws MyAppException {
        LOGGER.debug("Finding a to-do entry with id: {}", userId);
        if (userId == null) {
            throw new MyAppException(ServiceValidator.getErrorMessage("userId", ErrorMessage.IDENTIFIER_REQUIRED));
        }
        User user =  userRepository.findOne(userId);
        LOGGER.debug("Found to-do entry: {}", user);
        if(user==null){
            throw new MyAppException(ServiceValidator.getErrorMessage("", ErrorMessage.NOT_FOUND));
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws MyAppException {
        LOGGER.debug("Finding a to-do entry with email: {}", email);
        try {
            User user = userRepository.findByEmail(email);
            LOGGER.debug("Found to-do entry: {}", user);
            if(user==null){
                throw new MyAppException(ServiceValidator.getErrorMessage("", ErrorMessage.NOT_FOUND));
            }
            return user;
        }catch (Exception e){
            throw new MyAppException(ServiceValidator.getErrorMessage("Error in finding user (by email):" + e.toString(), null));
        }
    }

}
