package com.keto.graphqldemo.component.problemz;


import com.keto.generated.DgsConstants;
import com.keto.generated.types.SearchItemFilter;
import com.keto.generated.types.SearchableItem;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

/**
 * GraphQL resolver responsible for handling item search operations.
 *
 * This resolver provides query operations to search for items
 * based on the specified search criteria.
 */
@DgsComponent
public class ItemSearchDataResolver {

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

        // TODO: Search for items based on the provided filter criteria.
        return null;
    }

}