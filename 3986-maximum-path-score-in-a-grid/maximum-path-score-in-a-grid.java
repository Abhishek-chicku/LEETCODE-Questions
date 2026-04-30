class Solution {

    static int[] cost={0,1,1};
    static int[] profit={0,1,2};

    int[][][] t;
    
    public int maxPathScore(int[][] grid, int k) {
        int m=grid.length;
        int n=grid[0].length;

        t=new int[m][n][k+1];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                for(int a=0;a<=k;a++){
                    t[i][j][a]=-1;
                }
            }
        }
        int res=solve(0,0,k,grid);
        return res<0?-1:res;
    }
    public int solve(int x,int y,int k,int[][] grid){
        if(x>=grid.length || y>=grid[0].length)return Integer.MIN_VALUE;

        int curr=grid[x][y];
        int currCost=cost[curr];
        int currProfit=profit[curr];

        if(k<currCost)return Integer.MIN_VALUE;

        if(x==grid.length-1 && y==grid[0].length-1)return currProfit;

        if(t[x][y][k]!=-1)return t[x][y][k];

        int rest=k-currCost;

        int left=solve(x+1,y,rest,grid);
        int right=solve(x,y+1,rest,grid);

        int res=currProfit+Math.max(left,right);
        return t[x][y][k]=res;
    }
}