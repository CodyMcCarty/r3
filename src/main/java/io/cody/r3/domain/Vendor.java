package io.cody.r3.domain;

import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Vendor {

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

    @Column(nullable = false, unique = true, length = 60)
    private String vendorName;

    @Column(length = 60)
    private String headLine;

    @Column(nullable = false, unique = true)
    private String vendorEmail;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean localOnly;

    @Column(length = 1000)
    private String about;

    @Column
    private String promoVideoLink;

    @Column
    private String webLinks;

    @Column
    private String policies;

    @Column(nullable = false)
    private Boolean isVendorActive;

    @OneToMany(mappedBy = "vendorProducts")
    private Set<Product> vendorProductsProducts;

    @OneToMany(mappedBy = "vendorAddresses")
    private Set<Address> vendorAddressesAddresss;

    @OneToMany(mappedBy = "vendorSalesHistory")
    private Set<SaleHistory> vendorSalesHistorySaleHistorys;

    @OneToOne(
            mappedBy = "vendorBankAccount",
            fetch = FetchType.LAZY
    )
    private BankAccount vendorBankAccount;

    @OneToMany(mappedBy = "vendorCreditCard")
    private Set<CreditCard> vendorCreditCardCreditCards;

    @OneToMany(mappedBy = "vendorImages")
    private Set<Image> vendorImagesImages;

    @OneToOne(
            mappedBy = "localShopAddress",
            fetch = FetchType.LAZY
    )
    private Address localShopAddress;

    @OneToMany(mappedBy = "vendorReviews")
    private Set<Review> vendorReviewsReviews;

    @OneToOne(mappedBy = "vendor1099", fetch = FetchType.LAZY)
    private Info1099 vendor1099;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
