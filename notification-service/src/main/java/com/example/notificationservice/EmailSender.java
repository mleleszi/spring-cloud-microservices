package com.example.notificationservice;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    public void sendEmail(String orderNumber) {
        System.out.println("order placed succesfully - order number is: " + orderNumber);
        System.out.println("email sent");
    }
}
