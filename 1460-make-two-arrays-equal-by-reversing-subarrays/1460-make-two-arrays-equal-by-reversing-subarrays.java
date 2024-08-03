class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        return mySol_freq2(target, arr);
    }

    public boolean mySol_freq2(int[] target, int[] arr) {
        int[] freq = new int[1001];

        for (int i = 0; i < target.length; i++) {
            freq[target[i]]++;
            freq[arr[i]]--;
        }

        for (int f : freq) {
            if (f != 0) return false;
        }

        return true;
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