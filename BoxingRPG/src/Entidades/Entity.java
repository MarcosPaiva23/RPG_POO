package Entidades;

public abstract class Entity {
    protected String name;
    protected int maxHP;
    protected int hp;
    protected int power;
    protected int defense;
    protected int stamina;
    protected int maxStamina;
    protected final int MAX_STAMINA = 100;

    /**
     * Método construtor de <b>Entidade</b>
     * @param name Nome da <b>Entidade</b>
     * @param maxHP Pontos de vida/saúde máximos da <b>Entidade</b>
     * @param power Pontos de força da <b>Entidade</b>
     * @param defense Pontos de defesa da <b>Entidade</b>
     * @param stamina Pontos de resistência física da <b>Entidade</b>
     */
    public Entity(String name, int maxHP, int power, int defense, int stamina) {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.power = power;
        this.defense = defense;
        this.stamina = MAX_STAMINA;
        this.maxStamina = MAX_STAMINA;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public int getDefense() {
        return defense;
    }

    public int getStamina() {
        return stamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public abstract void showDetails();
}