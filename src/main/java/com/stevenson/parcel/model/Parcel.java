package com.stevenson.parcel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public static ParcelBuilder builder() {
        return new ParcelBuilder(){
            @Override
            public Parcel build() {
                prebuild();
                return super.build();
            }
        };
    }

    public static class ParcelBuilder  {
        void prebuild(){
            // additional processing with class fields can be performed here
            computeVolume();
            computeCost();
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
