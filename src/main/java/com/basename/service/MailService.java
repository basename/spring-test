package com.basename.service;

import com.basename.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MailService {

    private ZoneId zoneId = ZoneId.systemDefault();

    public void setZoneId(ZoneId zoneId){
        this.zoneId = zoneId;
    }

    public String getTime(){
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public void sendLoginMail(User user){
        System.err.println(String.format("hi %s rou are logged in at %s ",user.getName(),this.getTime()));
    }

    public void sendRegistrationMail(User user){
        System.err.println(String.format("welcome, %s !",user.getName()));
    }
}
