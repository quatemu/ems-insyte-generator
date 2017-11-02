package com.insyte.ems.utils.generation;

import com.insyte.ems.utils.generation.generators.*;
import com.insyte.ems.utils.generation.generators.analog.AnalogGenerator;
import com.insyte.ems.utils.generation.generators.analog.Variable;
import com.insyte.ems.utils.generation.generators.digital.DigitalGenerator;
import com.insyte.ems.utils.generation.generators.digital.FunctionPart;
import com.insyte.ems.utils.generation.generators.random.RandomGenerator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GeneratorBuilder {
    public Generator getGenerator(String JsonString){

        Generator generator = null;

        try{
            JSONObject root = new JSONObject(JsonString);
            String generatorType = root.getString("Generator_Type");
            JSONObject generationParameters = root.getJSONObject("Generation_Parameters");
            JSONObject dateTimeIntervals = generationParameters.getJSONObject("Datetime_Intervals");

            long timeStep = generationParameters.getLong("Time_Step");
            LocalDateTime beginDateTime = LocalDateTime.parse(dateTimeIntervals.getString("Begin_DateTime"));
            LocalDateTime endDateTime = LocalDateTime.parse(dateTimeIntervals.getString("End_DateTime"));

            if(generatorType.toLowerCase().equals("random")){
                JSONObject valueIntervals = root.getJSONObject("Value_Intervals");
                double minValue = valueIntervals.getDouble("Min_Value");
                double maxValue = valueIntervals.getDouble("Max_Value");

                generator = new RandomGenerator(minValue, maxValue);
            } else if(generatorType.toLowerCase().equals("analog")){
                long countOfSteps = beginDateTime.until(endDateTime, ChronoUnit.SECONDS) / timeStep;
                String formula = root.getString("Formula");
                JSONArray variablesArray = root.getJSONArray("Variables");
                List<Variable> variables = new ArrayList<>();
                for(int i=0; i<variablesArray.length(); i++){
                    JSONObject variable = variablesArray.getJSONObject(i);
                    variables.add(new Variable(
                        variable.getString("Name"),
                        variable.getDouble("Min_Value"),
                        variable.getDouble("Max_Value"),
                        countOfSteps
                    ));
                }

                generator = new AnalogGenerator(formula, variables, countOfSteps);
            }
            else if(generatorType.toLowerCase().equals("digital")) {
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
            }

            if(generator != null){
                generator.setTimeStep(timeStep);
                try{ generator.setDelimiter(generationParameters.getString("Delimiter")); }
                catch (JSONException ex){ ex.printStackTrace();}

                generator.setBeginDateTime(beginDateTime);
                generator.setEndDateTime(endDateTime);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            generator = null;
        }

        return generator;
    }
}
