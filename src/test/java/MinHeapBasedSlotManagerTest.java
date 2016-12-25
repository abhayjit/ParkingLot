import com.gojek.manager.MinHeapBasedSlotManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by akharbanda on 12/25/16.
 */
public class MinHeapBasedSlotManagerTest
{
  private MinHeapBasedSlotManager minHeapBasedSlotManager;
    int size = 3;

    @BeforeTest
    public void beforeTest()
    {
        minHeapBasedSlotManager = new MinHeapBasedSlotManager(size);
    }


    private void releaseAllSlots()
    {
        int slot1 = minHeapBasedSlotManager.releaseSlot();
        int slot2 = minHeapBasedSlotManager.releaseSlot();
        int slot3 = minHeapBasedSlotManager.releaseSlot();

        Assert.assertEquals(slot1,1);
        Assert.assertEquals(slot2,2);
        Assert.assertEquals(slot3,3);

    }

    @Test
    public void testReleaseSlotsInOrder()
    {
        releaseAllSlots();
    }

    @Test
    public void testReleaseUnavailableSlot()
    {
        releaseAllSlots();
        int slot4 = minHeapBasedSlotManager.releaseSlot();
        Assert.assertEquals(slot4,-1);
    }

    @Test
    public void testReleaseCollectedSlot()
    {
        releaseAllSlots();
        minHeapBasedSlotManager.collectSlot(2);
        int slot4 = minHeapBasedSlotManager.releaseSlot();
        Assert.assertEquals(slot4,2);

    }

    @Test
    public void testMinSlotReleasedAfterMultipleCollections()
    {
        releaseAllSlots();
        minHeapBasedSlotManager.collectSlot(2);
        minHeapBasedSlotManager.collectSlot(3);
        int slot4 = minHeapBasedSlotManager.releaseSlot();
        int slot5 = minHeapBasedSlotManager.releaseSlot();

        Assert.assertEquals(slot4,2);
        Assert.assertEquals(slot5,3);
    }
    
}
