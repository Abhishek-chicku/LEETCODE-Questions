class Solution {
    public int missingNumber(int[] nums) {
//         int i = 0;
//         for( i =1; i<nums.length; i++){
//             int flag = 0;
//         for(int j = 0; j<nums.length; j++){
//             if(nums[j]==i){
//                      flag = i;
//                      break;
//             }
//         }
//            if(flag == 0){
//             return i;
//         }
//         }
//         return i;  
//     }
// }
        int n = nums.length;
        int Total_sum = (n * (n + 1)) / 2;
        int actualSum = Arrays.stream(nums).sum();
        return Total_sum - actualSum;
    }
}
