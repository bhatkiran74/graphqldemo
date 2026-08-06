package com.keto.graphqldemo.util;

import com.keto.generated.types.*;
import com.keto.graphqldemo.datasource.entity.Problemz;
import com.keto.graphqldemo.datasource.entity.Solutionz;
import com.keto.graphqldemo.datasource.entity.Userz;
import com.keto.graphqldemo.datasource.entity.UserzToken;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Utility class responsible for mapping persistence layer entities
 * (Userz, Problemz, Solutionz, UserzToken) into GraphQL DTOs.
 *
 * <p>This class centralizes all entity-to-GraphQL conversions to keep
 * resolver classes clean and maintain a single mapping implementation.</p>
 */
public class GraphqlMapper {

    /**
     * Used to generate human-readable date strings
     * (e.g., "5 minutes ago", "2 days ago").
     */
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    /**
     * Default timezone offset used when converting timestamps
     * from the database to GraphQL OffsetDateTime values.
     */
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(7);

    /**
     * Converts a Userz entity into its corresponding GraphQL User model.
     *
     * @param original User entity retrieved from the database.
     * @return GraphQL User object.
     */
    public static User mapToGraphql(Userz original) {

        // Create GraphQL User object
        var result = new User();

        // Convert creation timestamp to OffsetDateTime
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);

        // Copy user details
        result.setAvatar(original.getAvatar());
        result.setCreatedDate(createDateTime);
        result.setDisplayName(original.getDisplayName());
        result.setEmail(original.getEmail());

        // Convert entity ID to String for GraphQL schema
        result.setId(original.getId().toString());

        result.setUsername(original.getUsername());

        return result;
    }

    /**
     * Converts a Problemz entity into a GraphQL Problem model.
     *
     * @param original Problem entity retrieved from the database.
     * @return GraphQL Problem object.
     */
    public static Problem mapToGraphql(Problemz original) {

        var result = new Problem();

        // Convert creation timestamp
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);

        // Convert problem author
        var author = mapToGraphql(original.getCreatedBy());

        // Convert all associated solutions
        var convertedSolutions = original.getSolutionzs()
                .stream()
                .map(GraphqlMapper::mapToGraphql)
                .collect(Collectors.toList());

        // Convert comma-separated tags into a list
        var tagList = List.of(original.getTags().split(","));

        // Populate GraphQL object
        result.setAuthor(author);
        result.setContent(original.getContent());
        result.setCreatedDate(createDateTime);
        result.setId(original.getId().toString());
        result.setSolutions(convertedSolutions);
        result.setTags(tagList);
        result.setTitle(original.getTitle());

        // Set derived fields
        result.setSolutionCount(convertedSolutions.size());
        result.setPrettyCreatedDate(PRETTY_TIME.format(createDateTime));

        return result;
    }

    /**
     * Converts a Solutionz entity into a GraphQL Solution model.
     *
     * @param original Solution entity retrieved from the database.
     * @return GraphQL Solution object.
     */
    public static Solution mapToGraphql(Solutionz original) {

        var result = new Solution();

        // Convert creation timestamp
        var createDateTime = original.getCreationTimestamp().atOffset(ZONE_OFFSET);

        // Convert solution author
        var author = mapToGraphql(original.getCreatedBy());

        // Map database category string to GraphQL enum
        var category = StringUtils.equalsIgnoreCase(
                original.getCategory(),
                SolutionCategory.EXPLANATION.toString())
                ? SolutionCategory.EXPLANATION
                : SolutionCategory.REFERENCE;

        // Populate solution details
        result.setAuthor(author);
        result.setCategory(category);
        result.setContent(original.getContent());
        result.setCreatedDate(createDateTime);
        result.setId(original.getId().toString());
        result.setVoteGoodCount(original.getVoteGoodCount());
        result.setVoteBadCount(original.getVoteBadCount());

        // Human-readable creation time
        result.setPrettyCreatedDate(PRETTY_TIME.format(createDateTime));

        return result;
    }

    /**
     * Converts a UserzToken entity into a GraphQL UserAuthToken model.
     *
     * @param original User authentication token entity.
     * @return GraphQL UserAuthToken object.
     */
    public static UserAuthToken mapToGraphql(UserzToken original) {

        var result = new UserAuthToken();

        // Convert token expiry timestamp
        var expiryDateTime = original.getExpiryTimestamp().atOffset(ZONE_OFFSET);

        // Populate token information
        result.setExpiryTime(expiryDateTime);
        result.setAuthToken(original.getAuthToken());

        return result;
    }
}
