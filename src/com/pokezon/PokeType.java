/**
 * PokeType are the possible elemental types of a Pokezon and a Move.
 *
 * FIRE > GRASS
 * WATER > FIRE
 * GRASS > WATER
 *
 * Normal is neutral.
 *
 * If PokeType1 > PokeType2 it is considered to be "super effective"
 *
 * @author Jeffrey J. Haywood, Justin Lamb, Marcelo Nazario
 * @version 0.9
 * @since 2020-06-19
 */
package com.pokezon;

public enum PokeType {
    GRASS, WATER, FIRE, NORMAL
}
