package pl.tb.myApp.controller.user.dto;

/**
 * Created by tomek on 25.10.15.
 */
public class UserDTOBuilder {

    private UserDTO dto;

    public UserDTOBuilder() {
        dto = new UserDTO();
    }

    public UserDTOBuilder id(Long id) {
        dto.setId(id);
        return this;
    }

    public UserDTOBuilder name(String name) {
        dto.setName(name);
        return this;
    }

    public UserDTOBuilder email(String email) {
        dto.setEmail(email);
        return this;
    }

    public UserDTO build() {
        return dto;
    }

}
