package io.github.shazxrin.sbemuadmin.model;

public enum EntityType {
    QUEUE, TOPIC, SUBSCRIPTION, UNKNOWN;

    public int toValue() {
        return switch (this) {
            case QUEUE -> 0;
            case TOPIC -> 1;
            case SUBSCRIPTION -> 2;
            case UNKNOWN -> -1;
        };
    }

    public static EntityType fromValue(int value) {
        return switch (value) {
            case 0 -> QUEUE;
            case 1 -> TOPIC;
            case 2 -> SUBSCRIPTION;
            default -> UNKNOWN;
        };
    }
}
