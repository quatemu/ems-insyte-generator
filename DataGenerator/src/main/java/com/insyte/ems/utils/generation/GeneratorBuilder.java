package com.insyte.ems.utils.generation;

import com.insyte.ems.utils.generation.generators.Generator;
import com.insyte.ems.utils.generation.generators.RandomGenerator;
import java.time.LocalDateTime;

public class GeneratorBuilder {
    public Generator getGenerator(){
        Generator generator = new RandomGenerator();

        generator.setBeginDateTime(LocalDateTime.of(2017, 10, 22, 18, 48));
        generator.setEndDateTime(LocalDateTime.of(2017, 10, 22, 19,1));
        generator.setStep(1);
        generator.setMinValue(-10);
        generator.setMaxValue(10);

        return generator;
    }
}
