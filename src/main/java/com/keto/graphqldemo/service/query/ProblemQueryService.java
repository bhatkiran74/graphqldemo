package com.keto.graphqldemo.service.query;

import com.keto.graphqldemo.datasource.entity.Problemz;
import com.keto.graphqldemo.datasource.repository.ProblemzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemQueryService {


    @Autowired
    private ProblemzRepository problemzRepository;

    public List<Problemz> problemzLatestList(){
        return problemzRepository.findAllByOrderByCreationTimestampDesc();
    }


    public Optional<Problemz> findByProblemId(UUID problemId) {
        return problemzRepository.findById(problemId);
    }

    public List<Problemz> problemzByKeyword(String keyword){
        return problemzRepository.findByKeyword("%" + keyword + "%");
    }

}
