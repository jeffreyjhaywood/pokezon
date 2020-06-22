package com.pokezon;

public class Move {
    public static String name;
    public static String type;
    public static int BaseAttack;
    public static int Mana;
    public static int CurrentMana;



    public static String getName() {
        return name;
    }

    public static String getType() {
        return type;
    }

    public static int getBaseAttack() {
        return BaseAttack;
    }

    public static int getMana() {
        return Mana;
    }

    public static int getCurrentMana() {
        return CurrentMana;
    }

       private void (String name, String Type,int Mana, int BaseAttack, int CurrentMana){
            Move.name = name;
            Move.Mana = Mana;
            Move.BaseAttack = BaseAttack;
            Move.CurrentMana = CurrentMana;

            System.out.println("This is:" + name);
            System.out.println(name + "is a:" + type);
            System.out.println("Attacking with:" + BaseAttack);
       }


}




