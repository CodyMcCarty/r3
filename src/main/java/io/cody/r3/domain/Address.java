package io.cody.r3.domain;

import io.cody.r3.model.AddressFor;
import io.cody.r3.model.USStates;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Address {

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
    @Enumerated(EnumType.STRING)
    private AddressFor addressFor;

    @Column(nullable = false)
    private Boolean isPrimary;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String street;

    @Column
    private String street2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private USStates state;

    @Column(nullable = false)
    private String zip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_addresses_id", nullable = false)
    private Vendor vendorAddresses;

    @OneToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Purchase shippingAddress;

    @OneToOne
    @JoinColumn(name = "billing_address_id", nullable = false)
    private Purchase billingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_addresses_id")
    private Customer customerAddresses;

    @OneToOne
    @JoinColumn(name = "home_address_id")
    private Info1099 homeAddress;

    @OneToOne
    @JoinColumn(name = "local_shop_address_id")
    private Vendor localShopAddress;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
