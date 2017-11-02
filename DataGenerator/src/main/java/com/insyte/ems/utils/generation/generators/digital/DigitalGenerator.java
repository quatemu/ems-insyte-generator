package com.insyte.ems.utils.generation.generators.digital;

import com.insyte.ems.utils.generation.generators.Generator;

import java.time.LocalDateTime;
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
    protected double getNextValue(){
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
}
