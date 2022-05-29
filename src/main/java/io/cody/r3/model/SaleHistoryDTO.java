package io.cody.r3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SaleHistoryDTO {

    private Long id;

    @NotNull
    private Boolean isFulfilled;

    @NotNull
    private Boolean isInprogress;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "44.08")
    private BigDecimal transactionFee;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "37.08")
    private BigDecimal paymentProcessingFee;

    private Long vendorSalesHistory;

    private Long purchase;

}
