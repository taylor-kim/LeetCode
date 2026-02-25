class Solution {
    public int[] sortByBits(int[] arr) {
        return mySol(arr);
    }

    public int[] mySol(int[] arr) {
        Integer[] ret = new Integer[arr.length];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr[i];
        }
        Arrays.sort(ret, (a, b) -> {
            int c1 = numberOf1(a);
            int c2 = numberOf1(b);
            
            return c1 != c2 ? c1 - c2 : a - b;
        });

        for (int i = 0; i < ret.length; i++) {
            arr[i] = ret[i];
        }

        return arr;
    }

    private int numberOf1(int num) {
        int mask = 1;
        int count = 0;

        while (num > 0) {
            if ((num & mask) != 0) {
                count++;
                num ^= mask;
            }

            mask <<= 1;
        }

        return count;

        // return Integer.bitCount(num);

        // int count = 0;

        // for (int pos = 0; pos < 31; pos++) {
        //     int mask = 1 << pos;

        //     if ((num & mask) != 0) {
        //         count++;
        //     }
        // }

        // return count;
    }
}