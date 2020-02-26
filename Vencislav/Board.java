package Vencislav;

import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private Scanner input = new Scanner(System.in);
    private Random random = new Random();
    private boolean isBuying;
    private boolean ownsPlace;
    private String[][] board = {
            //      PLACE                      BUY PRICE        OWNER         ID     FEE
            {
                    "Start", "", "Bank", "", "0"
            },
            {
                    "Okolchitsa st.", "60", "", "1", "30"
            },
            {
                    "Bratq Miladinovi st.", "80", "", "1", "40"
            },
            {
                    "bul. July", "90", "", "1", "50"
            },
            {
                    "Income Tax", "", "Bank", "", "50"
            },
            {
                    "Vratsa Train Station", "200", "", "train", "70"
            },
            {
                    "Kaleto", "100", "", "2", "50"
            },
            {
                    "Chance", "", "Bank", "", "0"
            },
            {
                    "Dunav Bridge", "110", "", "2", "55"
            },
            {
                    "Dunav Bridge2", "120", "", "2", "60"
            },
            {
                    "Jail Visiting", "", "Bank", "", "0"
            },
            {
                    "Sumi", "150", "", "3", "70"
            },
            {
                    "Electricity Tax", "", "Bank", "", "100"
            },
            {
                    "Rock club", "160", "", "3", "80"
            },
            {
                    "Mali", "170", "", "3", "80"
            },
            {
                    "Mezdra Train Station", "200", "", "train", "70"
            },
            {
                    "Ledenika Street", "160", "", "4", "80"
            },
            {
                    "Ledenika Factury", "170", "", "4", "85"
            },
            {
                    "G-town", "180", "", "4", "90"
            },
            {
                    "Free Parking", "", "Bank", "", "0"
            },
            {
                    "Chaika", "180", "", "5", "90"
            },
            {
                    "Chance", "", "Bank", "", "0"
            },
            {
                    "The Skuta", "190", "", "5", "95"
            },
            {
                    "Hotel Hemus", "200", "", "5", "100"
            },
            {
                    "Train Station Vidin", "200", "", "train", "70"
            },
            {
                    "Hizhata", "210", "", "6", "105"
            },
            {
                    "Kaulfand", "220", "", "6", "110"
            },
            {
                    "Billa", "230", "", "6", "120"
            },
            {
                    "Water Tax", "", "Bank", "", "150"
            },
            {
                    "Butka za duneri", "310", "", "7", "150"
            },
            {
                    "GO TO JAIL", "", "Bank", "", "0"
            },
            {
                    "Duner Butka", "320", "", "7", "160"
            },
            {
                    "Banicharnica", "330", "", "7", "170"
            },
            {
                    "Non Stop Shop", "350", "", "8", "180"
            },
            {
                    "Train Station Ruska Biala", "200", "", "train", "70"
            },
            {
                    "Chance", "", "Bank", "", "0"
            },
            {
                    "Hotel Rodina", "360", "", "8", "180"
            },
            {
                    "SUPER TAX", "", "Bank", "", "200"
            },
            {
                    "Chinese shop", "370", "", "8", "190"
            }

    };

    public void getInfo(String check) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(check)) {
                System.out.println(i);
                break;
            }
        }
    }

    /*
    Types in the console where the player is
    Types in the console if this place has an owner different than "Bank"
    Returns he position the player is on
     */

    public String boardPos(int pos) {
        System.out.print(board[pos][0]);

        if (!board[pos][2].equals("") && !isBankBelonging(pos)) {
            System.out.println(" Property of: " + board[pos][2]);
        } else System.out.println();

        return board[pos][0];
    }

    /*
    Returns the position only
     */
    public String bPos(int i) {
        return board[i][0];
    }

    /*
    Returns the owner of the place
     */
    public String placeOwner(int pos) {
        return board[pos][2];
    }

    /*
        Checks if the place has owner
     */
    public boolean isFree(int i) {
        return board[i][2].equals("");
    }

    /*
    Sets the new owner of the place
     */
    public void setOwner(int i, String owner) {
        board[i][2] = owner;
    }

    /*
    Checks if the property is owned by the Bank
     */
    public boolean isBankBelonging(int i) {
        if (i == 0 || i == 4 || i == 7 || i == 10 || i == 12 || i == 19 || i == 21 || i == 28 || i == 30 || i == 35 || i == 37)
            return true;
        else return false;
    }

    /*
    Asks and checks if player is buying the place
     */
    public void isBuying(int pos) {
        System.out.println("Would you like to buy this property? Write \'y\' for yes or \'n\' for no");
        System.out.println("The place costs: " + board[pos][1]);
        char choiceToBuy = input.next().charAt(0);
        if (choiceToBuy != 'n' && choiceToBuy != 'y') {
            System.out.println("Wrong input.");
            isBuying(pos);
        } else if (choiceToBuy == 'n') {
            isBuying = false;
        } else if (choiceToBuy == 'y') {
            System.out.println("You bought this property!");
            isBuying = true;
        }
    }
    public boolean getIsBuying(){
        return isBuying;
    }

    /*
    returns the price of the place
     */
    public int placePrize(int pos) {
        return Integer.parseInt(board[pos][1]);
    }

    public boolean ownsThePlace(int i, String nick) {
        return nick == board[i][2] || board[i][2].equals("");
    }

    public int feeTax(int i) {
        return Integer.parseInt(board[i][4]);
    }

    public boolean isChancePos(int i) {
        return board[i][0].equals("Chance");
    }

    public boolean getsInJail(int pos) {
        return board[pos][0].equals("GO TO JAIL");
    }

    public int getSellPrize(String place) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(place)) {
                return Integer.parseInt(board[i][1]) / 2;
            }
        }
        return 0;
    }

    public void removeOwner(String place) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(place)) {
                board[i][2] = "";
            }
        }
    }

    public void removeProperty(int i) {
        board[i][2] = "";
    }
}
