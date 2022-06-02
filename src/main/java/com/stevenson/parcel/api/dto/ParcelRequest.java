package com.stevenson.parcel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelRequest {

    @NotNull(message = "Weight is required.")
    @Min(value=0, message = "Minimum weight is 0.")
    private double weight;
    @NotNull(message = "Length is required.")
    @Min(value=0, message = "Minimum length is 0.")
    private double length;
    @NotNull(message = "Width is required.")
    @Min(value=0, message = "Minimum width is 0.")
    private double width;
    @NotNull(message = "height is required.")
    @Min(value=0, message = "Minimum height is 0.")
    private double height;

    private String voucherCode;
}
