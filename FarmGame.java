import java.security.SecureRandom;
import java.util.Scanner;

public class FarmGame {

    // Variable declarations

    static int money = 10000;
    static double fieldWorth;
    static int fieldStatus;
    static int poorTractorCost = 50000;
    static int decentTractorCost = 100000;
    static int greatTractorCost = 500000;
    static int cabbageCost = 2500;
    static int seedCost = 5000;
    static int pesticideCost = 750;
    static int season;
    static int extraLowChance;
    static int fieldAlreadyGrown;
    static String answer;
    static int[][] field = new int[5][5];
    public static boolean poorTractor = false;
    public static boolean decentTractor = false;
    public static boolean greatTractor = false;
    public static String tractorString;
    public static boolean pesticide;
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
    public static boolean pesticideHappened = false;
    public static boolean fireHappened = false;
    public static boolean greatCropsHappened = false;
    public static boolean disasterHappened = false;
    public static boolean waitingOnPoorTractor = false;
    public static boolean waitingOnDecentTractor = false;
    public static boolean waitingOnGreatTractor = false;
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
            fieldWorth = 10000;

            // Loop that runs the shop given that the player owns no crops. If crops are owned, the season will be run.
            while (!cabbage && !carrots && !corn && !potatoes && !wheat) {


                // SHOP \\
                // Explains the amount of money the user has, the losing condition, and the prices for each shop item.
                System.out.printf("You currently have $%d%n", money);
                System.out.printf("If you have -$10000 by the end of the season, you will lose your farm!%n");
                System.out.printf("Shop:%nYou can purchase a tractor: Poor Tractor: $%d, Decent Tractor: $%d, Great Tractor: $%d%n" +
                                "You can purchase Pesticide for your field: $%d%n" +
                                "You can purchase seeds for your field: Cabbage: $%d, Carrots: $%d, Corn: $%d, Potatoes: $%d, Wheat: $%d%n",
                        poorTractorCost,
                        decentTractorCost,
                        greatTractorCost,
                        pesticideCost,
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
                        waitingOnPoorTractor = true;
                        waitingOnDecentTractor = false;
                        waitingOnGreatTractor = false;
                        System.out.printf("Your new poor tractor will arrive next season!%n");
                        System.out.printf("You now have $%d", money);
                        System.out.printf("%nPress Enter to continue...%n");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }

                        // Decides what conditions change when a decent tractor is bought

                    } else if (answer.equalsIgnoreCase("Decent")) {
                        money = money - decentTractorCost;
                        waitingOnDecentTractor = true;
                        waitingOnPoorTractor = false;
                        waitingOnGreatTractor = false;
                        System.out.printf("Your new decent tractor will arrive next season!%n");
                        System.out.printf("You now have $%d", money);
                        System.out.printf("%nPress Enter to continue...%n");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }

                        // Decides what conditions change when a great tractor is bought

                    } else if (answer.equalsIgnoreCase("Great")) {
                        money = money - greatTractorCost;
                        waitingOnGreatTractor = true;
                        waitingOnDecentTractor = false;
                        waitingOnPoorTractor = false;
                        System.out.printf("Your new great tractor will arrive next season!%n");
                        System.out.printf("You now have $%d", money);
                        System.out.printf("%nPress Enter to continue...%n");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }

                        // throws an exception if an invalid input is entered

                    } else {
                        throw new IllegalArgumentException("Input must be one of the tractor types listed above.");
                    }
                }
                // Continues the code if the user decides not to buy a tractor
                else if (answer.equalsIgnoreCase("n")) {
                    // Throws an exception if an invalid input is entered.
                } else {
                    throw new IllegalArgumentException("Input must be 'y' or 'n'.");
                }

                // If no tractors are bought, sets the string for the constructor for SeasonConditions to represent no tractors bought.
                if (!greatTractor && !decentTractor && !poorTractor) {
                    tractorString = "none";
                }

                System.out.printf("Would you like to purchase pesticide for this season for 750? (y or n)%n");
                answer = input.next();
                if(answer.equalsIgnoreCase("y")){
                    pesticide = true;
                    money = money - pesticideCost;
                    System.out.printf("You have purchased pesticide.%nYou now have $%d%n", money);
                    System.out.printf("%nPress Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                } else if (answer.equalsIgnoreCase("n")){
                } else {
                    throw new IllegalArgumentException("Input must be 'y' or 'n'.");
                }

                // Asks the user what type of seeds they would like to purchase
                System.out.printf("What seeds would you like to purchase to plant for next season?" +
                        " (cabbage, carrots, corn, potatoes, or wheat)%n");

                // Allows the user to respond and adjusts conditions according to answer.
                answer = input.next();
                if (answer.equalsIgnoreCase("cabbage")) {
                    money = money - cabbageCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted cabbage%n");
                    cabbage = true;
                    cropsString = "cabbage";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                } else if (answer.equalsIgnoreCase("carrots")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted carrots%n");
                    carrots = true;
                    cropsString = "carrots";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                } else if (answer.equalsIgnoreCase("corn")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted corn%n");
                    corn = true;
                    cropsString = "corn";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                } else if (answer.equalsIgnoreCase("potatoes")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted potatoes%n");
                    potatoes = true;
                    cropsString = "potatoes";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                } else if (answer.equalsIgnoreCase("wheat")) {
                    money = money - seedCost;
                    System.out.printf("You now have $%d%n", money);
                    System.out.printf("You have planted wheat%n");
                    wheat = true;
                    cropsString = "wheat";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    // Throws an exception if an invalid input is given
                } else {
                    throw new IllegalArgumentException("Input must be one of the crops listed above.");
                }
            }

            // SEASON \\
            // Runs this section of code (the season) if a type of seed is owned
            if (cabbage | carrots | corn | potatoes | wheat) {
                System.out.printf("The season is beginning:%n");
                if (season > 4) {
                    season = 0;
                }
                switch (season) {
                    case 3:
                        System.out.printf("The season is Fall.%n");
                        fall = true;
                        seasonString = "fall";
                        System.out.printf("Press Enter to continue...%n");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                        break;
                    case 2:
                        System.out.printf("The season is Winter.%n");
                        winter = true;
                        seasonString = "winter";
                        System.out.printf("Press Enter to continue...%n");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                        break;
                    case 0:
                        System.out.printf("The season is Spring.%n");
                        spring = true;
                        seasonString = "spring";
                        System.out.printf("Press Enter to continue...%n");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                        break;
                    case 1:
                        System.out.printf("The season is Summer.%n");
                        summer = true;
                        seasonString = "summer";
                        System.out.printf("Press Enter to continue...%n");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                        break;
                }

                // Initialization of constructor that determines the worth of a field given conditions
                SeasonConditionsConstructor season1 = new SeasonConditionsConstructor(seasonString, tractorString, cropsString);
                if (season1.getSeasonName() == "spring") {
                    if (season1.getCrop() != "cabbage" && season1.getCrop() != "carrots") {
                        fieldWorth = fieldWorth * 1.5;
                    }
                    if (season1.getCrop() == "carrots") {
                        fieldWorth = fieldWorth * 1.8;
                    }
                    if (season1.getCrop() == "cabbage") {
                        fieldWorth = fieldWorth * .5;
                    }
                    if (season1.getTractor() == "poor") {
                        fieldWorth = fieldWorth * 2;
                    }
                    if (season1.getTractor() == "decent") {
                        fieldWorth = fieldWorth * 5;
                    }
                    if (season1.getTractor() == "great") {
                        fieldWorth = fieldWorth * 10;
                    }
                }
                if (season1.getSeasonName() == "summer") {
                    fieldWorth = 8500;
                    if (season1.getCrop() != "cabbage" && season1.getCrop() != "corn") {
                        fieldWorth = fieldWorth * 1.4;
                    }
                    if (season1.getCrop() == "corn") {
                        fieldWorth = fieldWorth * 1.7;
                    }
                    if (season1.getCrop() == "cabbage") {
                        fieldWorth = fieldWorth * .5;
                    }
                    if (season1.getTractor() == "poor") {
                        fieldWorth = fieldWorth * 2;
                    }
                    if (season1.getTractor() == "decent") {
                        fieldWorth = fieldWorth * 5;
                    }
                    if (season1.getTractor() == "great") {
                        fieldWorth = fieldWorth * 10;
                    }
                }
                if (season1.getSeasonName() == "fall") {
                    fieldWorth = 5000;
                    if (season1.getCrop() != "cabbage" && season1.getCrop() != "potatoes") {
                        fieldWorth = fieldWorth * 1.1;
                    }
                    if (season1.getCrop() == "potatoes") {
                        fieldWorth = fieldWorth * 1.4;
                    }
                    if (season1.getCrop() == "cabbage") {
                        fieldWorth = fieldWorth * .5;
                    }
                    if (season1.getTractor() == "poor") {
                        fieldWorth = fieldWorth * 2;
                    }
                    if (season1.getTractor() == "decent") {
                        fieldWorth = fieldWorth * 5;
                    }
                    if (season1.getTractor() == "great") {
                        fieldWorth = fieldWorth * 10;
                    }
                }
                if (season1.getSeasonName() == "winter") {
                    fieldWorth = 2500;
                    if (season1.getCrop() != "cabbage" && season1.getCrop() != "wheat") {
                        fieldWorth = fieldWorth * .8;
                    }
                    if (season1.getCrop() == "wheat") {
                        fieldWorth = fieldWorth * 1.6;
                    }
                    if (season1.getCrop() == "cabbage") {
                        fieldWorth = fieldWorth * .5;
                    }
                    if (season1.getTractor() == "poor") {
                        fieldWorth = fieldWorth * 2;
                    }
                    if (season1.getTractor() == "decent") {
                        fieldWorth = fieldWorth * 5;
                    }
                    if (season1.getTractor() == "great") {
                        fieldWorth = fieldWorth * 10;
                    }
                }


                // Prints a field
                for (int i = 0; i < field.length; i++) {
                    for (int j = 0; j < field[i].length; j++) {
                        System.out.printf("%d ", field[i][j]);
                    }
                    System.out.println();
                }

                // Generates random numbers to decide what events take place in the season.
                for (int counter = 0; counter < 25; counter++) {
                    fieldStatus = randomNumbers.nextInt(20);
                    // Switch containing what random events take place based on the fieldStatus variable
                    switch (fieldStatus) {
                        case 1:
                            System.out.printf("Your field has grown!%n");
                            System.out.printf("Press Enter to continue...%n");
                            try {
                                System.in.read();
                            } catch (Exception e) {
                            }
                            fieldAlreadyGrown = 1;
                            break;
                        case 2:
                            if (!greatCropsHappened) {
                                if (!disasterHappened) {
                                    if (!spring && !summer) {
                                        extraLowChance = randomNumbers.nextInt(2);
                                        if (extraLowChance == 1) {
                                            disasterHappened = true;
                                            System.out.printf("Your field has been hit by a natural disaster! Half your crops are destroyed!%n");
                                            fieldWorth = fieldWorth * .5;
                                            System.out.printf("Press Enter to continue...%n");
                                            try {
                                                System.in.read();
                                            } catch (Exception e) {
                                            }
                                        }
                                    } else {
                                        disasterHappened = true;
                                        System.out.printf("Your field has been hit by a natural disaster! Half your crops are destroyed!%n");
                                        fieldWorth = fieldWorth * .5;
                                        System.out.printf("Press Enter to continue...%n");
                                        try {
                                            System.in.read();
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            }
                        case 3:
                            if (!greatCropsHappened) {
                                extraLowChance = randomNumbers.nextInt(2);
                                if (extraLowChance == 1) {
                                    if (spring) {
                                        greatCropsHappened = true;
                                        System.out.printf("Your field is growing extremely well! The value has doubled!%n");
                                        fieldWorth = fieldWorth * 2;
                                        System.out.printf("Press Enter to continue...%n");
                                        try {
                                            System.in.read();
                                        } catch (Exception e) {
                                        }
                                    } else {
                                        extraLowChance = randomNumbers.nextInt(2);
                                        if (extraLowChance == 1) {
                                            greatCropsHappened = true;
                                            System.out.printf("Your field is growing extremely well! The value has doubled!%n");
                                            fieldWorth = fieldWorth * 2;
                                            System.out.printf("Press Enter to continue...%n");
                                            try {
                                                System.in.read();
                                            } catch (Exception e) {
                                            }
                                        }
                                    }
                                }
                            }
                        case 4:
                            if (!pesticide){
                                if (!greatCropsHappened) {
                                    if (!pesticideHappened) {
                                        extraLowChance = randomNumbers.nextInt(2);
                                        if (extraLowChance == 1) {
                                            if (!winter && !fall) {
                                                pesticideHappened = true;
                                                System.out.printf("Your field has been infested with pests." +
                                                        " Would you like to spray with pesticide for $3500? (y or n)%n" +
                                                        "If not, your crops will be worth much less.%n");
                                                answer = input.next();
                                                if (answer.equalsIgnoreCase("y")) {
                                                    money = money - 3500;
                                                    System.out.printf("Your crops have been sprayed with pesticide.%n");
                                                    System.out.printf("You now have $%d%n", money);
                                                    System.out.printf("Press Enter to continue...%n");
                                                    try {
                                                        System.in.read();
                                                    } catch (Exception e) {
                                                    }
                                                }
                                                if (answer.equalsIgnoreCase("n")) {
                                                    System.out.printf("Your crops will remain infested.%n");
                                                    System.out.printf("Press Enter to continue...%n");
                                                    try {
                                                        System.in.read();
                                                    } catch (Exception e) {
                                                    }
                                                    fieldWorth = fieldWorth * .25;
                                                }
                                            } else {
                                                pesticideHappened = true;
                                                System.out.printf("Your field has been infested with pests." +
                                                        " Would you like to spray with pesticide for $3500? (y or n)%n" +
                                                        "If not, your crops will be worth much less.%n");
                                                answer = input.next();
                                                if (answer.equalsIgnoreCase("y")) {
                                                    money = money - 3500;
                                                    System.out.printf("Your crops have been sprayed with pesticide.%n");
                                                    System.out.printf("You now have $%d%n", money);
                                                    System.out.printf("Press Enter to continue...%n");
                                                    try {
                                                        System.in.read();
                                                    } catch (Exception e) {
                                                    }
                                                }
                                                if (answer.equalsIgnoreCase("n")) {
                                                    System.out.printf("Your crops will remain infested.%n");
                                                    System.out.printf("Press Enter to continue...%n");
                                                    try {
                                                        System.in.read();
                                                    } catch (Exception e) {
                                                    }
                                                    fieldWorth = fieldWorth * .25;
                                                }
                                            }
                                        }
                                    }
                                }
                    }
                                case 5:
                                    if (!greatCropsHappened) {
                                        if (!fireHappened) {
                                            if (!winter) {
                                                extraLowChance = randomNumbers.nextInt(2);
                                                if (extraLowChance == 1){
                                                    fireHappened = true;
                                                money = money - 5000;
                                                System.out.printf("Your crops have been destroyed by a fire! You must pay $5000 to repair your farm!%n");
                                                System.out.printf("You now have $%d%n", money);
                                                System.out.printf("Press Enter to continue...%n");
                                                try {
                                                    System.in.read();
                                                } catch (Exception e) {
                                                }
                                            }
                                        } else {
                                                fireHappened = true;
                                                money = money - 5000;
                                                System.out.printf("Your crops have been destroyed by a fire! You must pay $5000 to repair your farm!%n");
                                                System.out.printf("You now have $%d%n", money);
                                                System.out.printf("Press Enter to continue...%n");
                                                try {
                                                    System.in.read();
                                                } catch (Exception e) {
                                                }
                                            }
                                        }
                                    }
                            }
                            if (fieldStatus == 1) {
                                fieldAlreadyGrown = 1;
                                break;
                            }
                    }
                    fieldStatus = 1;
                    if (fieldAlreadyGrown == 0) {
                        System.out.printf("Your field has grown!%n");
                        System.out.printf("Press Enter to continue...%n");
                        fieldAlreadyGrown = 0;
                        fieldStatus = 0;
                        try {
                            System.in.read();
                        } catch (Exception e) {
                        }
                    }
                    fieldStatus = 0;
                }
                season++;
                money += fieldWorth;
                if (waitingOnPoorTractor) {
                    waitingOnPoorTractor = false;
                    poorTractor = true;
                    decentTractor = false;
                    greatTractor = false;
                    tractorString = "poor";
                    System.out.printf("Your poor tractor has arrived!%n");
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                }
                if (waitingOnDecentTractor) {
                    waitingOnDecentTractor = false;
                    poorTractor = false;
                    decentTractor = true;
                    greatTractor = false;
                    tractorString = "decent";
                    System.out.printf("Your decent tractor has arrived!%n");
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                }
                if (waitingOnGreatTractor) {
                    waitingOnGreatTractor = false;
                    poorTractor = false;
                    decentTractor = false;
                    greatTractor = true;
                    tractorString = "great";
                    System.out.printf("Your great tractor has arrived!%n");
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                }
            }
            // Checks the loss condition
            if (money <= -10000) {
                System.out.printf("You are $%d in debt!%nYou have lost your farm!%nGame Over!", money * -1);
            }
            // Checks the win condition
            if (money >= 1000000) {
                System.out.printf("You have earned $%d%nCongratulations!%nYou win!", money);
            }
        }
    }