package Entidades;

import Cores.ConsoleColors;
import Itens.Consumable;
import Itens.HeroItem;
import Itens.MainWeapon;
import Itens.CombatConsumable;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Hero extends Entity {
    protected int level;
    protected int reputation;
    protected int money;
    protected MainWeapon weapon;
    protected ArrayList<Consumable> inventory;
    protected String careerPath;
    protected Scanner scanner = new Scanner(System.in);


    /**
     * Método para construir o <b>herói</b>
     * @param name Nome do <b>herói</b>
     * @param maxHP Pontos de vida/saúde máximos do <b>herói</b>
     * @param power Pontos de força do <b>herói</b>
     * @param defense Pontos de defesa do <b>herói</b>
     * @param stamina Pontos de resistência física do <b>herói</b>
     */

    public Hero(String name, int maxHP, int power, int defense, int stamina) {
        super(name, maxHP, power, defense, stamina);
        this.level = 1;
        this.reputation = 0;
        this.money = 0;
        this.weapon = null;
        this.inventory = new ArrayList<>();
        this.careerPath = "rookie";
    }

    /**
     * Método que lida com os <b>consumíveis de ataque</b>, para todos os tipos de boxer
     * @param adversario Adversário que vai ser atacado
     * @param moveChoice Número do consumível escolhido, dentro do inventário
     */
    protected void useConsumable(NPC adversario, int moveChoice) {
        if (moveChoice <= 0 || moveChoice > inventory.size()) {
            System.out.println("Invalid consumable choice!");
            return;
        }

        CombatConsumable consumable = (CombatConsumable) inventory.get(moveChoice - 1);
        int baseDamage = consumable.getInstantAttack();
        int totalDamage = baseDamage;

        switch(consumable.getEffectType()) {
            case ENEMY_POWER_BASED:
                totalDamage += (adversario.getPower() / 2);
                break;
            case DEFENSE_BREAK:
                adversario.setDefense((int)(adversario.getDefense() * 0.8));
                System.out.println(adversario.getName() + "'s defense is reduced!");
                break;
            case STAMINA_DRAIN:
                adversario.setStamina(Math.max(0, adversario.getStamina() - 30));
                System.out.println(ConsoleColors.GREEN_BOLD + "Drained 30 stamina from " + adversario.getName() + "!" + ConsoleColors.RESET);
                break;
            case POWER_BOOST:
                this.power = (int)(this.power * 1.2);
                System.out.println(ConsoleColors.GREEN_BOLD + "Your power is increased by 20% for the next turn!" + ConsoleColors.RESET);
                break;
            case HP_STEAL:
                int healing = totalDamage / 2;
                this.hp = Math.min(this.maxHP, this.hp + healing);
                System.out.println(ConsoleColors.GREEN_BOLD + "You heal for " + healing + " HP!" + ConsoleColors.RESET);
                break;
        }

        adversario.setHp(adversario.getHp() - totalDamage);
        System.out.println(ConsoleColors.BLUE_BOLD + consumable.getName() + " for " + totalDamage + " damage!" + ConsoleColors.RESET);

        inventory.remove(moveChoice - 1);
    }

    public abstract void attack(NPC adversario, int choice, boolean specialAttackAvailable);

    public void showDetails() {
        System.out.println("Boxer: " + name);
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp + "/" + maxHP);
        System.out.println("Power: " + power);
        System.out.println("Defense: " + defense);
        System.out.println("Stamina: " + stamina + "/" + maxStamina);
        System.out.println("Money: $" + money);
        System.out.println("Current Weapon: " + (weapon != null ? weapon.getName() : "None"));
        System.out.println("\nInventory:");
        if (inventory.isEmpty()) {
            System.out.println("Empty");
        } else {
            for (Consumable item : inventory) {
                System.out.println("- " + item.getName());
            }
        }
    }


    public int getLevel() {
        return level;
    }

    public int getReputation() {
        return reputation;
    }

    public int getMoney() {
        return money;
    }

    public MainWeapon getWeapon() {
        return weapon;
    }

    public ArrayList<Consumable> getInventory() {
        return inventory;
    }

    public String getCareerPath() {
        return careerPath;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setWeapon(MainWeapon weapon) {
        this.weapon = weapon;
    }

    public void setCareerPath(String careerPath) {
        this.careerPath = careerPath;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }



    public void equipWeapon(MainWeapon weapon) {
        this.weapon = weapon;
    }

    public void addToInventory(Consumable item) {
        inventory.add(item);
    }

    public void spendMoney(int amount) {
        this.money -= amount;
    }
}