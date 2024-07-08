class Solution {
    public int findTheWinner(int n, int k) {
        return official_recur(n, k);
    }

    public int official_recur(int n, int k) {
        return helper(n, k) + 1;
    }

    public int helper(int n, int k) {
        if (n == 1) return 0;

        return (helper(n - 1, k) + k) % n;
    }

    public int official_queue(int n, int k) {
        Queue<Integer> queue = new LinkedList();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        while (queue.size() > 1) {
            int size = k - 1;

            while (size-- > 0) {
                queue.add(queue.poll());
            }

            queue.poll();
        }

        return queue.peek();
    }

    public int official_list(int n, int k) {
        List<Integer> list = new ArrayList();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int start = 0;

        while (list.size() > 1) {
            start = (start + k - 1) % list.size();

            list.remove(start);
        }

        return list.get(0);
    }

    public int mySol(int n, int k) {
        List<Integer> list = new ArrayList();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        return getWinner(list, 0, k);
    }

    private int getWinner(List<Integer> list, int start, int k) {
        if (list.size() == 1) return list.get(0);

        int n = list.size();

        int target = start + k - 1;

        list.remove(target % n);

        return getWinner(list, target % n, k);
    }
}