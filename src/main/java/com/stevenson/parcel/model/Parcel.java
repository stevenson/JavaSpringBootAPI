package com.stevenson.parcel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parcel {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    private double weight;
    private double height;
    private double width;
    private double length;
    private double volume;
    private double cost = 0;

    public double getVolume(){
        this.volume = this.length*this.width*this.height;
        return this.volume;
    }
    public double getCost(){
        if(this.cost == 0){
            this.cost = 0.4 * this.volume;
        }
        return this.cost;
    }

    public double getCost(Rule rule){
        this.cost = rule.computeCost(this);
        return this.cost;
    }
}
