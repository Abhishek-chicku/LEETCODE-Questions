class Solution {
    public int dfs(int r, int c, int usedNeutralizers, Integer[][][] memo, int[][] grid) {
        if (r == grid.length || c == grid[0].length) {
            return Integer.MIN_VALUE;
        }
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            if (grid[r][c] < 0 && usedNeutralizers < 2) {
                return 0;
            }
            return grid[r][c];
        }
        if (memo[r][c][usedNeutralizers] != null) {
            return memo[r][c][usedNeutralizers];
        }

        int downMove = dfs(r + 1, c, usedNeutralizers, memo, grid);
        int rightMove = dfs(r, c + 1, usedNeutralizers, memo, grid);
        int currentPath = grid[r][c] + Math.max(downMove, rightMove);

        int skipPath = Integer.MIN_VALUE;
        if (grid[r][c] < 0 && usedNeutralizers < 2) {
            int downSkip = dfs(r + 1, c, usedNeutralizers + 1, memo, grid);
            int rightSkip = dfs(r, c + 1, usedNeutralizers + 1, memo, grid);
            skipPath = Math.max(downSkip, rightSkip);
        }

        return memo[r][c][usedNeutralizers] = Math.max(currentPath, skipPath);
    }

    public int maximumAmount(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Integer[][][] cache = new Integer[rows][cols][3];
        return dfs(0, 0, 0, cache, grid);
    }
}