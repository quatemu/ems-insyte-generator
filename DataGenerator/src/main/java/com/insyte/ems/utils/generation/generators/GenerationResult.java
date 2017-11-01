package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;

public class GenerationResult {
    public LocalDateTime dateTime;
    public double value;

    public GenerationResult(LocalDateTime dateTime, double value){
        this.dateTime = dateTime;
        this.value = value;
    }
}
