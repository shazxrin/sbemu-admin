package io.github.shazxrin.sbemuadmin.model;

public record Entity (
    String entityGroupId,
    long entityId,
    EntityType type,
    String name,
    long messageCount,
    String createdDateTime
) { }
