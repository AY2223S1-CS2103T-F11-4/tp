package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Tasks}'s {@code Description} and/or {@code Status}
 * matches any of the keywords given.
 */
public class TaskContainsKeywordsPredicate implements Predicate<Task> {
    private final List<Description> descriptionKeywords;
    private final List<Boolean> statusKeywords;


    /**
     * Constructs an {@code PersonContainsKeywordsPredicate}.
     *
     * @param descriptionKeywords A list containing keywords for {@code Name}.
     * @param statusKeywords A list containing keywords for {@code Phone}.
     */
    public TaskContainsKeywordsPredicate(List<Description> descriptionKeywords,
                                         List<Boolean> statusKeywords) {
        this.descriptionKeywords = descriptionKeywords;
        this.statusKeywords = statusKeywords;
    }

    @Override
    public boolean test(Task task) {
        return (descriptionKeywords.isEmpty() || descriptionKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(Boolean.toString(task.getTaskStatus()), keyword.toString())))
                && (statusKeywords.isEmpty() || statusKeywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(task.getDescription().toString(), keyword.toString())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskContainsKeywordsPredicate)) {
            return false;
        }

        TaskContainsKeywordsPredicate castedOther = (TaskContainsKeywordsPredicate) other;

        return descriptionKeywords.equals(castedOther.descriptionKeywords)
                && statusKeywords.equals(castedOther.statusKeywords);
    }
}
