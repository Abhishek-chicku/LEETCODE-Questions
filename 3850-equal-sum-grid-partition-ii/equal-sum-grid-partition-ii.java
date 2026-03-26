class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        HashMap<Long, Long> map1 = new HashMap<>();
        HashMap<Long, Long> map2 = new HashMap<>();
        long s1 = 0, s2 = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map1.put((long)grid[i][j], map1.getOrDefault((long)grid[i][j], 0L) + 1L);
                s1 += grid[i][j];
            }
        }

        for (int r = 0; r < n-1; r++) {
            for (int c = 0; c < m; c++) {
                long v = (long)grid[r][c];
                s2 += v;
                s1 -= v;

                map1.put(v, map1.get(v) - 1);
                if (map1.get(v) == 0) map1.remove(v);
                map2.put(v, map2.getOrDefault(v, 0L) + 1);
            }

            if (s1 == s2) return true;

            if (s1 < s2) {
                long diff = s2 - s1;
                if (m <= 1) {
                    if (map2.containsKey(diff) && (grid[0][0] == diff || grid[r][0] == diff)) return true;
                    continue;
                }
                int l = r + 1;
                if(l == 1){
                    if(map2.containsKey(diff) && (grid[0][0] == diff || grid[0][m-1] == diff))return true;
                }else{
                    if(map2.containsKey(diff))return true;
                }
            } else {
                long diff = s1 - s2;
                if (m <= 1) {
                    if (map1.containsKey(diff) && (r + 1 < n && (grid[r + 1][0] == diff || grid[n - 1][0] == diff))) return true;
                    continue;
                } 
                int l = n - r - 1;
                if(l == 1){
                    if(map1.containsKey(diff) && (grid[n-1][0] == diff || grid[n-1][m-1] == diff))return true;
                }else{
                    if(map1.containsKey(diff))return true;
                }
            }
        }

        map1 = new HashMap<>();
        map2 = new HashMap<>();
        s1 = 0;
        s2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map1.put((long)grid[i][j], map1.getOrDefault((long)grid[i][j], 0L) + 1);
                s1 += grid[i][j];
            }
        }

        for (int c = 0; c < m-1; c++) {
            for (int r = 0; r < n; r++) {
                long v = (long)grid[r][c];
                s2 += v;
                s1 -= v;

                map1.put(v, map1.get(v) - 1);
                if (map1.get(v) == 0) map1.remove(v);
                map2.put(v, map2.getOrDefault(v, 0L) + 1);
            }

            if (s1 == s2) return true;

            if (s1 < s2) {
                long diff = s2 - s1;
                if (n <= 1) {
                    if (map2.containsKey(diff) && (grid[0][0] == diff || grid[0][c] == diff)) return true;
                    continue;
                }
                int l = c + 1;
                if(l == 1){
                    if(map2.containsKey(diff) && (grid[0][0] == diff || grid[n-1][0] == diff))return true;
                }else{
                    if(map2.containsKey(diff))return true;
                }

            } else {
                long diff = s1 - s2;
                if (n <= 1) {
                    if (map1.containsKey(diff) && (c + 1 < m && (grid[0][c + 1] == diff || grid[0][m - 1] == diff))) return true;
                    continue;
                }
                int l = m - 1 - c;
                if(l == 1){
                    if(map1.containsKey(diff) && (grid[0][m-1] == diff || grid[n-1][m-1] == diff))return true;
                }else{
                    if(map1.containsKey(diff))return true;
                }
            }
        }

        return false;
    }
}