package solutions.CheckIfItIsAStraightLine;

class Solution {

    public static void main(String[] args) {
        int[][] coordinates = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
        Solution solution = new Solution();
        System.out.println(solution.checkStraightLine(coordinates));
    }

    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        int xDiff = coordinates[1][0] - coordinates[0][0];      // xDiff = x2 - x1
        int yDiff = coordinates[1][1] - coordinates[0][1];      // yDiff = y2 - y1;

        for(int i=2; i<n; i++) {
            int newXDiff = coordinates[i][0] - coordinates[0][0];   // newXDiff = x3-x1;
            int newYDiff = coordinates[i][1] - coordinates[0][1];   // newYDiff = y3-y1;
            if(yDiff*newXDiff != xDiff*newYDiff)    // (y2-y1) * (x3-x1) = (x2-x1) * (y3-y1)
                return false;
        }
        return true;
    }

}
