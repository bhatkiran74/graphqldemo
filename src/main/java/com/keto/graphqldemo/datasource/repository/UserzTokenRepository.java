package com.keto.graphqldemo.datasource.repository;

import com.keto.graphqldemo.datasource.entity.Userz;
import com.keto.graphqldemo.datasource.entity.UserzToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserzTokenRepository extends CrudRepository<UserzToken, UUID> {
}
