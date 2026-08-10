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
     * @param filter Search criteria used to filter the available items.
     * @return List of matching searchable items.
     */
    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItems(
            @InputArgument(name = "filter", collectionType = SearchableItem.class)
            SearchItemFilter filter) {

        var result = new ArrayList<SearchableItem>();

        List<Problem> problemList = problemQueryService.problemzByKeyword(filter.getKeyword())
                .stream().map(GraphqlMapper::mapToGraphql).toList();
        result.addAll(problemList);
        List<Solution> solutionList = solutionQueryService.problemzByKeyword(filter.getKeyword())
                .stream().map(GraphqlMapper::mapToGraphql).toList();

        result.addAll(solutionList);

        result.sort(Comparator.comparing(SearchableItem::getCreatedDate).reversed());
        return result;
    }

}