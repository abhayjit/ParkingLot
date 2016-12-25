package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public enum CommandType
{
    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REG_NUM_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
    SLOT_NUM_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
    SLOT_NUM_WITH_REG("slot_number_for_registration_number");

    String commandString;

    CommandType(String commandString)
    {
        this.commandString = commandString;
    }

    public static CommandType fromString(String commandString)
    {
        for(CommandType commandType : CommandType.values())
        {
            if(commandString.equalsIgnoreCase(commandType.commandString))
                return commandType;
        }

        return null;
    }
}
