package solutions.DetonateTheMaximumBombs;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        //int[][] bombs = new int[][]{{2,1,3},{6,1,4}};
        int[][] bombs = new int[][]{{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        //int[][] bombs = new int[][]{{4,4,3},{4,4,3}};
        System.out.println(solution.maximumDetonation(bombs));;
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int current=0; current<n; current++){
            int x1 = bombs[current][0];
            int y1 = bombs[current][1];
            long radiusSquare = (long)bombs[current][2]*bombs[current][2];
            graph.put(current, new LinkedList<>());

            for(int neighbour=0; neighbour<n; neighbour++){
                int x2 = bombs[neighbour][0];
                int y2 = bombs[neighbour][1];

                if(current!=neighbour){
                    long distanceSquare = (long)(x2-x1)*(x2-x1) + (long)(y2-y1)*(y2-y1);
                    if(radiusSquare>=distanceSquare){
                        graph.get(current).add(neighbour);
                    }
                }
            }
        }

        int maxDetonatedBombs = 0;
        for(int current=0; current<n; current++){
            Set<Integer> visited = new HashSet<>();
            dfs(current, visited, graph);
            maxDetonatedBombs = Math.max(maxDetonatedBombs, visited.size());
        }

        return maxDetonatedBombs;

    }

    private void dfs(int current, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        visited.add(current);
        for(Integer neighbour : graph.get(current)){
            if(!visited.contains(neighbour)){
                dfs(neighbour, visited, graph);
            }
        }
    }


}