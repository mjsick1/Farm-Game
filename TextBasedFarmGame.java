import java.security.SecureRandom;
import java.util.Scanner;

public class TextBasedFarmGame {
    //random number generation
    private static final SecureRandom randomNumbers = new SecureRandom();
    //user input
    private static final Scanner input = new Scanner(System.in);

    //declare player money
    static double money = 10_000.00;

    //win/lose condition constants
    static final double WIN_CONDITION = 100_000.00;
    static final double LOSE_CONDITION = -10_000;

    //weather constants.
    static final int WEATHER_RAINY = 0;
    static final int WEATHER_SUNNY = 1;
    static final int WEATHER_CLOUDY = 2;
    static final int WEATHER_SNOWY = 3;

    //Declare constants for percent chance of weather
    // (WEATHER_CHANCE_HIGH + 2(WEATHER_CHANCE_MEDIUM) + WEATHER_CHANCE_LOW) should equal 1.00
    static final double WEATHER_CHANCE_HIGH = 0.75;
    static final double WEATHER_CHANCE_MEDIUM = 0.10;
    static final double WEATHER_CHANCE_LOW = 0.05;

    //Declare crop sell and purchase price constants
    static final double PURCHASE_PRICE = 2500.00;
    static final double SELL_PRICE_GOOD = 7500.00;
    static final double SELL_PRICE_MEDIUM = 5000.00;
    static final double SELL_PRICE_POOR = 2500.00;

    //declare price for purchasing pesticides
    static final double PESTICIDE_PURCHASE_PRICE = 1000;

    //declare crop price reduction constants.
    static final double PESTICIDES = 0.90;
    static final double INSECTS = 0.60;
    static final double TORNADO = 0.35;
    static final double FROST = 0.85;

    //declare constants for percent chance of natural disasters.
    static final int INSECTS_CHANCE_RAINY = 20;
    static final int INSECTS_CHANCE_SUNNY = 65;
    static final int INSECTS_CHANCE_CLOUDY = 40;
    static final int INSECTS_CHANCE_SNOWY = 5;
    static final int TORNADO_CHANCE = 10;
    static final int FROST_CHANCE = 30;

    //declare strings
    static String answer;
    static String cropsString;

    //Declare constants for tractor durability
    static final int TRACTOR_GOOD_DURABILITY_MAX = 60;
    static final int TRACTOR_GOOD_DURABILITY_MIN = 40;
    static final int TRACTOR_MEDIUM_DURABILITY_MAX = 50;
    static final int TRACTOR_MEDIUM_DURABILITY_MIN = 30;
    static final int TRACTOR_POOR_DURABILITY_MAX = 40;
    static final int TRACTOR_POOR_DURABILITY_MIN = 20;
    static final int TRACTOR_USED_DURABILITY_MAX = 50;
    static final int TRACTOR_USED_DURABILITY_MIN = 5;

    //Declare constants for tractor price
    static final double TRACTOR_GOOD_PRICE = 25_000.00;
    static final double TRACTOR_MEDIUM_PRICE = 15_000.00;
    static final double TRACTOR_POOR_PRICE = 10_000.00;
    static final double TRACTOR_USED_PRICE = 5_000.00;

    public static void main(String[] args) {
        //declare season arrays and season counter
        int seasonCurrentCounter = 0;
        double[] seasonCurrent = new double[3];

        //declare int for tractor durability
        int tractorDurability = 0;

        //declare arrays for each of three land plots
        double[] field1 = new double[5];
        /*double[] field2 = new double[5];
        double[] field3 = new double[5];*/

        //declare pesticides boolean for each of three land plots
        boolean pesticideField1 = false;
        /*boolean pesticideField2 = false;
        boolean pesticideField3 = false;*/

        /*for the season arrays,
        Index 0 is rainy weather chance, index 1 is sunny weather chance, index 2 is cloudy weather chance.
        Index 3 is not necessary. If it is not rainy, sunny, or cloudy, it will default to snowy.
        {rainy weather chance, sunny weather chance, cloudy weather chance}
        */
        double[] seasonSpring = {WEATHER_CHANCE_HIGH, WEATHER_CHANCE_MEDIUM, WEATHER_CHANCE_LOW};
        double[] seasonSummer = {WEATHER_CHANCE_MEDIUM, WEATHER_CHANCE_HIGH, WEATHER_CHANCE_MEDIUM};
        double[] seasonFall = {WEATHER_CHANCE_LOW, WEATHER_CHANCE_MEDIUM, WEATHER_CHANCE_HIGH};
        double[] seasonWinter = {WEATHER_CHANCE_MEDIUM, WEATHER_CHANCE_LOW, WEATHER_CHANCE_MEDIUM};

        //Declare crop arrays.
        /* Indexes 0-3 are used to calculate sell price for specific seasons.
        The seasons correspond to the weather constants listed above, so index 0 is used for WEATHER_RAINY etc.
        Index 4 is the purchase price of the crop.
        {Rainy sell price, Sunny sell price, Cloudy sell price, Snowy sell price, purchase price}
         */
        double[] carrots = {SELL_PRICE_GOOD, SELL_PRICE_MEDIUM, SELL_PRICE_POOR, SELL_PRICE_MEDIUM, PURCHASE_PRICE};
        double[] corn = {SELL_PRICE_MEDIUM, SELL_PRICE_GOOD, SELL_PRICE_MEDIUM, SELL_PRICE_POOR, PURCHASE_PRICE};
        double[] potatoes = {SELL_PRICE_POOR, SELL_PRICE_MEDIUM, SELL_PRICE_GOOD, SELL_PRICE_MEDIUM, PURCHASE_PRICE};
        double[] wheat = {SELL_PRICE_MEDIUM, SELL_PRICE_POOR, SELL_PRICE_MEDIUM, SELL_PRICE_GOOD, PURCHASE_PRICE};
        double[] cabbage = {SELL_PRICE_MEDIUM, SELL_PRICE_MEDIUM, SELL_PRICE_MEDIUM, SELL_PRICE_MEDIUM, PURCHASE_PRICE};

        while (money > LOSE_CONDITION && money < WIN_CONDITION) {
            switch (seasonCurrentCounter % 4) { //determines which season it is. Repeats every 4 seasons.
                case 0:     //spring
                    seasonCurrent = seasonSpring;
                    System.out.println("The current season is Spring");
                    break;
                case 1:     //summer
                    seasonCurrent = seasonSummer;
                    System.out.println("The current season is Summer");
                    break;
                case 2:     //fall
                    seasonCurrent = seasonFall;
                    System.out.println("The current season is Fall");
                    break;
                default:    //default is winter
                    seasonCurrent = seasonWinter;
                    System.out.println("The current season is Winter");
                    break;
            }

            System.out.println();



            // SHOP \\
            // Explains the amount of money the user has, the losing condition, and the prices for each shop item.
            System.out.printf("You currently have $%.0f%n", money);
            System.out.printf("If you have %.0f dollars by the end of the season, you will lose your farm!%n", LOSE_CONDITION);
            System.out.printf("You can purchase Cabbage, Carrots, Corn, Potatoes, or Wheat for $%.0f.%n" +
                            "You can also purchase Pesticide for your field for $%.0f.%n",
                    PURCHASE_PRICE, PESTICIDE_PURCHASE_PRICE
            );
            //Purchase tractor. You must purchase a tractor if your current tractor has no durability.
            if (tractorDurability <= 0) {
                while (tractorDurability <= 0) {
                    System.out.printf("%nYou have no tractor. You must purchase a tractor to harvest crops.%n" +
                                    "You can purchase a good tractor for $%.0f, a decent tractor for $%.0f, " +
                                    "a poor tractor for $%.0f, or a used tractor for $%.0f",
                            TRACTOR_GOOD_PRICE, TRACTOR_MEDIUM_PRICE, TRACTOR_POOR_PRICE, TRACTOR_USED_PRICE);
                    while (answer != "good" && answer != "decent" && answer != "poor" && answer != "used") {
                        System.out.printf("%nWhat type of tractor would you like to purchase?" +
                                "(good, decent, poor, used)%n");
                        answer = input.next();

                        //determine if the answer is valid and adjust tractorDurability accordingly.
                        if (answer.equalsIgnoreCase("Good")) {
                            money -= TRACTOR_GOOD_PRICE;
                            tractorDurability = randomNumbers.nextInt(
                                    TRACTOR_GOOD_DURABILITY_MAX - TRACTOR_GOOD_DURABILITY_MIN)
                                    + TRACTOR_GOOD_DURABILITY_MIN;
                            System.out.printf("You now have $%.0f", money);
                            System.out.printf("%nPress Enter to continue...%n");
                            try {
                                System.in.read();
                            } catch (Exception e) {
                            }
                            break;
                        } else if (answer.equalsIgnoreCase("Decent")) {
                            money -= TRACTOR_MEDIUM_PRICE;
                            tractorDurability = randomNumbers.nextInt(
                                    TRACTOR_MEDIUM_DURABILITY_MAX - TRACTOR_MEDIUM_DURABILITY_MIN)
                                    + TRACTOR_MEDIUM_DURABILITY_MIN;
                            System.out.printf("You now have $%.0f", money);
                            System.out.printf("%nPress Enter to continue...%n");
                            try {
                                System.in.read();
                            } catch (Exception e) {
                            }
                            break;
                        } else if (answer.equalsIgnoreCase("Poor")) {
                            money -= TRACTOR_POOR_PRICE;
                            tractorDurability = randomNumbers.nextInt(
                                    TRACTOR_POOR_DURABILITY_MAX - TRACTOR_POOR_DURABILITY_MIN)
                                    + TRACTOR_POOR_DURABILITY_MIN;
                            System.out.printf("You now have $%.0f", money);
                            System.out.printf("%nPress Enter to continue...%n");
                            try {
                                System.in.read();
                            } catch (Exception e) {
                            }
                            break;
                        } else if (answer.equalsIgnoreCase("Used")) {
                            money -= TRACTOR_USED_PRICE;
                            tractorDurability = randomNumbers.nextInt(
                                    TRACTOR_USED_DURABILITY_MAX - TRACTOR_USED_DURABILITY_MIN)
                                    + TRACTOR_USED_DURABILITY_MIN;
                            System.out.printf("You now have $%.0f", money);
                            System.out.printf("%nPress Enter to continue...%n");
                            try {
                                System.in.read();
                            } catch (Exception e) {
                            }
                            break;
                        } else {
                            System.out.printf("That answer is not valid!%n" +
                                    "please enter good, decent, poor, or used");
                        }

                    }
                }
            }
            // Asks if the user would like to buy pesticide before the season.
            // This negates the chance of pest infestation during the season, which will be more expensive to fix.
            while (answer != "y" && answer != "n") {
                System.out.printf("Would you like to purchase pesticide for this season for %.0f? (y or n)%n",
                        PESTICIDE_PURCHASE_PRICE);
                answer = input.next();
                if (answer.equalsIgnoreCase("y")) {
                    pesticideField1 = true;
                    money = money - PESTICIDE_PURCHASE_PRICE;
                    System.out.printf("You have purchased pesticide.%nYou now have $%.0f%n", money);
                    System.out.printf("%nPress Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    break;
                } else if (answer.equalsIgnoreCase("n")) {
                    // breaks loop if input is 'n'
                    break;
                }
                if (answer != "y" && answer != "n") {
                    System.out.printf("Invalid input. Input must be 'y' or 'n'%n");
                }
            }
            answer = null;

            // Asks the user what type of seeds they would like to purchase
            while (answer != "cabbage" && answer != "carrots" && answer != "corn" && answer != "potatoes" && answer != "wheat"){
                System.out.printf("What seeds would you like to purchase to plant for next season?" +
                        " (cabbage, carrots, corn, potatoes, or wheat)%n");

                // Allows the user to respond and adjusts conditions according to answer.
                answer = input.next();
                if (answer.equalsIgnoreCase("cabbage")) {
                    money -= PURCHASE_PRICE;
                    System.out.printf("You now have $%.0f%n", money);
                    System.out.printf("You have planted cabbage%n");
                    field1 = cabbage;
                    cropsString = "cabbage";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    break;
                } else if (answer.equalsIgnoreCase("carrots")) {
                    money -= PURCHASE_PRICE;
                    System.out.printf("You now have $%.0f%n", money);
                    System.out.printf("You have planted carrots%n");
                    field1 = carrots;
                    cropsString = "carrots";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    break;
                } else if (answer.equalsIgnoreCase("corn")) {
                    money -= PURCHASE_PRICE;
                    System.out.printf("You now have $%.0f%n", money);
                    System.out.printf("You have planted corn%n");
                    field1 = corn;
                    cropsString = "corn";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    break;
                } else if (answer.equalsIgnoreCase("potatoes")) {
                    money -= PURCHASE_PRICE;
                    System.out.printf("You now have $%.0f%n", money);
                    System.out.printf("You have planted potatoes%n");
                    field1 = potatoes;
                    cropsString = "potatoes";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    break;
                } else if (answer.equalsIgnoreCase("wheat")) {
                    money -= PURCHASE_PRICE;
                    System.out.printf("You now have $%.0f%n", money);
                    System.out.printf("You have planted wheat%n");
                    field1 = wheat;
                    cropsString = "wheat";
                    System.out.printf("Press Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                    break;
                }
                if(answer != "cabbage" && answer != "carrots" && answer != "corn" && answer != "potatoes" && answer != "wheat"){
                    System.out.printf("Invalid input. Input must be 'cabbage', 'carrots', 'corn', 'potatoes', or 'wheat'%n");
                }
            }

            money += calculateSellPrice(cabbage, changeWeather(seasonCurrent), pesticideField1);
            System.out.println();

            seasonCurrentCounter++;     //increment the season counter
            tractorDurability--;

            //display a message if tractor breaks
            if (tractorDurability <= 0 ) {
                System.out.println("Your tractor broke!");
                System.out.printf("%nPress Enter to continue...%n");
                try {
                    System.in.read();
                } catch (Exception e) {
                }
            }
        }

        //check for win condition and lose condition
        if (money > WIN_CONDITION) {
            System.out.printf("%nYou have $%.0f! Your farm is thriving! You won!", money);
        } else {
            System.out.printf("%nYou are $%.0f in debt. You have to sell your farm. You lost.", -money);
        }

    }   //end of main


    //Take the 3 index values of a double array and use them to give an integer between 0 and 3
    //The integer given will be used to determine the current weather conditions.
    public static int changeWeather(double[] season) {
        int weather = WEATHER_SNOWY;    //default value is snowy
        double weatherThisSeason = randomNumbers.nextDouble();
        if (weatherThisSeason <= season[0]) {   // rainy weather
            weather = WEATHER_RAINY;
            System.out.printf("%nThe weather is rainy.%n");
            System.out.printf("Press Enter to continue...%n");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        } else if (weatherThisSeason <= (season[0] + season[1])) {  //if not rainy, check for sunny
            weather = WEATHER_SUNNY;
            System.out.printf("%nThe weather is sunny.%n");
            System.out.printf("Press Enter to continue...%n");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        } else if (weatherThisSeason <= (season[0] + season[1] + season[2])) {  //if not sunny, check for cloudy
            weather = WEATHER_CLOUDY;
            System.out.printf("%nThe weather is cloudy.%n");
            System.out.printf("Press Enter to continue...%n");
            try {
                System.in.read();
            } catch (Exception e) {
            }
            //If not rainy, sunny, or cloudy, weather is  set to WEATHER_SNOWY by default. No need to check further.
        } else {
            System.out.printf("%nThe weather is snowy.%n");
            System.out.printf("Press Enter to continue...%n");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }

        return weather;
    }   //end of changeWeather

    //Take the current weather, crops in a field, and pesticides, generate natural events,
    // and calculate the sell price for the end of a season.
    public static double calculateSellPrice(
            double[] crops, int season /*Use changeWeather(seasonCurrent)*/, boolean pesticidesUsed) {

        //declare sellPrice
        double sellPrice = 0;
        //declare insectChance
        double insectChance = 0;

        //generate sellPrice based off season and randomly-generated events. Calculate percent chance for insects.
        switch (season) {

            case WEATHER_RAINY:
                sellPrice = crops[WEATHER_RAINY];
                insectChance = INSECTS_CHANCE_RAINY;
                //randomly generate if tornado appears
                if (randomNumbers.nextInt(100) < TORNADO_CHANCE) {
                    sellPrice *= TORNADO;
                    System.out.println("A tornado has destroyed part of your field!");
                    System.out.printf("%nPress Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                }
                break;

            case WEATHER_SUNNY:
                sellPrice = crops[WEATHER_SUNNY];
                insectChance = INSECTS_CHANCE_SUNNY;
                break;

            case WEATHER_CLOUDY:
                sellPrice = crops[WEATHER_CLOUDY];
                insectChance = INSECTS_CHANCE_CLOUDY;
                break;

            case WEATHER_SNOWY:
                sellPrice = crops[WEATHER_SNOWY];
                insectChance = INSECTS_CHANCE_SNOWY;
                //randomly generate if frost appears.
                if (randomNumbers.nextInt(100) < FROST_CHANCE) {
                    sellPrice *= FROST;
                    System.out.println("Frost has appeared!");
                    System.out.printf("%nPress Enter to continue...%n");
                    try {
                        System.in.read();
                    } catch (Exception e) {
                    }
                }
                break;
        }

        //reduce sellPrice if pesticides have been applied.
        // If not, randomly generate if insects appear and reduce sellPrice if they do.
        if (pesticidesUsed) {
            sellPrice *= PESTICIDES;
        } else if (randomNumbers.nextInt(100) < insectChance) {
            sellPrice *= INSECTS;
            System.out.println("Insects have appeared and eaten your crops!");
            System.out.printf("%nPress Enter to continue...%n");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }

        System.out.printf("You sold your crops for $%.0f", sellPrice);
        System.out.printf("%nPress Enter to continue...%n");
        try {
            System.in.read();
        } catch (Exception e) {
        }

        return sellPrice;
    }   //end of calculateSellPrice

}   //end of class
