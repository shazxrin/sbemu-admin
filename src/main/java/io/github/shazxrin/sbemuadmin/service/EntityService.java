package io.github.shazxrin.sbemuadmin.service;

import io.github.shazxrin.sbemuadmin.model.Entity;
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

    public List<Entity> getAllEntities() {
        return entityRepository.findAllEntities();
    }
}
