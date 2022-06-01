package com.stevenson.parcel.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleRequest {

    @Min(value = 1, message = "priority is must be greater than one.")
    private int priority;
    @NotBlank(message = "name is required.")
    private String name;
    @NotBlank(message = "param is required.")
    private String param;
    @NotBlank(message = "condition is required.")
    private String condition;
    @Min(value = 1, message = "threshold is must be greater than one.")
    private double threshold;
    @Min(value = 0, message = "rate must be a whole number.")
    private double rate;
    @NotBlank(message = "factor is required.")
    private String factor;

}
