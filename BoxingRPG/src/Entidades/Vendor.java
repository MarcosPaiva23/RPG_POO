package Entidades;

import Enums.SpecialEffectType;
import Itens.Consumable;
import Itens.HeroItem;
import Itens.MainWeapon;
import Itens.TrainingTapes;
import Itens.CombatConsumable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Vendor {
    private String name;
    private ArrayList<HeroItem> stock;
    private Random random;

    public Vendor(String name) {
        this.name = name;
        this.stock = new ArrayList<>();
        this.random = new Random();
        initializeStock();
    }

    private void initializeStock() {
        // Basic gloves (for all)
        MainWeapon basicGloves = new MainWeapon("\uD83E\uDD4A Basic Pro Gloves ", 50, 10, 15);
        basicGloves.addAllowedBoxer("Brawler");
        basicGloves.addAllowedBoxer("CounterPuncher");
        basicGloves.addAllowedBoxer("Tank");
        stock.add(basicGloves);

        MainWeapon amateurGloves = new MainWeapon("\uD83E\uDD4A Amateur Gloves", 40, 8, 12);
        amateurGloves.addAllowedBoxer("Brawler");
        amateurGloves.addAllowedBoxer("CounterPuncher");
        amateurGloves.addAllowedBoxer("Tank");
        stock.add(amateurGloves);

        MainWeapon gymGloves = new MainWeapon("\uD83E\uDD4A Gym Gloves", 30, 5, 10);
        gymGloves.addAllowedBoxer("Brawler");
        gymGloves.addAllowedBoxer("CounterPuncher");
        gymGloves.addAllowedBoxer("Tank");
        stock.add(gymGloves);

        // Specialized gloves
        MainWeapon brawlerGloves = new MainWeapon("\uD83E\uDD4A Knockout Artist Gloves", 75, 15, 25);
        brawlerGloves.addAllowedBoxer("Brawler");
        stock.add(brawlerGloves);

        MainWeapon counterGloves = new MainWeapon("\uD83E\uDD4A Speed Demon Gloves", 75, 12, 20);
        counterGloves.addAllowedBoxer("CounterPuncher");
        stock.add(counterGloves);

        MainWeapon tankGloves = new MainWeapon("\uD83E\uDD4A Iron Fist Gloves", 75, 13, 22);
        tankGloves.addAllowedBoxer("Tank");
        stock.add(tankGloves);

        // Training tapes (for all)
        TrainingTapes footworkTape = new TrainingTapes("\uD83D\uDCFC Ali's Footwork Masterclass", 60, 0, 0, 0, 15);
        footworkTape.addAllowedBoxer("Brawler");
        footworkTape.addAllowedBoxer("CounterPuncher");
        footworkTape.addAllowedBoxer("Tank");
        stock.add(footworkTape);

        TrainingTapes powerTape = new TrainingTapes("\uD83D\uDCFC Tyson's Power Tutorial", 60, 0, 15, 0, 0);
        powerTape.addAllowedBoxer("Brawler");
        powerTape.addAllowedBoxer("CounterPuncher");
        powerTape.addAllowedBoxer("Tank");
        stock.add(powerTape);

        TrainingTapes defenseTape = new TrainingTapes("\uD83D\uDCFC Mayweather's Defense Tape", 60, 0, 0, 15, 0);
        defenseTape.addAllowedBoxer("Brawler");
        defenseTape.addAllowedBoxer("CounterPuncher");
        defenseTape.addAllowedBoxer("Tank");
        stock.add(defenseTape);

        TrainingTapes recoveryTape = new TrainingTapes("\uD83D\uDCFC Duran's Recovery Techniques", 40, 25, 0, 0, 0);
        recoveryTape.addAllowedBoxer("Brawler");
        recoveryTape.addAllowedBoxer("CounterPuncher");
        recoveryTape.addAllowedBoxer("Tank");
        stock.add(recoveryTape);

        TrainingTapes determinationTape = new TrainingTapes("\uD83D\uDCFCRocky's Determination", 70, 20, 10, 10, 10);
        determinationTape.addAllowedBoxer("Brawler");
        determinationTape.addAllowedBoxer("CounterPuncher");
        determinationTape.addAllowedBoxer("Tank");
        stock.add(determinationTape);

        TrainingTapes heavyHandsTape = new TrainingTapes("\uD83D\uDCFC Foreman's Heavy Hands", 65, 0, 20, 0, 0);
        heavyHandsTape.addAllowedBoxer("Brawler");
        heavyHandsTape.addAllowedBoxer("CounterPuncher");
        heavyHandsTape.addAllowedBoxer("Tank");
        stock.add(heavyHandsTape);

        // Combat consumables
        CombatConsumable uppercut = new CombatConsumable("\uD83D\uDC4A Uppercut", 30, 35,
                SpecialEffectType.NONE, null);
        uppercut.addAllowedBoxer("Brawler");
        uppercut.addAllowedBoxer("CounterPuncher");
        uppercut.addAllowedBoxer("Tank");
        stock.add(uppercut);

        CombatConsumable bodyHook = new CombatConsumable("\uD83D\uDC4A Body Hook", 30, 30,
                SpecialEffectType.NONE, null);
        bodyHook.addAllowedBoxer("Brawler");
        bodyHook.addAllowedBoxer("CounterPuncher");
        bodyHook.addAllowedBoxer("Tank");
        stock.add(bodyHook);

        CombatConsumable overhandRight = new CombatConsumable("\uD83D\uDC4A Overhand Right", 35, 40,
                SpecialEffectType.NONE, null);
        overhandRight.addAllowedBoxer("Brawler");
        overhandRight.addAllowedBoxer("CounterPuncher");
        overhandRight.addAllowedBoxer("Tank");
        stock.add(overhandRight);

        CombatConsumable leftHook = new CombatConsumable("\uD83D\uDC4A Left Hook", 30, 32,
                SpecialEffectType.NONE, null);
        leftHook.addAllowedBoxer("Brawler");
        leftHook.addAllowedBoxer("CounterPuncher");
        leftHook.addAllowedBoxer("Tank");
        stock.add(leftHook);

        CombatConsumable haymaker = new CombatConsumable("\uD83D\uDC4A Haymaker", 45, 45,
                SpecialEffectType.DEFENSE_BREAK, "Reduces opponent's defense by 20% for the rest of the fight");
        haymaker.addAllowedBoxer("Brawler");
        stock.add(haymaker);

        CombatConsumable brutalCombination = new CombatConsumable("\uD83D\uDC4A Brutal Combination", 40, 40,
                SpecialEffectType.STAMINA_DRAIN, "Drains 30 stamina from opponent");
        brutalCombination.addAllowedBoxer("Brawler");
        stock.add(brutalCombination);

        CombatConsumable perfectCounter = new CombatConsumable("\uD83D\uDC4A Perfect Counter", 45, 30,
                SpecialEffectType.ENEMY_POWER_BASED, "Adds 50% of opponent's power to damage");
        perfectCounter.addAllowedBoxer("CounterPuncher");
        stock.add(perfectCounter);

        CombatConsumable powerJab = new CombatConsumable("\uD83D\uDC4A Power Jab", 45, 35,
                SpecialEffectType.HP_STEAL, "Heals for 50% of damage dealt");
        powerJab.addAllowedBoxer("Tank");
        stock.add(powerJab);

        CombatConsumable heavyBodyBlow = new CombatConsumable("\uD83D\uDC4A Heavy Body Blow", 40, 30,
                SpecialEffectType.STAMINA_DRAIN, "Drains 40 stamina from opponent");
        heavyBodyBlow.addAllowedBoxer("Tank");
        stock.add(heavyBodyBlow);

    }

    public ArrayList<HeroItem> getAvailableItems() {
        ArrayList<HeroItem> availableItems = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < stock.size(); i++) {
            indexes.add(i);
        }

        int itemsToShow = Math.min(10, stock.size());
        for (int i = 0; i < itemsToShow; i++) {
            int randomIndex = random.nextInt(indexes.size());
            availableItems.add(stock.get(indexes.get(randomIndex)));
            indexes.remove(randomIndex);
        }

        return availableItems;
    }

    /**
     * Método para lidar com a venda dos produtos na loja. <b>NOTA:</b> O boolean <b>"isAllowedFor"</b> é definido na classe <b>HeroItem</b>
     * @param hero <b>Heroi</b> que vai adquirir o <b>item</b>.
     * @param item <b>Item</b> que vai ser adquirido pelo <b>herói</b>
     */
    public boolean sell(Hero hero, HeroItem item) {
        System.out.println("Boxer type: " + hero.getClass().getSimpleName());
        System.out.println("Item allowed boxers: " + item.getAllowedBoxers());

        if (hero.getMoney() < item.getPrice()) {
            System.out.println("Not enough money! You need $" + item.getPrice());
            return false;
        }

        if (!item.isAllowedFor(hero.getClass().getSimpleName())) {
            System.out.println("This item is not suitable for your boxer type!");
            return false;
        }
        hero.spendMoney(item.getPrice());

        if (item instanceof TrainingTapes) {
            TrainingTapes tape = (TrainingTapes) item;
            System.out.println("\nStudying " + tape.getName() + "...");

            if (tape.getHpHealed() > 0) {
                hero.setMaxHP(hero.getMaxHP() + tape.getHpHealed());
                hero.setHp(hero.getMaxHP());
                System.out.println("Your max HP increased by " + tape.getHpHealed() + "!");
            }
            if (tape.getPowerIncreased() > 0) {
                hero.setPower(hero.getPower() + tape.getPowerIncreased());
                System.out.println("Your power increased by " + tape.getPowerIncreased() + "!");
            }
            if (tape.getDefenseIncreased() > 0) {
                hero.setDefense(hero.getDefense() + tape.getDefenseIncreased());
                System.out.println("Your defense increased by " + tape.getDefenseIncreased() + "!");
            }
            if (tape.getStaminaIncreased() > 0) {
                hero.setMaxStamina(hero.getMaxStamina() + tape.getStaminaIncreased());
                hero.setStamina(hero.getMaxStamina());
                System.out.println("Your max stamina increased by " + tape.getStaminaIncreased() + "!");
            }
        } else if (item instanceof MainWeapon) {
            hero.equipWeapon((MainWeapon) item);
            System.out.println("New gloves equipped: " + item.getName());
        } else if (item instanceof CombatConsumable) {
            hero.addToInventory((CombatConsumable) item);
            System.out.println("Item added to inventory: " + item.getName());
        }
        stock.remove(item);

        return true;
    }

    public void printShop() {
        ArrayList<HeroItem> availableItems = getAvailableItems();

        if (availableItems.isEmpty()) {
            System.out.println("The shop is empty! Come back later!");
            return;
        }

        System.out.println("Welcome to " + name + "'s shop! Here's what's available:");
        System.out.println("----------------------------------------");

        for (int i = 0; i < availableItems.size(); i++) {
            System.out.println((i + 1) + ".");
            availableItems.get(i).showDetails();
            System.out.println("----------------------------------------");
        }
    }
}