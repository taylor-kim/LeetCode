class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        return mySol(customers, grumpy, minutes);
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

        System.out.println(Arrays.toString(pos));

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0 || (pos[0] <= i && i <= pos[1])) {
                ans += customers[i];
            }
        }

        return ans;
    }
}