package com.hispeed.controller;

import com.hispeed.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dengtg on 2018-8-28.
 */

@RestController
public class TestController {

    @Autowired
    private CarOwnerService carOwnerService;

    @RequestMapping(value = "/test")
    public void test(){

        String source1 = "dataSource0";
        String source2 = "dataSource1";

        System.out.println("11111111111   "+ carOwnerService.getCarOwnerInfo(source1));


        System.out.println("2222222222222   "+ carOwnerService.getCarOwnerInfo(source2));

    }
}
