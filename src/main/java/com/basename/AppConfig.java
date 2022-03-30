package com.basename;

import com.basename.models.User;
import com.basename.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@basename.com", "password");
        System.out.println(user.getName());
        System.out.println(userService.getDataSource());
    }
}
