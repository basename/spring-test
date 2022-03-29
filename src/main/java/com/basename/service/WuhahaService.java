package com.basename.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.PipedReader;

@Service
public class WuhahaService {

    @Autowired
    private GiftService giftService;

    public String test(){
        return  giftService.getGift();
    }
}
