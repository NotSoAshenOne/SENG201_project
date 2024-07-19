package seng201.team0.models;

import static seng201.team0.models.RESOURCES.WATER;

/**
 * The {@code Cart} class represents the cart objects in the game that need to be filled.
 * The cart has a size, resource type, and speed.
 */

public class Cart {
    // ---------- INITIALISATION ---------- //
    /** The size of the cart. */
    public int size;
    /** The type of resource the cart needs. */
    public RESOURCES resourceType;
    /**
     * The speed of the cart in meters per second.
     * Each tower is 10 meters apart.
     */
    public int speed;

    // ---------- CONSTRUCTORS ---------- //
    /**
     * Default constructor that initializes the cart with default values:
     * size = 5, resourceType = WATER, speed = 10 m/s.
     */
    public Cart(){
        size = 5;
        resourceType = WATER;
        speed = 10;
    }

    /**
     * Constructor that initializes the cart with specified values.
     * @param tempSize the size of the cart
     * @param resource the type of resource the cart requires
     * @param tempSpeed the speed of the cart in mps
     */
    public Cart(int tempSize, RESOURCES resource, int tempSpeed){
        size = tempSize;
        resourceType = resource;
        speed = tempSpeed;
    }

    // ----------  GETTERS  ---------- //

    /**
     * Gets the size of the cart.
     * @return the cart size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the resource type of the cart.
     * @return the cart resource type.
     */
    public RESOURCES getResourceType(){
        return resourceType;
    }
    // ----------  HELPER FUNCTIONS  ---------- //

    /**
     * Gets a string showing the size of the cart and the resource type of the cart.
     * @return String of the size and resource type.
     */
    public String getCartString(){
        String sizeString = String.valueOf(getSize());
        String resourceString = getResourceType().name();
        return sizeString + " " + resourceString;
    }
}
