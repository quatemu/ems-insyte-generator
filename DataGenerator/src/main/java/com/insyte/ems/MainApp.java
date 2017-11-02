package com.insyte.ems;

import com.insyte.ems.utils.generation.generators.Generator;
import com.insyte.ems.utils.generation.GeneratorBuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainApp {
    public static void main(String[] args){
        //test("../random_generator-config.json");
        //test("../analog_generator-config.json");
        test("../digital_generator-config.json");
    }

    public static void test(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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

