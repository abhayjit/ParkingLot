package com.gojek;

import com.gojek.entities.commands.Command;
import com.gojek.inputreader.AbstractInputReader;
import com.gojek.inputreader.FileInputReader;
import com.gojek.inputreader.InteractiveInputReader;
import com.gojek.manager.ParkingLotManager;

/**
 * Created by akharbanda on 12/25/16.
 */
public class Main
{
    public static void main(String[] args) {

        AbstractInputReader inputReader ;
        if(args.length==0)
            inputReader = new InteractiveInputReader();
        else
        {
            inputReader = new FileInputReader(args[0]);
        }

        ParkingLotManager parkingLotManager = new ParkingLotManager();

        while(true)
        {
            Command command = inputReader.getNextCommand();
            if(command!=null)
                parkingLotManager.executeCommand(command);
        }
    }
}
