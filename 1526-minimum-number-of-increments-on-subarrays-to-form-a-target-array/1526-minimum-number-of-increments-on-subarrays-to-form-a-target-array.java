class Solution {
    public int minNumberOperations(int[] target) {
        return mySol3(target);
    }

    public int mySol3(int[] target) {
        int stack = -1;
        int ans = 0;

        for (int i = 0; i < target.length; i++) {
            if (stack != -1 && target[stack] <= target[i]) {
                stack = -1;
            }

            if (stack != -1 && target[stack] > target[i]) {
                ans += target[stack] - target[i];
            }

            stack = i;
        }

        ans += target[stack];

        return ans;
    }

    public int mySol2_by_topic(int[] target) {
        Stack<Integer> stack = new Stack();
        int ans = 0;

        for (int i = 0; i < target.length; i++) {
            while (!stack.isEmpty() && target[stack.peek()] <= target[i]) {
                stack.pop();
            }

            if (!stack.isEmpty() && target[stack.peek()] > target[i]) {
                ans += target[stack.pop()] - target[i];
            }

            stack.push(i);
        }

        if (!stack.isEmpty()) {
            ans += target[stack.pop()];
        }

        return ans;
    }

    public int mySol_tle(int[] target) {
        

        int ans = topdown(target, 0, target.length - 1);

        // System.out.println(Arrays.toString(target));

        return ans;
    }

    public int topdown(int[] target, int lo, int hi) {
        if (lo > hi) return 0;

        if (lo == hi) {
            int val = target[lo];
            target[lo] -= val;

            return val;
        }

        if (target[lo] == 0) {
            return topdown(target, lo + 1, hi);
        } else if (target[hi] == 0) {
            return topdown(target, lo, hi - 1);
        }

        boolean op = false;

        for (int i = lo; i <= hi; i++) {
            if (target[i] == 0) {
                return (op ? 1 : 0) + topdown(target, lo, i - 1) + topdown(target, i + 1, hi);
            } else {
                target[i]--;
                op = true;
            }
        }

        return 1 + topdown(target, lo, hi);
    }
}