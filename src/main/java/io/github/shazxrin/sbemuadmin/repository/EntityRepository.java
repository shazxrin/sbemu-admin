package io.github.shazxrin.sbemuadmin.repository;

import io.github.shazxrin.sbemuadmin.model.Entity;
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
            (resultSet, _) -> QueryMapper.mapToEntity(resultSet)
        );
    }

    public boolean existsByEntityGroupIdAndEntityId(String entityGroupId, long entityId) {
        List<Integer> count = jdbcTemplate.query(
            Query.ENTITY_COUNT_QUERY,
            (resultSet, _) -> resultSet.getInt("Count"),
            entityGroupId, entityId
        );

        if (count.isEmpty()) {
            return false;
        }

        return count.getFirst() > 0;
    }
}
