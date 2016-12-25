package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class CommandFactory
{
    public static Command createCommand(String[] commandEntities)
    {
        CommandType commandType = CommandType.fromString(commandEntities[0]);
        Command command = null;

        if(commandType!=null)
        {
            switch (commandType)
            {
                case CREATE_PARKING_LOT:
                    command = new CreateParkingLotCommand(Integer.parseInt(commandEntities[1]));
                    break;

                case LEAVE:
                    command = new LeaveCommand(Integer.parseInt(commandEntities[1]));
                    break;

                case PARK:
                    command = new ParkCommand(commandEntities[1],commandEntities[2]);
                    break;

                case REG_NUM_WITH_COLOUR:
                    command = new RegNumberForCarWithColourCommand(commandEntities[1]);
                    break;

                case SLOT_NUM_WITH_COLOUR:
                    command = new SlotNumberWithColourCommand(commandEntities[1]);
                    break;

                case SLOT_NUM_WITH_REG:
                   command = new SlotNumberForRegNumberCommand(commandEntities[1]);
                   break;

                case STATUS:
                    command = new StatusCommand();
                    break;

           }
        }

        else
            System.out.println("Unrecognized command");

        return command;
    }
}
