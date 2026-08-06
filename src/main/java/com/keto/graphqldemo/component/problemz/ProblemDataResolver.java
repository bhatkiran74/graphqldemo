package com.keto.graphqldemo.component.problemz;

import com.keto.generated.DgsConstants;
import com.keto.generated.types.Problem;
import com.keto.generated.types.ProblemCreateInput;
import com.keto.generated.types.ProblemResponse;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

import java.util.List;
/**
 * GraphQL resolver responsible for handling problem-related
 * queries and mutations.
 *
 * This resolver provides operations to:
 * - Retrieve the latest problems.
 * - Retrieve problem details.
 * - Create a new problem.
 */
@DgsComponent
public class ProblemDataResolver {


    /**
     * Retrieves the latest list of problems.
     *
     * @return List of recently created problems.
     */
    @DgsData(parentType = DgsConstants.QUERY_TYPE , field = DgsConstants.QUERY.ProblemLatestList)
    public List<Problem> getproblemLatestList(){
        return null;
    }
    /**
     * Retrieves the details of a specific problem.
     *
     * @param problemId Unique identifier of the problem.
     * @return Problem details.
     */
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemDetail)
    public Problem getProbleDetails(@InputArgument(name = "id") String problemId){
        return null;
    }
    /**
     * Creates a new problem.
     *
     * @param authToken Authentication token of the logged-in user.
     * @param problemCreateInput Problem details provided by the user.
     * @return Newly created ProblemResponse.
     */
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ProblemCreate)
    public ProblemResponse createProblem(
            @RequestHeader(name = "authToken", required = true) String authToken,
            @InputArgument(name = "problem")ProblemCreateInput problemCreateInput){
        return null;
    }

    /**
     * Subscribes to newly created problems.
     *
     * This subscription notifies clients in real time whenever
     * a new problem is added to the system.
     *
     * @return A reactive stream of newly created problems.
     */
    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE , field = DgsConstants.SUBSCRIPTION.ProblemAdded)
    public Flux<Problem> subscribeProblemAdded(){
        return null;
    }
}
