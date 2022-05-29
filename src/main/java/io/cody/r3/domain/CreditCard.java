package io.cody.r3.domain;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class CreditCard {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private Boolean isPrimary;

    @Column(nullable = false)
    private String cardholder;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String expiration;

    @Column(nullable = false, length = 5)
    private String cvv;

    @Column
    private String zip;

    @OneToOne
    @JoinColumn(name = "pruchase_credit_card_id", nullable = false)
    private Purchase pruchaseCreditCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_credit_cards_id")
    private Customer customerCreditCards;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_credit_card_id", nullable = false)
    private Vendor vendorCreditCard;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
