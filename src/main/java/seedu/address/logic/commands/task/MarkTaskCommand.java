package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;
import seedu.address.model.task.Task;

/**
 * Marks a task in the tasklist as completed.
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "markT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks as complete the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an MarkTaskCommand object.
     * @param index of the task in the filtered task list to edit.
     * @param editTaskDescriptor details to edit the task with.
     */
    public MarkTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(index.getZeroBased());
        Task markedTask = createEditedTask(taskToMark, editTaskDescriptor);

        if (!taskToMark.isSameTask(markedTask) && model.hasTask(markedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToMark, markedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());
        Boolean updatedIsDone = editTaskDescriptor.getIsDone().orElse(taskToEdit.getStatus());
        Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());
        // Id cannot be updated
        Id id = taskToEdit.getId();

        return new Task(updatedDescription, updatedDeadline, updatedIsDone, updatedTags, id);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkTaskCommand)) {
            return false;
        }

        // state check
        MarkTaskCommand m = (MarkTaskCommand) other;
        return index.equals(m.index)
                && editTaskDescriptor.equals(m.editTaskDescriptor);
    }
}
