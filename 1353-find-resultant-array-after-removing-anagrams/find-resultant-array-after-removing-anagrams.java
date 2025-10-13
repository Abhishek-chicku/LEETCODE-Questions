class Solution {
    private int[] primes = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    private int mod = 1000000007;

    private int normal(String s) {
        long res = 1;
        for (char c : s.toCharArray())
            res = (res * primes[c - 'a']) % mod;
        return (int) res;
    }

    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        res.add(words[0]);
        int prev = normal(words[0]);

        for (int i = 1; i < words.length; i++) {
            int key = normal(words[i]);
            if (key != prev) {
                res.add(words[i]);
                prev = key;
            }
        }

        return res;
    }
}