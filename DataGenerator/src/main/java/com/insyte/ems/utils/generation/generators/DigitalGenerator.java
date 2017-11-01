package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DigitalGenerator extends Generator {
    List<FunctionPart> functionParts = null;

    public DigitalGenerator(List<FunctionPart> functionParts){
        super();

        if(functionParts == null){
            throw new IllegalArgumentException("В качестве функции используется null-объект");
        }
        else{
            this.functionParts = functionParts;
        }
    }

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

    private double getNextValue(){
        for(FunctionPart functionPart : functionParts){
            if(IsCurrentDateBetweenDates(functionPart.beginDate, functionPart.endDate)){
                return functionPart.value;
            }
        }
        return 0;
    }

    private boolean IsCurrentDateBetweenDates(LocalDateTime beginDate, LocalDateTime endDate){
        return (currentDateTime.isAfter(beginDate) || currentDateTime.isEqual(beginDate)) &&
                (currentDateTime.isBefore(endDate) || currentDateTime.isEqual(endDate));
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
}
