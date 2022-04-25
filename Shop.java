import java.security.SecureRandom;
import java.util.Scanner;

public class Shop {
    static int money = 10000;
    static int poorTractorCost = 25000;
    static int decentTractorCost = 50000;
    static int greatTractorCost = 100000;
    static int cabbageCost = 2500;
    static int seedCost = 5000;
    static int season;
    static String answer;
    static int[][] field = new int[5][5];
    public static boolean poorTractor = false;
    public static boolean decentTractor = false;
    public static boolean greatTractor = false;
    public static boolean cabbage = false;
    public static boolean carrots = false;
    public static boolean corn = false;
    public static boolean potatoes = false;
    public static boolean wheat = false;
    public static boolean summer = false;
    public static boolean spring = false;
    public static boolean fall = false;
    public static boolean winter = false;


    public static void main(String[] args) {
        SecureRandom randomNumbers = new SecureRandom();
        Scanner input = new Scanner(System.in);
        while (money > -10000) {
            while (cabbage == false && carrots == false && corn == false && potatoes == false && wheat == false) {

                System.out.printf("You currently have $%d%n", money);
                System.out.printf("If you have -$10000 by the end of the season, you will lose your farm!%n");
                System.out.printf("Shop:%nYou can purchase a tractor: Poor Tractor: $%d, Decent Tractor: $%d, Great Tractor: $%d%n" +
                                "You can purchase seeds for your field: Cabbage: $%d, Carrots: $%d, Corn: $%d, Potatoes: $%d, Wheat: $%d%n",
                        poorTractorCost,
                        decentTractorCost,
                        greatTractorCost,
                        cabbageCost,
                        seedCost,
                        seedCost,
                        seedCost,
                        seedCost);
                System.out.printf("%nWould you like to purchase a tractor? (y or n)%n");
                answer = input.next();
                if (answer.equalsIgnoreCase("y")) {
                    System.out.printf("What tractor would you like to purchase? (Poor, Decent, Great)%n");
                    answer = input.next();
                    if (answer.equalsIgnoreCase("Poor")) {
                        money = money - poorTractorCost;
                        poorTractor = true;
                        System.out.printf("You now own a Poor Tractor%n");
                        System.out.printf("You now have $%d", money);
                    } else if (answer.equalsIgnoreCase("Decent")) {
                        money = money - decentTractorCost;
                        decentTractor = true;
                        System.out.printf("You now own a Decent Tractor%n");
                        System.out.printf("You now have $%d", money);
                    } else if (answer.equalsIgnoreCase("Great")) {
                        money = money - greatTractorCost;
                        greatTractor = true;
                        System.out.printf("YOu now own a Great Tractor%n");
                        System.out.printf("You now have $%d", money);
                    } else {
                        throw new IllegalArgumentException("Error: input must be one of the tractor types listed");
                    }
                } else if (answer.equalsIgnoreCase("n")) {
                } else {
                    throw new IllegalArgumentException("Error: input must be 'y' or 'n'");
                }
                System.out.printf("%n%nWhat seeds would you like to purchase to plant for next season?" +
                        " (cabbage, carrots, corn, potatoes, or wheat)%n");
                answer = input.next();
                if (answer.equalsIgnoreCase("cabbage")) {
                    money = money - cabbageCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted cabbage%n");
                    cabbage = true;
                } else if (answer.equalsIgnoreCase("carrots")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted carrots%n");
                    carrots = true;
                } else if (answer.equalsIgnoreCase("corn")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted corn%n");
                    corn = true;
                } else if (answer.equalsIgnoreCase("potatoes")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted potatoes%n");
                    potatoes = true;
                } else if (answer.equalsIgnoreCase("wheat")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted wheat%n");
                    wheat = true;
                } else {
                    throw new IllegalArgumentException("Error: input must be one of the crops listed above");
                }
            }
            if (cabbage == true | carrots == true | corn == true | potatoes == true | wheat == true) {
                System.out.printf("The season is beginning:%n");
                season = randomNumbers.nextInt(3);
                switch (season) {
                    case 1:
                        System.out.printf("The season is Fall.%n");
                        fall = true;
                        break;
                    case 2:
                        System.out.printf("The season is Summer.%n");
                        summer = true;
                        break;
                    case 3:
                        System.out.printf("The season is Spring.%n");
                        spring = true;
                        break;
                    case 0:
                        System.out.printf("The season is Winter.%n");
                        winter = true;
                        break;
                }
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field[i].length; j++) {
                        System.out.printf("%d ", field[i][j]);
                    }
                    System.out.println();
                }
            }
        }
        if(money <= -10000){
            System.out.printf("You have lost your farm!%nGame Over!");
        }
    }
}