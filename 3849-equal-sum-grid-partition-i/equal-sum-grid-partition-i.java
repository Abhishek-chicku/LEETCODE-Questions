class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long hc[]=new long[grid.length],vc[]=new long[grid[0].length];
        long vt=0l,ht=0l;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                vc[j]+=grid[i][j];
                hc[i]+=grid[i][j];
                
            }
            ht+=hc[i];
        }
        for(long i:vc){
            vt+=i;
        }
        long t=0l;
        for(long i:vc){
            t+=i;
            if(vt-t==t)
                return true;
        }
        t=0l;
        for(long i:hc){
            t+=i;
            if(ht-t==t)
                return true;
        }
        
        
        return false;

    }
}