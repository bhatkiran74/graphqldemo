package com.keto.graphqldemo.service.query;


import com.keto.graphqldemo.datasource.entity.Solutionz;
import com.keto.graphqldemo.datasource.repository.SolutionzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SolutionQueryService {

    // Repository used to interact with the Solutionz database entity.
    @Autowired
    private SolutionzRepository solutionzRepository;

    /**
     * Searches for solutions based on the provided keyword.
     *
     * The keyword is wrapped with '%' characters so that the database
     * can perform a partial/fuzzy search and find the keyword anywhere
     * within the searchable fields.
     *
     * @param keyword keyword used to search for solutions
     * @return list of solutions matching the provided keyword
     */
    public List<Solutionz> problemzByKeyword(String keyword) {
        return solutionzRepository.findByKeyword("%" + keyword + "%");
    }
}
