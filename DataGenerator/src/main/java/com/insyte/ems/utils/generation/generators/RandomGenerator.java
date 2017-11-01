package com.insyte.ems.utils.generation.generators;

import java.time.format.DateTimeFormatter;

public class RandomGenerator extends Generator {
    @Override
    public GenerationResult getNext(){
        GenerationResult result = null;
        try{
            if(!currentDateTime.isAfter(endDateTime)) {
                result = new GenerationResult(currentDateTime, getNextValue());
                incDateTime();
            }
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    public String getNextString() {
        String result = null;
        try{
            if(!currentDateTime.isAfter(endDateTime)) {
                result = currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME) + delimiter + String.valueOf(getNextValue());
                incDateTime();
            }
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
            result = null;
        }
        return result;
    }

    private double getNextValue(){
        return Math.random() * (maxValue - minValue) + minValue;
    }
}
