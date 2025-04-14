package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.entities.Payment;
import com.avinash.project.uber.uberApp.entities.Ride;
import com.avinash.project.uber.uberApp.repositories.PaymentRepository;
import com.avinash.project.uber.uberApp.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {

    }

    @Override
    public Payment createPayment(Ride ride) {
        return null;
    }
}
