package com.insyte.ems;

import com.insyte.ems.utils.generation.generators.Generator;
import com.insyte.ems.utils.generation.GeneratorBuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainApp {
    public static void main(String[] args){
        try(BufferedReader br = new BufferedReader(new FileReader("../digital_generator-config.json"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            GeneratorBuilder gb = new GeneratorBuilder();
            Generator generator = gb.getGenerator(sb.toString());
            String value;
            while((value = generator.getNextString()) != null){
                System.out.println(value);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

