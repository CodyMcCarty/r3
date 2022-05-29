package io.cody.r3.domain;

import io.cody.r3.model.WhoMade;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class ListingDetails {

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
    private WhoMade whoMade;

    @Column(nullable = false)
    private Boolean isHandMade;

    @Column(nullable = false)
    private Boolean isAutoRenewed;

    @Column
    private String sKU;

    @Column(nullable = false)
    private Boolean hasColors;

    @Column(nullable = false)
    private Boolean hasSecondaryColor;

    @Column(nullable = false)
    private Boolean hasSizes;

    @Column(nullable = false)
    private Boolean hasMaterials;

    @Column(nullable = false)
    private Boolean hasGenders;

    @Column(nullable = false)
    private Boolean hasPersonalization;

    @Column(nullable = false)
    private Boolean hasOtherfield;

    @Column
    private String colors;

    @Column
    private String sizes;

    @Column
    private String material;

    @Column
    private String genders;

    @Column
    private String secondaryColor;

    @Column
    private String personalization;

    @Column
    private String otherfields;

    @OneToOne
    @JoinColumn(name = "product_details_id", nullable = false)
    private Product productDetails;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
