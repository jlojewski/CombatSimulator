package Z1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.toList;


public class Combatant {

    int strength;
    int toughness;
    int combatantId;
    String name;
    boolean isDead = false;

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCombatantId() {
        return combatantId;
    }

    public void setCombatantId(int combatantId) {
        this.combatantId = combatantId;
    }

    public Combatant(String name, int strength, int toughness, int combatantId) {
        this.name = name;
        this.strength = strength;
        this.toughness = toughness;
        this.combatantId = combatantId;
    }

    public int getStrength() {
        return strength;
    }

    public int getToughness() {
        return toughness;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }


}