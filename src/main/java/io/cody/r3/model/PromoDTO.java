package io.cody.r3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PromoDTO {

    private Long id;

    @Size(max = 60)
    private String promoName;

    @Size(max = 60)
    private String promoCode;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    private LocalDate startDate;

    private Long numOfDiscounts;

    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "48.08")
    private BigDecimal discountAmmount;

    private Double discontPrecentage;

    @NotNull
    private Boolean isActive;

    @Size(max = 255)
    private String about;

    private Long productPromo;

}
