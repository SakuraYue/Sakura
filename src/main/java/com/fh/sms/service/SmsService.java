package com.fh.sms.service;

import com.fh.common.ServerResponse;

public interface SmsService {

    ServerResponse getCode(String phone);
}
