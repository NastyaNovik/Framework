package service;

import model.User;
import static util.Resolver.resolveTemplate;

public class UserCreator {

    public static final String USER_LOGIN="test.data.%s.user.login";
    public static final String USER_PASSWORD = "test.data.%s.user.password";

    public static User withCredentialsFromProperty(String orderNumber){
        orderNumber = orderNumber.toLowerCase();
        String userLogin = resolveTemplate(USER_LOGIN, orderNumber);
        String userPassword = resolveTemplate(USER_PASSWORD, orderNumber);
        return new User(TestDataReader.getTestData(userLogin),TestDataReader.getTestData(userPassword));
    }
}
