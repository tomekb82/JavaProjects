package eu.qualent.telecom.admin.bean;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.liferay.portal.model.User;

import eu.qualent.telecom.admin.service.IServiceAdmin;
import eu.qualent.telecom.admin.service.impl.ServiceAdmin;

@ManagedBean(name="adminMB")
@SessionScoped
public class AdminManagedBean {

	private static final long serialVersionUID = 1L;;
    private final static Logger LOGGER = Logger.getLogger(AdminManagedBean.class.getName());
    
    private static final String LIST_USERS_PAGE="listUsers";
    private static final String ADD_USER_PAGE="addUser";
    private static final String DEL_USER_PAGE="delUser";
    private static final String BACK_TO_MAIN="main";
    
    private long userId;
	private String userName;
    private String firstName;
    private String lastName;
    private String passwd;
    private IServiceAdmin serviceAdmin;
   
	public AdminManagedBean(){
		serviceAdmin = new ServiceAdmin(); 	
    }
    
    /* User management functions */
    
    /*
     * Add user
     */  
    public void addUser(){
    	getServiceAdmin().addUser(this.userName, 
    			this.firstName, 
    			this.lastName, 
    			this.passwd);
    }
    
    /*
     * List users
     */
    public List<User> getUserList(){
    	return getServiceAdmin().getUsers();
    }
    
    /*
     * Delete user
     */
    public void delUser(){
    	getServiceAdmin().delUser(this.userId);
    }
    
    /* 
     * Get user by id
     */
    public void getUserById(){
    	getServiceAdmin().getUserById(this.userId);
    }
    
    /* Navigation functions */
    public String back(){
		return BACK_TO_MAIN;
	}
    public String gotoListUsers(){
		return LIST_USERS_PAGE;
	}
    public String gotoAddUser(){
		return ADD_USER_PAGE;
	}
    public String gotoDelUser(){
		return DEL_USER_PAGE;
	}
     
    /* Setters and getters */
    public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public IServiceAdmin getServiceAdmin() {
		return serviceAdmin;
	}

	public void setServiceAdmin(IServiceAdmin serviceAdmin) {
		this.serviceAdmin = serviceAdmin;
	}
	
	@Override
	public String toString() {
		return "AdminManagedBean [userId=" + userId + ", userName=" + userName
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", passwd=" + passwd + "]";
	}
    
}
