package com.insyte.ems.utils.generation;

import com.insyte.ems.utils.generation.generators.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GeneratorBuilder {
    public Generator getGenerator(String JsonString){

        Generator generator = null;

        try{
            JSONObject root = new JSONObject(JsonString);
            String generatorType = root.getString("Generator_Type");
            if(generatorType.toLowerCase().equals("random")){
                generator = new RandomGenerator();
            }
            else if(generatorType.toLowerCase().equals("analog")){
                generator = new AnalogGenerator();
            }
            else if(generatorType.toLowerCase().equals("digital"))
            {
                JSONObject generationParameters = root.getJSONObject("Generation_Parameters");
                JSONObject dateTimeIntervals = generationParameters.getJSONObject("Datetime_Intervals");
                JSONArray function = root.getJSONArray("Function");
                List<FunctionPart> functionParts = new ArrayList<>();
                for(int i=0; i<function.length(); i++){
                    JSONObject functionPart = function.getJSONObject(i);
                    functionParts.add(new FunctionPart(
                        functionPart.getDouble("Value"),
                        LocalDateTime.parse(functionPart.getString("Begin_DateTime")),
                        LocalDateTime.parse(functionPart.getString("End_DateTime"))
                    ));
                }
                generator = new DigitalGenerator(functionParts);
                generator.setBeginDateTime(LocalDateTime.parse(dateTimeIntervals.getString("Begin_DateTime")));
                generator.setEndDateTime(LocalDateTime.parse(dateTimeIntervals.getString("End_DateTime")));

                generator.setTimeStep(generationParameters.getInt("Time_Step"));
                generator.setDelimiter(generationParameters.getString("Delimiter"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
            generator = null;
        }

        return generator;
    }
}
