class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        return improve_mySol_onepass(customers, grumpy, minutes);
    }

    public int improve_mySol_onepass(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int maxUnsatisfied = 0;
        int currentUnsatisfied = 0;
        int[] pos = {-1, -1};

        Queue<Integer> queue = new LinkedList();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            queue.add(i);

            if (grumpy[i] == 1) {
                currentUnsatisfied += customers[i];
            } else {
                ans += customers[i];
            }

            if (!queue.isEmpty() && queue.size() > minutes) {
                int index = queue.poll();
                if (grumpy[index] == 1) {
                    currentUnsatisfied -= customers[index];
                }
            }

            if (maxUnsatisfied < currentUnsatisfied) {
                maxUnsatisfied = currentUnsatisfied;
            }
        }

        return ans + maxUnsatisfied;
    }

    public int official_better_than_me(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int unsatisfied = 0;

        for (int i = 0; i < minutes; i++) {
            unsatisfied += customers[i] * grumpy[i];
        }

        int max = unsatisfied;

        for (int i = minutes; i < n; i++) {
            unsatisfied += customers[i] * grumpy[i];
            unsatisfied -= customers[i - minutes] * grumpy[i - minutes];

            max = Math.max(max, unsatisfied);
        }

        int ans = max;

        for (int i = 0; i < n; i++) {
            ans += customers[i] * (1 - grumpy[i]);
        }

        return ans;
    }

    public int mySol(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int maxUnsatisfied = 0;
        int currentUnsatisfied = 0;
        int[] pos = {-1, -1};

        Queue<Integer> queue = new LinkedList();

        for (int i = 0; i < n; i++) {
            queue.add(i);

            if (grumpy[i] == 1) {
                currentUnsatisfied += customers[i];
            }

            if (!queue.isEmpty() && queue.size() > minutes) {
                int index = queue.poll();
                if (grumpy[index] == 1) {
                    currentUnsatisfied -= customers[index];
                }
            }

            if (maxUnsatisfied < currentUnsatisfied) {
                maxUnsatisfied = currentUnsatisfied;
                pos[0] = queue.peek();
                pos[1] = i;
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0 || (pos[0] <= i && i <= pos[1])) {
                ans += customers[i];
            }
        }

        return ans;
    }
}