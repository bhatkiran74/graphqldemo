package com.keto.graphqldemo.datasource.repository;

import com.keto.graphqldemo.datasource.entity.Problemz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemzRepository extends CrudRepository<Problemz, UUID> {
    List<Problemz> findAllByOrderByCreationTimestampDesc();

    @Query(value = "Select * from problemz where upper(content) like upper(:keyword)" +
            "or upper(title) like upper(:keyword)" +
            "or upper(tags) like upper(:keyword)", nativeQuery = true)
    List<Problemz> findByKeyword(@Param("keyword") String keyword);

}
