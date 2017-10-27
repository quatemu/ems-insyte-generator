package com.insyte.ems.utils.generation.generators;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Generator {
    protected LocalDateTime BeginDateTime;
    protected LocalDateTime CurrentDateTime;
    protected LocalDateTime EndDateTime;

    protected int Step = 1;
    protected String Delimiter = "\t";

    protected double MinValue = 0;
    protected double MaxValue = 1;

    public void setBeginDateTime(LocalDateTime beginDateTime) {
        this.BeginDateTime = beginDateTime;
        this.CurrentDateTime = this.BeginDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.EndDateTime = endDateTime;
    }

    public void setStep(int step) { this.Step = step; }

    public void setMinValue(double minValue) {
        this.MinValue = minValue;
    }
    public void setMaxValue(double maxValue) { this.MaxValue = maxValue; }

    protected void incDateTime(){
        this.CurrentDateTime = this.CurrentDateTime.plusSeconds(this.Step);
    }

    public abstract GenerationResult getNext();
    public abstract String getNextString();

    public abstract List<GenerationResult> getAll();
    public abstract StringBuilder getAllStrings();
}
