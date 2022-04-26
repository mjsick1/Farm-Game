import java.security.SecureRandom;
import java.util.Scanner;

public class FarmGame {

    // Variable declarations

    static int money = 10000;
    static double fieldWorth;
    static int fieldStatus;
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
    public static String tractorString;
    public static boolean cabbage = false;
    public static boolean carrots = false;
    public static boolean corn = false;
    public static boolean potatoes = false;
    public static boolean wheat = false;
    public static boolean summer = false;
    public static String cropsString;
    public static boolean spring = false;
    public static boolean fall = false;
    public static boolean winter = false;
    public static String seasonString;



    public static void main(String[] args) {
        SecureRandom randomNumbers = new SecureRandom();
        Scanner input = new Scanner(System.in);

        // Loop that runs the game given that the conditions for the game being lost or won are not met.

        while (money > -10000 && money < 1000000) {
            cabbage = false;
            carrots = false;
            corn = false;
            potatoes = false;
            wheat = false;
            summer = false;
            spring = false;
            fall = false;
            winter = false;

            // Loop that runs the shop given that the player owns no crops. If crops are owned, the season will be run.
            while (!cabbage && !carrots && !corn && !potatoes && !wheat) {


                // SHOP \\
                // Explains the amount of money the user has, the losing condition, and the prices for each shop item.
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

                // Asks the user if they would like to buy a tractor

                System.out.printf("%nWould you like to purchase a tractor? (y or n)%n");

                // Allows the user to answer

                answer = input.next();
                if (answer.equalsIgnoreCase("y")) {
                    System.out.printf("What tractor would you like to purchase? (Poor, Decent, Great)%n");
                    answer = input.next();

                    // Decides what conditions change when a poor tractor is bought

                    if (answer.equalsIgnoreCase("Poor")) {
                        money = money - poorTractorCost;
                        poorTractor = true;
                        decentTractor = false;
                        greatTractor = false;
                        tractorString = "poor";
                        System.out.printf("You now own a Poor Tractor%n");
                        System.out.printf("You now have $%d", money);

                        // Decides what conditions change when a decent tractor is bought

                    } else if (answer.equalsIgnoreCase("Decent")) {
                        money = money - decentTractorCost;
                        decentTractor = true;
                        poorTractor = false;
                        greatTractor = false;
                        tractorString = "decent";
                        System.out.printf("You now own a Decent Tractor%n");
                        System.out.printf("You now have $%d", money);

                        // Decides what conditions change when a great tractor is bought

                    } else if (answer.equalsIgnoreCase("Great")) {
                        money = money - greatTractorCost;
                        greatTractor = true;
                        decentTractor = false;
                        poorTractor = false;
                        tractorString = "great";
                        System.out.printf("YOu now own a Great Tractor%n");
                        System.out.printf("You now have $%d", money);

                        // throws an exception if an invalid input is entered

                    } else {
                        throw new IllegalArgumentException("Error: input must be one of the tractor types listed");
                    }
                    // Continues the code if the user decides not to buy a tractor
                } else if (answer.equalsIgnoreCase("n")) {
                    // Throws an exception if an invalid input is entered.
                } else {
                    throw new IllegalArgumentException("Error: input must be 'y' or 'n'");
                }

                // If no tractors are bought, sets the string for the constructor for SeasonConditions to represent no tractors bought.
                if (!greatTractor && !decentTractor && !poorTractor) {
                    tractorString = "none";
                }

                // Asks the user what type of seeds they would like to purchase
                System.out.printf("%n%nWhat seeds would you like to purchase to plant for next season?" +
                        " (cabbage, carrots, corn, potatoes, or wheat)%n");

                // Allows the user to respond and adjusts conditions according to answer.
                answer = input.next();
                if (answer.equalsIgnoreCase("cabbage")) {
                    money = money - cabbageCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted cabbage%n");
                    cabbage = true;
                    cropsString = "cabbage";
                } else if (answer.equalsIgnoreCase("carrots")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted carrots%n");
                    carrots = true;
                    cropsString = "carrots";
                } else if (answer.equalsIgnoreCase("corn")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted corn%n");
                    corn = true;
                    cropsString = "corn";
                } else if (answer.equalsIgnoreCase("potatoes")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted potatoes%n");
                    potatoes = true;
                    cropsString = "potatoes";
                } else if (answer.equalsIgnoreCase("wheat")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted wheat%n");
                    wheat = true;
                    cropsString = "wheat";
                    // Throws an exception if an invalid input is given
                } else {
                    throw new IllegalArgumentException("Error: input must be one of the crops listed above");
                }
            }

            // SEASON \\
            // Runs this section of code (the season) if a type of seed is owned
            if (cabbage | carrots | corn | potatoes | wheat) {
                System.out.printf("The season is beginning:%n");
                if (season > 4) {
                    season = 1;
                }
                switch (season) {
                    case 1:
                        System.out.printf("The season is Fall.%n");
                        fall = true;
                        seasonString = "fall";
                        break;
                    case 2:
                        System.out.printf("The season is Winter.%n");
                        winter = true;
                        seasonString = "winter";
                        break;
                    case 3:
                        System.out.printf("The season is Spring.%n");
                        spring = true;
                        seasonString = "spring";
                        break;
                    case 4:
                        System.out.printf("The season is Summer.%n");
                        summer = true;
                        seasonString = "summer";
                        break;
                }

                // Initialization of constructor that determines the worth of a field given conditions
                SeasonConditionsConstructor season1 = new SeasonConditionsConstructor(seasonString, tractorString, cropsString);


                // Prints a field
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field[i].length; j++) {
                        System.out.printf("%d ", field[i][j]);
                    }
                    System.out.println();
                }

                // Generates random numbers to decide what events take place in the season.
                for(int counter = 0; counter < 25; counter++){
                    fieldStatus = randomNumbers.nextInt(25);
                    // Switch containing what random events take place based on the fieldStatus variable
                   switch(fieldStatus){
                       case 1:
                           break;
                       case 2:
                           System.out.printf("Your field has been hit by a natural disaster! Half your crops are destroyed!%n");
                           fieldWorth = fieldWorth * .5;
                       case 3:
                           System.out.printf("Your field is growing extremely well! The value has doubled!%n");
                           fieldWorth = fieldWorth * 2;
                       case 4:
                           System.out.printf("Your field has been infested with pests." +
                                   " Would you like to spray with pesticide for $1000? (y or n)" +
                           "If not, your crops will be worth much less.%n");
                           answer = input.next();
                           if(answer.equalsIgnoreCase("y")){
                               money = money - 1000;
                               System.out.printf("Your crops have been sprayed with pesticide.");
                           }
                           if(answer.equalsIgnoreCase("n")){
                               System.out.printf("Your crops will remain infested.%n");
                               fieldWorth = fieldWorth * .25;
                           }

                   }
                   // If fieldStatus = 1, the season is ended by breaking from the for loop
                   if (fieldStatus == 1){
                       break;
                   }

                }
            }
        }
        // Checks the loss condition
        if (money <= -10000) {
            System.out.printf("You have lost your farm!%nGame Over!");
        }
        // Checks the win condition
        if(money >= 1000000){
            System.out.printf("You have earned $1000000%nYou win!");
        }
    }
}
