class Solution {
    public long findScore(int[] nums) {
        return try_monotonic_stack(nums);
    }

    public long try_monotonic_stack(int[] nums) {
        Stack<Integer> stack = new Stack();
        long ans = 0;

        for (int i = 0; i < nums.length; i++) {
            boolean marked = false;

            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                marked = true;

                ans += nums[stack.pop()];

                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }

            if (!marked) {
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            ans += nums[stack.pop()];

            if (!stack.isEmpty()) stack.pop();
        }

        return ans;
    }

    public long others_genious_sliding_window(int[] nums) {
        long ans = 0;

        for (int i = 0; i < nums.length; i += 2) {
            int left = i;

            while (i + 1 < nums.length && nums[i] > nums[i + 1]) {
                i++;
            }

            for (int right = i; right >= left; right -= 2) {
                ans += nums[right];
            }
        }

        return ans;
    }

    public long mySol2_fail(int[] nums) {
        int max = 0;

        for (int num : nums) max = Math.max(max, num);

        int[] freq = new int[max + 1];
        Map<Integer, List<Integer>> indices = new HashMap();
        
        for (int i = 0; i < nums.length; i++) {
            freq[nums[i]]++;
            indices.computeIfAbsent(nums[i], k -> new ArrayList()).add(i);
        }

        long sum = 0;
        int num = 1;

        while (num <= max) {
            while (freq[num]-- > 0 && indices.containsKey(num)) {
                sum += num;
                int index = removeFirstAndClearIfEmpty(indices, num);

                if (index < 0) break;

                if (index - 1 >= 0) {
                    int prev = nums[index - 1];
                    freq[prev]--;
                    // removeFirstAndClearIfEmpty(indices, prev);
                }

                if (index + 1 < nums.length) {
                    int next = nums[index + 1];
                    freq[next]--;
                    // removeFirstAndClearIfEmpty(indices, next);
                }
            }
            num++;
        }

        return sum;
    }

    private int removeFirstAndClearIfEmpty(Map<Integer, List<Integer>> map, int key) {
        if (!map.containsKey(key)) return -1;

        int index = map.get(key).remove(0);

        if (map.get(key).size() == 0) map.remove(key);

        return index;
    }

    public long mySol(int[] nums) {
        List<int[]> list = new ArrayList();
        
        for (int i = 0; i < nums.length; i++) {
            list.add(new int[] {nums[i], i});
        }

        Collections.sort(list, (a, b) -> {
            return a[0] - b[0];
        });

        long sum = 0;

        Set<Integer> marked = new HashSet();

        for (int i = 0; i < list.size(); i++) {
            int[] pick = list.get(i);

            if (marked.add(pick[1])) {
                sum += pick[0];
                
                marked.add(Math.max(pick[1] - 1, 0));
                marked.add(Math.min(pick[1] + 1, nums.length - 1));
            }
        }

        return sum;
    }
}