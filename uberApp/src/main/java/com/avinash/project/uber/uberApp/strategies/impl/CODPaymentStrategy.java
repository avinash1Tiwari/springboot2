package com.avinash.project.uber.uberApp.strategies.impl;

import com.avinash.project.uber.uberApp.entities.Drivers;
import com.avinash.project.uber.uberApp.entities.Payment;
import com.avinash.project.uber.uberApp.entities.Wallet;
import com.avinash.project.uber.uberApp.services.WalletService;
import com.avinash.project.uber.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Let Rider gives 100rs in cash
//then => Driver gets 70 as 30rs(platform - commission) wiil be deducted from driver's-wallet by uberApp.


@Service
@RequiredArgsConstructor
public class CODPaymentStrategy implements PaymentStrategy {                          //// COD => Cash On Delivery

private final WalletService walletService;

    @Override
    public void processPayment(Payment payment) {

        Drivers driver = payment.getRide().getDriver();

        Wallet driverWallet = walletService.findByUser(driver.getUser());

        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(),platformCommission);

    }
}
