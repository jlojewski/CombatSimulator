package Z1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Initiative {

    public static void rollInitiative(ArrayList combatants) {
        Collections.shuffle(combatants);
    }
}