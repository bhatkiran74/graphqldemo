package com.keto.graphqldemo.component.problemz;


import com.keto.generated.DgsConstants;
import com.keto.generated.types.Problem;
import com.keto.generated.types.SearchItemFilter;
import com.keto.generated.types.SearchableItem;
import com.keto.generated.types.Solution;
import com.keto.graphqldemo.service.query.ProblemQueryService;
import com.keto.graphqldemo.service.query.SolutionQueryService;
import com.keto.graphqldemo.util.GraphqlMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * GraphQL resolver responsible for handling item search operations.
 * <p>
 * This resolver provides query operations to search for items
 * based on the specified search criteria.
 */
@DgsComponent
public class ItemSearchDataResolver {

    @Autowired
    private ProblemQueryService problemQueryService;

    @Autowired
    private SolutionQueryService solutionQueryService;


    /**
     * Searches for items that match the provided filter criteria.
     *
     * <p>
     * The search is performed across both problems and solutions using the
     * keyword provided in the filter. The results are mapped to GraphQL
     * searchable items, combined into a single list, and sorted by creation
     * date in descending order.
     * </p>
     *
     * @param filter search criteria used to filter the available items
     * @return list of matching searchable items sorted by creation date
     */
    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.ItemSearch
    )
    public List searchItems(
            @InputArgument(
                    name = "filter",
                    collectionType = SearchableItem.class
            )
            SearchItemFilter filter) {

        // Create a common result list to store both problems and solutions.
        var result = new ArrayList<SearchableItem>();

        // Search for problems using the keyword provided in the filter.
        // Map each Problem entity to its GraphQL representation.
        List<Problem> problemList = problemQueryService
                .problemzByKeyword(filter.getKeyword())
                .stream()
                .map(GraphqlMapper::mapToGraphql)
                .toList();

        // Add the matching problems to the common search result list.
        result.addAll(problemList);

        // Search for solutions using the same keyword.
        // Map each Solution entity to its GraphQL representation.
        List<Solution> solutionList = solutionQueryService
                .problemzByKeyword(filter.getKeyword())
                .stream()
                .map(GraphqlMapper::mapToGraphql)
                .toList();

        // Add the matching solutions to the common search result list.
        result.addAll(solutionList);

        // Sort all results by creation date in descending order,
        // so the newest items appear first.
        result.sort(
                Comparator.comparing(SearchableItem::getCreatedDate)
                        .reversed()
        );

        // Return the combined and sorted search results.
        return result;
    }

}