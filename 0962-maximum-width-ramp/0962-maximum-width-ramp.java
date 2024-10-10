class Solution {
    public int maxWidthRamp(int[] nums) {
        return official_sort(nums);
    }

    public int official_sort(int[] nums) {
        int n = nums.length;
        Integer[] indices = new Integer[n];

        // Initialize the array with indices
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices based on corresponding values in nums and ensure stability
        Arrays.sort(indices, (i, j) ->
            nums[i] != nums[j] ? nums[i] - nums[j] : i - j
        );

        int minIndex = n; // Minimum index encountered so far
        int maxWidth = 0;

        // Calculate maximum width ramp
        for (int i : indices) {
            maxWidth = Math.max(maxWidth, i - minIndex);
            minIndex = Math.min(minIndex, i);
        }

        return maxWidth;
    }

    public int mySol(int[] nums) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;

        int ans = 0;

        while (left < right) {
            System.out.println(String.format("left:%d, right:%d", left, right));
            if (nums[left] <= nums[right]) {
                return right - left;
            } else {
                if (nums[left + 1] - nums[left + 1] <= nums[right - 1] - nums[right]) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ans;
    }

    public int mySol_fail(int[] nums) {
        int n = nums.length;

        Integer[][] arr = new Integer[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = i;
            arr[i][1] = nums[i];
        }

        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });

        int left = 0;
        int right = n - 1;

        int ans = 0;

        for (Integer[] d : arr) {
            System.out.print(Arrays.toString(d) + ", ");
        }

        System.out.println("");

        while (left < right) {
            System.out.println(String.format("left:%d, right:%d", left, right));
            if (arr[left][0] < arr[right][0]) {
                ans = Math.max(ans, arr[right][0] - arr[left][0]);
            }

            if (arr[left + 1][0] - arr[left][0] < arr[right][1] - arr[right - 1][1]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
}