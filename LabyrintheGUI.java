import javax.swing.*;
import java.awt.*;

class LabyrintheGUI extends JFrame 
{
    private Labyrinthe lab;

    // Constructeur
    public LabyrintheGUI(Labyrinthe lab) 
    {
        this.lab = lab;
        setTitle("Résolution du Labyrinthe");
        setSize(900, 500);
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
        mainPanel.setBounds(10, 10, 870, 450);
        mainPanel.setLayout(null);
        add(mainPanel);

        // Deuxieme backgroud
        JPanel innerPanel = new JPanel();
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBounds(10, 10, 850, 430);
        innerPanel.setLayout(null);
        mainPanel.add(innerPanel);

        // Panel qui contient le titre
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.GREEN);
        titlePanel.setBounds(10, 10, 830, 50);
        JLabel titleLabel = new JLabel("Résolution du Labyrinthe", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        innerPanel.add(titlePanel);

        // Panel pour l'affichage du Labyrinthe
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBounds(20, 70, 810, 300);
        contentPanel.setLayout(new GridLayout(1, 3, 10, 10)); // Trois colonnes avec espace
        innerPanel.add(contentPanel);

        // Affichage des trois labyrinthes avec les algorithme DFS et BFS
        contentPanel.add(creerPanelLab("Labyrinthe Initial", lab.getGrille()));
        DFSSolver.solve(lab); 
        contentPanel.add(creerPanelLab("Résolution DFS", lab.getGrille()));
        BFSSolver.solve(lab); 
        contentPanel.add(creerPanelLab("Résolution BFS", lab.getGrille()));

        // Panel qui contient les boutons en bas**
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(10, 380, 830, 50);
        innerPanel.add(buttonPanel);

        // Configuration du bouton qui permet de regenerer un nouveau Labyrinthe avec ses solution
        JButton regenButton = new JButton("Régénérer Labyrinthe");
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
