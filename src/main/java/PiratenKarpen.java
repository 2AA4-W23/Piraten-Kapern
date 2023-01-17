import pk.Simulation;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        // Simulate the game
        Simulation sim = new Simulation();
        sim.run();

        // Display simulation statistics
        sim.displayStats();
    }
    
}
