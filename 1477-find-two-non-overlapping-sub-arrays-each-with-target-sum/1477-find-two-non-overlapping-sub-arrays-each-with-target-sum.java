class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        return official_psum(arr, target);
    }

    public int official_psum(int[] arr, int target) {
        int n = arr.length;
        Map<Integer, Integer> pSum = new HashMap();
        pSum.put(0, -1);
        int sum = 0;
        int ans = n + 1;
        int prevMinLength = n + 1;
        
        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (pSum.containsKey(sum - target)) {
                int j = pSum.get(sum - target);
                int length = i - j;

                ans = Math.min(ans, j < 0 ? n + 1 : arr[j] + length);

                prevMinLength = Math.min(prevMinLength, length);
            }

            arr[i] = prevMinLength;

            pSum.put(sum, i);
        }

        return ans == n + 1 ? -1 : ans;
    }

    public int mySol_by_hint_and_gemini(int[] arr, int target) {
        List<int[]> ranges = new ArrayList();

        int left = 0;
        int sum = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            if (sum == target) {
                ranges.add(new int[] {left, right});
            }
        }

        Collections.sort(ranges, (a, b) -> {
            return (a[1] - a[0]) - (b[1] - b[0]);
        });

        // for (int[] range : ranges) {
        //     System.out.print(Arrays.toString(range));
        // }

        int lo = 0;
        int hi = arr.length + 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (findAnswer(ranges, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo == 0 || lo > arr.length ? -1 : lo;

        // for (int i = 0; i < ranges.size(); i++) {
        //     int[] a = ranges.get(i);
        //     for (int j = i + 1; j < ranges.size(); j++) {
        //         int[] b = ranges.get(j);

        //         if (isNonOverlap(a, b)) {
        //             return a[1] - a[0] + 1 + b[1] - b[0] + 1;
        //         }
        //     }
        // }

        // return -1;
    }

    private boolean findAnswer(List<int[]> ranges, int length) {
        for (int i = 0; i < ranges.size(); i++) {
            int[] a = ranges.get(i);
            int al = a[1] - a[0] + 1;
            for (int j = i + 1; j < ranges.size(); j++) {
                int[] b = ranges.get(j);

                int bl = b[1] - b[0] + 1;

                if (al + bl > length) break;

                if (isNonOverlap(a, b)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isNonOverlap(int[] a, int[] b) {
        return Math.max(a[0], b[0]) > Math.min(a[1], b[1]);
    }
}