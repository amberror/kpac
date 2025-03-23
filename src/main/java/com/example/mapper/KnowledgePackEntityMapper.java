package com.example.mapper;

import com.example.entity.KnowledgePackEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class KnowledgePackEntityMapper implements RowMapper<KnowledgePackEntity> {

    @Override
    public KnowledgePackEntity mapRow(ResultSet result, int rowNum) throws SQLException {
        return KnowledgePackEntity.builder()
                .id(result.getLong("id"))
                .title(result.getString("title"))
                .description(result.getString("description"))
                .creationDate(result.getString("creation_date"))
                .build();
    }

}
