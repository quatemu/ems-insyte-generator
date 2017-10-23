package com.insyte.ems.utils.generation;

import com.insyte.ems.utils.generation.generators.Generator;
import com.insyte.ems.utils.generation.generators.RandomGenerator;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class GeneratorBuilder {
    public Generator getGenerator(String JsonString){

        Generator generator = null;

        try{
            JSONObject root = new JSONObject(JsonString);
            JSONObject signalParameters = root.getJSONObject("Signal_Parameters");
            JSONObject valueIntervals = signalParameters.getJSONObject("Value_Intervals");

            JSONObject generationParameters = root.getJSONObject("Generation_Parameters");
            JSONObject dateTimeIntervals = generationParameters.getJSONObject("Datetime_Intervals");


            String generatorType = root.getString("Generator_Type");
            if(generatorType.toLowerCase().equals("random")){
                generator = new RandomGenerator();
            }

            generator.setMinValue(Double.valueOf(valueIntervals.getString("Min_Value")));
            generator.setMaxValue(Double.valueOf(valueIntervals.getString("Max_Value")));
            generator.setStep(Integer.valueOf(generationParameters.getString("Generation_Step")));

            generator.setBeginDateTime(LocalDateTime.parse(dateTimeIntervals.getString("Min_Value")));
            generator.setEndDateTime(LocalDateTime.parse(dateTimeIntervals.getString("Max_Value")));
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return generator;
    }
}
