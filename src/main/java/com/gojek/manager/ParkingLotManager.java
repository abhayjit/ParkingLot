package com.gojek.manager;

import com.gojek.entities.ParkingTicket;
import com.gojek.entities.commands.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by akharbanda on 12/25/16.
 */
public class ParkingLotManager
{
    Map<Integer,ParkingTicket> slotNumToTicketMap = new HashMap<Integer, ParkingTicket>();

    Map<String,Set<Integer>> colourToSlotNumMap = new HashMap<String, Set<Integer>>();
    Map<String,Integer> regNumToSlotNumMap = new HashMap<String, Integer>();

    MinHeapBasedSlotManager slotsManager ;
    boolean initialized = false;

    private static String LEAVE_STRING = "Slot number %d is free\n";
    private static String PARK_STRING = "Allocated slot number: %d\n" ;
    private static String LOT_FULL_STRING = "Sorry, parking lot is full";
    private static String STATUS_HEADER_STRING = "Slot No.\tRegistration No \tColour";
    private static String NOT_FOUND_STRING = "Not found";
    private static String CREATE_PARKING_LOT_STRING = "Created a parking lot with %d slots\n";

    public ParkingLotManager(int size)
    {
        slotsManager = new MinHeapBasedSlotManager(size);
        initialized = true;
    }

    public ParkingLotManager()
    {}

    private void initialize(int size)
    {
        if(!initialized)
        {
            slotsManager = new MinHeapBasedSlotManager(size);
            initialized = true;
            System.out.format(CREATE_PARKING_LOT_STRING,size);
        }
    }

    public void executeCommand(Command command)
    {
        try
        {
            if(command!=null && command.getCommandType()!=null)
            {
                switch (command.getCommandType())
                {
                    case CREATE_PARKING_LOT:
                        initialize(((CreateParkingLotCommand)command).getSize());
                        break;

                    case LEAVE:
                        executeLeave((LeaveCommand)command);
                        break;

                    case PARK:
                        executePark((ParkCommand)command);
                        break;

                    case REG_NUM_WITH_COLOUR:
                        executeRegNumWithColour((RegNumberForCarWithColourCommand)command);
                        break;
                    case SLOT_NUM_WITH_COLOUR:
                        executeSlotNumberWithColour((SlotNumberWithColourCommand)command);
                        break;
                    case SLOT_NUM_WITH_REG:
                        executeSlotNumWithRegNum((SlotNumberForRegNumberCommand)command);
                        break;
                    case STATUS:
                        executeStatusCommand((StatusCommand)command);
                        break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void executeLeave(LeaveCommand leaveCommand)
    {
        int slotNumber = leaveCommand.getSlotNumber();
        slotsManager.collectSlot(slotNumber);

        ParkingTicket ticket = slotNumToTicketMap.get(slotNumber);
        if(ticket!=null)
        {
            slotNumToTicketMap.remove(ticket.getSlot());
            regNumToSlotNumMap.remove(ticket.getRegNumber());
            Set<Integer> colourSlotSet = colourToSlotNumMap.get(ticket.getCarColour());
            colourSlotSet.remove(ticket.getSlot());

            System.out.format(LEAVE_STRING, slotNumber);
        }

        else
            System.out.println(NOT_FOUND_STRING);

    }

    private void executePark(ParkCommand parkCommand)
    {
        int slotNumber = slotsManager.releaseSlot();
        if(slotNumber==-1)
            System.out.println(LOT_FULL_STRING);

        else
        {
            ParkingTicket ticket = new ParkingTicket(parkCommand.getRegNumber(),parkCommand.getColour(),slotNumber);

            slotNumToTicketMap.put(slotNumber, ticket);
            regNumToSlotNumMap.put(ticket.getRegNumber(), slotNumber);

            Set<Integer> colourSlotSet = colourToSlotNumMap.get(ticket.getCarColour());
            if(colourSlotSet == null)
            {
                colourSlotSet = new HashSet<Integer>();
                colourSlotSet.add(ticket.getSlot());
                colourToSlotNumMap.put(ticket.getCarColour(), colourSlotSet);
            }
            else
                colourSlotSet.add(ticket.getSlot());

            System.out.format(PARK_STRING,slotNumber);
        }
    }

    private void executeRegNumWithColour(RegNumberForCarWithColourCommand carWithColourCommand)
    {
        Set<Integer> colourSlotSet = colourToSlotNumMap.get(carWithColourCommand.getColour());
        StringBuilder builder = new StringBuilder();
        String output = "";

        if(colourSlotSet!=null && colourSlotSet.size()>0)
        {
            for(Integer slot : colourSlotSet)
            {
                if(slot!=null)
                {
                    ParkingTicket ticket = slotNumToTicketMap.get(slot);
                    if(ticket!=null)
                    {
                        builder.append(ticket.getRegNumber());
                        builder.append(", ");
                    }
                }
            }

            output = builder.toString();
            output = output.substring(0,output.length()-2);

        }

        System.out.println(output);
    }

    private void executeSlotNumberWithColour(SlotNumberWithColourCommand slotNumberWithColourCommand)
    {
        Set<Integer> colourSlotSet = colourToSlotNumMap.get(slotNumberWithColourCommand.getColour());
        StringBuilder builder = new StringBuilder();
        String output = "";

        if(colourSlotSet!=null && colourSlotSet.size()>0)
        {
            for(Integer slot : colourSlotSet)
            {
                if(slot!=null)
                {
                    builder.append(slot);
                    builder.append(", ");
                }
            }

            output = builder.toString();
            output = output.substring(0,output.length()-2);

        }

        System.out.println(output);
    }

    private void executeSlotNumWithRegNum(SlotNumberForRegNumberCommand slotNumberForRegNumberCommand)
    {
        ParkingTicket ticket = slotNumToTicketMap.get(regNumToSlotNumMap.get(slotNumberForRegNumberCommand.getRegNum()));
        if(ticket!=null)
            System.out.println(ticket.getSlot());
        else
            System.out.println(NOT_FOUND_STRING);
    }

    private void executeStatusCommand(StatusCommand statusCommand)
    {
        if(!slotNumToTicketMap.isEmpty())
        {
            System.out.println(STATUS_HEADER_STRING);

            for (Map.Entry<Integer, ParkingTicket> entry : slotNumToTicketMap.entrySet())
            {
                ParkingTicket ticket = entry.getValue();
                System.out.println(entry.getKey()+"\t"+ticket.getRegNumber()+"\t"+ticket.getCarColour());
            }
        }
    }
}



