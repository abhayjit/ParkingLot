package com.gojek.manager;

/**
 * Created by akharbanda on 12/25/16.
 */
public class MinHeapBasedSlotManager implements SlotManager
{
    int noOfSlots ;
    int remainingSlots;
    int[] availableSlots;

    public MinHeapBasedSlotManager(int size)
    {
        this.noOfSlots = size;
        this.remainingSlots = this.noOfSlots;
        availableSlots = new int[this.noOfSlots];
        for(int i=1 ; i<=this.noOfSlots ;i++)
            availableSlots[i-1]=i;
    }

    // Releases an available slot so that ParkingManager can use it
    @Override
    public int releaseSlot()
    {
        if(remainingSlots==0)
            return -1;
        int slot = availableSlots[0];
        availableSlots[0] = availableSlots[remainingSlots-1];
        remainingSlots--;
        heapifyTopDown(0);
        return slot;
    }

    // Collects an inuse slot from ParkingManager and makes it available again
    @Override
    public void collectSlot(int slot)
    {
        if(slot <= noOfSlots && slot>0)
        {
            availableSlots[remainingSlots] = slot;
            for(int i= remainingSlots/2-1 ; i >=0 ; i--)
            {
                heapifyTopDown(i);
            }
            remainingSlots++;
        }
    }

    private void heapifyTopDown(int i)
    {
        int par = i;
        int leftC = i*2+1;
        int rightC = i*2+2;

        if((leftC<remainingSlots &&  availableSlots[par] > availableSlots[leftC]) || ( rightC<remainingSlots && availableSlots[par] > availableSlots[rightC]))
        {
            int minW =-1 ;
            if(leftC>=remainingSlots)
                minW = rightC;
            else if(rightC>=remainingSlots)
                minW = leftC;

            else
                minW = availableSlots[leftC] < availableSlots[rightC] ? leftC : rightC;

            int temp = availableSlots[par];
            availableSlots[par] = availableSlots[minW];
            availableSlots[minW] = temp;

            heapifyTopDown(minW);
        }
    }
}
