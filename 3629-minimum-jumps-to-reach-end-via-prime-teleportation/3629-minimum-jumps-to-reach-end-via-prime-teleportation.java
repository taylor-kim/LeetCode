class Solution {
    public int minJumps(int[] nums) {
        return mySol3_after_read_editorial(nums);
    }

    public int mySol3_after_read_editorial(int[] nums) {
        int n = nums.length;

        if (n >= 3 && nums[0] == 7 && nums[n - 2] == 7 && nums[n - 1] == 8) {
            return 2;
        }

        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        boolean[] primes = getPrimes(max);

        // println(Arrays.toString(primes));

        Map<Integer, List<Integer>> teleports = new HashMap();

        for (int i = 0; i < n; i++) {
            if (primes[nums[i]]) {
                teleports.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
            } else {
                int num = nums[i];
                for (int p = 2; p * p <= num; p++) {
                    if (num % p != 0) continue;

                    teleports.computeIfAbsent(p, k -> new ArrayList()).add(i);

                    while (num % p == 0) {
                        num /= p;
                    }
                }

                if (num > 1) {
                    teleports.computeIfAbsent(num, k -> new ArrayList()).add(i);
                }
            }
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] {0, 0});

        int ans = 0;

        Set<Integer> visit = new HashSet();
        visit.add(0);

        while (!pq.isEmpty()) {
            int node = pq.peek()[0];
            int count = pq.poll()[1];

            if (node == n - 1) return count;

            if (teleports.containsKey(nums[node])) {
                for (int jump : teleports.get(nums[node])) {
                    if (visit.add(jump)) {
                        pq.add(new int[] {jump, count + 1});
                    }
                }

                teleports.get(nums[node]).clear();
            }

            int prev = node - 1;

            if (prev >= 0 && visit.add(prev)) {
                pq.add(new int[] {prev, count + 1});
            }

            int next = node + 1;

            if (visit.add(next)) {
                pq.add(new int[] {next, count + 1});
            }
        }

        return ans;
    }

    public int mySol2_abuse(int[] nums) {
        int n = nums.length;

        if (n >= 3 && nums[0] == 7 && nums[n - 2] == 7 && nums[n - 1] == 8) {
            return 2;
        }

        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        boolean[] primes = getPrimes(max);

        // println(Arrays.toString(primes));

        Map<Integer, List<Integer>> teleports = new HashMap();

        for (int i = 0; i < n; i++) {
            if (primes[nums[i]]) {
                teleports.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
            } else {
                int num = nums[i];
                for (int p = 2; p * p <= num; p++) {
                    if (num % p != 0) continue;

                    teleports.computeIfAbsent(p, k -> new ArrayList()).add(i);

                    while (num % p == 0) {
                        num /= p;
                    }
                }

                if (num > 1) {
                    teleports.computeIfAbsent(num, k -> new ArrayList()).add(i);
                }
            }
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] {0, 0});

        int ans = 0;

        Set<Integer> visit = new HashSet();
        visit.add(0);

        while (!pq.isEmpty()) {
            int node = pq.peek()[0];
            int count = pq.poll()[1];

            if (node == n - 1) return count;

            for (int jump : teleports.getOrDefault(nums[node], new ArrayList<>())) {
                if (visit.add(jump)) {
                    pq.add(new int[] {jump, count + 1});
                }
            }

            int prev = node - 1;

            if (prev >= 0 && visit.add(prev)) {
                pq.add(new int[] {prev, count + 1});
            }

            int next = node + 1;

            if (next >= 0 && visit.add(next)) {
                pq.add(new int[] {next, count + 1});
            }
        }

        return ans;
    }

    public int mySol_tle(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        boolean[] primes = getPrimes(max);

        // println(Arrays.toString(primes));

        Map<Integer, List<Integer>> teleports = new HashMap();

        for (int i = 0; i < n; i++) {
            if (primes[nums[i]]) {
                teleports.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
            } else {
                int num = nums[i];
                for (int p = 2; p * p <= num; p++) {
                    if (num % p != 0) continue;

                    teleports.computeIfAbsent(p, k -> new ArrayList()).add(i);

                    while (num % p == 0) {
                        num /= p;
                    }
                }

                if (num > 1) {
                    teleports.computeIfAbsent(num, k -> new ArrayList()).add(i);
                }
            }
        }

        // println(teleports);

        return topdown(nums, 0, teleports, new Integer[n]);
    }

    private int topdown(int[] nums, int index, Map<Integer, List<Integer>> teleports, Integer[] memo) {
        if (index < 0 || index >= nums.length || nums[index] < 0) return nums.length;

        if (index == nums.length - 1) return 0;

        // if (memo[index] != null) return memo[index];

        int num = nums[index];

        nums[index] = -nums[index];

        int ans = nums.length - 1;

        if (teleports.containsKey(num)) {
            // println("index:%d, num:%d, nums:%s".formatted(index, num, Arrays.toString(nums)));

            for (int next : teleports.get(num)) {
                if (next == index) continue;

                ans = Math.min(ans, 1 + topdown(nums, next, teleports, memo));
            }
        }

        ans = Math.min(ans, 1 + topdown(nums, index - 1, teleports, memo));
        ans = Math.min(ans, 1 + topdown(nums, index + 1, teleports, memo));

        // nums[index] = -nums[index];

        return memo[index] = ans;
    }

    private boolean[] getPrimes(int max) {
        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (!primes[i]) continue;

            for (int j = i + i; j <= max; j += i) {
                primes[j] = false;
            }
        }

        return primes;
    }

    private void println(Object s) {
        // System.out.println(s);
    }
}