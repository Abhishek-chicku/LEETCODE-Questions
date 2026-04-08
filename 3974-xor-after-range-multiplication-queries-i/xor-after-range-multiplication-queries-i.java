class Solution 
{
    public int xorAfterQueries(int[] nums, int[][] queries) 
    {
        int mod = (int) 1e9 + 7;
        int n = nums.length;
        int m = queries.length;

        for(int[] query : queries)
        {
            int idx = query[0];
            int r = query[1];
            int v = query[3];
            int k = query[2];

            while(idx <= r)
            {
                nums[idx] = (int) (((long) nums[idx] * v) % mod);
                idx += k;
            }
        }

        for(int i = 1; i < n; i++)
        {
            nums[0] ^= nums[i];
        }

        return nums[0];
    }
}