package com.gojek.manager;

import com.gojek.entities.commands.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by akharbanda on 12/25/16.
 */
public class ParkingLotManagerTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ParkingLotManager parkingLotManager ;
    private int size = 3;
    private static String LEAVE_STRING = "Slot number %d is free\n";
    private static String PARK_STRING = "Allocated slot number: %d\n" ;
    private static String LOT_FULL_STRING = "Sorry, parking lot is full\n";
    private static String STATUS_HEADER_STRING = "Slot No.\tRegistration No \tColour\n";
    private static String NOT_FOUND_STRING = "Not found\n";
    private static String CREATE_PARKING_LOT_STRING = "Created a parking lot with %d slots\n";

    @BeforeClass
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterClass
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @BeforeMethod
    public void setUp()
    {
        parkingLotManager = new ParkingLotManager(size);
    }

    @Test
    public void testParkCommmand()
    {
        Command command = new ParkCommand("dl-abc","white");
        parkingLotManager.executeCommand(command);
        Assert.assertEquals(outContent.toString(), String.format(PARK_STRING, 1));
        outContent.reset();

        parkingLotManager.executeCommand(command);
        Assert.assertEquals(outContent.toString(),String.format(PARK_STRING,2));
        outContent.reset();

        parkingLotManager.executeCommand(command);
        Assert.assertEquals(outContent.toString(),String.format(PARK_STRING,3));
        outContent.reset();

        parkingLotManager.executeCommand(command);
        Assert.assertEquals(outContent.toString(),LOT_FULL_STRING);
        outContent.reset();
    }

    @Test
    public void testLeaveCommmand()
    {
        Command leaveCommand = new LeaveCommand(1);
        Command parkCommand = new ParkCommand("dl-abc","white");

        parkingLotManager.executeCommand(parkCommand);
        Assert.assertEquals(outContent.toString(),String.format(PARK_STRING,1));
        outContent.reset();

        parkingLotManager.executeCommand(leaveCommand);
        Assert.assertEquals(outContent.toString(),String.format(LEAVE_STRING,1));
        outContent.reset();
    }

    @Test
    public void testCreateParkingLotCommmand()
    {
        Command parkingLotCommand = new CreateParkingLotCommand(3);

        parkingLotManager = new ParkingLotManager();
        parkingLotManager.executeCommand(parkingLotCommand);
        Assert.assertEquals(outContent.toString(),String.format(CREATE_PARKING_LOT_STRING,3));
        outContent.reset();
    }

    @Test
    public void testRegNumsWithColourCommand()
    {
        Command regNumWithColourCommand = new RegNumberForCarWithColourCommand("white");
        parkCarsForTesting();

        parkingLotManager.executeCommand(regNumWithColourCommand);
        Assert.assertEquals(outContent.toString(), "dl-abc, dl-def\n");
        outContent.reset();
    }

    @Test
    public void testSlotNumsWithColourCommand()
    {
        Command slotNumberWithColourCommand = new SlotNumberWithColourCommand("white");
        parkCarsForTesting();

        parkingLotManager.executeCommand(slotNumberWithColourCommand);
        Assert.assertEquals(outContent.toString(), "1, 2\n");
        outContent.reset();
    }

    @Test
    public void testSlotNumsWithRegNumCommand()
    {
        Command slotNumberForRegNumberCommand = new SlotNumberForRegNumberCommand("dl-abc");
        parkCarsForTesting();

        parkingLotManager.executeCommand(slotNumberForRegNumberCommand);
        Assert.assertEquals(outContent.toString(), "1\n");
        outContent.reset();
    }

    @Test
    public void testSlotNumsWithRegNumCommandNotFoundCase()
    {
        Command slotNumberForRegNumberCommand = new SlotNumberForRegNumberCommand("dl-abcd");
        parkCarsForTesting();

        parkingLotManager.executeCommand(slotNumberForRegNumberCommand);
        Assert.assertEquals(outContent.toString(),NOT_FOUND_STRING);
        outContent.reset();
    }

    @Test
    public void testStatusCommand()
    {
        Command statusCommand = new StatusCommand();
        parkCarsForTesting();

        parkingLotManager.executeCommand(statusCommand);
        Assert.assertEquals(outContent.toString(), "Slot No.\tRegistration No \tColour\n1\tdl-abc\twhite\n2\tdl-def\twhite\n3\tdl-ghi\tblack\n");
        outContent.reset();
    }

    private void parkCarsForTesting()
    {
        Command command = new ParkCommand("dl-abc","white");
        parkingLotManager.executeCommand(command);
        Assert.assertEquals(outContent.toString(),String.format(PARK_STRING,1));
        outContent.reset();

        command = new ParkCommand("dl-def","white");
        parkingLotManager.executeCommand(command);
        Assert.assertEquals(outContent.toString(),String.format(PARK_STRING,2));
        outContent.reset();

        command = new ParkCommand("dl-ghi","black");
        parkingLotManager.executeCommand(command);
        Assert.assertEquals(outContent.toString(),String.format(PARK_STRING,3));
        outContent.reset();
    }

}
