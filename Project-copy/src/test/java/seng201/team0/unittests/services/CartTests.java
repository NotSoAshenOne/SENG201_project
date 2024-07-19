package seng201.team0.unittests.services;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import static org.junit.jupiter.api.Assertions.*;
import static seng201.team0.models.RESOURCES.FIRE;

public class CartTests {
    @Test
    public void testParameterisedConstructor() {
        Cart customCart = new Cart(10, FIRE, 15);
        assertEquals(10, customCart.size);
        assertEquals(FIRE, customCart.resourceType);
        assertEquals(15, customCart.speed);
    }
}
