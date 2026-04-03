class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int  pI = -1;
        for(int i = n-1;i>0;i--){
            if(nums[i] > nums[i-1]){
                pI = i-1;
                break;
            }
        }
        if(pI == -1){
            Arrays.sort(nums);
            return;
        }
        int nextGreaterElementIndex = -1;
        for(int i=n-1;i>0;i--){
            if(nums[i] > nums[pI]){
                nextGreaterElementIndex = i;
                break;
            }
        }
        
        int temp = nums[nextGreaterElementIndex];
        nums[nextGreaterElementIndex] = nums[pI];
        nums[pI] = temp;

        int i = pI +1 , j=n-1;
        while(i<j){
            int x = nums[i];
            nums[i] = nums[j];
            nums[j] = x;
            i++;
            j--;
        }

    }
}