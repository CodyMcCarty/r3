package io.cody.r3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Info1099DTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String dateOfBirth;

    @NotNull
    @JsonProperty("tIN")
    private Integer tIN;

    @NotNull
    private Long vendor1099;

}
