package com.gojek.entities.commands;

/**
 * Created by akharbanda on 12/25/16.
 */
public class SlotNumberForRegNumberCommand extends Command
{
    String regNum ;

    public SlotNumberForRegNumberCommand(String regNum)
    {
        super(CommandType.SLOT_NUM_WITH_REG);
        this.regNum = regNum;
    }

    public String getRegNum()
    {
        return this.regNum;
    }
}
