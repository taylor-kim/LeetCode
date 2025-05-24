class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        return official_greedy_and_pq(nums, queries);
    }

    public int official_greedy_and_pq(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diffArray = new int[n + 1];

        Arrays.sort(queries, (a, b) -> {
            return a[0] - b[0];
        });

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        int op = 0;

        for (int i = 0, j = 0; i < n; i++) {
            op += diffArray[i];

            while (j < queries.length && queries[j][0] <= i) {
                pq.add(queries[j++][1]);
            }

            while (op < nums[i] && !pq.isEmpty() && pq.peek() >= i) {
                op++;
                diffArray[pq.poll() + 1]--;
            }

            if (op < nums[i]) return -1;
        }

        return pq.size();
    }

    public int read_official_explain(int[] nums, int[][] queries) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        for (int[] q : queries) pq.add(q);

        int n = nums.length;
        int[] diffArray = new int[n + 1];

        for (int[] query : queries) {
            diffArray[query[0]]++;
            diffArray[query[1] + 1]--;
        }

        int ans = 0;
        int selected = 0;

        for (int i = 0; i < nums.length; i++) {
            if (diffArray[i] < 0) {
                selected -= diffArray[i];
            }

            // if (nums[i] == 0) continue;

            while (!pq.isEmpty() && pq.peek()[0] <= i) {
                pq.poll();
                selected++;
            }

            System.out.println(String.format("selected:%d, nums[%d]:%d", selected, i, nums[i]));

            if (selected < nums[i]) return -1;

            // selected -= nums[i];
        }

        return selected;
    }

    public int tryAgain_20250523_fail(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diffArray = new int[n + 1];

        for (int[] query : queries) {
            diffArray[query[0]]++;
            diffArray[query[1] + 1]--;
        }

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += diffArray[i];

            if (nums[i] > sum) return -1;

            int spare = nums[i] - sum;

            nums[i] -= sum;
        }

        return 0;
    }

    public int mySol_fail(int[] nums, int[][] queries) {
        int ans = 0;

        Arrays.sort(queries, (a, b) -> {
            int al = a[1] - a[0];
            int bl = b[1] - b[0];
            
            return al != bl ? bl - al : a[0] - b[0];
        });

        for (int[] q : queries) {
            boolean required = false;
            
            for (int i = q[0]; i <= q[1]; i++) {
                nums[i]--;

                if (nums[i] >= 0) required = true;
            }

            System.out.println(Arrays.toString(nums));
            System.out.println(String.format("q:%s, required:%b", Arrays.toString(q), required));
        
            if (!required) ans++;
        }

        // System.out.println(Arrays.toString(maxDec));

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return -1;
        }

        // System.out.println(Arrays.toString(nums));

        return ans;
    }
}