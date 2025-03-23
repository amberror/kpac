package com.example.dao.impl;

import com.example.dao.KnowledgePackSetDao;
import com.example.entity.KnowledgePackEntity;
import com.example.entity.KnowledgePackSetEntity;
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
public class KnowledgePackSetDaoImpl implements KnowledgePackSetDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<KnowledgePackSetEntity> mapper;
    private final RowMapper<KnowledgePackEntity> mapperKPac;

    private static final String SQL_FIND_KPAC_SET = "SELECT * FROM kpac_set WHERE id = ?";
    private static final String SQL_GET_KPAC_SET = "SELECT * FROM kpac_set";
    private static final String SQL_UPDATE_KPAC_SET = "UPDATE kpac_set SET title = ? WHERE id = ?";
    private static final String SQL_INSERT_KPAC_SET = "INSERT INTO kpac_set (title) VALUES(?)";
    private static final String SQL_DELETE_KPAC_SET = "DELETE FROM kpac_set WHERE id = ?";
    private static final String SQL_INSERT_KPAC_SET_KPAC_RELATION = "INSERT INTO kpac_set_kpac (kpac_set_id, kpac_id) VALUES (?, ?)";
    private static final String SQL_DELETE_KPAC_SET_KPAC_RELATION_BY_PK = "DELETE FROM kpac_set_kpac WHERE kpac_set_id = ? AND kpac_id = ?";
    private static final String SQL_DELETE_KPAC_SET_KPAC_BY_ID = "DELETE FROM kpac_set_kpac WHERE kpac_id = ?";
    private static final String SQL_DELETE_KPAC_SET_KPAC_SET_BY_ID = "DELETE FROM kpac_set_kpac WHERE kpac_set_id = ?";
    private static final String SQL_GET_ALL_ATTACHED_KPACS = "SELECT * FROM kpac AS k JOIN kpac_set_kpac AS ksk ON k.id = ksk.kpac_id WHERE ksk.kpac_set_id = ?";

    @Autowired
    public KnowledgePackSetDaoImpl(JdbcTemplate jdbcTemplate,
                                   RowMapper<KnowledgePackSetEntity> mapper,
                                   RowMapper<KnowledgePackEntity> mapperKPac) {
        this.jdbcTemplate =  jdbcTemplate;
        this.mapper = mapper;
        this.mapperKPac = mapperKPac;
    }

    @Override
    public KnowledgePackSetEntity create(KnowledgePackSetEntity entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_KPAC_SET, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            return ps;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());

        return entity;
    }

    @Override
    public boolean delete(KnowledgePackSetEntity entity) {
        return jdbcTemplate.update(SQL_DELETE_KPAC_SET, entity.getId()) > 0;
    }

    @Override
    public boolean update(KnowledgePackSetEntity entity) {
        return jdbcTemplate.update(SQL_UPDATE_KPAC_SET, entity.getTitle(), entity.getId()) > 0;
    }

    @Override
    public KnowledgePackSetEntity getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_KPAC_SET, new Object[] { id }, mapper);
    }

    @Override
    public List<KnowledgePackSetEntity> getAll() {
        return jdbcTemplate.query(SQL_GET_KPAC_SET, mapper);
    }

    @Override
    public boolean addPackToSet(Long setId, Long packId) {
        return jdbcTemplate.update(SQL_INSERT_KPAC_SET_KPAC_RELATION, setId, packId) > 0;
    }

    @Override
    public boolean removePackFromSet(Long setId, Long packId) {
        return jdbcTemplate.update(SQL_DELETE_KPAC_SET_KPAC_RELATION_BY_PK, setId, packId) > 0;
    }

    @Override
    public boolean removeSetFromRelation(Long setId) {
        return jdbcTemplate.update(SQL_DELETE_KPAC_SET_KPAC_SET_BY_ID, setId) > 0;
    }

    @Override
    public boolean removePackFromRelation(Long packId) {
        return jdbcTemplate.update(SQL_DELETE_KPAC_SET_KPAC_BY_ID, packId) > 0;
    }

    @Override
    public List<KnowledgePackEntity> getKPacsAttachedToSet(KnowledgePackSetEntity entity) {
        return jdbcTemplate.query(SQL_GET_ALL_ATTACHED_KPACS, new Object[] { entity.getId() }, mapperKPac);
    }
}
