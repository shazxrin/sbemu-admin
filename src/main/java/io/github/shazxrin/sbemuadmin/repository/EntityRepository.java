package io.github.shazxrin.sbemuadmin.repository;

import io.github.shazxrin.sbemuadmin.model.Entity;
import io.github.shazxrin.sbemuadmin.model.EntityInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EntityRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Entity> findAllEntities() {
        return jdbcTemplate.query(
            Query.ENTITY_LIST_ALL_QUERY,
            (resultSet, rowNo) -> QueryMapper.mapToEntity(resultSet)
        );
    }

    public EntityInfo findEntityInfo(String entityGroupId, long entityId) {
        List<EntityInfo> entityInfoList = jdbcTemplate.query(
            Query.ENTITY_INFO_QUERY,
            (resultSet, rowNo) -> QueryMapper.mapToEntityInfo(resultSet),
            entityGroupId, entityId
        );

        if (entityInfoList.isEmpty()) {
            return null;
        }

        return entityInfoList.getFirst();
    }

    public boolean existsByEntityGroupIdAndEntityId(String entityGroupId, long entityId) {
        List<Integer> count = jdbcTemplate.query(
            Query.ENTITY_COUNT_QUERY,
            (resultSet, rowNo) -> resultSet.getInt("Count"),
            entityGroupId, entityId
        );

        if (count.isEmpty()) {
            return false;
        }

        return count.getFirst() > 0;
    }
}
