class Solution {
    public boolean reorderedPowerOf2(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        int[] freq = getFreq(n);

        for (int i = 0; i < 32; i++) {
            int[] powerOfTwo = getFreq(1 << i);

            boolean ans = true;

            for (int j = 0; j < powerOfTwo.length; j++) {
                if (freq[j] != powerOfTwo[j]) {
                    ans = false;
                    break;
                }
            }

            if (ans) return true;
        }

        return false;
    }

    private int[] getFreq(int n) {
        int[] freq = new int[10];

        while (n > 0) {
            int digit = n % 10;
            freq[digit]++;

            n /= 10;
        }

        return freq;
    }
}