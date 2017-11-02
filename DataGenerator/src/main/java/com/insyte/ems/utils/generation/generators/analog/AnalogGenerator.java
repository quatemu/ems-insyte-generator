package com.insyte.ems.utils.generation.generators.analog;

import com.insyte.ems.utils.generation.generators.Generator;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class AnalogGenerator extends Generator {
    private List<Variable> variables;
    private String formula;
    private long countOfSteps;
    private ScriptEngine engine = null;

    public AnalogGenerator(String formula, List<Variable> variables, long countOfSteps){
        super();

        if(variables == null){
            throw new IllegalArgumentException("В качестве списка переменных используется null-объект");
        } else if(formula == null){
            throw new IllegalArgumentException("В качестве формулы используется null-объект");
        } else if(countOfSteps < 1){
            throw new IllegalArgumentException("Количество шагов должно быть положительным значением");
        } else {
            this.formula = formula;
            this.variables = variables;
            this.countOfSteps = countOfSteps;
            init();
        }

    }

    private void init(){
        ScriptEngineManager mgr = new ScriptEngineManager();
        this.engine = mgr.getEngineByName("JavaScript");
    }

    @Override
    protected double getNextValue() {
        String curFormula = this.formula;
        for(Variable variable : variables){
            curFormula = curFormula.replace(variable.getName(), variable.getValue());
        }

        double result = 0;
        try{
            result = Double.valueOf(engine.eval(curFormula).toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }
}
