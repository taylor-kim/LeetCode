class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        return mySol(deck);
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