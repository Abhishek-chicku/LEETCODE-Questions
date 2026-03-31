class Solution {
    char[] ans;
    boolean[] canChange;
    int n,m;
    public boolean check(int ind, String str){
        int k = 0;
        for(int i = ind; i < ind+m && k < m; i++,k++){
            if(ans[i] != 0 && ans[i] != str.charAt(k)) return false;
            ans[i] = str.charAt(k);
        }
        return true;
    }
    public String generateString(String str1, String str2) {
        this.n = str1.length();
        this.m = str2.length();
        ans = new char[n+m-1];
        canChange = new boolean[n+m-1];
        
        for(int i = 0; i < n; i++){
            if(str1.charAt(i) == 'T'){
                if(!check(i,str2)) return "";
            }
        }
        
        for(int i = 0; i < n+m-1; i++){
            if(ans[i] == 0){ 
                ans[i] = 'a';
                canChange[i] = true;
            }
        }
        
        for(int i = 0; i < n; i++){
            if(str1.charAt(i) == 'F'){
                boolean isSame = true;
                int k = 0;
                for(int j = i; j < i+m && k < m; j++,k++){
                    if(str2.charAt(k) != ans[j]){
                        isSame = false;
                        break;
                    }
                }
                if(isSame){
                    int ind = -1;
                    for(int j = i+m-1; j >= i; j--){
                        if(canChange[j]){
                            ind = j;
                            break;
                        }
                    }
                    if(ind == -1) return "";
                    else{
                        ans[ind] = 'b';
                    }
                }
            }
        }
        return new String(ans);
    }
}