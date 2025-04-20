package io.github.shazxrin.sbemuadmin.service;

import io.github.shazxrin.sbemuadmin.model.Entity;
import io.github.shazxrin.sbemuadmin.model.EntityInfo;
import io.github.shazxrin.sbemuadmin.repository.EntityRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EntityService {
    private final EntityRepository entityRepository;

    public boolean existsByEntityGroupIdAndEntityId(String entityGroupId, long entityId) {
        return entityRepository.existsByEntityGroupIdAndEntityId(entityGroupId, entityId);
    }

    public List<Entity> getFilteredEntities() {
        return entityRepository.findAllEntities()
            .stream()
            .filter(entity -> {
                boolean isTransferQueue = entity.name().endsWith("$TRANSFER");
                boolean isDefaultQueue = entity.name().startsWith("MICROSOFT.SERVICEBUS.MESSAGECONTAINER");

                return !isTransferQueue && !isDefaultQueue;
            })
            .toList();
    }

    public EntityInfo getEntityInfo(String entityGroupId, long entityId) {
        if (!entityRepository.existsByEntityGroupIdAndEntityId(entityGroupId, entityId)) {
            return null;
        }

        return entityRepository.findEntityInfo(entityGroupId, entityId);
    }
}
