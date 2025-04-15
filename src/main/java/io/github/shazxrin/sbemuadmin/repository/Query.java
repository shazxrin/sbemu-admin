package io.github.shazxrin.sbemuadmin.repository;

public final class Query {
    public static final String ENTITY_LIST_ALL_QUERY = """
        SELECT
            e_lock.EntityGroupId as EntityGroupId,
            e_lock.EntityId as EntityId,
            e_lookup.Type as Type,
            e_lookup.Name as Name,
            e_lookup.MessageCount as MessageCount,
            e_lookup.CreatedTime as CreatedDateTime
        FROM EntityLockTable e_lock
        JOIN EntityLookupTable e_lookup
            ON e_lock.EntityGroupId = e_lookup.EntityGroupId
            AND e_lock.EntityId = e_lookup.Id
        """;
    public static final String ENTITY_COUNT_QUERY = """
        SELECT
            COUNT(*) as Count
        FROM EntityLockTable e_lock
        JOIN EntityLookupTable e_lookup
            ON e_lock.EntityGroupId = e_lookup.EntityGroupId
            AND e_lock.EntityId = e_lookup.Id
        WHERE
            e_lock.EntityGroupId = ? AND
            e_lock.EntityId = ?
        """;

    public static final String MESSAGE_LIST_QUEUE_QUERY = """
        SELECT
            m.SequenceNumber as SequenceNumber,
            m.Headers as Headers,
            m.Body as Body,
            m.ExpiresAtUtc as ExpireDateTime,
            m.CreatedTime as CreatedDateTime
        FROM MessagesTable m
        WHERE
            m.EntityGroupId = ?
            AND m.EntityId = ?
            AND m.SubqueueType = 0
            AND m.SequenceNumber BETWEEN ? AND ?
        """;
    public static final String MESSAGE_LIST_TOPIC_QUERY = """
        SELECT
            b.SequenceNumber as SequenceNumber,
            b.Headers as Headers,
            b.Body as Body,
            b.CreatedTime as CreatedDateTime
        FROM BodiesTable b
        WHERE
            b.EntityGroupId = ?
            AND b.EntityId = ?
            AND b.SubqueueType = 0
            AND b.SequenceNumber BETWEEN ? AND ?
        """;
    public static final String MESSAGE_LIST_SUBSCRIPTION_QUERY = """
        SELECT
            m_ref.SequenceNumber as SequenceNumber,
            b.Headers as Headers,
            b.Body as Body,
            m_ref.ExpiresAtUtc as ExpireDateTime,
            m_ref.CreatedTime as CreatedDateTime
        FROM MessageReferencesTable m_ref
        JOIN BodiesTable b
            ON m_ref.EntityGroupId = b.EntityGroupId
            AND m_ref.BodyId = b.SequenceNumber
        WHERE
            m_ref.EntityGroupId = ?
            AND m_ref.EntityId = ?
            AND m_ref.SubqueueType = 0
            AND m_ref.SequenceNumber BETWEEN ? AND ?
        """;

    private Query() {
    }
}
