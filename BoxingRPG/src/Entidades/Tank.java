package Entidades;

import Cores.ConsoleColors;
import Itens.CombatConsumable;
import Itens.Consumable;
import Itens.TrainingTapes;

import java.util.Scanner;

public class Tank extends Hero {
    public Tank(String name, int maxHP, int power, int defense, int stamina) {
        super(name, maxHP, power, defense, stamina);
    }

    /**
     * Método <b>ATACAR</b> da classe <b>Tank</b>
     * @param adversario <b>Adversário</b> que irá sofrer o ataque.
     * @param choice Escolha no <b>menu de ataques</b> (durante a luta).
     * @param specialAttackAvailable Boolean que determina se o <b>Ataque Especial</b> está disponível (1x por luta).
     * <b>Stamina</b> usada e <b>Damage</b> infligido diferem entre os tipos de boxer.
     */
    @Override
    public void attack(NPC adversario, int choice, boolean specialAttackAvailable) {
        switch(choice) {
            case 1:
                if (this.stamina < 25) {
                    System.out.println("Too tired to attack!");
                    return;
                }
                int damage;
                if (this.weapon != null) {
                    damage = (this.power / 5) + this.weapon.getAttack();
                } else {
                    damage = this.power / 5;
                }

                adversario.setHp(adversario.getHp() - damage);
                this.stamina -= 25;
                System.out.println("You land a steady blow for " + damage + " damage!");
                break;

            case 2:
                if (!specialAttackAvailable) {
                    System.out.println("Special attack already used!");
                    return;
                }
                if (this.stamina < 40) {
                    System.out.println("Not enough stamina for special attack!");
                    return;
                }
                int specialDamage;
                if (this.weapon != null) {
                    specialDamage = ((this.power / 5) * 4/3) + this.weapon.getSpecialAttack();
                } else {
                    specialDamage = (this.power / 5) * 4/3;
                }
                adversario.setHp(adversario.getHp() - specialDamage);
                this.stamina -= 40;
                System.out.println(ConsoleColors.BLUE_BOLD + "You deliver a powerful blow for " + specialDamage + " damage!" + ConsoleColors.RESET);
                break;

            case 3:
                if (this.inventory.isEmpty()) {
                    System.out.println("No special moves available!");
                    return;
                }

                System.out.println("\nChoose a special move:");
                for (int i = 0; i < this.inventory.size(); i++) {
                    System.out.println((i+1) + ". " + this.inventory.get(i).getName());
                }
                System.out.println((this.inventory.size() + 1) + ". Cancel");

                Scanner scanner = new Scanner(System.in);
                int moveChoice;
                do {
                    System.out.print("Enter your choice: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a number.");
                        scanner.next();
                    }
                    moveChoice = scanner.nextInt();
                } while (moveChoice < 1 || moveChoice > this.inventory.size() + 1);

                if (moveChoice == this.inventory.size() + 1) {
                    System.out.println("Cancelled special move.");
                    return;
                }

                Consumable selectedConsumable = this.inventory.get(moveChoice - 1);
                if (selectedConsumable instanceof CombatConsumable) {
                    CombatConsumable selectedMove = (CombatConsumable) selectedConsumable;
                    int consumableDamage = selectedMove.getInstantAttack();
                    adversario.setHp(adversario.getHp() - consumableDamage);
                    System.out.println("You used " + selectedMove.getName() + " for " + consumableDamage + " damage!");
                    this.inventory.remove(moveChoice - 1);
                } else if (selectedConsumable instanceof TrainingTapes) {
                    System.out.println("Training tapes can't be used during combat!");
                    return;
                }
                break;
        }
    }
}