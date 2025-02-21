package Itens;

import Entidades.NPC;
import java.util.ArrayList;
import Enums.SpecialEffectType;


public class CombatConsumable extends Consumable {
    private int instantAttack;
    private SpecialEffectType effectType;
    private String specialEffect;

    /**
     * Método para construir os <b>Consumíveis de Combate</b>
     * @param name Nome do <b>consumível</b>
     * @param price Preço do <b>consumível</b>
     * @param instantAttack Valor de <b>Ataque Instantâneo</b> para consumíves de ataque.
     * @param effectType Vem do Enum <b>SpecialEffectType</b> que determina o tipo de efeito de consumíveis (ex: consumíveis que aumentam força, roubam vida, ou dependem da força do adversário).
     * @param specialEffect Apresentado quando um <b>consumível</b> tem um efeito especial, dos determinados acima.
     */
    public CombatConsumable(String name, int price, int instantAttack, SpecialEffectType effectType, String specialEffect) {
        super(name, price);
        this.instantAttack = instantAttack;
        this.effectType = effectType;
        this.specialEffect = specialEffect;
    }

    @Override
    public void showDetails() {
        System.out.println("Special Move: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Base Damage: " + instantAttack);
        if (specialEffect != null) {
            System.out.println("Special Effect: " + specialEffect);
        }
        System.out.println("Allowed Boxers: " + String.join(", ", allowedBoxers));
    }

    public int getInstantAttack() {
        return instantAttack;
    }

    public SpecialEffectType getEffectType() {
        return effectType;
    }
}
