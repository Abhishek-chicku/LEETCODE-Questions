class Solution {
    public int maxProfit(int[] price) {
        int result = 0;
        int minPrice = price[0];
        for(int i=1;i<price.length;i++) {
           if(minPrice>price[i]){
               minPrice=price[i];
           }
           else{
               result=Math.max(result,price[i]-minPrice);
           }
        }
        return result;
    }
    
}