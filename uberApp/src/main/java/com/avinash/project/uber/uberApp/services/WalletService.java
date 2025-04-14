package com.avinash.project.uber.uberApp.services;

import com.avinash.project.uber.uberApp.entities.User;
import com.avinash.project.uber.uberApp.entities.Wallet;

public interface WalletService {

    Wallet addMoneyToWallet(User user,Double amount);

    void withDrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createWallet(User user);

    Wallet findByUser(User user);

    Wallet deductMoneyFromWallet(User user,Double amount);
}
