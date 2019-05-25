package com.app.com.app.ch12.patterns.creational.app01.builder;

public class BusDriver {
    private final String name;
    private final int age;

    private BusDriver(BusDriverBuilder driverBuilder) {
        name = driverBuilder.builderName;
        age = driverBuilder.builderAge;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static class BusDriverBuilder {
        private String builderName;
        private int builderAge;

        public BusDriver build() {
            return new BusDriver(this);
        }

        public BusDriverBuilder name(String name) {
            builderName = name;
            return this;
        }

        public BusDriverBuilder age(int age) {
            builderAge = age;
            return this;
        }

    }
}
