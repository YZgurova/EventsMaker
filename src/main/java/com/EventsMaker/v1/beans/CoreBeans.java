package com.EventsMaker.v1.beans;

import com.EventsMaker.v1.payments.StripeChargeService;
import com.EventsMaker.v1.repositories.*;
import com.EventsMaker.v1.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@ComponentScan
public class CoreBeans
{
//    @Bean
//    public UserService userService(UserRepository repository) {
//        return new UserService(repository);
//    }
//
//    @Bean
//    public MusicCreatorService creatorService(TransactionTemplate txTemplate, MusicCreatorRepository repository, WalletRepository walletRepository) {
//        return new MusicCreatorService(txTemplate, repository, walletRepository);
//    }
//
    @Bean
    public EventsService songService(EventsRepository repository) {
        return new EventsService(repository);
    }
//    @Bean
//    public WalletService walletService(WalletRepository walletRepository, SongRepository songRepository) {
//        return new WalletService(walletRepository, songRepository);
//    }
//    @Bean
//    public TransactionsService transactionsService(TransactionsRepository transactionsRepository) {
//        return  new TransactionsService(transactionsRepository);
//    }

    @Bean
    public AuthService authService(CredentialsRepository credentialsRepository, UserRepository userRepository, TransactionTemplate transactionTemplate) {
        return new AuthService(credentialsRepository, userRepository, transactionTemplate);
    }

    @Bean
    public StripeChargeService paymentService() {
        return new StripeChargeService();
    }
}
