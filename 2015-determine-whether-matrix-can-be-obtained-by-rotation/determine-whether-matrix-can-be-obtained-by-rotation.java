import java.util.*;

class Solution {

    void rotate(int[][] mat){
        int n = mat.length;
        int[][] temp = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                temp[i][j] = mat[j][n-i-1];
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mat[i][j] = temp[i][j];
            }
        }
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        for(int i=0;i<4;i++){
            if(Arrays.deepEquals(mat, target)) return true;
            rotate(mat);
        }
        return false;
    }
}