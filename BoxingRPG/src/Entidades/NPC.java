package Entidades;

import Cores.ConsoleColors;

public class NPC extends Entity {
    protected int money;

    /**
     * Método para construir os <b>NPCs</b>
     * @param name nome do
     * @param maxHP Pontos de vida máximos do <b>NPC</b>
     * @param power Pontos de força do <b>NPC</b>
     * @param defense Pontos de defesa do <b>NPC</b>
     * @param stamina Pontos de resistência física do <b>NPC</b>
     * @param money Não está a ser usado nesta versão do jogo, uma vez que o "payout" por luta é fixo, não vem do adversário
     */
    public NPC(String name, int maxHP, int power, int defense, int stamina, int money) {
        super(name, maxHP, power, defense, stamina);
        this.money = money;
    }

    public void attack(Hero hero) {
        if (this.stamina < 25) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + this.name + " is too tired to attack!" + ConsoleColors.RESET);
            return;
        }

        int damage = this.power - (hero.getDefense() / 3);
        if (damage < 5) {
            damage = 5;
        }

        hero.setHp(hero.getHp() - damage);
        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + this.name + " hits you for " + damage + " damage!" + ConsoleColors.RESET);
        this.stamina -= 25;
    }

    @Override
    public void showDetails() {
        System.out.println("Fighter: " + name);
        System.out.println("HP: " + hp + "/" + maxHP);
        System.out.println("Power: " + power);
        System.out.println("Defense: " + defense);
        System.out.println("Stamina: " + stamina + "/" + maxStamina);
    }
}