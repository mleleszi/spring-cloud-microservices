package com.example.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailSender {

    public void sendEmail(String orderNumber) {
        log.info("order placed succesfully - order number is: {}", orderNumber);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("email sent for order id {}", orderNumber);
    }
}
