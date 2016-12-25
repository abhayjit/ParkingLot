package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class Command
{
   CommandType commandType;

    public Command(CommandType commandType)
    {
        this.commandType = commandType;
    }

   public CommandType getCommandType()
   {
       return commandType;
   }

}
