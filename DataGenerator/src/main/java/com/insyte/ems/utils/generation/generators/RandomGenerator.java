package com.insyte.ems.utils.generation.generators;

import java.time.format.DateTimeFormatter;

public class RandomGenerator extends Generator {
    @Override
    public String getNext() {
        String result = null;

        try{
            if(CurrentDateTime.isAfter(EndDateTime)) {
                return result;
            }
            else
            {
                result = CurrentDateTime.format(DateTimeFormatter.ISO_DATE_TIME) + Delimiter + String.valueOf(getNextValue());
                incDateTime();
            }
        }
        catch (NullPointerException ex){
            //System.err.println(ex.);
            ex.printStackTrace();
            result = null;
        }

        return result;
    }

    /*@Override
    public StringBuilder getAll() {
        StringBuilder builder = new StringBuilder();
        String value;
        while((value = getNext()) != null){
            builder.append(value);
        }
        return builder;
    }*/

    private double getNextValue(){
        return Math.random() * (MaxValue - MinValue) + MinValue;
    }
}
