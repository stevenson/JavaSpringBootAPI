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
    private double cost;

    public static class ParcelBuilder {
        public ParcelBuilder weight(double weight){
            this.weight = weight;
            computeCost();
            return this;
        }
        public ParcelBuilder length(double length){
            this.length = length;
            computeVolume();
            return this;
        }
        public ParcelBuilder width(double width){
            this.width = width;
            computeVolume();
            return this;
        }
        public ParcelBuilder height(double height){
            this.height = height;
            computeVolume();
            return this;
        }
        private void computeVolume(){
            this.volume = this.length*this.width*this.height;
            computeCost();
        }
        private void computeCost(){
            this.cost = 0.4 * this.volume;
        }

    }
}
