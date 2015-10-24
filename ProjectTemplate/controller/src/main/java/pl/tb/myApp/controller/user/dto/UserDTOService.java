
package pl.tb.myApp.controller.user.dto;

import org.springframework.stereotype.Component;
import pl.tb.myApp.controller.util.dto.BasicDTO;
import pl.tb.myApp.model.user.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 24.10.15.
 */
@Component
public class UserDTOService implements BasicDTO<UserDTO, User> {

    @Override
    public List<UserDTO> createDTOs(List<User> models) {
        List<UserDTO> dtos = new ArrayList<>();

        for (User model: models) {
            dtos.add(createDTO(model));
        }

        return dtos;
    }

    @Override
    public UserDTO createDTO(User model) {
        UserDTO dto = new UserDTO();

        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setGender(model.getGender());
        return dto;
    }
}
