package com.kidd.demos.java.util.stream;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 * @author Kidd
 */
@Log4j2
public class IntStreamTest {

    @Test
    public void range(){
        IntStream.range(1, 4).forEach(log::info);
    }

    @Test
    public void rangeClosed(){
        IntStream.rangeClosed(1, 3).forEach(log::info);
    }

    @Test
    public void of(){
        IntStream.of(1, 2, 3).forEach(log::info);
    }

    @Test
    public void concat(){
        IntStream a = IntStream.of(1);
        IntStream b = IntStream.of(2, 3);
        IntStream.concat(a, b).forEach(log::info);
    }

    @Test
    public void generate(){
        AtomicInteger i = new AtomicInteger(1);
        IntSupplier supplier = i::getAndIncrement;
        IntStream.generate(supplier).limit(3).forEach(log::info);
    }

    @Test
    public void builder(){
        IntStream.builder()
                .add(1)
                .add(2)
                .add(3)
                .build()
                .forEach(log::info);
    }


}
