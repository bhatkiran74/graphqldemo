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

    // Repository used to perform database operations related to problems.
    @Autowired
    private ProblemzRepository problemzRepository;

    /**
     * Retrieves the latest problems from the database.
     *
     * <p>
     * Problems are sorted by their creation timestamp in descending order,
     * so the most recently created problems are returned first.
     * </p>
     *
     * @return list of problems sorted from newest to oldest
     */
    public List<Problemz> problemzLatestList() {
        return problemzRepository.findAllByOrderByCreationTimestampDesc();
    }

    /**
     * Finds a problem by its unique identifier.
     *
     * @param problemId unique identifier of the problem
     * @return an Optional containing the problem if found, otherwise empty
     */
    public Optional<Problemz> findByProblemId(UUID problemId) {
        return problemzRepository.findById(problemId);
    }

    /**
     * Searches for problems matching the provided keyword.
     *
     * <p>
     * The keyword is wrapped with '%' characters to support partial
     * matching in the repository query.
     * </p>
     *
     * @param keyword keyword used to search for problems
     * @return list of problems matching the provided keyword
     */
    public List<Problemz> problemzByKeyword(String keyword) {
        return problemzRepository.findByKeyword("%" + keyword + "%");
    }

}
