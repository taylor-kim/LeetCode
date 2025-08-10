class Solution {
    public boolean reorderedPowerOf2(int n) {
        return mySol(n);
    }

    public boolean mySol(int n) {
        List<int[]> powerOfTwos = new ArrayList();

        for (int i = 0; i < 32; i++) {
            powerOfTwos.add(getFreq(1 << i));
        }

        int[] freq = getFreq(n);

        for (int[] powerOfTwo : powerOfTwos) {
            // System.out.println(Arrays.toString(freq));
            // System.out.println(Arrays.toString(powerOfTwo));

            boolean ans = true;

            for (int i = 0; i < powerOfTwo.length; i++) {
                if (freq[i] != powerOfTwo[i]) {
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