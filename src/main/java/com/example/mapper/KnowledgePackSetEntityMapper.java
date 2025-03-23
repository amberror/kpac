package com.example.mapper;

import com.example.entity.KnowledgePackSetEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class KnowledgePackSetEntityMapper implements RowMapper<KnowledgePackSetEntity> {

    @Override
    public KnowledgePackSetEntity mapRow(ResultSet result, int rowNum) throws SQLException {
        return KnowledgePackSetEntity.builder()
                .id(result.getLong("id"))
                .title(result.getString("title"))
                .build();
    }

}
