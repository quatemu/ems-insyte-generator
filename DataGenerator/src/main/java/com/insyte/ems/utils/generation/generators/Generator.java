package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Generator {
    protected LocalDateTime beginDateTime;
    protected LocalDateTime currentDateTime;
    protected LocalDateTime endDateTime;

    protected int timeStep = 1;
    protected String delimiter = "\t";

    protected double minValue = 0;
    protected double maxValue = 1;

    public abstract GenerationResult getNext();
    public abstract String getNextString();

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

    public void setTimeStep(int timeStep) {
        if(timeStep < 0){
            throw new IllegalArgumentException("В качестве шага указано отрицательное значение");
        }
        else {
            this.timeStep = timeStep;
        }
    }
    public void setDelimiter(String delimiter) {
        if(delimiter == null){
            throw new IllegalArgumentException("В качестве разделителя используется null-объект");
        }
        else {
            this.delimiter = delimiter;
        }
    }

    public void setMinValue(double minValue) { this.minValue = minValue; }
    public void setMaxValue(double maxValue) { this.maxValue = maxValue; }


    protected void incDateTime(){
        this.currentDateTime = this.currentDateTime.plusSeconds(this.timeStep);
    }

    protected List<GenerationResult> getAll() {
        List<GenerationResult> results = new ArrayList<>();
        GenerationResult value;
        while((value = getNext()) != null){
            results.add(value);
        }
        return results;
    }

    protected StringBuilder getAllStrings() {
        StringBuilder builder = new StringBuilder();
        String value;
        while((value = getNextString()) != null){
            builder.append(value);
        }
        return builder;
    }
}
