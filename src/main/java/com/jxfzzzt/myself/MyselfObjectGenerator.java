package com.jxfzzzt.myself;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class MyselfObjectGenerator extends Generator<MyselfObject> {


    public MyselfObjectGenerator(Class<MyselfObject> type) {
        super(type);
    }

    @Override
    public MyselfObject generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus) {
        int age = sourceOfRandomness.nextInt(10);
        MyselfObject o = new MyselfObject(age);
        return o;
    }
}
