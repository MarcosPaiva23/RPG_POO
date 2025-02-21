package Itens;

import java.util.ArrayList;

public class TrainingTapes extends Consumable {
    protected int hpHealed;
    protected int powerIncreased;
    protected int defenseIncreased;
    protected int staminaIncreased;



    /**
     * Método para construir as <b>TrainingTapes (aka Poções)</b>.
     * @param name Nome da <b>TrainingTape</b>
     * @param price Preço da <b>TrainingTape</b>
     * @param hpHealed Pontos de vida "curados" pela <b>TrainingTape</b>
     * @param powerIncreased Pontos de força adicionados pela <b>TrainingTape</b>
     * @param defenseIncreased Pontos de defesa adicionados pela <b>TrainingTape</b>
     * @param staminaIncreased Pontos de resistência física adicionados pela <b>TrainingTape</b>
     */
    public TrainingTapes(String name, int price, int hpHealed, int powerIncreased, int defenseIncreased, int staminaIncreased) {
        super(name, price);
        this.hpHealed = hpHealed;
        this.powerIncreased = powerIncreased;
        this.defenseIncreased = defenseIncreased;
        this.staminaIncreased = staminaIncreased;
    }

    public int getHpHealed() {
        return hpHealed;
    }

    public int getPowerIncreased() {
        return powerIncreased;
    }

    public int getDefenseIncreased() {
        return defenseIncreased;
    }

    public int getStaminaIncreased() {
        return staminaIncreased;
    }

    @Override
    public void showDetails() {
        System.out.println("Training Tape: " + name);
        System.out.println("Price: $" + price);
        if (hpHealed > 0) System.out.println("Heals: " + hpHealed + " HP");
        if (powerIncreased > 0) System.out.println("Increases Power by: " + powerIncreased);
        if (defenseIncreased > 0) System.out.println("Increases Defense by: " + defenseIncreased);
        if (staminaIncreased > 0) System.out.println("Increases Stamina by: " + staminaIncreased);
        System.out.println("Allowed Boxers: " + String.join(", ", allowedBoxers));
    }
}