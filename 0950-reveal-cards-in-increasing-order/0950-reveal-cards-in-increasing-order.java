class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        return official_two_pointers(deck);
    }

    public int[] official_two_pointers(int[] deck) {
        int n = deck.length;

        int[] ans = new int[n];

        int di = 0;
        int ai = 0;
        boolean skip = false;

        Arrays.sort(deck);

        while (di < n) {
            if (ans[ai] == 0) {
                if (!skip) {
                    ans[ai] = deck[di++];
                }

                skip = !skip;
            }

            ai = (ai + 1) % n;
        }

        return ans;
    }

    public int[] official_queue_twopass(int[] deck) {
        int n = deck.length;

        Queue<Integer> queue = new LinkedList();

        for (int i = 0; i < n; i++) queue.add(i);

        int[] ans = new int[n];

        Arrays.sort(deck);

        for (int i = 0; i < n; i++) {
            ans[queue.poll()] = deck[i];
            queue.add(queue.poll());
        }

        return ans;
    }

    public int[] mySol(int[] deck) {
        int n = deck.length;

        Queue<Integer> queue = new LinkedList();

        for (int i = 0; i < n; i++) queue.add(i);

        int index = 0;

        int[] order = new int[n];

        while (index < n) {
            order[index++] = queue.poll();
            queue.add(queue.poll());
        }

        int[] ans = new int[n];

        Arrays.sort(deck);

        // System.out.println(Arrays.toString(deck));
        // System.out.println(Arrays.toString(order));

        for (int i = 0; i < n; i++) {
            ans[order[i]] = deck[i];
        }

        return ans;
    }
}