class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        return mySol(arr1, arr2);
    }

    public int[] mySol(int[] arr1, int[] arr2) {
        int[] freq = new int[1001];

        for (int num : arr1) {
            freq[num]++;
        }

        int index = 0;

        for (int num : arr2) {
            while (freq[num]-- > 0) {
                arr1[index++] = num;
            }
        }

        int num = 0;

        while (index < arr1.length && num < freq.length) {
            if (freq[num] <= 0) {
                num++;
                continue;
            }

            while (freq[num]-- > 0) {
                arr1[index++] = num;
            }
        }

        return arr1;
    }
}