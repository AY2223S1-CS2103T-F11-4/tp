package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindTCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTCommand object
 */
public class FindTCommandParser implements Parser<FindTCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTCommand
     * and returns a FindTCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_STATUS);

        if (arePrefixesEmpty(argMultimap, PREFIX_DESCRIPTION, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTCommand.MESSAGE_USAGE));
        }

        List<Description> descriptions = getDescriptions(argMultimap);
        List<Boolean> statuses = getStatuses(argMultimap);

        return new FindTCommand(new TaskContainsKeywordsPredicate(descriptions, statuses));
    }

    private List<Description> getDescriptions(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseDescriptions(argMultimap.getValue(PREFIX_DESCRIPTION).get());
    }

    private List<Boolean> getStatuses(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getValue(PREFIX_STATUS).isEmpty()) {
            return new ArrayList<>();
        }
        return ParserUtil.parseStatuses(argMultimap.getValue(PREFIX_STATUS).get());
    }

    /**
     * Returns true if all the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
