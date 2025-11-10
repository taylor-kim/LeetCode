class Solution {
    public int minOperations(int[] nums) {
        return regressed(nums);
    }

    public int regressed(int[] nums) {
        Stack<Integer> stack = new Stack();
        int ans = 0;

        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();
            }

            if (num != 0 && (stack.isEmpty() || stack.peek() < num)) {
                stack.push(num);
                ans++;
            }
        }

        return ans;
    }

    public int mySol_202511_fail(int[] nums) {
        int ans = 0;
        
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            treeMap.computeIfAbsent(num, k -> new ArrayList()).add(i);
        }

        

        return ans;
    }

    public int mySol2(int[] nums) {
        int ans = 0;
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
                // ans++;
            }

            if (nums[i] != 0 && (stack.isEmpty() || nums[stack.peek()] < nums[i])) {
                stack.push(i);
                ans++;
            }
        }

        // return ans + stack.size();
        return ans;
    }

    public int mySol_fail(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        Integer[][] range = new Integer[max - min + 1][2];

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            if (num == 0) continue;

            if (range[num - min][0] == null) {
                range[num - min][0] = i;
            } else {
                range[num - min][1] = i;
            }
        }

        int ans = 0;

        for (int i = 0; i < range.length; i++) {
            if (range[i][0] != null) ans++;
        }

        return ans;
    }
}