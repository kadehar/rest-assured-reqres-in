package com.github.kadehar.tests;

import com.github.kadehar.steps.Steps;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static Steps steps;

    @BeforeAll
    public static void init() {
        steps = new Steps();
    }
}
