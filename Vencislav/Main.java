package Vencislav;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("Table for ");
        Board board = new Board();
        int playerNumber = input.nextInt();
        Player[] player = new Player[playerNumber];
        for (int i = 0; i < player.length; i++) {
            player[i] = new Player();
        }
        for (int i = 0; i < playerNumber; i++) {
            player[i].setPlayerNick(i);
        }
        int l = 0;
        while (true) {
            if (player[l].isInPrison()) {
                if (player[l].usesPrisonPass()) {
                    player[l].getOutOfPrison();
                }
                player[l].getOutOfPrison();
                nextPlayer(l, playerNumber);
                continue;
            }
            System.out.println(player[l].getPlayerNick());
            player[l].playerMovement();
            int pos = player[l].getPosition();
            board.boardPos(pos);
            if (board.getsInJail(pos)) {
                System.out.println("You are now in prison for your bad crimes.");
                player[l].getInPrison();
                nextPlayer(l, playerNumber);
                continue;
            } else if (board.isFree(pos)) {
                board.isBuying(pos);
                if (board.getIsBuying()) {
                    if (player[l].hasEnoughMoney(board.placePrize(pos))) {
                        board.setOwner(pos, player[l].getPlayerNick());
                        player[l].buyingProperty(board.placePrize(pos));
                        player[l].addLocation(board.bPos(pos));
                    }
                }
            }
            if(board.isChancePos(pos)){
                
            }
            else if (!board.ownsThePlace(pos, player[l].getPlayerNick())) {
                player[l].beingCharged(board.feeTax(pos));
                for (int i = 0; i < playerNumber; i++) {
                    if (board.ownsThePlace(pos, player[i].getPlayerNick())) {
                        player[i].receiveMoney(board.feeTax(pos));
                    }
                }
            }
            System.out.println("Type \"menu\" to open the list of menus");
            String chooseMenu = input.next();
            if (chooseMenu.equalsIgnoreCase("menu")) {
                menuOptions();
                while (!chooseMenu.equalsIgnoreCase("end")) {
                    chooseMenu = input.next();
                    if (chooseMenu.equalsIgnoreCase("menu1")) {
                        player[l].getPlayerMenu();
                        player[l].playerMenu();
                    }
                    if (chooseMenu.equalsIgnoreCase("menu2")) {
                        menu2Options();
                        while (!chooseMenu.equalsIgnoreCase("close")) {

                            chooseMenu = input.next();
                            switch (chooseMenu) {
                                case "sell":
                                    player[l].writeBelongings();
                                    int choice = player[l].chooseProp();
                                    if (player[l].isSellingProp()) {
                                        player[l].receiveMoney(board.getSellPrize(player[l].getPropToSell(choice)));
                                        board.removeOwner(player[l].getPropToSell(choice));
                                        player[l].removeProp(choice);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
            System.out.println("Your turn ended");
            l = nextPlayer(l, playerNumber);
        }
    }

    private static int nextPlayer(int l, int length) {
        l++;
        if (l == length) {
            l = 0;
        }
        return l;
    }

    public static void menuOptions() {
        System.out.println("Menu 1: Check your money/props - just type \"menu1\"");
        System.out.println("Menu 2: Check your selling/trading - just type \"menu2\"");
        System.out.println("To end your turn type \"end\"");
    }

    public static void menu2Options() {
        System.out.println("Type \"sell\" to sell property");
        System.out.println("Type \"trade\" to trade with player");
        System.out.println("Type \"close\" to close this menu");
    }
}
//TODO create the chance option