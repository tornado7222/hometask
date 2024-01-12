package com.example.demohometask.sms;



import com.example.demohometask.sms.entity.SmsCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCodeRepository extends CrudRepository<SmsCode, String>
{
}
