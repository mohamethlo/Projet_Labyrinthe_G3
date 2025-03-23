import javax.swing.*;
import java.awt.*;

class LabyrintheGUI extends JFrame 
{
    private Labyrinthe lab;
    private boolean dfsSolved;
    private boolean bfsSolved;
    private double dfsTime;
    private double bfsTime;
    private int dfsSteps;
    private int bfsSteps;

    // Constructeur
    public LabyrintheGUI(Labyrinthe lab) 
    {
        this.lab = lab;
        setTitle("Resolution du Labyrinthe");
        setSize(900, 550);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        initUI();
    }

    // Methode qui permet d'initialiser les composants de l'interface graphique
    public void initUI()
    {
        // Backgroud principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.GREEN);
        mainPanel.setBounds(10, 10, 870, 500);  
        mainPanel.setLayout(null);
        add(mainPanel);

        // Deuxieme backgroud
        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBounds(10, 10, 850, 480);  
        innerPanel.setLayout(null);
        mainPanel.add(innerPanel);

        // Panel qui contient le titre
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.GREEN);
        titlePanel.setBounds(10, 10, 830, 50);
        JLabel titleLabel = new JLabel("Resolution du Labyrinthe", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        innerPanel.add(titlePanel);

        // Panel pour l'affichage du Labyrinthe
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBounds(20, 70, 810, 280);  
        contentPanel.setLayout(new GridLayout(1, 3, 10, 10)); 
        innerPanel.add(contentPanel);

        char[][] originalGrid = copyGrid(lab.getGrille());
        
        // Affichage du labyrinthe initial
        contentPanel.add(creerPanelLab("Labyrinthe Initial", originalGrid));

        // Résoudre avec DFS et capturer le résultat
        Labyrinthe labDFS = new Labyrinthe(lab.getGrille().length, lab.getGrille()[0].length);
        labDFS.setGrille(copyGrid(originalGrid));
        labDFS.setStartX(lab.getStartX());
        labDFS.setStartY(lab.getStartY());
        labDFS.setEndX(lab.getEndX());
        labDFS.setEndY(lab.getEndY());
        
        long dfsStartTime = System.nanoTime();
        dfsSolved = DFSSolver.solve(labDFS);
        long dfsEndTime = System.nanoTime();
        dfsTime = (dfsEndTime - dfsStartTime) / 1_000_000; 
        dfsSteps = DFSSolver.getSteps();
        
        // Affichage DFS
        JPanel dfsPanel = creerPanelLab(dfsSolved ? "Resolution DFS" : "DFS - Pas de chemin trouve", labDFS.getGrille());
        contentPanel.add(dfsPanel);
        
        // Résoudre avec BFS et capturer le résultat
        Labyrinthe labBFS = new Labyrinthe(lab.getGrille().length, lab.getGrille()[0].length);
        labBFS.setGrille(copyGrid(originalGrid));
        labBFS.setStartX(lab.getStartX());
        labBFS.setStartY(lab.getStartY());
        labBFS.setEndX(lab.getEndX());
        labBFS.setEndY(lab.getEndY());
        
        long bfsStartTime = System.nanoTime();
        bfsSolved = BFSSolver.solve(labBFS);
        long bfsEndTime = System.nanoTime();
        bfsTime = (bfsEndTime - bfsStartTime) / 1_000_000; 
        bfsSteps = BFSSolver.getSteps();
        
        // Affichage BFS
        JPanel bfsPanel = creerPanelLab(bfsSolved ? "Resolution BFS" : "BFS - Pas de chemin trouve", labBFS.getGrille());
        contentPanel.add(bfsPanel);

        // Panel de comparaison
        JPanel comparisonPanel = new JPanel();
        comparisonPanel.setBackground(Color.LIGHT_GRAY);
        comparisonPanel.setBounds(20, 360, 810, 60);
        comparisonPanel.setLayout(new BorderLayout());
        innerPanel.add(comparisonPanel);

        // Texte de comparaison
        JTextArea comparisonText = new JTextArea();
        comparisonText.setEditable(false);
        comparisonText.setBackground(Color.LIGHT_GRAY);
        comparisonText.setFont(new Font("Arial", Font.PLAIN, 14));
        comparisonText.setLineWrap(true);
        comparisonText.setWrapStyleWord(true);
        
        if (!dfsSolved && !bfsSolved) 
        {
            comparisonText.setText("Aucun chemin n a ete trouve par les deux methodes. Le labyrinthe pourrait ne pas avoir de solution.");
        } 
        else 
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Comparaison: ");
            
            if (dfsSolved) {
                sb.append("DFS a termine en ").append(dfsSteps).append(" etapes et ").append(dfsTime).append(" ms. ");
            } else {
                sb.append("DFS n a pas trouve de solution. ");
            }
            
            if (bfsSolved) {
                sb.append("BFS a termine en ").append(bfsSteps).append(" etapes et ").append(bfsTime).append(" ms. ");
            } else {
                sb.append("BFS n a pas trouve de solution. ");
            }
            
            if (dfsSolved && bfsSolved) {
                String faster = dfsTime < bfsTime ? "DFS" : "BFS";
                String efficient = dfsSteps < bfsSteps ? "DFS" : "BFS";
                sb.append("\nResultat: ").append(faster).append(" etait plus rapide en temps, et ").append(efficient).append(" a utilise moins d'etapes.");
            }
            
            comparisonText.setText(sb.toString());
        }
        
        comparisonPanel.add(comparisonText, BorderLayout.CENTER);

        // Panel qui contient les boutons en bas
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(10, 430, 830, 50);
        innerPanel.add(buttonPanel);

        // Configuration du bouton qui permet de regenerer un nouveau Labyrinthe avec ses solution
        JButton regenButton = new JButton("Regenerer Labyrinthe");
        regenButton.setBackground(Color.GREEN);
        regenButton.setForeground(Color.WHITE);
        regenButton.setFont(new Font("Arial", Font.BOLD, 14));
        regenButton.addActionListener(e -> {
            dispose();
            new LabyrintheGUI(new Labyrinthe(10, 10)).setVisible(true);
        });

        // Configuration du bouton qui permet de quitter le programme
        JButton quitButton = new JButton("Quitter");
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(new Font("Arial", Font.BOLD, 14));
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(regenButton);
        buttonPanel.add(quitButton);
    }

    // Méthode pour copier une grille
    private char[][] copyGrid(char[][] original) {
        char[][] copy = new char[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    // Methode qui permet de creer le Panel qui contient le Labyrinthe en mode interface graphique
    private JPanel creerPanelLab(String title, char[][] grille) 
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(grille.length, grille[0].length, 1, 1));
        gridPanel.setBackground(Color.BLACK);

        for (char[] row : grille) 
        {
            for (char cell : row) 
            {
                JLabel label = new JLabel(" ");
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(10, 10)); 
                if (cell == '#') 
                    label.setBackground(Color.BLACK); 
                else if (cell == 'S') 
                    label.setBackground(Color.BLUE); 
                else if (cell == 'E') 
                    label.setBackground(Color.RED); 
                else if (cell == '+') 
                    label.setBackground(Color.yellow); 
                else 
                    label.setBackground(Color.WHITE); 
                
                gridPanel.add(label);
            }
        }

        panel.add(gridPanel, BorderLayout.CENTER);
        return panel;
    }
}










