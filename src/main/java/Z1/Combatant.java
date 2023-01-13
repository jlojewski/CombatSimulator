package Z1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.toList;


public class Combatant {

    int strength;
    int toughness;
    int initialToughness;
    UUID combatantId;
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

    public UUID getCombatantId() {
        return combatantId;
    }

    public void setCombatantId(UUID combatantId) {
        this.combatantId = combatantId;
    }

    @JsonCreator
    public Combatant(@JsonProperty("name") String name, @JsonProperty("strength") int strength, @JsonProperty("toughness") int toughness, @JsonProperty("combatantId") UUID combatantId) {
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

    public int getInitialToughness() {
        return initialToughness;
    }

    public void setInitialToughness(int initialToughness) {
        this.initialToughness = initialToughness;
    }

    public void promoteCombatant(int boostToStrength, int boostToToughness) {
        strength = getStrength() + boostToStrength;
        toughness = getInitialToughness() + boostToToughness;
        String relayedMessage = getName() + " grows stronger through victory (Strength + " + boostToStrength + ", Toughness + " + boostToToughness + ")!";
        IOManager.getInstance().relayString(relayedMessage);
    }

}