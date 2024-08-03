class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        return mySol(target, arr);
    }

    public boolean mySol(int[] target, int[] arr) {
        Map<Integer, Integer> freq = new HashMap();

        for (int i = 0; i < target.length; i++) {
            int t = target[i];
            int n = arr[i];

            freq.put(t, freq.getOrDefault(t, 0) + 1);
            freq.put(n, freq.getOrDefault(n, 0) - 1);

            if (freq.containsKey(t) && freq.get(t) == 0) {
                freq.remove(t);
            }

            if (freq.containsKey(n) && freq.get(n) == 0) {
                freq.remove(n);
            }
        }

        return freq.size() == 0;
    }
}