class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length;
        int[] count = new int[n];

        for (int num : nums) {
            if (num >= n || num < 1) {
                return false;
            }

            count[num]++;

            if (count[num] > 1) {
                if ((num == n - 1 && count[num] > 2) || (num != n - 1)) {
                    return false;
                }
            }
        }

        return true;
    }
}