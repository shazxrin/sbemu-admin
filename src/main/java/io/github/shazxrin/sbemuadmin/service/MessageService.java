package io.github.shazxrin.sbemuadmin.service;

import io.github.shazxrin.sbemuadmin.exception.NotFoundException;
import io.github.shazxrin.sbemuadmin.model.Message;
import io.github.shazxrin.sbemuadmin.repository.MessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final EntityService entityService;
    private final MessageRepository messageRepository;

    public List<Message> getMessagesInQueue(String entityGroupId, long entityId, long startSequenceNumber, long endSequenceNumber) {
        if (entityGroupId == null || entityGroupId.isEmpty()) {
            throw new IllegalArgumentException("Entity group ID cannot be null or empty.");
        }

        if (startSequenceNumber < 0 || endSequenceNumber < 0) {
            throw new IllegalArgumentException("Sequence numbers cannot be negative.");
        }

        if (!entityService.existsByEntityGroupIdAndEntityId(entityGroupId, entityId)) {
            throw new NotFoundException("Queue not created or has been deleted.");
        }

        return messageRepository.findMessagesInQueue(entityGroupId, entityId, startSequenceNumber, endSequenceNumber);
    }

    public List<Message> getMessagesInTopic(String entityGroupId, long entityId, long startSequenceNumber, long endSequenceNumber) {
        if (entityGroupId == null || entityGroupId.isEmpty()) {
            throw new IllegalArgumentException("Entity group ID cannot be null or empty.");
        }

        if (startSequenceNumber < 0 || endSequenceNumber < 0) {
            throw new IllegalArgumentException("Sequence numbers cannot be negative.");
        }

        if (!entityService.existsByEntityGroupIdAndEntityId(entityGroupId, entityId)) {
            throw new NotFoundException("Topic not created or has been deleted.");
        }

        return messageRepository.findMessagesInTopic(entityGroupId, entityId, startSequenceNumber, endSequenceNumber);
    }

    public List<Message> getMessagesInSubscription(String entityGroupId, long entityId, long startSequenceNumber, long endSequenceNumber) {
        if (entityGroupId == null || entityGroupId.isEmpty()) {
            throw new IllegalArgumentException("Entity group ID cannot be null or empty.");
        }

        if (startSequenceNumber < 0 || endSequenceNumber < 0) {
            throw new IllegalArgumentException("Sequence numbers cannot be negative.");
        }

        if (!entityService.existsByEntityGroupIdAndEntityId(entityGroupId, entityId)) {
            throw new NotFoundException("Subscription not created or has been deleted.");
        }

        return messageRepository.findMessagesInSubscription(entityGroupId, entityId, startSequenceNumber, endSequenceNumber);
    }
}
