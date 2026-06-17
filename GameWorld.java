/**
 * GameWorld.java
 *
 * Represents the world/level the player moves through. For Week 1
 * this is mostly placeholders so the structure is in place. Later
 * you can add rooms, maps, enemies, and items here.
 */
public class GameWorld {

    private boolean generated = false;

    /**
     * Builds the world. A placeholder for now.
     */
    public void generate() {
        generated = true;
        System.out.println("The world has been generated.");
    }

    /**
     * Lets the player explore. Behavior depends on whether a world
     * has been generated yet.
     */
    public void explore(Player player) {
        if (!generated) {
            System.out.println("There's nothing to explore yet. Start a new game first!");
            return;
        }
        System.out.println(player.getName() + " explores the world...");
        System.out.println("(Nothing dangerous here yet — gameplay coming soon!)");
        // TODO: random events, encounters, loot, movement between areas.
    }
}