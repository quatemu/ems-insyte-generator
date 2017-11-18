package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Generator {
    protected LocalDateTime beginDateTime;
    protected LocalDateTime currentDateTime;
    protected LocalDateTime endDateTime;

    protected long timeStep = 1;
    protected String delimiter = ";";


    protected abstract double getNextValue();

    public void setBeginDateTime(LocalDateTime beginDateTime) {
        if(beginDateTime == null){
            throw new IllegalArgumentException("В качестве beginDateTime используется null-объект");
        }
        else {
            this.beginDateTime = beginDateTime;
            this.currentDateTime = this.beginDateTime;
        }
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        if(endDateTime == null){
            throw new IllegalArgumentException("В качестве endDateTime используется null-объект");
        }
        else {
            this.endDateTime = endDateTime;
        }
    }

    public void setTimeStep(long timeStep) {
        if(timeStep < 0){
            throw new IllegalArgumentException("В качестве шага указано отрицательное значение");
        }
        else {
            this.timeStep = timeStep;
        }
    }
    public void setDelimiter(String delimiter) {
        if(delimiter != null){
            this.delimiter = delimiter;
        }
    }

    protected void incDateTime(){
        this.currentDateTime = this.currentDateTime.plusSeconds(this.timeStep);
    }

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

    public List<GenerationResult> getAll() {
        List<GenerationResult> results = new ArrayList<>();
        GenerationResult value;
        while((value = getNext()) != null){
            results.add(value);
        }
        return results;
    }

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

    public StringBuilder getAllStrings() {
        StringBuilder builder = new StringBuilder();
        String value;
        while((value = getNextString()) != null){
            builder.append(value);
        }
        return builder;
    }
}
