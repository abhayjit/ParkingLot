package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class SlotNumberWithColourCommand extends Command
{
    public String colour;

    public SlotNumberWithColourCommand(String colour)
    {
        super(CommandType.SLOT_NUM_WITH_COLOUR);
        this.colour = colour;
    }

    public String getColour()
    {
        return colour;
    }
}
