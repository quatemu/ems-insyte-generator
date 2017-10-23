package com.insyte.ems;

import com.insyte.ems.utils.generation.generators.Generator;
import com.insyte.ems.utils.generation.GeneratorBuilder;

public class MainApp {
    public static void main(String[] args){
        GeneratorBuilder gb = new GeneratorBuilder();
        Generator generator = gb.getGenerator();

        String value = null;
        while((value = generator.getNext()) != null){
            System.out.println(value);
        }
    }
}

