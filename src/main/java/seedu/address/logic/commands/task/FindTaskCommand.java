package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in address book whose description and/or deadline
 * contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findT";

    public static final String MESSAGE_EXAMPLE = COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "party "
            + PREFIX_TASK_DEADLINE + "25-12-2022 ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks whose field(s) contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_TASK_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TASK_DEADLINE + "DEADLINE "
            + "Example: "
            + MESSAGE_EXAMPLE;

    private final TaskContainsKeywordsPredicate predicate;

    public FindTaskCommand(TaskContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW,
                        model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
