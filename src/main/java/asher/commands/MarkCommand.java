package asher.commands;

import asher.BotException;
import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * Represents a command to mark a command.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a Mark Command object with the given input string.
     *
     * @param input The input string for the Mark Command.
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Executes the Mark Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for Mark Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            int taskNumber = taskList.getTaskNumber(input);
            assert taskNumber > 0 : "Task number should not be less than 1!";

            if (taskNumber != -1) {
                taskList.getTasks().get(taskNumber).markDone();
                return ui.showMarkTaskMessage(taskList.getTasks().get(taskNumber));
            } else {
                throw new BotException("Invalid Task!");
            }
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
