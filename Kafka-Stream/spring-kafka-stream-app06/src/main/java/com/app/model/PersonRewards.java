package com.app.model;

public class PersonRewards {
    private int rewardAge;
    private String rewardName;
    private String rewardCard;

    public PersonRewards() {
    }

    public PersonRewards(Person person) {
        rewardAge=person.getAge();
        rewardCard=person.getCreditCard();
        rewardName=person.getName();
    }

    public int getRewardAge() {
        return rewardAge;
    }

    public void setRewardAge(int rewardAge) {
        this.rewardAge = rewardAge;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardCard() {
        return rewardCard;
    }

    public void setRewardCard(String rewardCard) {
        this.rewardCard = rewardCard;
    }
}
