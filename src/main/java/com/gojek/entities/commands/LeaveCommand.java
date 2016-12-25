package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class LeaveCommand extends Command
{
    int slotNumber;

    public LeaveCommand(int slotNumber)
    {
        super(CommandType.LEAVE);
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber()
    {
        return slotNumber;
    }

}
