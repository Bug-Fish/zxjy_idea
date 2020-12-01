package com.zjy.msmservice.service;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
