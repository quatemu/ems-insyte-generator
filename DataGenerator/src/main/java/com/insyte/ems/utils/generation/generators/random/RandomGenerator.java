package com.insyte.ems.utils.generation.generators.random;

import com.insyte.ems.utils.generation.generators.Generator;

public class RandomGenerator extends Generator {
    private double minValue = 0;
    private double maxValue = 1;

    public  RandomGenerator(double minValue, double maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    protected double getNextValue(){
        return Math.random() * (maxValue - minValue) + minValue;
    }
}
