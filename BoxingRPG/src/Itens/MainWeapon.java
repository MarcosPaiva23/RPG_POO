package Itens;

import java.util.ArrayList;

public class MainWeapon extends HeroItem {
    int attack;
    int specialAttack;

    /**
     * Método para construir uma <b>arma/luva</b>
     * @param name Nome da <b>arma/luva</b>
     * @param price Preço da <b>arma/luva</b>
     * @param attack Valor de ataque da <b>arma/luva</b>
     * @param specialAttack Valor de ataque especial da <b>arma/luva</b>
     */
    public MainWeapon(String name, int price, int attack, int specialAttack) {
        super(name, price);
        this.attack = attack;
        this.specialAttack = specialAttack;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    @Override
    public void showDetails() {
        System.out.println("Gloves: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Attack: " + attack);
        System.out.println("Special Attack: " + specialAttack);
        System.out.println("Allowed Boxers: " + String.join(", ", allowedBoxers));
    }
}