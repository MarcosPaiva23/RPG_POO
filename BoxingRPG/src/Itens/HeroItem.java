package Itens;

import java.util.ArrayList;


public abstract class HeroItem {
    protected String name;
    protected int price;
    protected ArrayList<String> allowedBoxers;

    /**
     * Método construtor para inicializar um item de herói com nome, preço e lista de tipos de boxers que podem utilizá-lo.
     *
     * @param name Nome do item.
     * @param price Preço do item em dinheiro do jogo.
     */
    public HeroItem(String name, int price) {
        this.name = name;
        this.price = price;
        this.allowedBoxers = new ArrayList<>();
    }

    public void addAllowedBoxer(String boxerType) {
        allowedBoxers.add(boxerType);
    }

    /**
     * Obtém o nome do item.
     *
     * @return O nome do item.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o preço do item.
     *
     * @return O preço do item em dinheiro do jogo.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Obtém os <b>boxers</b> permitidos (para aqusição de itens)
     * @return os <b>boxers</b> permitidos
     */
    public ArrayList<String> getAllowedBoxers() {
        return allowedBoxers;
    }

    /**
     * Verifica se um determinado tipo de boxeador pode utilizar este item.
     * Esse método é utilizado no sistema de compras do vendedor.
     *
     * @param boxerType O tipo de boxer a ser verificado.
     * @return true se o tipo de boxer pode utilizar o item, false caso contrário.
     */
    public boolean isAllowedFor(String boxerType) {
        return allowedBoxers.contains(boxerType);
    }

    /**
     * Exibe os detalhes do item, incluindo as suas características e restrições de uso.
     */
    public abstract void showDetails();
}