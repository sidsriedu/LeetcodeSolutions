package solutions.ShortestBridge;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {
                {0,0,0,0,0},
                {1,1,0,0,1},
                {1,0,0,1,1},
                {1,1,0,0,1},
                {0,0,0,0,0}
        };
        System.out.println(solution.shortestBridge(grid));;
    }

    private int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean isIslandFound = false;

        List<int[]> currentIslandNodes = new ArrayList<>();

        for(int row=0; row<n && !isIslandFound; row++){
            for(int col=0; col<n && !isIslandFound; col++){
                if(grid[row][col]==1){
                    dfs(grid, row, col, n, currentIslandNodes);
                    isIslandFound = true;
                }
            }
        }

        int distance = 0;
        while (!currentIslandNodes.isEmpty()){
            List<int[]> extendedIslandNodes = new ArrayList<>();
            for(int[] currentIslandPair : currentIslandNodes){
                int row = currentIslandPair[0];
                int col = currentIslandPair[1];
                List<int[]> neighbourNodes = getValidNeighboursOfNode(row, col, n);
                for(int[] neighbour: neighbourNodes ){
                    int row1 = neighbour[0];
                    int col1 = neighbour[1];
                    if(grid[row1][col1]==1)
                        return distance;
                    if(grid[row1][col1]==0){
                        grid[row1][col1] = -1;
                        extendedIslandNodes.add(new int[]{row1, col1});
                    }
                }
            }
            distance++;
            currentIslandNodes = extendedIslandNodes;
        }



        return distance;
    }

    private void dfs(int[][] grid, int row, int col, int n, List<int[]> currentIslandNodePairs) {
        if(grid[row][col]==1){
            grid[row][col] = -1;
            currentIslandNodePairs.add(new int[]{row,col});
            List<int[]> neighbours = getValidNeighboursOfNode(row, col, n);
            for (int[] neighbour : neighbours){
                dfs(grid, neighbour[0], neighbour[1], n, currentIslandNodePairs);
            }
        }
    }

    private List<int[]> getValidNeighboursOfNode(int row, int col, int n) {
        ArrayList<int[]> neighbours = new ArrayList<>();
        if(!isOutOfGrid(row, col-1, n)){
            neighbours.add(new int[]{row, col-1});
        }
        if(!isOutOfGrid(row-1, col, n)){
            neighbours.add(new int[]{row-1, col});
        }
        if(!isOutOfGrid(row, col+1, n)){
            neighbours.add(new int[]{row, col+1});
        }
        if(!isOutOfGrid(row+1, col, n)){
            neighbours.add(new int[]{row+1, col});
        }
        return neighbours;
    }

    private boolean isOutOfGrid(int row, int col, int n) {
        return row<0 || row>=n || col<0 || col>=n;
    }

}
