package com.nocountry.woco.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RatingRequest {
    @Min(1)
    @Max(5)
    private Integer rating;
    @NotNull
    @NotBlank
    private String comment;
}
