package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class ParkCommand extends Command
{
    String regNumber ;
    String colour;

    public ParkCommand(String regNum , String colour)
    {
        super(CommandType.PARK);
        this.regNumber = regNum;
        this.colour = colour;
    }

    public String getRegNumber()
    {
        return regNumber;
    }

    public String getColour()
    {
        return colour;
    }

}
