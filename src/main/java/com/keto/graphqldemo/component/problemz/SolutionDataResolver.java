package com.keto.graphqldemo.component.problemz;


import com.keto.generated.DgsConstants;
import com.keto.generated.types.*;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;

/**
 * GraphQL resolver responsible for handling solution-related
 * mutations and subscriptions.
 * <p>
 * This resolver provides operations to:
 * - Create a new solution.
 * - Vote on an existing solution.
 * - Subscribe to solution vote updates.
 */
@DgsComponent
public class SolutionDataResolver {

    /**
     * Creates a new solution for a problem.
     *
     * @param authToken           Authentication token of the logged-in user.
     * @param solutionCreateInput Details of the solution to be created.
     * @return Response containing the result of the solution creation.
     */
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionCreate)
    public SolutionResponse createSolution(
            @RequestHeader(name = "authtoken") String authToken,
            @InputArgument(name = "newSolution") SolutionCreateInput solutionCreateInput) {
        return null;
    }

    /**
     * Records a user's vote for a solution.
     *
     * @param authToken         Authentication token of the logged-in user.
     * @param solutionVoteInput Vote details submitted by the user.
     * @return Response containing the updated vote information.
     */
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.SolutionVote)
    public SolutionResponse createSolutionVote(@RequestHeader(name = "authToken") String authToken,
                                               @InputArgument(name = "newVote") SolutionVoteInput solutionVoteInput) {

        return null;
    }

    /**
     * Subscribes to vote updates for a specific solution.
     *
     * @param solutionId Unique identifier of the solution.
     * @return Reactive stream of solution updates whenever vote counts change.
     */
    @DgsData(parentType = DgsConstants.SUBSCRIPTION_TYPE, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    public Flux<Solution> subscribeSolutionVote(@InputArgument(name = "solutionId") String solutionId) {
        return null;
    }

}

