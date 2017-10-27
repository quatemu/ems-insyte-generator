package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;

public class GenerationResult {
    public LocalDateTime DateTime;
    public double Value;

    public GenerationResult(LocalDateTime dateTime, double value){
        this.DateTime = dateTime;
        this.Value = value;
    }
}
