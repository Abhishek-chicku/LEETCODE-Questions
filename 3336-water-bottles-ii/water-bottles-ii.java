class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        // numBottles = numExchange ;
       int totalBottles=numBottles;
       int emptybottle=numBottles;

        while(emptybottle>=numExchange){
              emptybottle-=numExchange;
              numExchange++;
                emptybottle++;
              totalBottles++;

        }return totalBottles;
        }
      
}