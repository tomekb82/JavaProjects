package eu.qualent.telecom.admin.bean;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.liferay.portal.model.User;

import eu.qualent.telecom.admin.service.User3ULocalServiceUtil;
import eu.qualent.telecom.admin.service.impl.User3ULocalServiceImpl;

@ManagedBean(name="adminMB")
@SessionScoped
public class AdminManagedBean {

	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private final static Logger LOGGER = Logger.getLogger(AdminManagedBean.class.getName());
   
   	/*
     * Get all users
     */
    public List<User> getUserList(){
    	return eu.qualent.telecom.admin.service.User3ULocalServiceUtil.getUserList();
    }
    
    
   
}
