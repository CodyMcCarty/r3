package io.cody.r3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    private Long quantityAvaliable;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "71.08")
    private BigDecimal price;

    @NotNull
    private Boolean isProductActive;

    @NotNull
    private Long relavance;

    private Long vendorProducts;

    @NotNull
    private Long purchaseCart;

    private Long favorites;

}
