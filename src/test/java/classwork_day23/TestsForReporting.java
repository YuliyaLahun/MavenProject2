package classwork_day23;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({MyListener.class})
public class TestsForReporting {

    @Test (description = "147411")
    public void firstTestToPass(){
        Assert.assertTrue(1==1);
    }

    @Test (description = "147413")
    public void secondTestToPass(){
        Assert.assertTrue(1==1);
    }

    @Test (description = "147414")
    public void firstTestToFail(){
        Assert.assertTrue(1==0);
    }

    @Test (description = "147417")
    public void thirdTestToPass(){
        Assert.assertTrue(1==1);
    }

}
