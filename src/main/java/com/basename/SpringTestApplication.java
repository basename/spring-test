package com.basename;

import com.basename.models.User;
import com.basename.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.transform.Source;

public class SpringTestApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@basename.com", "password");
        String address = userService.getDataSource();
        System.out.println(address);
        System.out.println(user.getName());
    }
}
