class Solution {
    public int maximumSwap(int num) {
        return mySol(num);
    }

    public int mySol(int num) {
        String s = String.valueOf(num);
        char[] arr = new char[s.length()];

        int[] freq = new int[10];

        Arrays.fill(freq, -1);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.charAt(i);
            freq[arr[i] - '0'] = i;
        }

        int left = 0;

        while (left < arr.length) {
            int digit = (int)(arr[left] - '0');

            // System.out.println(String.format("left:%d, digit:%d", left, digit));

            for (int max = 9; max > digit; max--) {
                if (freq[max] > -1) {
                    int j = freq[max];

                    if (left >= j) continue;

                    char temp = arr[left];
                    arr[left] = arr[j];
                    arr[j] = temp;
                    
                    return Integer.parseInt(new String(arr));
                }
            }

            left++;
        }

        return num;
    }
}