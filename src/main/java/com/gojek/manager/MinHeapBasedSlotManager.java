package com.gojek.manager;

/**
 * Created by akharbanda on 12/25/16.
 */
public class MinHeapBasedSlotManager implements SlotManager
{
    public MinHeapBasedSlotManager(int size)
    {};

    @Override
    public int releaseSlot()
    {
        return -1;
    }

    @Override
    public void collectSlot(int slot)
    {}

}
