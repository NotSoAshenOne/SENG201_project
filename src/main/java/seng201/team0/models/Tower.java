package seng201.team0.models;

/**
 * Represents a Tower in the game with attributes resource amount, reload speed,
 * resource type, level, and cost.
 * A Tower can be in different states: inCurrent, inReserve, or inShop.
 */
public class Tower {

    // ---------- INITIALISATION ---------- //
    /** The amount of resources the tower currently has to fill the carts with. */
    int resourceAmount;

    /** The reload speed of the tower in resources per second. */
    int reloadSpeed;

    /** The type of resource the tower stores. */
    public RESOURCES resourceType;

    /** The level of the tower. */
    int level;

    /** The cost of the tower, either to purchase or sell back to the shop. */
    int cost;

    /** Indicates if the tower is currently active */
    boolean inCurrent;

    /** Indicates if the tower is available in the shop. */
    boolean inShop;

    /** Indicates if the tower is in the reserves. */
    boolean inReserve;

    /** String representation of the tower's level. */
    String levelString;

    /** String representation of the tower's reload speed */
    String reloadString;

    /** String representation of the tower's resource amount */
    String resourceAmountString;

    /** String representation of the tower's resource type */
    String resourceTypeString;

    // ---------- CONSTRUCTORS ---------- //

    /**
     * Default constructor that initialises the tower with default values:
     * Level 1 Water tower with 0 resources, reload speed of 5, cost of 10
     * State is set to inShop to start
     */
    public Tower(){
        // Sets default values of towers
        resourceAmount = 0;
        reloadSpeed = 5;
        resourceType = RESOURCES.WATER;
        level = 1;
        cost = 10;
        inCurrent = false;
        inReserve = false;
        inShop = true;
    }

    /**
     * Constructs a tower with specified values.
     * @param tempResAmount The amount of resources.
     * @param tempRelSpeed The reload speed.
     * @param tempResType The type of resource.
     * @param tempLevel The level of the tower.
     * @param tempCost The cost of the tower.
     * @param tempCurrent Indicates if the tower is currently in use.
     * @param tempReserve Indicates if the tower is in reserve.
     * @param tempShop Indicates if the tower is in the shop.
     */
    public Tower(int tempResAmount, int tempRelSpeed, RESOURCES tempResType, int tempLevel, int tempCost, boolean tempCurrent, boolean tempReserve, boolean tempShop){
        // Sets values of attributes
        resourceAmount = tempResAmount;
        reloadSpeed = tempRelSpeed;
        resourceType = tempResType;
        level = tempLevel;
        cost = tempCost;
        inCurrent = tempCurrent;
        inShop = tempShop;
        inReserve = tempReserve;
    }

    // ---------- FUNCTIONAL METHODS ---------- //
    /**
     * Levels up the tower, increasing its level, reload speed, resource amount, and cost.
     */
    public void levelUp(){
        this.level += 1;
        this.reloadSpeed += 3;
        this.resourceAmount += 5;
        this.cost += 5;
    }

    /**
     * Puts the tower in the current state, setting inCurrent to true and others false
     */
    public void putInCurrent(){
        this.inCurrent = true;
        this.inReserve = false;
        this.inShop = false;
    }

    /**
     * Puts the tower in reserve. If the tower is being purchased from the shop, it deducts the cost from coins.
     */
    public void putInReserve(){
        if (this.inShop){
            Inventory.setCoins(-(this.cost));
        }
        this.inCurrent = false;
        this.inReserve = true;
        this.inShop = false;
    }

    /**
     *  Puts the tower back in the shop, resets its attributes to default values,and adds the cost to the coins if sold.
     */
    public void putInShop(boolean sale){
        if (sale){
            Inventory.setCoins((this.cost)); //adds coins from sale
        }
        this.resourceAmount = 5;
        this.reloadSpeed = 5;
        this.level = 1;
        this.cost = 10;
        this.inCurrent = false;
        this.inReserve = false;
        this.inShop = true;
    }

    // ----------  GETTERS  ---------- //
    /**
     * Gets the reload speed of the tower.
     * @return The reload speed.
     */
    public int getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Gets the level of the tower
     * @return The level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the cost of the tower.
     * @return The cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the amount of resources the tower has
     * @return The resource amount.
     */
    public int getResourceAmount() {
        return resourceAmount;
    }

    /**
     * Gets an array of string representations of the tower's attributes.
     * @return An array containing resource amount, reload speed, level, and resource type as strings.
     */
    public String[] getAttributes() {
        return new String[]{String.valueOf(getResourceAmount()), String.valueOf(getReloadSpeed()), String.valueOf(getLevel()), resourceTypeString};
    }

    /**
     * Checks if the tower is currently in use.
     * @return True if the tower is active, false otherwise
     */
    public boolean isInCurrent() {
        return inCurrent;
    }

    /**
     * Checks if the tower is in reserve
     * @return True if the tower is reserved, false otherwise.
     */
    public boolean isInReserve() {
        return inReserve;
    }

    /**
     * Checks if the tower is in the shop
     * @return True if the tower is in the shop, false otherwise.
     */
    public boolean isInShop() {
        return inShop;
    }

    // ----------  SETTERS  ---------- //

    /**
     * Sets the reload speed of the tower.
     * @param reloadSpeed The new reload speed.
     */
    public void setReloadSpeed(int reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
        reloadString = String.valueOf(this.reloadSpeed);
    }

    /**
     * Sets the level of the tower.
     * @param level The new level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    // MORGAN: the 4 setters below have no usages, can we remove them?? i'm too scared to bc i didnt make them
    /**
     * Sets the inCurrent state of the tower.
     * @param inCurrent The new inCurrent state.
     */
    public void setInCurrent(boolean inCurrent) {
        this.inCurrent = inCurrent;
    }

    /**
     * Sets the inShop state of the tower
     * @param inShop The new inShop state.
     */
    public void setInShop(boolean inShop) {
        this.inShop = inShop;
    }
    public void setInReserve(boolean inReserve) {
        this.inReserve = inReserve;
    }
    public void setResourceTypeString(String type){
        this.resourceTypeString = type;
    }

    /**
     * Sets the resource amount of the tower.
     * @param tempResourceAmount The amount to add to the current resource amount.
     */
    public void setResourceAmount(int tempResourceAmount) {
        this.resourceAmount += tempResourceAmount;
    }
}
