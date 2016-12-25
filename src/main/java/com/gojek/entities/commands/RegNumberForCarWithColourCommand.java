package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class RegNumberForCarWithColourCommand extends Command
{
    String colour;

    public RegNumberForCarWithColourCommand(String colour)
    {
        super(CommandType.REG_NUM_WITH_COLOUR);
        this.colour = colour;
    }

    public String getColour()
    {
        return colour;
    }
}
