package Vencislav;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private Scanner input = new Scanner(System.in);
    private Random rand = new Random();
    private int dice1 = 2;
    private int dice2 = 4;
    private int position;
    private String playerNick;
    private int money = 1500;
    private List belongings = new ArrayList();
    private boolean isInPrison;
    private int prisonBreakPass;


    private int throwDice1() {
        dice1 = rand.nextInt(6) + 1;
        return dice1;

    }

    private int throwDice2() {
        dice2 = rand.nextInt(6) + 1;
        return dice2;

    }

    public int getPosition() {
        return position;

    }

    public void playerMovement() {

//        throwDice1();
//        throwDice2();
        if (position + dice1 + dice2 > 38) {
            position = (dice1 + dice2) - Math.abs(position - 38) - 1;
            money += 200;
        } else position = position + dice1 + dice2;
        System.out.println("You threw " + dice1 + " and " + dice2 + ". So you move " + (dice1 + dice2));
    }

    public void setPlayerNick(int i) {
        System.out.println("Player " + (i + 1) + " nick is:");
        playerNick = input.nextLine();
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void addLocation(String newBelonging) {
        belongings.add(newBelonging);
    }

    public void writeBelongings() {
        for (int i = 0; i < belongings.size(); i++) {
            System.out.println(i + 1 + ". " + belongings.get(i));
        }
    }


    public void removeProp(int i) {
        belongings.remove(i - 1);
    }

    public int getMoney() {
        return money;
    }

    public void buyingProperty(int price) {
        money -= price;
    }

    public boolean hasEnoughMoney(int price) {
        if (money - price >= 0)
            return true;
        System.out.println("Not enough money!");
        return false;
    }

    public void getPlayerMenu() {
        System.out.println("Type \"money\" to see how much money you have.");
        System.out.println("Type \"props\" to see the places you own");
        System.out.println("Type \"close\" to close this menu");
    }

    public void beingCharged(int fee) {
        money -= fee;
    }

    public void receiveMoney(int receive) {
        money += receive;
    }

    public void drawCard() {
        throwDice1();
        switch (dice1) {
            case 1:
                System.out.println("FBI! Open up!\n I know what you have done.\n Get in the prison son!");
                getInPrison();
                break;
            case 2:
                System.out.println("It's your lucky day! You can escape from the jail for free with this pass!");
                prisonBreakPass++;
                break;
            case 3:
                System.out.println("You wanted to try your luck with a lotary ticked \nSo you tried.\n And you tried again and again and " +
                        "again...\n After all that you understood that you wasted 200 leva.");
                beingCharged(200);
                break;
            case 4:
                System.out.println("Wow what a lucky dude. You just won 200 leva from a lotarry ticket");
                receiveMoney(200);
            case 5:
                System.out.println("You've been daylight robbed.\nYou are now 50 leva behind");
                beingCharged(50);
                break;
            case 6:
                System.out.println("What a day man!\nYou fell down stars.\nYou broke your hand.\nWent to hospital.\nAnd then you found 50 leva in your pocket.\nSuch a lucky dude");
                receiveMoney(50);
                break;
        }
    }

    public boolean isInPrison() {
        return isInPrison;
    }

    public void getInPrison() {
        isInPrison = true;
    }

    public void getOutOfPrison() {
        isInPrison = false;
    }

    public boolean usesPrisonPass() {
        if (isInPrison && prisonBreakPass > 0) {
            System.out.println("Would you like to use your free break pass? Type 'y' or whatever if you don't want to");
            char choice = input.next().charAt(0);
            if (choice == 'y') {
                prisonBreakPass--;
                return true;
            }
        }
        return false;
    }

    public String getPropToSell(int i) {
        return belongings.get(i - 1).toString();
    }

    public boolean isSellingProp() {
        System.out.println("Do you really want to sell this property? Type 'y' to sell it or 'n' if you don't want to.");
        char choice = input.next().charAt(0);
        if (choice == 'y') {
            return true;
        }
        return false;
    }

    public int chooseProp() {
        System.out.println("Choose the index of property you want to sell.");
        int choice = input.nextInt();
        return choice;
    }

    public void playerMenu() {
        String option = input.next();
        switch (option) {
            case "money":
                System.out.println(getMoney());
                playerMenu();
                break;

            case "props":
                writeBelongings();
                playerMenu();
                break;
            case "close":
                System.out.println("You closed quick check menu");
                break;
            default:
                System.out.println("Wrong input. Try again!");
                playerMenu();
        }
    }
}
