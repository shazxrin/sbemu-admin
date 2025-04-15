package io.github.shazxrin.sbemuadmin.repository;

import io.github.shazxrin.sbemuadmin.model.Entity;
import io.github.shazxrin.sbemuadmin.model.EntityType;
import io.github.shazxrin.sbemuadmin.model.Message;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class QueryMapper {
    public static Entity mapToEntity(ResultSet resultSet) throws SQLException {
        return new Entity(
            resultSet.getString("EntityGroupId"),
            resultSet.getLong("EntityId"),
            EntityType.fromValue(resultSet.getInt("Type")),
            resultSet.getString("Name"),
            resultSet.getLong("MessageCount"),
            resultSet.getString("CreatedDateTime")
        );
    }

    public static Message mapToMessage(ResultSet resultSet) throws SQLException {
        boolean hasExpireDateTime = true;
        try {
            hasExpireDateTime = resultSet.findColumn("ExpireDateTime") >= 0;
        } catch (SQLException e) {
            hasExpireDateTime = false;
        }

        return new Message(
            resultSet.getLong("SequenceNumber"),
            new String(resultSet.getBytes("Headers"), StandardCharsets.UTF_8),
            new String(resultSet.getBytes("Body"), StandardCharsets.UTF_8),
            hasExpireDateTime ? resultSet.getTimestamp("ExpireDateTime").toLocalDateTime() : null,
            resultSet.getTimestamp("CreatedDateTime").toLocalDateTime()
        );
    }
}
