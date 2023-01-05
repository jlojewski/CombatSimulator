package Z1;


import java.util.ArrayList;

public class CombatSettings {

    public int rounds;
    public int hitChance;
    public int critChance;
    public ArrayList<Combatant> combatants;
    public ArrayList<Combatant> optionalChampionCombatants;


    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getHitChance() {
        return hitChance;
    }

    public void setHitChance(int hitChance) {
        this.hitChance = hitChance;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }


}
