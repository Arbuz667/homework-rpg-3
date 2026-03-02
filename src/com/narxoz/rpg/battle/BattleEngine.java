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
        this.random = new Random(1L);
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        EncounterResult result = new EncounterResult();
        int rounds = 0;

        List<Combatant> a = new ArrayList<>(teamA);
        List<Combatant> b = new ArrayList<>(teamB);

        while (!a.isEmpty() && !b.isEmpty()) {
            rounds++;
            result.addLog("--- Round " + rounds + " ---");

            executeTurn(a, b, result);
            if (b.isEmpty()) break;

            executeTurn(b, a, result);
        }

        result.setRounds(rounds);
        result.setWinner(a.isEmpty() ? "Team B (Enemies)" : "Team A (Heroes)");
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
