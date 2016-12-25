package com.gojek.manager;

/**
 * Created by akharbanda on 12/25/16.
 */
public interface SlotManager
{
    public int releaseSlot();

    public void collectSlot(int slot);
}
