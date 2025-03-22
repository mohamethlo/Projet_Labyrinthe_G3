import java.util.Scanner;
import javax.swing.SwingUtilities;

public class TestLabyrinthe 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choisissez le mode d'affichage :");
        System.out.println("1 - Interface Graphique");
        System.out.println("0 - Mode Terminal");
        System.out.print("Votre choix : ");
        
        int choix = scanner.nextInt();
        scanner.close();

        Labyrinthe lab = new Labyrinthe(10, 10);

        // Affichage du Labyrinthe en mode interface graphique
        if (choix == 1) 
            SwingUtilities.invokeLater(() -> new LabyrintheGUI(lab).setVisible(true));

        // Affichage du Labyrinthe en mode terminal
        else 
        {
            // Affichage du Labyrinthe initial
            System.out.println("\nLabyrinthe Initial :");
            lab.afficher();

            // Resolution du Labyrinthe avec l'algorithme DFS
            System.out.println("\nRésolution avec DFS :");
            if (DFSSolver.solve(lab))  
                lab.afficher();
            else 
                System.out.println("Aucun chemin trouvé avec DFS.");

            // Resolution du Labyrinthe avec l'algorithme DFS
            System.out.println("\nRésolution avec BFS :");
            if (BFSSolver.solve(lab)) 
                lab.afficher(); 
            else 
                System.out.println("Aucun chemin trouvé avec BFS.");
        }
    }
}
