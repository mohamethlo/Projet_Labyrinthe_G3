import java.util.Stack;

class DFSSolver 
{
    private static int steps;
    
    // Methode qui permet de resoudre le Labyrinthe avec l'algorithme DFS
    public static boolean solve(Labyrinthe lab) 
    {
        char[][] grille = lab.getGrille();
        boolean[][] visite = new boolean[grille.length][grille[0].length];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{lab.getStartX(), lab.getStartY()});
        steps = 0;
        long startTime = System.nanoTime();

        while (!stack.isEmpty()) 
        {
            int[] pos = stack.pop();
            int x = pos[0], y = pos[1];
            steps++;

            if (x < 0 || y < 0 || x >= grille.length || y >= grille[0].length || grille[x][y] == '#' || visite[x][y]) 
            {
                continue;
            }
            if (grille[x][y] == 'E') 
            {
                long endTime = System.nanoTime();
                System.out.println("DFS terminé en " + steps + " étapes et " + (endTime - startTime) / 1e6 + " ms");
                return true;
            }

            visite[x][y] = true;
            grille[x][y] = '+';
            stack.push(new int[]{x + 1, y});
            stack.push(new int[]{x - 1, y});
            stack.push(new int[]{x, y + 1});
            stack.push(new int[]{x, y - 1});
        }
        return false;
    }
    
    // Getter pour récupérer le nombre d'étapes
    public static int getSteps() {
        return steps;
    }
}





