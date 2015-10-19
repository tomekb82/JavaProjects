package pl.tb.myApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.tb.myApp.controller.enumeration.ErrorType;
import pl.tb.myApp.controller.util.BasicController;
import pl.tb.myApp.model.User;
import pl.tb.myApp.model.enumeration.Gender;
import pl.tb.myApp.model.exception.MyAppException;
import pl.tb.myApp.service.UserService;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@Controller
@Transactional(propagation = Propagation.REQUIRED)
public class UserController extends BasicController{

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserService userService;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param name User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String email, String name, String gender) {
    User user = null;
    try {
      user = new User(email, name, Gender.fromValue(gender));
      user.setVersion(0L);
      user.setUser("Tomek");
      userService.addUser(user);
    } catch (MyAppException ex) {
      return prepareErrorMessage(ex, "");
    }
    return "User succesfully created! (id = " + user.getId() + ")";
  }
  
  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(Long id) {
    try {
      userService.deleteUser(id);
    } catch (MyAppException ex) {
      return prepareErrorMessage(ex, "");
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /get-by-email  --> Return the id for the user having the passed email.
   * 
   * @param email The email to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId = null;
    try {
      User user = userService.findByEmail(email);
      userId = String.valueOf(user.getId());
    } catch (MyAppException ex) {
      return prepareErrorMessage(ex, "");
    } catch (Exception e) {
      return "User not found";
    }

    return "The user id is: " + userId;
  }
  
  /**
   * /update  --> Update the email and the name for the user in the database 
   * having the passed id.
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      User user = userService.getUser(id);
      user.setEmail(email);
      user.setName(name);
      userService.updateUser(user);
    }catch (MyAppException ex) {
      return prepareErrorMessage(ex, "");
    }
    return "User succesfully updated!";
  }


  
} // class UserController
