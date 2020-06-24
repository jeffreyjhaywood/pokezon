package com.pokezon;

public class WildBattle extends Battle{
    /* No Constructor to maintain loose coupling?
     * will assign properties using setters
     * If WildBattle instantiated
     */

    // WildBattle Business methods
    @Override
    public void giveXpForWin() {
        setXpForWin(BASE_XP); // Offer less xp
    }

}
