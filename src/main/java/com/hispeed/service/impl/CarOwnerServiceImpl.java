package com.hispeed.service.impl;

import com.hispeed.config.DataSourceAnnotation;
import com.hispeed.mapper.CarOwnerMapper;
import com.hispeed.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dengtg on 2018-8-28.
 */

@Service
public class CarOwnerServiceImpl implements CarOwnerService {

    @Autowired
    private CarOwnerMapper carOwnerMapper;

    @Override
    @DataSourceAnnotation
    public int getCarOwnerInfo(String ds) {
        return carOwnerMapper.getCarOwnerInfo();
    }
}
