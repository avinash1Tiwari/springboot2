//package web.app.uber.entity;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//
//
//@Entity
//public class WalletEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    private UberUserEntity user;
//
//    private Double balance;
//
//    @OneToMany(mappedBy = "wallet",fetch = FetchType.LAZY)
//    private List<WalletTransactionEntity> transactions;
//}
