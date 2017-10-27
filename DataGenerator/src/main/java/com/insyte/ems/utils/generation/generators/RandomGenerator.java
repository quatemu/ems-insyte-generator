

package com.insyte.ems.utils.generation.generators;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RandomGenerator extends Generator {
    @Override
    public String getNextString() {
        String result = null;
        try{
            if(!CurrentDateTime.isAfter(EndDateTime)) {
                result = CurrentDateTime.format(DateTimeFormatter.ISO_DATE_TIME) + Delimiter + String.valueOf(getNextValue());
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
    public GenerationResult getNext(){
        GenerationResult result = null;
        try{
            if(!CurrentDateTime.isAfter(EndDateTime)) {
                result = new GenerationResult(CurrentDateTime, getNextValue());
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
    public List<GenerationResult> getAll() {
        List<GenerationResult> results = new ArrayList<>();
        GenerationResult value;
        while((value = getNext()) != null){
            results.add(value);
        }
        return results;
    }

    @Override
    public StringBuilder getAllStrings() {
        StringBuilder builder = new StringBuilder();
        String value;
        while((value = getNextString()) != null){
            builder.append(value);
        }
        return builder;
    }

    private double getNextValue(){
        return Math.random() * (MaxValue - MinValue) + MinValue;
    }
}
