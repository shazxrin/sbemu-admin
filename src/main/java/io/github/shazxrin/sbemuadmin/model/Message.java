package io.github.shazxrin.sbemuadmin.model;

import java.time.LocalDateTime;

public record Message(
    long sequenceNumber,
    String headers,
    String body,
    LocalDateTime expireDateTime,
    LocalDateTime createdDateTime
) { }
