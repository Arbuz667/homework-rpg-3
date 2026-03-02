package com.narxoz.rpg.battle;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        // TODO: reset any battle state if you add it
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        // TODO: validate inputs and run round-based battle
        // TODO: use random if you add critical hits or target selection
        EncounterResult result = new EncounterResult();
        result.setWinner("TBD");
        result.setRounds(0);
        result.addLog("TODO: implement battle simulation");
        return result;
    }
    private void executeTurn(List<Combatant> attackers, List<Combatant> defenders, EncounterResult result) {
        for (Combatant attacker : attackers) {
            if (defenders.isEmpty()) break;

            int targetIdx = random.nextInt(defenders.size());
            Combatant target = defenders.get(targetIdx);

            int damage = attacker.getAttackPower();
            target.takeDamage(damage);

            result.addLog(attacker.getName() + " attacks " + target.getName() + " for " + damage + " HP.");

            if (!target.isAlive()) {
                result.addLog(target.getName() + " has fallen!");
                defenders.remove(targetIdx);
            }
        }
}
