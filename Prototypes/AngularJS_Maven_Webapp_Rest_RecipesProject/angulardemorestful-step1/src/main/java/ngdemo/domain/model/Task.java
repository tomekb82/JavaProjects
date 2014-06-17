package ngdemo.domain.model;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/3/13
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Task {

    private Integer id;
    private Integer userID;
    private String title;
    private String description;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserID() {
        return userID;
    }
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}


