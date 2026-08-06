package com.keto.graphqldemo.datasource.repository;

import com.keto.graphqldemo.datasource.entity.Solutionz;
import com.keto.graphqldemo.datasource.entity.Userz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionzRepository extends CrudRepository<Solutionz, UUID> {
}
