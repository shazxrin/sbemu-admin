package io.github.shazxrin.sbemuadmin.model;

public record EntityInfo(
    EntityType type,
    String name,
    long messageCount,
    long maximumDeletedSequenceNumber,
    String createdDateTime
) { }
