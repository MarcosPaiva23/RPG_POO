package Jogo;

import Cores.ConsoleColors;
import Entidades.*;
import Itens.HeroItem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Jogo {

    private Scanner in = new Scanner(System.in);
    private Vendor trainer;

    /**
     * Introdução do jogo, que vai levar à criação da nossa personagem.
     */
    public void startGame() throws InterruptedException {
        trainer = new Vendor("Coach Miller");

        System.out.println("");
        System.out.println("You always loved boxing. Ever since you were a kid, you watched old fight tapes, shadowboxed in front of the mirror, and dreamed of stepping into the ring under the bright lights. 🥊");
        pressEnterToContinue();

        System.out.println("But dreams cost money, and you never had much of that. Gyms, trainers, equipment — all things you couldn't afford.");
        pressEnterToContinue();

        System.out.println("Instead, you fight wherever you can. Underground street fights, back-alley brawls, makeshift rings in abandoned warehouses.");
        pressEnterToContinue();

        System.out.println("You go to bed every night hoping to make it one day. This is the beginning of that story...");
        sleep(2000);
        System.out.println("""
 █████   ██████   █████  ██ ███    ██ ███████ ████████     ████████ ██   ██ ███████     ██████   ██████  ██████  ███████ ███████ 
██   ██ ██       ██   ██ ██ ████   ██ ██         ██           ██    ██   ██ ██          ██   ██ ██    ██ ██   ██ ██      ██      
███████ ██   ███ ███████ ██ ██ ██  ██ ███████    ██           ██    ███████ █████       ██████  ██    ██ ██████  █████   ███████ 
██   ██ ██    ██ ██   ██ ██ ██  ██ ██      ██    ██           ██    ██   ██ ██          ██   ██ ██    ██ ██      ██           ██ 
██   ██  ██████  ██   ██ ██ ██   ████ ███████    ██           ██    ██   ██ ███████     ██   ██  ██████  ██      ███████ ███████ 
                                                                                                                                 
                                                                                                                                 
""");


        System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "But first, you need to decide what kind of fighter you are." + ConsoleColors.RESET);


        Hero player = createCharacter();
        streetFight(player);
    }

    //===================================== CRIAÇÃO DE PERSONAGEM =====================================//

    /**
     * Método de <b>criação da personagem</b>, incluindo <b>nome</b>, <b>tipo de boxer</b> (cada um com os respetivos atributos base), e distribuição de <b>pontos de atributos</b>.
     * @return Um objeto <b>Hero</b> correspondente ao tipo de <b>boxer</b> escolhido.
     */
    public Hero createCharacter() {
        String name;
        do {
            System.out.println("Choose your boxer's name: ");
            name = in.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty! Please enter a valid name.");
            }
        } while (name.isEmpty());

        System.out.println("Choose your boxer type: ");
        System.out.println("1. Brawler \uD83D\uDC4A (More power and stamina, less defense)");
        System.out.println("2. Counter-puncher \uD83D\uDC31\u200D\uD83D\uDC64 (More defense and stamina, less power)");
        System.out.println("3. Tank \uD83D\uDEE1\uFE0F (More power and defense, less stamina)");

        int choice;
        do {
            System.out.print("Enter the corresponding number: ");
            while (!in.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number (1-3).");
                in.next();
            }
            choice = in.nextInt();
        } while (choice < 1 || choice > 3);

        in.nextLine();

        String type;
        switch (choice) {
            case 1: type = "Brawler"; break;
            case 2: type = "Counterpuncher"; break;
            case 3: type = "Tank"; break;
            default: throw new IllegalStateException("Invalid choice!");
        }

        int baseHP, basePower, baseDefense, baseStamina;
        switch (type) {
            case "Brawler":
                baseHP = 100;
                basePower = 50;
                baseDefense = 30;
                baseStamina = 70;
                break;
            case "Counterpuncher":
                baseHP = 100;
                basePower = 30;
                baseDefense = 60;
                baseStamina = 60;
                break;
            case "Tank":
                baseHP = 100;
                basePower = 60;
                baseDefense = 60;
                baseStamina = 30;
                break;
            default:
                throw new IllegalStateException("Invalid type!");
        }

        int remainingPoints = 200;
        int power = basePower, defense = baseDefense, stamina = baseStamina;

        System.out.println("You have 200 points to distribute among Power, Defense, and Stamina.");
        while (remainingPoints > 0) {
            System.out.println("\nRemaining points: " + remainingPoints);

            System.out.println("\nChoose which stat to improve:");
            System.out.println("1. Power (current: " + power + ")");
            System.out.println("2. Defense (current: " + defense + ")");
            System.out.println("3. Stamina (current: " + stamina + ")");

            int statChoice = 0;
            boolean validChoice = false;

            while (!validChoice) {
                System.out.print("Enter your choice (1-3): ");
                if (in.hasNextInt()) {
                    statChoice = in.nextInt();
                    if (statChoice >= 1 && statChoice <= 3) {
                        validChoice = true;
                    } else {
                        System.out.println("Please enter a number between 1 and 3.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    in.next();
                }
            }

            int pointsToAdd = 0;
            switch (statChoice) {
                case 1:
                    pointsToAdd = allocatePoints(in, "Power", power, remainingPoints);
                    power += pointsToAdd;
                    break;
                case 2:
                    pointsToAdd = allocatePoints(in, "Defense", defense, remainingPoints);
                    defense += pointsToAdd;
                    break;
                case 3:
                    pointsToAdd = allocatePoints(in, "Stamina", stamina, remainingPoints);
                    stamina += pointsToAdd;
                    break;
            }
            remainingPoints -= pointsToAdd;
        }

        System.out.println("\nCharacter successfully created!");
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("HP: 100/100");
        System.out.println("Power: " + power);
        System.out.println("Defense: " + defense);
        System.out.println("Stamina: " + stamina);

        switch (type) {
            case "Brawler":
                return new Brawler(name, 100, power, defense, stamina);
            case "Counterpuncher":
                return new CounterPuncher(name, 100, power, defense, stamina);
            case "Tank":
                return new Tank(name, 100, power, defense, stamina);
            default:
                throw new IllegalStateException("Invalid boxer type!");
        }
    }

    /**
     * Método que permite ao jogador distribuir pontos adicionais num atributo específico (Força, Defesa ou Resistência Física).
     * Garante que o input do jogador é válido e que os pontos distribuídos não excedem o total disponível.
     *
     * @param scanner Scanner para leitura do input do jogador.
     * @param attribute Nome do atributo que será melhorado (ex: Força, Defesa, Resistência Física).
     * @param current Valor atual do atributo.
     * @param remainingPoints Pontos restantes que o jogador pode distribuir.
     * @return A quantidade de pontos atribuída ao atributo escolhido.
     */
    private int allocatePoints(Scanner scanner, String attribute, int current, int remainingPoints) {
        int pointsToAdd;
        do {
            System.out.println(attribute + " (Current: " + current + ", Remaining Points: " + remainingPoints + ")");
            System.out.print("How many points do you want to add? ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            pointsToAdd = scanner.nextInt();

            if (pointsToAdd > remainingPoints) {
                System.out.println("You can't add more points than you have remaining!");
            } else if (pointsToAdd < 0) {
                System.out.println("You can't add negative points!");
            }
        } while (pointsToAdd > remainingPoints || pointsToAdd < 0);

        return pointsToAdd;
    }

    //=================================== MECANICAS DE COMBATE =========================================//

    /**
     * Método que gere a dinâmica do combate. Cada combate tem <b>3 rounds</b>, e cada round tem <b>3 turnos</b> por boxer.
     * O combate pode terminar por KO (HP = 0) ou por decisão dos juízes após os 3 rounds.
     *
     * @param player O boxer controlado pelo jogador
     * @param opponent O adversário controlado pelo computador
     * @return true se o jogador vencer, false se perder
     */
    private boolean startBoxingMatch(Hero player, NPC opponent) {
        int currentRound = 1;
        boolean specialAttackAvailable = true; // Special attack is available at the start of the fight

        System.out.println("\n=== FIGHT START ===");

        try {
            sleep(2000);  // 2 second pause before fight starts
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (currentRound <= 3 && player.getHp() > 0 && opponent.getHp() > 0) {
            System.out.println("\n=== ROUND " + currentRound + " ===");
            displayFightStats(player, opponent);
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            for (int turn = 1; turn <= 3 && player.getHp() > 0 && opponent.getHp() > 0; turn++) {
                System.out.println("\n----- Turn " + turn + " -----");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Store the updated specialAttackAvailable value
                specialAttackAvailable = playerTurn(player, opponent, specialAttackAvailable);

                if (opponent.getHp() <= 0) {
                    return true; // Player wins
                }

                opponentTurn(opponent, player);

                if (player.getHp() <= 0) {
                    return false; // Player loses
                }

                displayFightStats(player, opponent);
            }

            if (player.getHp() <= 0 || opponent.getHp() <= 0) {
                break; // Exit loop if someone is knocked out
            }

            currentRound++;
            endRound(player, opponent, currentRound);
        }

        return decideWinner(player, opponent);
    }



    private boolean playerTurn(Hero player, NPC opponent, boolean specialAttackAvailable) {

        int normalAttackCost;
        int specialAttackCost;

        if (player instanceof Brawler) {
            normalAttackCost = 30;
            specialAttackCost = 45;
        } else if (player instanceof CounterPuncher) {
            normalAttackCost = 20;
            specialAttackCost = 35;
        } else {
            normalAttackCost = 25;
            specialAttackCost = 40;
        }

        System.out.println("\nYour turn! Choose your action:");
        System.out.println("1. 👊 Normal Attack (" + normalAttackCost + " stamina)");
        if (specialAttackAvailable) {
            System.out.println("2. 💥 Special Attack (" + specialAttackCost + " stamina) (Once per fight!)");
        }
        if (!player.getInventory().isEmpty()) {
            System.out.println("3. \uD83D\uDCA3 Use Special Move");
        }
        System.out.println("4. \uD83D\uDECC\uD83C\uDFFB Rest (Recover 30 stamina)");

        int choice;
        do {
            System.out.print("Enter your choice: ");
            while (!in.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                in.next();
            }
            choice = in.nextInt();

            if (choice == 1 && player.getStamina() < normalAttackCost) {
                System.out.println("Not enough stamina for normal attack! (Need " + normalAttackCost + ")");
                choice = 0;
            }
            if (choice == 2) {
                if (!specialAttackAvailable) {
                    System.out.println("Special Attack already used!");
                    choice = 0;
                } else if (player.getStamina() < specialAttackCost) {
                    System.out.println("Not enough stamina for special attack! (Need " + specialAttackCost + ")");
                    choice = 0;
                }
            }
            if (choice == 3 && player.getInventory().isEmpty()) {
                System.out.println("No special moves available!");
                choice = 0;
            }
        } while (choice < 1 || choice > 4);

        if (choice == 4) {

            int recoveredStamina = Math.min(30, player.getMaxStamina() - player.getStamina());
            player.setStamina(player.getStamina() + recoveredStamina);
            System.out.println("You take a defensive stance and recover " + recoveredStamina + " stamina!");
        } else {
            player.attack(opponent, choice, specialAttackAvailable);
            if (choice == 2) {
                specialAttackAvailable = false;
            }
        }


        return specialAttackAvailable;
    }

    /**
     * Método que gere o turno do CPU durante um combate
     * @param opponent O adversário
     * @param player O <b>boxer</b> controlado pelo jogador
     */
    private void opponentTurn(NPC opponent, Hero player) {
        System.out.println("\n" + opponent.getName() + "'s turn!");
        opponent.attack(player);
    }

    /**
     * Exibe as estatísticas atuais do jogador e do adversário durante a luta.
     *
     * @param player O <b>boxer</b> do jogador.
     * @param opponent O adversário <b>NPC</b>.
     */
    private void displayFightStats(Hero player, NPC opponent) {

        System.out.println("\n=== FIGHT STATUS \uD83D\uDCAA ===");

        System.out.println(player.getName() + ": HP " + player.getHp() + "/" + player.getMaxHP() +  " | Stamina " + player.getStamina() + "/" + player.getMaxStamina());
        System.out.println(opponent.getName() + ": HP " + opponent.getHp() + "/" + opponent.getMaxHP() + " | Stamina " + opponent.getStamina() + "/" + opponent.getMaxStamina());


    }

    /**
     * Finaliza o round atual, restaurando parte da stamina do jogador e do adversário antes do próximo round.
     *
     * @param player   O <b>boxer</b> controlado pelo jogador.
     * @param opponent O <b>adversário</b> NPC.
     * @param nextRound O número do próximo round.
     */
    private void endRound(Hero player, NPC opponent, int nextRound) {
        System.out.println("\nEnd of Round " + (nextRound - 1) + " 🔔");
        pressEnterToContinue();

        // Recover some stamina between rounds
        if (player.getStamina() + 20 <= player.getMaxStamina()) {
            int recoveredStamina = Math.min(20, player.getMaxStamina() - player.getStamina());
            player.setStamina(player.getStamina() + recoveredStamina);
            System.out.println("You recovered " + recoveredStamina + " stamina!");
        }
        if (opponent.getStamina() + 20 <= opponent.getMaxStamina()) {
            opponent.setStamina(opponent.getStamina() + 20);
        }

        displayFightStats(player, opponent);  // Show updated stats after recovery
        pressEnterToContinue();
    }

    /**
     * Decide o vencedor da luta com base no HP restante ou, caso necessário, na decisão dos juízes.
     *
     * @param player   O <b>boxer</b> controlado pelo jogador.
     * @param opponent O <b>adversário</b> NPC.
     * @return true se o jogador vencer, false se perder.
     */
    private boolean decideWinner(Hero player, NPC opponent) {
        if (player.getHp() <= 0 || opponent.getHp() <= 0) {
            return player.getHp() > 0;
        }
        return judgeDecision(player, opponent);
    }

    /**
     * Realiza o cálculo da decisão dos juízes, caso o combate não tenha terminado por KO.
     *
     * @param player O <b>boxer</b> do jogador.
     * @param opponent O <b>adversário</b> NPC.
     * @return true se o jogador for declarado vencedor, false caso contrário.
     */
    private boolean judgeDecision(Hero player, NPC opponent) {
        System.out.println("\nThe fight goes to the judges' scorecards...");
        pressEnterToContinue();


        int playerHealthScore = (int)((player.getHp() / (double)player.getMaxHP()) * 60);
        int playerStaminaScore = (int)((player.getStamina() / (double)player.getMaxStamina()) * 40);
        int playerTotalScore = playerHealthScore + playerStaminaScore;

        int opponentHealthScore = (int)((opponent.getHp() / (double)opponent.getMaxHP()) * 60);
        int opponentStaminaScore = (int)((opponent.getStamina() / (double)opponent.getMaxStamina()) * 40);
        int opponentTotalScore = opponentHealthScore + opponentStaminaScore;

        System.out.println("\nJUDGE'S SCORECARD");
        System.out.println("----------------------------------------");
        System.out.println(player.getName() + ":");
        System.out.println("Health Score: " + playerHealthScore + "/60");
        System.out.println("Stamina Score: " + playerStaminaScore + "/40");
        System.out.println("Total Score: " + playerTotalScore + "/100");
        System.out.println("----------------------------------------");
        System.out.println(opponent.getName() + ":");
        System.out.println("Health Score: " + opponentHealthScore + "/60");
        System.out.println("Stamina Score: " + opponentStaminaScore + "/40");
        System.out.println("Total Score: " + opponentTotalScore + "/100");
        System.out.println("----------------------------------------");

        pressEnterToContinue();

        if (playerTotalScore > opponentTotalScore) {
            System.out.println("AND THE WINNER BY UNANIMOUS DECISION... " + player.getName().toUpperCase() + "!");
        } else if (playerTotalScore < opponentTotalScore) {
            System.out.println("AND THE WINNER BY UNANIMOUS DECISION... " + opponent.getName().toUpperCase() + "!");
        } else {
            System.out.println("AND IT'S A DRAW!");
            return true;
        }

        return playerTotalScore >= opponentTotalScore;
    }

    /**
     * Método que processa o resultado do combate, atribuindo recompensas (<b>dinheiro</b> e <b>reputação</b>) em caso de vitória ou oferecendo a opção de tentar novamente em caso de derrota.
     *
     * @param player O <b>boxer</b> do jogador
     * @param victory Indica se o jogador venceu o combate
     * @param fightType Indica qual é a luta dentro da estrutura da história
     */
    private void handleFightOutcome(Hero player, boolean victory, String fightType) {
        if (victory) {
            int baseMoney;
            int reputationGain;

            switch(fightType) {
                case "streetFight":
                    baseMoney = 50;
                    reputationGain = 5;
                    break;
                case "amateurFight":
                    baseMoney = 75;
                    reputationGain = 10;
                    break;
                case "proFight":
                    baseMoney = 100;
                    reputationGain = 25;
                    break;
                case "thirdFight":
                    baseMoney = 150;
                    reputationGain = 20;
                    break;
                case "ppvFight":
                    baseMoney = 200;
                    reputationGain = 25;
                    break;
                case "contenderFight":
                    baseMoney = 250;
                    reputationGain = 30;
                    break;
                case "championshipFight":
                    baseMoney = 500;
                    reputationGain = 50;
                    break;
                case "legendaryFight":
                    baseMoney = 1000;
                    reputationGain = 100;
                    break;
                default:
                    baseMoney = 50;
                    reputationGain = 5;
                    break;
            }

            double reputationMultiplier = 0.5 + (player.getReputation() * 0.01);
            int moneyGain = (int)(baseMoney * reputationMultiplier);

            if (player.getReputation() > 0) {
                System.out.println("Your reputation earned you a " +
                        String.format("%.1f", (reputationMultiplier - 1) * 100) + "% bonus!");
            }

            System.out.println("\nVICTORY! The crowd goes wild! 🏆");
            pressEnterToContinue();
            player.setMoney(player.getMoney() + moneyGain);
            player.setReputation(player.getReputation() + reputationGain);
            System.out.println("You earned $" + moneyGain + " 💰 and your reputation increased by " + reputationGain + " ⭐!");

            player.setHp(player.getMaxHP());
            player.setStamina(player.getMaxStamina());
            System.out.println("\nYou recover after the fight.");
            pressEnterToContinue();

            postFightMenu(player, fightType);
        } else {
            System.out.println("\nDEFEAT! You've been knocked out. 💫");
            System.out.println("Maybe with proper training, you could do better next time...");
            System.out.println("\n1. Try again");
            System.out.println("2. Exit game");

            int choice;
            do {
                System.out.print("Enter your choice (1-2): ");
                while (!in.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.");
                    in.next();
                }
                choice = in.nextInt();
            } while (choice < 1 || choice > 2);

            if (choice == 1) {
                player.setHp(player.getMaxHP());
                player.setStamina(player.getMaxStamina());

                switch(fightType) {
                    case "streetFight":
                        streetFight(player);
                        break;
                    case "amateurFight":
                        amateurFight(player);
                        break;
                    case "proFight":
                        proFight(player);
                        break;
                    case "thirdFight":
                        thirdFight(player);
                        break;
                    case "ppvFight":
                        ppvUndercardFight(player);
                        break;
                    case "contenderFight":
                        contenderFight(player);
                        break;
                    case "championshipFight":
                        championshipFight(player);
                        break;
                    case "legendaryFight":
                        legendaryFight(player);
                        break;

                }
            } else {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
    }

    //============================== COMBATES ======================================//

    /**
     * Luta inicial do jogo - uma luta de rua que serve como tutorial.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void streetFight(Hero player) {
        System.out.println("\nIt's late night at the underground fight scene. The air is thick with smoke and tension.");
        pressEnterToContinue();

        System.out.println("Your opponent arrives - a local tough guy who's been making noise in the scene.");
        pressEnterToContinue();

        NPC opponent = new NPC("Street Fighter Joe", 100, 30, 30, 30, 10);

        showTutorial();
        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "streetFight");
    }

    /**
     * Luta amadora - ocorre antes do jogador se tornar profissional (caso o utilizador escolha este caminho).
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void amateurFight(Hero player) {
        System.out.println("A few weeks later, after training with Coach Miller...");
        pressEnterToContinue();

        System.out.println("You're in a proper boxing gym for the first time. The ring looks pristine compared to your street fighting days.");
        pressEnterToContinue();

        System.out.println("Your opponent is another up-and-coming amateur. Not as rough as street fighters, but technically better trained.");
        pressEnterToContinue();

        NPC opponent = new NPC("Amateur Boxer Richard", 175, 40, 40, 40, 20);

        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "amateurFight");
    }

    /**
     * Primeira luta profissional do jogador (caso o utilizador escolha este caminho).
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void proFight(Hero player) {
        System.out.println("One month later, at your professional debut...");
        pressEnterToContinue();

        System.out.println("The arena might be small, but it's still a huge step up from street fighting.");
        pressEnterToContinue();

        System.out.println("Your opponent is a seasoned pro with a record of 8 wins and 2 losses. This won't be easy.");
        pressEnterToContinue();

        NPC opponent = new NPC("Journeyman Jalen", 200, 50, 45, 45, 50);

        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "proFight");
    }

    /**
     * Segunda luta profissional (primeira se tiver escolhido o caminho amador).
     * @param player O <b>boxer</b> do jogador.
     */
    private void thirdFight(Hero player) {
        String pathDescription;
        if (player.getCareerPath().equals("pro")) {
            pathDescription = "Your professional debut showed everyone you belong in the big leagues. Now it's time to prove it wasn't a fluke.";
        } else {
            pathDescription = "Your amateur fight proved you have what it takes. Now it's time to show you can handle the next level.";
        }

        System.out.println("\n" + pathDescription);
        pressEnterToContinue();

        System.out.println("Your opponent is a rising star with a record of 12-2.");
        pressEnterToContinue();

        System.out.println("He's known for his conditioning and ring IQ. This won't be easy.");
        pressEnterToContinue();

        NPC opponent = new NPC("Rising Star Alexander", 200, 55, 50, 50, 100);

        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "thirdFight");
    }

    /**
     * Primeira luta em PPV, no undercard
     * @param player O <b>boxer</b> do jogador.
     */
    private void ppvUndercardFight(Hero player) {
        System.out.println("\nThis is it - your first fight on Pay-Per-View!");
        pressEnterToContinue();

        System.out.println("You're on the undercard, but this is still a massive opportunity.");
        pressEnterToContinue();

        System.out.println("Your opponent is a skilled veteran with a record of 15-3.");
        pressEnterToContinue();

        NPC opponent = new NPC("Veteran Victor", 200, 60, 65, 65, 150);

        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "ppvFight");
    }

    /**
     * Luta entre candidatos ao título
     * @param player O <b>boxer</b> do jogador
     */
    private void contenderFight(Hero player) {
        System.out.println("\nThis is it - the number one contender fight!");
        pressEnterToContinue();

        System.out.println("Win this, and you're fighting for the undisputed championship!!!");
        pressEnterToContinue();

        System.out.println("Your opponent is a dangerous veteran with a record of 28-2.");
        System.out.println("A former champion looking to get his belt back.");
        pressEnterToContinue();

        NPC opponent = new NPC("The Veteran Malik", 250, 85, 70, 70, 200);

        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "contenderFight");
    }

    /**
     * Luta pelo cinturão
     * @param player O <b>boxer</b> do jogador
     */
    private void championshipFight(Hero player) {
        System.out.println("\nFight night. The biggest night in boxing.");
        pressEnterToContinue();

        System.out.println("You can barely hear Coach Miller's final instructions over the crowd.");
        pressEnterToContinue();

        System.out.println("\"Ladies and gentlemen, this is for the UNDISPUTED WORLD CHAMPIONSHIP! 👑\"");
        pressEnterToContinue();

        NPC opponent = new NPC("The Champion", 300, 90, 85, 75, 500);

        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "championshipFight");
    }

    /**
     * Luta opcional, funciona como um epílogo, contra uma lenda viva
     * @param player O <b>boxer</b> do jogador
     */
    private void legendaryFight(Hero player) {
        System.out.println("\nThe world stands still for this moment.");
        pressEnterToContinue();

        System.out.println("Two legends, one ring, infinite stakes.");
        pressEnterToContinue();

        System.out.println("This isn't about money or belts anymore. This is about immortality.");
        pressEnterToContinue();

        NPC opponent = new NPC("The Legend", 300, 80, 75, 75, 1000);

        boolean fightResult = startBoxingMatch(player, opponent);
        handleFightOutcome(player, fightResult, "legendaryFight");
    }

    //=============================== EVENTOS DE HISTORIA ==============================//

    /**
     * Evento no qual o jogador conhece o treinador Coach Miller, que lhe oferece duas opções:
     * seguir diretamente para o boxe profissional ou ter uma luta amadora primeiro.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void meetTrainer(Hero player) {
        System.out.println("\nA well-dressed man approaches you after the fight.");
        pressEnterToContinue();

        System.out.println("\"Kid, I saw something special in there. With proper training, you could be something great.\"");
        pressEnterToContinue();

        System.out.println("\"My name's Coach Miller. I've trained champions before, and I think you could be next.\"");
        pressEnterToContinue();

        System.out.println("\nAfter showing you his equipment, Coach Miller presents you with two options:");
        System.out.println("1. Go straight to your first professional fight");
        System.out.println("   Higher risk, but higher reward. Show everyone you're ready for the big leagues.");
        System.out.println("\n2. Have an amateur fight first");
        System.out.println("   Safer choice. Test your new skills before going pro.");

        int choice = getValidInput(1, 2);

        if (choice == 1) {
            System.out.println("\n\"That's the spirit, kid! Let's get you ready for your pro debut.\"");
            player.setCareerPath("pro");
            pressEnterToContinue();
            proFight(player);
        } else {
            System.out.println("\n\"Smart choice. Let's get you some experience first.\"");
            player.setCareerPath("amateur");
            pressEnterToContinue();
            amateurFight(player);
        }
    }

    /**
     * Evento de história/escolha que simula um contrato de patrocínio com possíveis consequências positivas ou negativas.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void brandDealEvent(Hero player) {
        String brandTier;
        int offerAmount;

        if (player.getReputation() >= 30) {
            brandTier = "Premium";
            offerAmount = 200;
        } else {
            brandTier = "Rising Star";
            offerAmount = 150;
        }

        System.out.println("\nYour growing reputation in the professional boxing scene has caught attention.");
        pressEnterToContinue();

        System.out.println(brandTier + " Sports wants you to wear their clothes for a night out at the prestigious 'Ring Club'.");
        System.out.println("The deal is worth $" + offerAmount + ". 💰");
        System.out.println("It could be great exposure, but nightclubs can be unpredictable...");
        pressEnterToContinue();

        System.out.println("\nWhat do you want to do?");
        System.out.println("1. Accept the deal and go to the club");
        System.out.println("2. Decline and focus on training");

        int choice = getValidInput(1, 2);

        if (choice == 1) {
            Random random = new Random();
            if (random.nextInt(10) < 7) {
                positiveClubOutcome(player, offerAmount);
            } else {
                negativeClubOutcome(player, offerAmount);
            }
        } else {
            System.out.println("\n\"I appreciate the offer, but I need to stay focused on my training.\"");
            System.out.println("Your dedication to training impresses " + brandTier + " Sports.");
            System.out.println("They respect your professionalism and might offer better deals in the future.");
            player.setReputation(player.getReputation() + 5);
            pressEnterToContinue();
        }
    }

    /**
     * Método para as consequências positivas do evento de patrocínio
     * @param player O <b>boxer</b> do jogador.
     * @param baseAmount A quantia monetária ganha.
     */
    private void positiveClubOutcome(Hero player, int baseAmount) {
        System.out.println("\nThe night is a huge success!");
        pressEnterToContinue();

        System.out.println("You make great connections, take perfect photos for social media, " +
                "and represent the brand perfectly.");
        pressEnterToContinue();

        int reputationGained = 20;
        player.setMoney(player.getMoney() + baseAmount);
        player.setReputation(player.getReputation() + reputationGained);

        System.out.println("You earned $" + baseAmount + " and your reputation increased by " + reputationGained + "!");
        pressEnterToContinue();
    }

    /**
     * Método para as consequências negativas do evento de patrocínio
     * @param player O <b>boxer</b> do jogador
     * @param baseAmount A quantia monetária ganha.
     */
    private void negativeClubOutcome(Hero player, int baseAmount) {
        System.out.println("\nThe night starts well, but things get out of hand...");
        pressEnterToContinue();

        System.out.println("You have one too many drinks and end up making a scene. " +
                "Social media is already full of embarrassing videos.");
        pressEnterToContinue();

        int reducedAmount = baseAmount / 2;
        int staminaPenalty = 20;

        player.setMoney(player.getMoney() + reducedAmount);
        player.setStamina(Math.max(60, player.getStamina() - staminaPenalty));

        System.out.println("You still got paid $" + reducedAmount + " but your stamina decreased by " + staminaPenalty +
                " for your next fight.");
        pressEnterToContinue();
    }

    /**
     * Evento de história/escolha onde um ex-campeão oferece um treino de sparring ao jogador.
     * Pode ter um desfecho positivo ou negativo, dependendo da sorte.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void sparringEvent(Hero player) {
        System.out.println("\nThe former champion has been watching your rise.");
        pressEnterToContinue();

        System.out.println("He offers to spar with you at his private gym. It could be invaluable experience...");
        System.out.println("Or it could be a poisoned invitation from a jealous has-been looking to hurt you...");
        pressEnterToContinue();

        System.out.println("\n1. Accept the sparring invitation");
        System.out.println("2. Politely decline");

        int choice = getValidInput(1, 2);

        if (choice == 1) {
            Random random = new Random();
            if (random.nextInt(10) < 6) {
                positiveSparringOutcome(player);
            } else {
                negativeSparringOutcome(player);
            }
        } else {
            System.out.println("\n\"Thank you, but I prefer to prepare with my own team.\"");
            pressEnterToContinue();
        }
    }

    /**
     * Método para as consequências positivas do evento de treino
     * @param player O <b>boxer</b> do jogador
     */
    private void positiveSparringOutcome(Hero player) {
        System.out.println("\nThe sparring session is incredible! The champion shares invaluable knowledge.");
        pressEnterToContinue();

        System.out.println("Your body adapts to the higher level of competition.");
        player.setMaxHP(player.getMaxHP() + 20);
        player.setHp(player.getMaxHP());
        player.setReputation(player.getReputation() + 15);

        System.out.println("Your max HP increased by 20 and reputation increased by 15!");
        pressEnterToContinue();
    }

    /**
     * Método para as consequências negativas do evento de treino
     * @param player O <b>boxer</b> do jogador
     */
    private void negativeSparringOutcome(Hero player) {
        System.out.println("\nThe champion goes too hard, clearly trying to hurt you.");
        pressEnterToContinue();

        System.out.println("You suffer a minor injury that will affect your next fight.");
        player.setHp(player.getHp() - 30);

        System.out.println("You will start your next fight with -30 HP!");
        pressEnterToContinue();
    }

    /**
     * Final do jogo caso o jogador decida retirar-se depois de ganhar o cinturão.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void retirementEnding(Hero player) {
        System.out.println("\nYou announce your retirement at the peak of your career.");
        pressEnterToContinue();

        System.out.println("From the streets to the summit, you conquered boxing on your own terms.");
        pressEnterToContinue();

        System.out.println("Your name will be remembered forever in boxing history.");
        pressEnterToContinue();

        System.out.println("\nTHE END");
        System.exit(0);
    }

    /**
     * Método para a última escolha da história - retirar-se ou aceitar a luta lendária
     * @param player O <b>boxer</b> do jogador
     */
    private void legendaryFightChoice(Hero player) {
        System.out.println("What do you want to do?");
        System.out.println("1. Accept the challenge - one last fight for eternal glory");
        System.out.println("2. Retire as champion - your legacy is already secure");

        int choice = getValidInput(1, 2);

        if (choice == 1) {
            legendaryFight(player);
        } else {
            retirementEnding(player);
        }
    }

    //=============================== MENU E COMPRAS ===================================//

    /**
     * Menu pós-luta que permite ao jogador escolher entre continuar a carreira, visitar a loja,
     * ver os detalhes do personagem ou sair do jogo.
     *
     * @param player O <b>boxer</b> do jogador.
     * @param fightType O tipo da luta mais recente.
     */
    private void postFightMenu(Hero player, String fightType) {
        boolean choosingAction = true;
        while (choosingAction) {
            System.out.println("1. Continue your boxing career 🥊");
            System.out.println("2. Visit Coach Miller's gym 🏋️ (shop)");
            System.out.println("3. Check your fighter's details 📊");
            System.out.println("4. Exit game 🚪");

            int choice = getValidInput(1, 4);

            switch (choice) {
                case 1:
                    choosingAction = false;
                    switch(fightType) {
                        case "streetFight":
                            meetTrainer(player);
                            break;
                        case "amateurFight":
                            amateurFightAftermath(player);
                            break;
                        case "proFight":
                            proFightAftermath(player);
                            break;
                        case "thirdFight":
                            thirdFightAftermath(player);
                            break;
                        case "ppvFight":
                            ppvFightAftermath(player);
                            break;
                        case "contenderFight":
                            contenderFightAftermath(player);
                            break;
                        case "championshipFight":
                            championshipAftermath(player);
                            break;
                        case "legendaryFight":
                            legendaryFightAftermath(player);
                            break;
                    }
                    break;
                case 2:
                    trainer.printShop();
                    handleShopping(player, trainer);
                    break;
                case 3:
                    player.showDetails();
                    pressEnterToContinue();
                    break;
                case 4:
                    System.out.println("Thanks for playing!");
                    System.exit(0);
            }
        }
    }

    /**
     * Exibe os itens disponíveis na loja do treinador e permite ao jogador comprar itens.
     *
     * @param player O <b>boxer</b> do jogador.
     * @param trainer O <b>vendedor</b> (Coach Miller).
     */
    private void handleShopping(Hero player, Vendor trainer) {
        ArrayList<HeroItem> availableItems = trainer.getAvailableItems();

        if (availableItems.isEmpty()) {
            System.out.println("No items available!");
            return;
        }

        boolean shopping = true;
        while (shopping) {
            System.out.println("\nYou have $" + player.getMoney() + " available.");
            System.out.println("\nAvailable items:");
            System.out.println("----------------------------------------");


            for (int i = 0; i < availableItems.size(); i++) {
                System.out.println((i + 1) + ".");
                availableItems.get(i).showDetails();
                System.out.println("----------------------------------------");
            }

            System.out.println("\nEnter item number to buy (0 to exit):");
            int choice = getValidInput(0, availableItems.size());

            if (choice == 0) {
                shopping = false;
            } else {
                HeroItem selectedItem = availableItems.get(choice - 1);
                if (trainer.sell(player, selectedItem)) {
                    System.out.println("Purchase successful!");
                    availableItems.remove(choice - 1);
                    if (availableItems.isEmpty()) {
                        System.out.println("\nNo more items available!");
                        shopping = false;
                    }
                }
                pressEnterToContinue();
            }
        }
    }

    //=============================== UTILIDADES ========================================//

    /**
     * Método que permite esperar por input do jogador antes de continuar o jogo
     */
    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        in.nextLine();
    }

    /**
     * Exibe um tutorial breve sobre as mecânicas do combate.
     */

    private void showTutorial() {
        System.out.println("\nFIGHT TUTORIAL 📖:");
        System.out.println("- Each round has 3 turns for each fighter");
        System.out.println("- On your turn, you can choose between:");
        System.out.println("  1. Normal Attack: Basic combination using your power + glove attack");
        System.out.println("  2. Special Attack: Powerful strike (only once per fight!)");
        System.out.println("  3. Special Move: Use a combat consumable from your inventory");
        System.out.println("\nThe fight ends by KO (0 HP) or goes to the judges after 3 rounds.");
        System.out.println("\nYou earn reputation with each win, which impacts your payout at the end of the fights! \uD83E\uDD11");
        pressEnterToContinue();
    }

    /**
     * Garante que o jogador insira um número válido dentro do intervalo especificado.
     *
     * @param min Valor mínimo aceitável.
     * @param max Valor máximo aceitável.
     * @return O número válido inserido pelo jogador.
     */
    private int getValidInput(int min, int max) {
        int choice;
        do {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            while (!in.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                in.next();
            }
            choice = in.nextInt();
        } while (choice < min || choice > max);

        in.nextLine();
        return choice;
    }

    /**
     * Processa as consequências da luta amadora e leva o jogador à sua estreia profissional.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void amateurFightAftermath(Hero player) {
        System.out.println("\"Good work in there, kid,\" Coach Miller says. \"You've shown you can handle yourself in a proper ring.\"");
        pressEnterToContinue();

        System.out.println("\"I think you're ready for the pros now. Your next fight will be your professional debut.\"");
        pressEnterToContinue();

        proFight(player);
    }

    /**
     * Processa as consequências da primeira luta profissional do jogador e avança na carreira.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void proFightAftermath(Hero player) {
        if (player.getMoney() >= 50) {
            System.out.println("\"Not bad for your first pro fight!\" Coach Miller beams. \"But this is just the beginning.\"");
        } else {
            System.out.println("\"The pro ranks are tough, kid. But you've got heart. We'll get them next time.\"");
        }
        pressEnterToContinue();

        thirdFight(player);
    }

    /**
     * Processa as consequências da terceira luta do jogador e introduz novos eventos.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void thirdFightAftermath(Hero player) {
        System.out.println("\"You're making waves in the boxing world, kid,\" Coach Miller says with pride.");
        pressEnterToContinue();

        brandDealEvent(player);

        System.out.println("\nA week later, Coach Miller arrives at the gym with big news.");
        pressEnterToContinue();

        System.out.println("\"They want you on the next Pay-Per-View card! It's your chance to show everyone what you can do.\"");
        pressEnterToContinue();

        ppvUndercardFight(player);
    }

    /**
     * Processa o desfecho da luta no Pay-Per-View e prepara o jogador para uma disputa com o outro candidato ao cinturão.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void ppvFightAftermath(Hero player) {
        System.out.println("\"You just proved you belong at this level,\" Coach Miller beams.");
        pressEnterToContinue();

        System.out.println("\"Word is, you're one fight away from a title shot. But first...\"");
        pressEnterToContinue();

        sparringEvent(player);

        contenderFight(player);
    }

    /**
     * Processa o desfecho da luta contra o outro candidato antes do título mundial.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void contenderFightAftermath(Hero player) {
        System.out.println("The crowd is going crazy! This fight lived up to all expectations!");
        pressEnterToContinue();

        System.out.println("\"You did it, kid!\" Coach Miller hugs you. \"You're fighting for the world title!\"");
        pressEnterToContinue();

        System.out.println("The champion enters the ring, and you both face off as the crowd roars.");
        pressEnterToContinue();

        championshipFight(player);
    }

    /**
     * Processa as consequências da luta pelo cinturão e prepara o jogador para sua decisão final.
     *
     * @param player O boxer do jogador.
     */
    private void championshipAftermath(Hero player) {
        System.out.println("Tears stream down your face as the referee raises your hand.");
        pressEnterToContinue();

        System.out.println("\"AND NEW... UNDISPUTED WORLD CHAMPION!\"");
        pressEnterToContinue();

        System.out.println("From street fights to world champion. You've done it.");
        pressEnterToContinue();

        System.out.println("\nTHREE MONTHS LATER");
        pressEnterToContinue();

        System.out.println("You're considering retirement. You've achieved everything you dreamed of.");
        pressEnterToContinue();

        System.out.println("But then the news breaks: The greatest boxer of all time is coming out of retirement.");
        pressEnterToContinue();

        System.out.println("He wants you. The biggest fight in boxing history.");
        pressEnterToContinue();

        legendaryFightChoice(player);
    }

    /**
     * Processa o desfecho da luta lendária, determinando o legado final do jogador.
     *
     * @param player O <b>boxer</b> do jogador.
     */
    private void legendaryFightAftermath(Hero player) {
        if (player.getHp() > 0) {
            System.out.println("\nSilence falls over the arena as the legendary battle ends.");
            pressEnterToContinue();

            System.out.println("You've done the impossible. Beaten the unbeatable.");
            pressEnterToContinue();

            System.out.println("Your name will be spoken with reverence for generations to come.");
            pressEnterToContinue();

            System.out.println("\nTHE END");
        } else {
            System.out.println("\nYou gave everything, but the legend proved why he's the greatest.");
            pressEnterToContinue();

            System.out.println("Still, you've earned his respect, and the world's admiration.");
            pressEnterToContinue();

            System.out.println("Your incredible journey ends here, but your legacy will live forever.");
            pressEnterToContinue();

            System.out.println("\nTHE END");
        }
        System.exit(0);
    }

}