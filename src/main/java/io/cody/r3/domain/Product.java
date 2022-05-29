package io.cody.r3.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Product {

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

    @Column(nullable = false, length = 60)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Long quantityAvaliable;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isProductActive;

    @Column(nullable = false)
    private Long relavance;

    @OneToMany(mappedBy = "productTags")
    private Set<Tag> productTagsTags;

    @OneToMany(mappedBy = "productImages")
    private Set<Image> productImagesImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_products_id")
    private Vendor vendorProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_cart_id", nullable = false)
    private Purchase purchaseCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorites_id")
    private Customer favorites;

    @OneToOne(mappedBy = "productDetails", fetch = FetchType.LAZY)
    private ListingDetails productDetails;

    @OneToMany(mappedBy = "productReviews")
    private Set<Review> productReviewsReviews;

    @OneToMany(mappedBy = "productPromo")
    private Set<Promo> productPromoPromos;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
