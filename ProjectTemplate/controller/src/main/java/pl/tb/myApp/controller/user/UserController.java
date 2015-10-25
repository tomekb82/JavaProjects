package pl.tb.myApp.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.tb.myApp.controller.user.dto.UserDTO;
import pl.tb.myApp.controller.user.dto.UserDTOService;
import pl.tb.myApp.controller.util.controller.BasicController;
import pl.tb.myApp.model.user.entity.User;
import pl.tb.myApp.model.user.enumeration.Gender;
import pl.tb.myApp.model.util.exception.MyAppException;
import pl.tb.myApp.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 */
@Controller
@RequestMapping("/myApp/user")
@Transactional(propagation = Propagation.REQUIRED)
public class UserController extends BasicController{

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserDTOService userDTOService;

  @RequestMapping(value = "/findAll", method = RequestMethod.GET)
  @ResponseBody
  public List<UserDTO> findAll() throws MyAppException{
    LOGGER.debug("Finding all todo entries.");

    List<User> models = userService.findAll();
    LOGGER.debug("Found {} to-do entries.", models.size());

    return userDTOService.createDTOs(models);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  @ResponseBody
  public UserDTO add(@Valid @RequestBody UserDTO userDTO) throws MyAppException{

    User user = new User(userDTO.getEmail(), userDTO.getName(), Gender.F);
    //user.setVersion(0L);
    user.setUser("Tomek");
    LOGGER.debug("Adding a new to-do entry with information: {}", user);

    User added = userService.add(user);
    LOGGER.debug("Added a to-do entry with information: {}", added);

    return userDTOService.createDTO(added);
  }

  @RequestMapping(value ="/delete", method = RequestMethod.DELETE)
  @ResponseBody
  public User deleteById(@PathVariable("id") Long id) throws MyAppException{
    LOGGER.debug("Deleting a to-do entry with id: {}", id);

    User user = userService.deleteById(id);
    LOGGER.debug("Deleted to-do entry with information: {}", user);

    return user;
  }

  @RequestMapping(value = "/findByEmail/{email}", method = RequestMethod.GET)
  @ResponseBody
  public UserDTO findByEmail(@PathVariable("email") String email) throws MyAppException{
    LOGGER.debug("Finding to-do entry with email: {}", email);

    User user = userService.findByEmail(email);
    LOGGER.debug("Found to-do entry with information: {}", user);

    return userDTOService.createDTO(user);
  }

  @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
  @ResponseBody
  public UserDTO findById(@PathVariable("id") Long id) throws MyAppException{
    LOGGER.debug("Finding to-do entry with id: {}", id);

    User user = userService.findById(id);
    LOGGER.debug("Found to-do entry with information: {}", user);

    return userDTOService.createDTO(user);
  }

  @RequestMapping(value ="/update/{id}{email}{name}", method = RequestMethod.PUT)
  @ResponseBody
  public UserDTO updateUser(@PathVariable("id") long id,
                           @PathVariable("email") String email,
                           @PathVariable("name") String name) throws MyAppException{
    User user = userService.findById(id);
    LOGGER.debug("Updating a to-do entry with information: {}", user);

    user.setEmail(email);
    user.setName(name);
    User updated = userService.update(user);
    LOGGER.debug("Updated the information of a to-entry to: {}", updated);

    return userDTOService.createDTO(updated);
  }

} // class UserController
