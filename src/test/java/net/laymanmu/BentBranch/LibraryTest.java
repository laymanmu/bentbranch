package net.laymanmu.BentBranch;

import org.junit.Test;
import org.junit.Assert;

public class LibraryTest {

    @Test
    public void testGetMob() {
        var library = new Library();
        var mob = library.getMob();
        Assert.assertNotNull(mob);
    }
}
