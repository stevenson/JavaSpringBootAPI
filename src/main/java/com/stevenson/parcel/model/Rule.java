package com.stevenson.parcel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rule {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    private int priority;
    private String name;
    private String param;
    private String condition;
    private double comparison;
    private double costRate;
    private String costFactor;

    public boolean applies(Parcel parcel){
        double checkParam = 0;
        switch(this.param){
            case "weight":
                checkParam = parcel.getWeight();
                break;
            case "volume":
            default:
                checkParam = parcel.getVolume();
                break;
        }
        switch(this.condition){
            case "exceeds":
                return checkParam > this.comparison;
            case "below":
            default:
                return checkParam < this.comparison;
        }
    }
    public double computeCost(Parcel parcel){
        switch(costFactor){
            case "weight":
                return this.costRate * parcel.getWeight();
            case "volume":
            default:
                return this.costRate *  parcel.getVolume();
        }
    }
}
