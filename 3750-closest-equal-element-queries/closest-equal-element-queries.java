class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            map.putIfAbsent(num, new ArrayList<>());
            map.get(num).add(i);
        }

        for (int index : queries) {
            List<Integer> indices = map.get(nums[index]);

            if (indices.size() == 1) {
                ans.add(-1);
                continue;
            }

            int pos = BS(index, indices);
            int size = indices.size();
            int minDis = n;

            if (pos == 0) {
                minDis = Math.min(indices.get(1) - index,
                        n - indices.get(size - 1) + index);
            } 
            else if (pos == size - 1) {
                minDis = Math.min(index - indices.get(size - 2),
                        n + indices.get(0) - index);
            } 
            else {
                minDis = Math.min(index - indices.get(pos - 1),
                        indices.get(pos + 1) - index);
            }

            ans.add(minDis);
        }

        return ans;
    }

    public int BS(int target, List<Integer> arr) {
        int l = 0, r = arr.size() - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (arr.get(mid) == target) return mid;
            else if (target > arr.get(mid)) l = mid + 1;
            else r = mid - 1;
        }

        return -1;
    }
}