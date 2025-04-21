package io.github.shazxrin.sbemuadmin.repository;

import io.github.shazxrin.sbemuadmin.model.Message;
import static io.github.shazxrin.sbemuadmin.repository.QueryMapper.mapToMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MessageRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Message> findMessagesInQueue(
        String entityGroupId,
        long entityId,
        long startSequenceNumber,
        long endSequenceNumber
    ) {
        return jdbcTemplate.query(
            Query.MESSAGE_LIST_QUEUE_QUERY,
            (resultSet, rowNo) -> mapToMessage(resultSet),
            entityGroupId, entityId, startSequenceNumber, endSequenceNumber
        );
    }

    public List<Message> findMessagesInTopic(
        String entityGroupId,
        long entityId,
        long startSequenceNumber,
        long endSequenceNumber
    ) {
        return jdbcTemplate.query(
            Query.MESSAGE_LIST_TOPIC_QUERY,
            (resultSet, rowNo) -> mapToMessage(resultSet),
            entityGroupId, entityId, startSequenceNumber, endSequenceNumber
        );
    }

    public List<Message> findMessagesInSubscription(
        String entityGroupId,
        long entityId,
        long startSequenceNumber,
        long endSequenceNumber
    ) {
        return jdbcTemplate.query(
            Query.MESSAGE_LIST_SUBSCRIPTION_QUERY,
            (resultSet, rowNo) -> mapToMessage(resultSet),
            entityGroupId, entityId, startSequenceNumber, endSequenceNumber
        );
    }
}
