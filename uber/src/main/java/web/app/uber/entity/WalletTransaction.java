//package web.app.uber.entity;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.CreationTimestamp;
//import web.app.uber.enums.TransactionMethod;
//import web.app.uber.enums.TransactionType;
//
//import java.time.LocalDateTime;
//
//
//@Entity
//public class WalletTransactionEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Double amount;
//
//    private TransactionType transactionType;
//
//    private TransactionMethod transactionMethod;
//
//    @OneToOne
//    @JoinColumn(name = "ride_id", referencedColumnName = "id")
//    private RideEntity ride_id;
//
//    private String transactionId;
//
//
//    @ManyToOne
//    private WalletEntity wallet;           ////multiple transaction can belong to a single Wallet
//
//
//    @CreationTimestamp
//    private LocalDateTime timeStamp;
//}
