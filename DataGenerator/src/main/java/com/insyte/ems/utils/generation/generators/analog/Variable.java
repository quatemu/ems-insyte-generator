package com.insyte.ems.utils.generation.generators.analog;

public class Variable {
    private String name;
    private double minValue;
    private double maxValue;
    private double curValue;
    private double step;

    public Variable(String name, double minValue, double maxValue, long countOfSteps){
        if(minValue > maxValue){
            throw new IllegalArgumentException("Минимальное значение больше максимального");
        } else if(name == null){
            throw new IllegalArgumentException("В качестве имени переменной используется null-объект");
        } else if(countOfSteps < 1){
            throw new IllegalArgumentException("Количество шагов должно быть положительным значением");
        } else{
            this.name = name;
            this.minValue = minValue;
            this.curValue = minValue;
            this.maxValue = maxValue;
            this.step = (maxValue - minValue) / countOfSteps;
        }
    }

    public String getName(){ return this.name; }

    public String getValue(){
        String result;
        if(curValue < 0){
            result = String.format("(%g)", curValue);
        } else {
            result = String.format("%g", curValue);
        }

        curValue += step;
        return result.replace(',', '.');
    }
}
