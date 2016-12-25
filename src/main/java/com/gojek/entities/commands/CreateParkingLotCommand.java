package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class CreateParkingLotCommand extends Command
{
    int size;

    public CreateParkingLotCommand(int size)
    {
        super(CommandType.CREATE_PARKING_LOT);
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }
}
