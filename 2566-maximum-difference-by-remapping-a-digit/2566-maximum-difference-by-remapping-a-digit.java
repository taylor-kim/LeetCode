class Solution {
    public int minMaxDifference(int num) {
        return mySol(num);
    }

    public int mySol(int num) {
        int max = 0;
        int min = 0;

        Integer[] remapMax = new Integer[10];
        Integer[] remapMin = new Integer[10];

        boolean foundRemapMax = false;
        boolean foundRemapMin = false;

        char[] arr = String.valueOf(num).toCharArray();

        for (int i = 0; i < arr.length; i++) {
            int dmax = arr[i] - '0';
            int dmin = arr[i] - '0';

            if (!foundRemapMax && dmax < 9) {
                remapMax[dmax] = 9;
                foundRemapMax = true;
            }

            if (!foundRemapMin) {
                remapMin[dmin] = 0;
                foundRemapMin = true;
            }

            max = max * 10 + (remapMax[dmax] != null ? remapMax[dmax] : dmax);

            min = min * 10 + (remapMin[dmin] != null ? remapMin[dmin] : dmin);
        }

        return max - min;
    }
}