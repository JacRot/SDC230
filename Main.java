import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main.java  (Week 2 — single-file version)
 *
 * In Java, a file can hold several classes but only ONE may be public,
 * and it must match the file name. So this file is named Main.java and
 * the public class is Main. All the other classes (Game, Character,
 * Mission, Item, Player, GameWorld) live in the same file as
 * package-private classes.
 *
 * Week 2 features demonstrated here:
 *   - At least one additional class (Character, Mission, Item)
 *   - private fields with getters and setters
 *   - objects created using constructors
 *   - objects stored in ArrayLists
 *   - menu options to CREATE objects
 *   - menu options to DISPLAY stored objects
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}


/**
 * Game.java
 *
 * The controller class. Owns the main loop, displays the menu, reads
 * input with a Scanner, and stores all created objects in ArrayLists.
 */
class Game {

    // One Scanner, reused for all input.
    private final Scanner scanner = new Scanner(System.in);

    // Week 1 foundation (kept so the project keeps building on itself).
    private final Player player = new Player("Hero");
    private final GameWorld world = new GameWorld();

    // Week 2: objects are stored in ArrayLists.
    private final ArrayList<Character> characters = new ArrayList<>();
    private final ArrayList<Mission> missions = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();

    // Controls whether the menu loop keeps running.
    private boolean running = true;

    /**
     * The main loop: show the menu, read a choice, handle it, repeat
     * until the user quits.
     */
    public void run() {
        System.out.println("=================================");
        System.out.println("   Welcome to MY JAVA GAME!");
        System.out.println("=================================");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            handleChoice(choice);
        }

        System.out.println("Thanks for playing. Goodbye!");
        scanner.close();
    }

    /**
     * Prints the main menu to the console.
     */
    private void showMenu() {
        System.out.println();
        System.out.println("------------- MAIN MENU -------------");
        System.out.println(" Create");
        System.out.println("   1) Create Character");
        System.out.println("   2) Create Mission");
        System.out.println("   3) Create Item");
        System.out.println(" View");
        System.out.println("   4) View All Characters");
        System.out.println("   5) View All Missions");
        System.out.println("   6) View All Items");
        System.out.println(" Game");
        System.out.println("   7) View Player Stats");
        System.out.println("   8) Explore the World");
        System.out.println("   9) Help");
        System.out.println("   0) Quit");
        System.out.print("Choose an option: ");
    }

    /**
     * Reacts to whatever the user typed.
     */
    private void handleChoice(String choice) {
        switch (choice) {
            case "1": createCharacter(); break;
            case "2": createMission();   break;
            case "3": createItem();      break;
            case "4": displayCharacters(); break;
            case "5": displayMissions();   break;
            case "6": displayItems();      break;
            case "7": player.printStats(); break;
            case "8": world.explore(player); break;
            case "9": showHelp(); break;
            case "0": running = false; break;
            default:
                System.out.println("'" + choice + "' isn't a valid option. Try again.");
        }
    }

    // -------------------- CREATE OBJECTS --------------------

    private void createCharacter() {
        System.out.println("--- New Character ---");
        String name = ask("Name: ");
        int health = askInt("Health (e.g. 100): ");
        int strength = askInt("Strength (e.g. 10): ");

        // Object created using a constructor, then stored in the ArrayList.
        Character c = new Character(name, health, strength);
        characters.add(c);
        System.out.println("Created character: " + c.getName());
    }

    private void createMission() {
        System.out.println("--- New Mission ---");
        String title = ask("Title: ");
        String description = ask("Description: ");

        Mission m = new Mission(title, description);
        missions.add(m);
        System.out.println("Created mission: " + m.getTitle());
    }

    private void createItem() {
        System.out.println("--- New Item ---");
        String name = ask("Item name: ");
        String type = ask("Type (e.g. weapon, potion): ");
        int quantity = askInt("Quantity: ");

        Item i = new Item(name, type, quantity);
        items.add(i);
        System.out.println("Created item: " + i.getName());
    }

    // -------------------- DISPLAY OBJECTS --------------------

    private void displayCharacters() {
        System.out.println("--- CHARACTERS (" + characters.size() + ") ---");
        if (characters.isEmpty()) {
            System.out.println("No characters yet. Use option 1 to create one.");
            return;
        }
        for (Character c : characters) {
            c.printInfo();
        }
    }

    private void displayMissions() {
        System.out.println("--- MISSIONS (" + missions.size() + ") ---");
        if (missions.isEmpty()) {
            System.out.println("No missions yet. Use option 2 to create one.");
            return;
        }
        for (Mission m : missions) {
            m.printInfo();
        }
    }

    private void displayItems() {
        System.out.println("--- ITEMS (" + items.size() + ") ---");
        if (items.isEmpty()) {
            System.out.println("No items yet. Use option 3 to create one.");
            return;
        }
        for (Item i : items) {
            i.printInfo();
        }
    }

    // -------------------- HELPERS --------------------

    /** Prompts the user and returns the line they typed (trimmed). */
    private String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /** Like ask(), but keeps asking until a valid whole number is entered. */
    private int askInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private void showHelp() {
        System.out.println("HELP: Pick a number from the menu and press Enter.");
        System.out.println("Create characters, missions, and items, then view them.");
    }
}


/**
 * Character.java
 *
 * Lays the foundation for player settings. Demonstrates private fields
 * with getters and setters, plus a constructor.
 */
class Character {
    private String name;
    private int health;
    private int strength;

    // Constructor
    public Character(String name, int health, int strength) {
        this.name = name;
        this.health = health;
        this.strength = strength;
    }

    // Getters
    public String getName()   { return name; }
    public int getHealth()    { return health; }
    public int getStrength()  { return strength; }

    // Setters
    public void setName(String name)        { this.name = name; }
    public void setHealth(int health)       { this.health = health; }
    public void setStrength(int strength)   { this.strength = strength; }

    public void printInfo() {
        System.out.println("  Character: " + name
                + " | HP: " + health
                + " | STR: " + strength);
    }
}


/**
 * Mission.java
 *
 * Specifies what characters do and how. Tracks whether it's complete.
 */
class Mission {
    private String title;
    private String description;
    private boolean completed;

    // Constructor (new missions start out not completed)
    public Mission(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    // Getters
    public String getTitle()        { return title; }
    public String getDescription()  { return description; }
    public boolean isCompleted()    { return completed; }

    // Setters
    public void setTitle(String title)              { this.title = title; }
    public void setDescription(String description)  { this.description = description; }
    public void setCompleted(boolean completed)     { this.completed = completed; }

    public void printInfo() {
        String status = completed ? "[DONE]" : "[OPEN]";
        System.out.println("  Mission " + status + " " + title + " - " + description);
    }
}


/**
 * Item.java
 *
 * Helps maintain inventory. Each item has a name, a type, and a count.
 */
class Item {
    private String name;
    private String type;
    private int quantity;

    // Constructor
    public Item(String name, String type, int quantity) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }

    // Getters
    public String getName()     { return name; }
    public String getType()     { return type; }
    public int getQuantity()    { return quantity; }

    // Setters
    public void setName(String name)            { this.name = name; }
    public void setType(String type)            { this.type = type; }
    public void setQuantity(int quantity)       { this.quantity = quantity; }

    public void printInfo() {
        System.out.println("  Item: " + name
                + " (" + type + ") x" + quantity);
    }
}


/**
 * Player.java  (kept from Week 1)
 *
 * Represents the person playing. Simple stats for now.
 */
class Player {
    private String name;
    private int health;
    private int level;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.level = 1;
    }

    public void reset() {
        this.health = 100;
        this.level = 1;
    }

    public void printStats() {
        System.out.println("--- PLAYER STATS ---");
        System.out.println("Name:   " + name);
        System.out.println("Health: " + health);
        System.out.println("Level:  " + level);
    }

    public String getName()  { return name; }
    public int getHealth()   { return health; }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public void levelUp() {
        level++;
    }
}


/**
 * GameWorld.java  (kept from Week 1)
 *
 * Represents the world the player explores. Mostly placeholders.
 */
class GameWorld {
    private boolean generated = false;

    public void generate() {
        generated = true;
        System.out.println("The world has been generated.");
    }

    public void explore(Player player) {
        if (!generated) {
            generate(); // auto-generate the first time so explore always works
        }
        System.out.println(player.getName() + " explores the world...");
        System.out.println("(Nothing dangerous here yet — gameplay coming soon!)");
    }
}
