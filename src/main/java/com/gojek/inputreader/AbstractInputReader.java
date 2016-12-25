package com.gojek.inputreader;

import com.gojek.entities.commands.Command;
import com.gojek.entities.commands.CommandFactory;

/**
 * Created by akharbanda on 12/25/16.
 */
public abstract class AbstractInputReader
{
    public abstract Command getNextCommand();

    public Command parseCommandString(String commandString)
    {
        String[] commandEntities = commandString.split(" ");

        Command command = CommandFactory.createCommand(commandEntities);
        return command;
    }

}