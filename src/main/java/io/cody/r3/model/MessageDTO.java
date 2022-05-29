package io.cody.r3.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MessageDTO {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String to;

    @NotNull
    @Size(max = 60)
    private String whoFrom;

    @NotNull
    @Size(max = 255)
    private String body;

}
