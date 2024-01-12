package com.example.demohometask.sms;


import com.example.demohometask.sms.dto.SmsCodeSendDto;
import com.example.demohometask.sms.dto.SmsCodeVerifyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/sms-code" )
@RequiredArgsConstructor
public class SmsCodeController
{
    private final SmsCodeService service;

    @PostMapping( "/send" )
    public void send( @RequestBody SmsCodeSendDto smsCodeSendDto )
    {
        service.sendSms( smsCodeSendDto );
    }

    @PostMapping( "/verify" )
    public void just( @RequestBody SmsCodeVerifyDto verifyDto )
    {
        service.verifyCode( verifyDto );
    }
}
