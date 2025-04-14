package com.avinash.project.uber.uberApp.services;

import com.avinash.project.uber.uberApp.entities.Payment;
import com.avinash.project.uber.uberApp.entities.Ride;

public interface PaymentService {

    void processPayment(Payment payment);

    Payment createPayment(Ride ride);


}
