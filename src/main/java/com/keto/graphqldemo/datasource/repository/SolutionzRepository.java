package com.keto.graphqldemo.datasource.repository;

import com.keto.graphqldemo.datasource.entity.Solutionz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SolutionzRepository extends CrudRepository<Solutionz, UUID> {

    @Query(value = "Select * from solutionz where upper(content) like upper(:keyword)", nativeQuery = true)
    List<Solutionz> findByKeyword(@Param("keyword") String keyword);
}
