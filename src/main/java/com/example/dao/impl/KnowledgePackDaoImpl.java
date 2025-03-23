package com.example.dao.impl;

import com.example.dao.KnowledgePackDao;
import com.example.entity.KnowledgePackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class KnowledgePackDaoImpl implements KnowledgePackDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<KnowledgePackEntity> mapper;

    private static final String SQL_FIND_KPAC = "SELECT * FROM kpac where id = ?";
    private static final String SQL_GET_KPAC = "SELECT * FROM kpac";
    private static final String SQL_DELETE_KPAC = "DELETE FROM kpac where id = ?";
    private static final String SQL_UPDATE_KPAC = "UPDATE kpac set title = ?, description = ?, creation_date  = ? where id = ?";
    private static final String SQL_INSERT_KPAC = "INSERT into kpac (title, description, creation_date) values(?,?,?)";

    @Autowired
    public KnowledgePackDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<KnowledgePackEntity> mapper) {
        this.jdbcTemplate =  jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public KnowledgePackEntity getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_KPAC, new Object[] { id }, mapper);
    }

    @Override
    public List<KnowledgePackEntity> getAll() {
        return jdbcTemplate.query(SQL_GET_KPAC, mapper);
    }

    @Override
    public boolean delete(KnowledgePackEntity entity) {
        return jdbcTemplate.update(SQL_DELETE_KPAC, entity.getId()) > 0;
    }

    @Override
    public boolean update(KnowledgePackEntity entity) {
        return jdbcTemplate.update(SQL_UPDATE_KPAC, entity.getTitle(),
                entity.getDescription(), entity.getCreationDate(), entity.getId()) > 0;
    }

    @Override
    public KnowledgePackEntity create(KnowledgePackEntity entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_KPAC, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getCreationDate());
            return ps;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());

        return entity;
    }
}
