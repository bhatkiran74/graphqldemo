package com.keto.graphqldemo.datasource.repository;

import com.keto.graphqldemo.datasource.entity.Problemz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProblemzRepository extends CrudRepository<Problemz, UUID> {
}
