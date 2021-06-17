package kz.krisha.utils;

import kz.krisha.bisness_objects.User;
import kz.krisha.config.Config;

public class CreateUser {
    private CreateUser() {
    }

    public static User getUser() {
        final Config config = ReadConfig.getConfig();
        User user = new User();
        user.setPhoneNumber(config.getPhoneNumber());
        user.setPassword(config.getPassword());
        return user;
    }


}
