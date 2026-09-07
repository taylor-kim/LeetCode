class Solution {
    public int countCommas(int n) {
        return mySol(n);
    }

    public int mySol(int n) {
        //aa,bbb,ccc
        //21,234,567
        //34,567
        int[] counter = new int[32];

        int num = n;
        int index = 0;

        while (num > 0) {
            int d = 0;
            int pos = 1;
            for (int i = 0; i < 3 && num > 0; i++) {
                d += pos * (num % 10);
                num /= 10;
                pos *= 10;
            }

            counter[index++] = d;
        }

        if (index == 1) return 0;

        int highest3 = counter[index - 1];
        int others = 0;

        int pos = 1;

        for (int i = 0; i < index - 1; i++) {
            others += pos * counter[i];
            pos *= 1000;
        }

        // System.out.println(Arrays.toString(counter));

        return (highest3 - 1) * pos + (others + 1);
    }
}