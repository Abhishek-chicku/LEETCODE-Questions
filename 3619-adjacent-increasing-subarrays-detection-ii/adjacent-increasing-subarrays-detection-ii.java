public class Solution {
    public int maxIncreasingSubarrays(int[] a) {
        int n = a == null ? 0 : a.length;
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; ++i) {
            left[i] = (a[i - 1] < a[i]) ? left[i - 1] + 1 : 1;
        }

        int[] b = new int[n];
        for (int i = 0; i < n; ++i) b[i] = a[n - 1 - i];

        int[] revAccum = new int[n];
        revAccum[0] = 1;
        for (int i = 1; i < n; ++i) {
            revAccum[i] = (b[i - 1] > b[i]) ? revAccum[i - 1] + 1 : 1;
        }

        int ans = 0;
    
        for (int k = 0; k <= n - 2; ++k) {
            int rightVal = revAccum[n - 2 - k];
            ans = Math.max(ans, Math.min(left[k], rightVal));
        }
        return ans;
    }

    public int maxIncreasingSubarrays(java.util.List<Integer> list) {
        if (list == null || list.isEmpty()) return 0;
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        return maxIncreasingSubarrays(arr);
    }
}