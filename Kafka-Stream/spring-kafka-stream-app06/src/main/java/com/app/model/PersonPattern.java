package com.app.model;

public class PersonPattern {
    private int patternAge;
    private String patternName;
    private String patternCard;

    public PersonPattern(Person person){
        this.patternAge=person.getAge();
        this.patternName=person.getName();
        this.patternCard=person.getCreditCard();
    }

    public PersonPattern() {
    }

    public int getPatternAge() {
        return patternAge;
    }

    public void setPatternAge(int patternAge) {
        this.patternAge = patternAge;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getPatternCard() {
        return patternCard;
    }

    public void setPatternCard(String patternCard) {
        this.patternCard = patternCard;
    }

    @Override
    public String toString() {
        return "PersonPattern{" +
                "patternAge=" + patternAge +
                ", patternName='" + patternName + '\'' +
                ", patternCard='" + patternCard + '\'' +
                '}';
    }
}
