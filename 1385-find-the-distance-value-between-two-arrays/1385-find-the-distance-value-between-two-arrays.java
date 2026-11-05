class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        return mySol(arr1, arr2, d);
    }

    public int mySol(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);

        int ans = 0;

        //4,5,8
        //1,8,9,10

        // System.out.println(Arrays.toString(arr2));

        for (int i = 0; i < arr1.length; i++) {
            int target = arr1[i];

            int left = leftmost(arr2, target - d);
            int right = leftmost(arr2, target + d + 1) - 1;

            if (left < arr2.length && target - d <= arr2[left] && arr2[left] <= target + d) continue;

            if (right >= 0 && target - d <= arr2[right] && arr2[right] <= target + d) continue;

            ans++;

            // System.out.println("target1, 2:%d, %d, left:%d, right:%d".formatted(target - d, target + d + 1, left, right));
        }

        return ans;
    }

    private int leftmost(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}