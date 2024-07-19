package seng201.team0.models;

import java.util.ArrayList;

/**
 * Class for managing game settings and configurations.
 */
public class Settings {
    // ---------- INITIALISATION ---------- //
    private static Settings settings_instance = null;
    public static synchronized Settings getInstance(){
        if (settings_instance == null){
            settings_instance = new Settings();
        }
        return settings_instance;
    }

    private static int coins = 0;
    private static Settings instance;
    static String userName;
    static int numRounds;
    static int difficulty;

    // ---------- CREATING TOWERS ---------- //
    static ArrayList<Tower> towers = new ArrayList<Tower>();
    static Tower waterTower = new Tower(0, 5, RESOURCES.WATER, 1, 10, false, false, true);
    static Tower fireTower = new Tower(0, 5, RESOURCES.FIRE, 1, 12, false, false, true);
    static Tower earthTower = new Tower(0, 5, RESOURCES.EARTH, 1, 15, false, false, true);
    static Tower airTower = new Tower(0, 5, RESOURCES.AIR, 1, 12, false, false, true);
    static Tower eggTower = new Tower(0, 5, RESOURCES.EGGS, 1, 20, false, false, true);

    /** Adds predefined towers to the list of towers. */
    public static void addTowers(){
        towers.add(waterTower);
        waterTower.resourceTypeString = RESOURCES.WATER.name();
        towers.add(fireTower);
        fireTower.resourceTypeString = RESOURCES.FIRE.name();
        towers.add(earthTower);
        earthTower.resourceTypeString = RESOURCES.EARTH.name();
        towers.add(airTower);
        airTower.resourceTypeString = RESOURCES.AIR.name();
        towers.add(eggTower);
        eggTower.resourceTypeString = RESOURCES.EGGS.name();
    }

    // ---------- CONSTRUCTORS ---------- //
    /** Default constructor setting initial game settings. */
    public Settings(){
        String userName = "Player";
        int numRounds = 5;
        int difficulty = 1;
    }

    /**
     * Constructor setting game settings with given parameters.
     * @param tempUser  the username.
     * @param tempRounds the number of rounds.
     * @param tempDiff  the difficulty level.
     */
    public Settings(String tempUser, int tempRounds, int tempDiff){
        String userName = tempUser;
        int numRounds = tempRounds;
        int difficulty = tempDiff;
    }

    // ---------- GETTERS ---------- //

    /**
     * Gets the player's name.
     * @return return the player's name.
     */
    public String getName() {
        return userName;
    }

    /**
     * Gets the list of towers.
     * @return the list of towers.
     */
    public static ArrayList<Tower> getTowers(){
        addTowers(); //adds set Towers to towers ArrayList
        return towers;
    }

    // ---------- SETTER ---------- //
    /**
     * Sets the username after validating it.
     * @param tempName the username to set.
     */
    public static void setName(String tempName){

        String name = tempName;

        int len = name.length();
        String[] special = {"[","@","_","!","#","$","%","^","&","*","(",")","<",">","?","/","|","}","{","~",":","]"};
        boolean valid = true;

        if (len < 3 || len > 15){
            valid = false;
        } else {
            for (String character : special){
                if (name.contains(character)){
                    System.out.println("Name cannot contain special characters");
                    valid = false;
                    break;
                }
            }
        }

        if (valid){
            userName = name;
        }
    }

    /**
     * Sets the current tower based on the selected resource type.
     *
     * @param resource the resource type of the tower to set as current.
     */
    public static void setTowers(RESOURCES resource){
        // displays options tower1, tower2 ... tower5 with stats
        // e.g. when buttons pressed:

        //if waterTower selected:
        if (resource == RESOURCES.WATER) {
            waterTower.setLevel(1);
            waterTower.setReloadSpeed(1);
            waterTower.setResourceAmount(5);
            waterTower.putInCurrent();

        }

        //if fireTower selected:
        if (resource == RESOURCES.FIRE) {
            fireTower.setLevel(1);
            fireTower.setReloadSpeed(1);
            fireTower.setResourceAmount(5);
            fireTower.putInCurrent();

        }

        //if earthTower selected:
        if (resource == RESOURCES.EARTH) {
            earthTower.setLevel(1);
            earthTower.setReloadSpeed(1);
            earthTower.setResourceAmount(5);
            earthTower.putInCurrent();

        }

        //if airTower selected:
        if (resource == RESOURCES.AIR) {
            airTower.setLevel(1);
            airTower.setReloadSpeed(1);
            airTower.setResourceAmount(5);
            airTower.putInCurrent();

        }

        //if eggTower selected:
        if (resource == RESOURCES.EGGS) {
            eggTower.setLevel(1);
            eggTower.setReloadSpeed(1);
            eggTower.setResourceAmount(5);
            eggTower.putInCurrent();

        }
    }
}
