class Solution {
    public int shortestSubarray(int[] nums, int k) {
        return after_editorial(nums, k);
    }

    public int after_editorial(int[] nums, int k) {
        long sum = 0;
        int ans = Integer.MAX_VALUE;

        Queue<long[]> pq = new PriorityQueue<>((a, b) -> {
            // return a[0] - b[0];
            return Long.compare(a[0], b[0]);
        });

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum >= k) {
                ans = Math.min(ans, i + 1);
            }

            while (!pq.isEmpty() && sum - pq.peek()[0] >= k) {
                long[] d = pq.poll();
                ans = Math.min(ans, i - (int)d[1]);
            }

            pq.add(new long[] {sum, i});
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int mySol2_fail(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap();
        map.put(0, 0);

        int min = Integer.MAX_VALUE;

        // for (int num : nums) min = Math.min(min, num);

        // if (min < 0) {
        //     min = Math.abs(min);
        //     k += min;
        // } else {
        //     min = 0;
        // }

        min = 0;

        int ans = Integer.MAX_VALUE;

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i] + min;

            sum += num;

            map.put(sum, i);

            Integer key = null;

            while ((key = map.floorKey(sum - k)) != null) {
                System.out.println(String.format("i:%d, sum:%d, key:%d\n", i, sum, key));
                    
                int val = map.remove(key);

                ans = Math.min(ans, i - val + 1);
            }
        }

        // sum : 2, 1 x, {0:0, 2:0}
        // sum : 1, 2 0, {0:0, 2:0, 1:1}
        // sum : 3, 0 0, {0:0, 2:0, 1:1, 3:2}

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int mySol_fail(int[] nums, int k) {
        return topdown(nums, 0, k);
    }

    public int topdown(int[] nums, int index, int k) {
        int max = (int)1e5 + 1;

        if (index >= nums.length) return max;

        int sum = 0;
        int ans = max;

        for (int i = index; i < nums.length; i++) {
            sum += nums[i];

            if (sum >= k) {
                ans = i - index + 1;
                break;
            }
        }

        int sub = topdown(nums, index + 1, k);

        if (sub >= 0) {
            ans = Math.min(ans, sub);
        }

        return ans == max ? -1 : ans;
    }
}