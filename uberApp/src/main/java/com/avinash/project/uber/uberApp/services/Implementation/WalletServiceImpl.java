package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.entities.User;
import com.avinash.project.uber.uberApp.entities.Wallet;
import com.avinash.project.uber.uberApp.repositories.WalletRepository;
import com.avinash.project.uber.uberApp.services.WalletService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;


    @Override
    public Wallet addMoneyToWallet(User user, Double amount) {
        Wallet wallet = findByUser(user);

        wallet.setBalance(wallet.getBalance() + amount);


      return walletRepository.save(wallet);

    }

    @Override
    public void withDrawAllMyMoneyFromWallet() {


    }

    @Override
    public Wallet findWalletById(Long walletId) {
       return walletRepository.findById(walletId).orElseThrow(()-> new RuntimeException("wallet with given walletId : " + walletId + " not found "));
    }

    @Override
    public Wallet createWallet(User user) {

        Wallet wallet = new Wallet();
        wallet.setUser(user);

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("wallet not found for the userId : " + user.getId()));
    }

    @Override
    public Wallet deductMoneyFromWallet(User user, Double amount) {
        Wallet wallet = findByUser(user);

        wallet.setBalance(wallet.getBalance() - amount);


        return walletRepository.save(wallet);
    }
}
