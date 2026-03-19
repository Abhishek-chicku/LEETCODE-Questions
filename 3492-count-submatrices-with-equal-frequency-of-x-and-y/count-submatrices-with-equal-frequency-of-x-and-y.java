class Solution {
    class Pair{
        int xc;
        int yc;
        public Pair(int a,int b){
            this.xc=a;
            this.yc=b;
        }
    }
    public int numberOfSubmatrices(char[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        Pair pre[][]=new Pair[n][m];
    
        if(grid[0][0]=='X'){
            pre[0][0]=new Pair(1,0);   
        }
        else if(grid[0][0]=='Y'){
            pre[0][0]=new Pair(0,1);      
        }
        else{
            pre[0][0]=new Pair(0,0);
            
        }
        int c=0;
        for(int i=1;i<n;i++){
            if(grid[i][0]=='.'){
                pre[i][0]=new Pair(pre[i-1][0].xc,pre[i-1][0].yc);
                
            }
            else if(grid[i][0]=='X'){
                pre[i][0]=new Pair(pre[i-1][0].xc+1,pre[i-1][0].yc);
                
            }
            else{
                pre[i][0]=new Pair(pre[i-1][0].xc,pre[i-1][0].yc+1);
               
            }
            if(pre[i][0].xc==pre[i][0].yc && pre[i][0].xc>0) c++;
        }
        for(int i=1;i<m;i++){
            if(grid[0][i]=='.'){
                pre[0][i]=new Pair(pre[0][i-1].xc,pre[0][i-1].yc);
               
            }
            else if(grid[0][i]=='X'){
                pre[0][i]=new Pair(pre[0][i-1].xc+1,pre[0][i-1].yc);
             
            }
            else{
                pre[0][i]=new Pair(pre[0][i-1].xc,pre[0][i-1].yc+1);
            }
            if(pre[0][i].xc == pre[0][i].yc && pre[0][i].xc > 0)
    c++;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                int xcnt=0;
                int ycnt=0;
                if(grid[i][j]=='.'){
                    xcnt=pre[i-1][j].xc+pre[i][j-1].xc-pre[i-1][j-1].xc;
                    ycnt=pre[i-1][j].yc+pre[i][j-1].yc-pre[i-1][j-1].yc;
                    
                }
                else if(grid[i][j]=='X'){
                    xcnt=pre[i-1][j].xc+pre[i][j-1].xc+1-pre[i-1][j-1].xc;
                    ycnt=pre[i-1][j].yc+pre[i][j-1].yc-pre[i-1][j-1].yc;
                }
                else{
                    xcnt=pre[i-1][j].xc+pre[i][j-1].xc-pre[i-1][j-1].xc;
                    ycnt=pre[i-1][j].yc+pre[i][j-1].yc+1-pre[i-1][j-1].yc;
                }
                pre[i][j]=new Pair(xcnt,ycnt);
                if(xcnt==ycnt && xcnt>0) c++;
            }
        }
        return c;
    }
}