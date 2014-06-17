package ngdemo.service;

import ngdemo.domain.model.Address;
import ngdemo.domain.model.User;

public class UserService {

    public User getDefaultUser() {
        User user = new User();
        user.setFirstName("JonFromREST");
        user.setLastName("DoeFromREST");

        Address address = new Address();
        address.setCity("Warszawa");
        address.setStreet("Lipki");
        address.setNumber(24);

        //user.setAddress(address);

        return user;
    }
}
