/**
 * WildBattle is a sublcass of battle where the player were battle
 * against only ONE enemy Pokezon.
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
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
