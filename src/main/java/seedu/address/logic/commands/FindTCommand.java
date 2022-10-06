package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTCommand extends Command {

    public static final String COMMAND_WORD = "findT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION_KEYWORDS "
            + PREFIX_STATUS + "STATUS_KEYWORDS " + "\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Homework "
            + PREFIX_STATUS + "TRUE";

    private final TaskContainsKeywordsPredicate predicate;

    public FindTCommand(TaskContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTCommand // instanceof handles nulls
                && predicate.equals(((FindTCommand) other).predicate)); // state check
    }
}
