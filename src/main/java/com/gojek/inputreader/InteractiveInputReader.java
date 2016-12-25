package com.gojek.inputreader;

import com.gojek.entities.commands.Command;

import java.io.*;
import java.util.Scanner;

/**
 * Created by akharbanda on 12/25/16.
 */

public class InteractiveInputReader extends AbstractInputReader
{
    Scanner scanner ;

    public InteractiveInputReader()
    {
        scanner = new Scanner(System.in);
    }
    @Override
    public Command getNextCommand()
    {
        String commandString = scanner.nextLine();
        Command command = null;
        if(commandString!=null)
            command = parseCommandString(commandString);
        return command;
    }
}
