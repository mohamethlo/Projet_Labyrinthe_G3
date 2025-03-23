import java.io.*;
import java.util.*;

class Labyrinthe 
{
    private char[][] grille;
    private int largeur, hauteur;
    private int startX, startY, endX, endY;
    
    // Constructeur qui permet de charger un Labyrinthe via un fichier
    public Labyrinthe(String filename) throws IOException 
    {
        chargerLabyrinthe(filename);
    }
    
    // Constructeur qui permet de generer un Labyrinthe
    public Labyrinthe(int largeur, int hauteur) 
    {
        this.largeur = largeur;
        this.hauteur = hauteur;
        genererLabyrintheAleatoire();
    }
    
    // Methode qui permet de charger le Labyrinthe
    private void chargerLabyrinthe(String filename) throws IOException 
    {
        List<String> lignes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) 
        {
            String ligne;
            while ((ligne = br.readLine()) != null) 
            {
                lignes.add(ligne);
            }
        }
        hauteur = lignes.size();
        largeur = lignes.get(0).length();
        grille = new char[hauteur][largeur];
        
        for (int i = 0; i < hauteur; i++) 
        {
            grille[i] = lignes.get(i).toCharArray();
            for (int j = 0; j < largeur; j++) 
            {
                if (grille[i][j] == 'S') 
                {
                    startX = i;
                    startY = j;
                } 
                else if (grille[i][j] == 'E') 
                {
                    endX = i;
                    endY = j;
                }
            }
        }
    }
    
    // Methode qui permet de generer le La byrinthe
    private void genererLabyrintheAleatoire() 
    {
        Random rand = new Random();
        grille = new char[hauteur][largeur];
        
        for (int i = 0; i < hauteur; i++) 
        {
            for (int j = 0; j < largeur; j++) 
            {
                grille[i][j] = (rand.nextDouble() < 0.3) ? '#' : '='; 
            }
        }
        
        startX = 1; startY = 1;
        endX = hauteur - 2; endY = largeur - 2;
        grille[startX][startY] = 'S';
        grille[endX][endY] = 'E';
    }
    
    // Methode qui permet d'afficher le Labyrinthe
    public void afficher() 
    {
        char[][] grilleAffichage = new char[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) 
        {
            for (int j = 0; j < largeur; j++) 
            {
                grilleAffichage[i][j] = grille[i][j];
            }
        }
        grilleAffichage[startX][startY] = 'S';
        grilleAffichage[endX][endY] = 'E';
        
        for (char[] ligne : grilleAffichage) 
        {
            System.out.println(new String(ligne));
        }
    }
    
    // Getters qui permet de retouner aux attributs
    public char[][] getGrille() 
    {
        return grille;
    }
    
    public int getStartX() 
    {
        return startX;
    }
    
    public int getStartY() 
    {
        return startY;
    }
    
    public int getEndX() 
    {
        return endX;
    }
    
    public int getEndY() 
    {
        return endY;
    }

   // Setters 
    public void setGrille(char[][] grille) 
    {
        this.grille = grille;
    }

    public void setStartX(int startX) 
    {
        this.startX = startX;
    }

    public void setStartY(int startY) 
    {
        this.startY = startY;
    }

    public void setEndX(int endX) 
    {
        this.endX = endX;
    }

    public void setEndY(int endY) 
    {
        this.endY = endY;
    }
}
