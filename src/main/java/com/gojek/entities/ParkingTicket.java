package com.gojek.entities;

/**
 * Created by akharbanda on 12/25/16.
 */
public class ParkingTicket
{
    String regNumber;
    String carColour;
    int slot;


    public ParkingTicket(String regNumber, String carColour, int slot)
    {
        this.regNumber = regNumber;
        this.carColour = carColour;
        this.slot = slot;
    }

    public String getRegNumber()
    {
        return regNumber;
    }

    public void setRegNumber(String regNumber)
    {
        this.regNumber = regNumber;
    }

    public String getCarColour()
    {
        return carColour;
    }

    public void setCarColour(String carColour)
    {
        this.carColour = carColour;
    }

    public int getSlot()
    {
        return slot;
    }

    public void setSlot(int slot)
    {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object ticket)
    {
        ParkingTicket other = (ParkingTicket)ticket;
        if(other.regNumber.equalsIgnoreCase(this.regNumber))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return 31*this.regNumber.hashCode();
    }
}

