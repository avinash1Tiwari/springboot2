package com.avinash.project.uber.uberApp.entities;


import com.avinash.project.uber.uberApp.entities.enums.PaymentMethod;
import com.avinash.project.uber.uberApp.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

   @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

   @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

   private Double amount;

   @CreationTimestamp
    private LocalDateTime paymentTime;
}
