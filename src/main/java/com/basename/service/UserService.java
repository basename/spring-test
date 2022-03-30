package com.basename.service;

import com.basename.interfaces.MetricTime;
import com.basename.interfaces.Printlog;
import com.basename.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private DataSource dataSource;

    public void  setMailService(MailService mailService){
        this.mailService = mailService;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<User> users = new ArrayList<>(List.of(
            new User(1,"bob@basename.com","password","bob"),
            new User(2,"tom@basename.com","password","tom")
                    )
    );

    @Printlog(level = 1,name = "yanzhengke",value = "hhh")
    public User login(String email,String password){

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                mailService.sendLoginMail(user);
                return  user;
            }
        }
        throw new RuntimeException("login failed");
    }
    
    public User getUser(Integer id){
        User user1 = this.users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow();
        return user1;
    }

    @MetricTime("reginster")
    @Printlog(level = 1,name = "yanzhengke",value = "hhh")
    public User register(String email,String password,String name){
        users.forEach(user -> {
            if (user.getEmail().equalsIgnoreCase(email)){
                throw new RuntimeException("email exist");
            }
        });

        User user = new User((int) (users.stream().mapToLong(u -> u.getId()).max().getAsLong()+1),email,password,name);
        users.add(user);

        mailService.sendRegistrationMail(user);
        return  user;
    }

    public String getDataSource(){
        return  this.dataSource.getAddress();
    }


}
