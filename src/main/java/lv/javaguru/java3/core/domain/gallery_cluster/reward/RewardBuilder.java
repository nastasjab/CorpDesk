package lv.javaguru.java3.core.domain.gallery_cluster.reward;

/**
 * Created by Aleksej_home on 2015.11.09..
 */
public class RewardBuilder {
    private String label;
    private String description;
    private boolean isActive;
    private long id;
    private String name;

    private RewardBuilder() {
    }

    public static RewardBuilder aReward() {
        return new RewardBuilder();
    }

    public RewardBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public RewardBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RewardBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public RewardBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public RewardBuilder withIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public RewardBuilder but() {
        return aReward().withId(id).withName(name).withLabel(label).withDescription(description).withIsActive(isActive);
    }

    public Reward build() {
        Reward reward = new Reward();
        reward.setId(id);
        reward.setName(name);
        reward.setLabel(label);
        reward.setDescription(description);
        reward.setIsActive(isActive);
        return reward;
    }
}
