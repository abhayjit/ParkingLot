package com.gojek.inputreader;

import com.gojek.entities.commands.Command;

import java.io.*;

/**
 * Created by akharbanda on 12/25/16.
 */
public class FileInputReader extends AbstractInputReader
{
    String filePath ;
    File file ;
    FileReader fileReader;
    BufferedReader bufferedReader;

    public FileInputReader(String filePath)
    {
        this.filePath = filePath;
        initializeReader();

    }

    private void initializeReader()
    {
        try
        {
            file = new File(filePath);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        }

        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getStackTrace());
        }
    }

    @Override
    public Command getNextCommand()
    {
        String str = null;
        Command command = null;
        try
        {
            str  = bufferedReader.readLine();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getStackTrace());
        }
        if(str!=null)
            command = parseCommandString(str);
        return command;
    }

}