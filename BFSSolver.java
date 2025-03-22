import java.util.LinkedList;
import java.util.Queue;

class BFSSolver 
{
    // Methode qui permet de resoudre le Labyrinthe avec la methode de BFS
    public static boolean solve(Labyrinthe lab) 
    {
        char[][] grille = lab.getGrille();
        boolean[][] visite = new boolean[grille.length][grille[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{lab.getStartX(), lab.getStartY()});
        int steps = 0;
        long startTime = System.nanoTime();

        while (!queue.isEmpty()) 
        {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            steps++;

            if (x < 0 || y < 0 || x >= grille.length || y >= grille[0].length || grille[x][y] == '#' || visite[x][y]) 
            {
                continue;
            }
            if (grille[x][y] == 'E') 
            {
                long endTime = System.nanoTime();
                System.out.println("BFS terminé en " + steps + " étapes et " + (endTime - startTime) / 1e6 + " ms");
                return true;
            }

            visite[x][y] = true;
            grille[x][y] = '+';
            queue.add(new int[]{x + 1, y});
            queue.add(new int[]{x - 1, y});
            queue.add(new int[]{x, y + 1});
            queue.add(new int[]{x, y - 1});
        }
        return false;
    }
}