#Groupe 3 
-mjhh
- Marvin Lloyd KOUTELE
- Mohameth LO
- Zeinebou El Wely CHEIKH ABD EDAIM

## Résolution de Labyrinthe

Ce projet implémente une application permettant de visualiser et comparer différents algorithmes de résolution de labyrinthe. Il utilise des algorithmes classiques de parcours de graphe (BFS et DFS) pour trouver un chemin entre un point de départ et un point d'arrivée dans un labyrinthe.

## Fonctionnalités

- Génération aléatoire de labyrinthes
- Résolution automatique avec deux algorithmes différents:
  - BFS (Breadth-First Search / Parcours en largeur)
  - DFS (Depth-First Search / Parcours en profondeur)
- Visualisation des solutions avec interface graphique
- Comparaison des performances (temps d'exécution et nombre d'étapes)
- Mode terminal pour les environnements sans interface graphique

## Captures d'écran

*![Image](https://github.com/user-attachments/assets/c6e77076-b5ba-4d12-aef8-b46b8b694a73)*

## Structure du projet

Le projet est organisé en plusieurs classes Java:

- `Labyrinthe.java`: Définit la structure du labyrinthe et gère sa génération/chargement
- `BFSSolver.java`: Implémente l'algorithme de résolution BFS
- `DFSSolver.java`: Implémente l'algorithme de résolution DFS
- `LabyrintheGUI.java`: Gère l'interface graphique pour la visualisation
- `TestLabyrinthe.java`: Point d'entrée de l'application

## Explication des algorithmes

### BFS (Breadth-First Search)
L'algorithme BFS explore le labyrinthe niveau par niveau, en visitant d'abord tous les voisins directs d'une case avant de passer aux cases suivantes. Il utilise une file d'attente (Queue) pour stocker les cellules à visiter.

Avantages:
- Garantit de trouver le chemin le plus court
- Très efficace pour les labyrinthes avec beaucoup de chemins possibles

### DFS (Depth-First Search)
L'algorithme DFS explore le labyrinthe en profondeur, en suivant un chemin aussi loin que possible avant de revenir en arrière. Il utilise une pile (Stack) pour stocker les cellules à visiter.

Avantages:
- Utilise moins de mémoire que BFS dans certains cas
- Peut être plus rapide pour trouver une solution (mais pas nécessairement la plus courte)

## Comment exécuter le projet

### Prérequis
- Java JDK 8 ou supérieur

### Compilation et Exécution
- `ls && javac TestLabyrinthe.java && java TestLabyrinthe`

Suivez les instructions à l'écran pour choisir le mode d'affichage (interface graphique ou terminal).

## Représentation du labyrinthe

- `#` : Mur
- `S` : Point de départ (Start)
- `E` : Point d'arrivée (End)
- `+` : Chemin de la solution
- `=` : Case libre

## Personnalisation

Vous pouvez modifier la taille du labyrinthe en changeant les paramètres dans la méthode `main` de `TestLabyrinthe.java`:

```java
Labyrinthe lab = new Labyrinthe(10, 10);  // Hauteur, Largeur
```

## Idées d'améliorations futures

- Implémenter d'autres algorithmes (A*, Dijkstra, etc.)
- Ajouter un générateur de labyrinthe plus sophistiqué
- Permettre à l'utilisateur de dessiner son propre labyrinthe
- Ajouter des animations pour visualiser le processus de résolution pas à pas
- Implémenter un système de sauvegarde/chargement de labyrinthes personnalisés


Projet réalisé dans le cadre d'un projet de groupe en Master 1 GLSI
