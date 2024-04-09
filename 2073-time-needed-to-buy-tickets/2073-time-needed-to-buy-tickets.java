class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        return mySol(tickets, k);
    }

    public int mySol(int[] tickets, int k) {
        Queue<int[]> queue = new LinkedList();

        for (int i = 0; i < tickets.length; i++) {
            queue.add(new int[] {i, tickets[i]});
        }

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int index = queue.peek()[0];
                int count = queue.poll()[1] - 1;
                ans++;

                if (count == 0 && index == k) {
                    return ans;
                }

                if (count > 0) {
                    queue.add(new int[] {index, count});
                }
            }
        }

        return ans;
    }
}