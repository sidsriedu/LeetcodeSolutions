package solutions.ShortestPathInBinaryMatrix;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid =  {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println(solution.shortestPathBinaryMatrix(grid));
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});

        int[][] possibleDirections = new int[][]    {{-1,-1}, {-1, 0},{-1, 1},
                                                     { 0,-1},         { 0, 1},
                                                     { 1,-1}, { 1, 0},{ 1, 1}};

        int distance = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            distance++;
            while(size!=0){
                int[] currentIndex = queue.remove();
                int row = currentIndex[0];
                int col = currentIndex[1];
                if(isValidCell(currentIndex, grid, n)){

                    if(row==n-1 && col==n-1)
                        return distance;

                    grid[row][col] = 1;
                    for(int[] direction : possibleDirections){
                        queue.add(new int[]{row+direction[0], col+direction[1]});
                    }
                }
                size--;
            }
        }
        return -1;
    }

    private boolean isValidCell(int[] index, int[][] grid, int n){
        int row = index[0];
        int col = index[1];
        if(row<0 || row>=n || col<0 || col>=n || grid[row][col]==1)
            return false;
        return true;
    }


}