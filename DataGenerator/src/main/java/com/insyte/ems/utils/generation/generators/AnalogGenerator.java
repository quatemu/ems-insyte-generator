package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AnalogGenerator extends Generator {
    private double valueStep = 1;

    public AnalogGenerator(){
        super();

        LocalDateTime d1 = LocalDateTime.parse("2012-12-31T23:55:00");
        LocalDateTime d2 = LocalDateTime.parse("2013-01-01T00:05:00");
        long seconds = d1.until(d2, ChronoUnit.SECONDS);
        System.out.println(seconds);
    }

    @Override
    public GenerationResult getNext() {
        return null;
    }

    @Override
    public String getNextString() {
        return null;
    }
}
