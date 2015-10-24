package pl.tb.myApp.model.user.entity;

import org.springframework.test.util.ReflectionTestUtils;
import pl.tb.myApp.model.user.enumeration.Gender;

/**
 * Created by tomek on 24.10.15.
 */
public class UserBuilder {

    private User model;
    public UserBuilder() {
        model = new User();
    }
    public UserBuilder id(Long id) {
        ReflectionTestUtils.setField(model, "id", id);
        return this;
    }
    public UserBuilder email(String email) {
        model.update(model.getName(), model.getEmail(), model.getGender());
        return this;
    }
    public UserBuilder name(String name) {
        model.update(name, model.getEmail(), model.getGender());
        return this;
    }
    public UserBuilder gender(Gender gender) {
        model.update(model.getName(), model.getEmail(), gender);
        return this;
    }
    public User build() {
        return model;
    }
}
