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

    private String voucherCode;

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
            //NOTE: additional processing with class fields can be performed here
            computeVolume();
            computeDefaultCost();
        }
        private void computeVolume(){
            this.volume = this.length*this.width*this.height;
        }
        private void computeDefaultCost(){
            this.cost = 0.4 * this.volume;
        }
    }

    public void applyRule(Rule rule){
        switch(rule.getFactor()){
            case "weight":
                this.cost = rule.getRate() * this.weight;
                break;
            case "volume":
            default:
               this.cost= rule.getRate() * this.volume;
                break;
        }

    }

    public void applyVoucher(Voucher voucher){
        this.cost = this.cost - voucher.getDiscount();
    }
}
