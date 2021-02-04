package Java.Testcases;

import Java.Factories.CoffeeControllerFactory;
import org.junit.Assert;
import org.junit.Test;

public class CoffeeControllerFactoryTest {

    @Test
    public void CoffeeControllerFactorySimple(){
        var cmcFactory = new CoffeeControllerFactory();
        var machine = cmcFactory.getCmc("simple");
        Assert.assertTrue(machine.getType().equals("simple"));
    }

    @Test
    public void CoffeeControllerFactoryAdvanced(){
        var cmcFactory = new CoffeeControllerFactory();
        var machine = cmcFactory.getCmc("advanced");
        Assert.assertTrue(machine.getType().equals("advanced"));
    }

    @Test
    public void CoffeeControllerFactoryProgrammable(){
        var cmcFactory = new CoffeeControllerFactory();
        var machine = cmcFactory.getCmc("programmable");
        Assert.assertTrue(machine.getType().equals("programmable"));
    }
}
