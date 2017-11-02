package com.insyte.ems.utils.generation.generators.digital;

import java.time.LocalDateTime;

public class FunctionPart {
    public double value;
    public LocalDateTime beginDate;
    public LocalDateTime endDate;

    public FunctionPart(double value, LocalDateTime beginDate, LocalDateTime endDate){
        if(beginDate == null || endDate == null){
            throw new IllegalArgumentException("В качестве даты используется null-объект");
        }
        else{
            this.value = value;
            this.beginDate = beginDate;
            this.endDate = endDate;
        }
    }
}
