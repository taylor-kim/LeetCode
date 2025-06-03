class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        return mySol2(status, candies, keys, containedBoxes, initialBoxes);
    }

    public int mySol2(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Queue<Integer> queue = new LinkedList();
        Set<Integer> candidates = new HashSet();

        for (int init : initialBoxes) {
            if (status[init] == 1) {
                queue.add(init);
            } else {
                candidates.add(init);
            }
        }

        int ans = 0;

        while (!queue.isEmpty()) {
            int box = queue.poll();

            ans += candies[box];

            for (int key : keys[box]) {
                status[key] = 1;
            }

            for (int nextBox : containedBoxes[box]) {
                if (status[nextBox] == 1) {
                    status[nextBox] = 1;
                    queue.add(nextBox);
                } else {
                    candidates.add(nextBox);
                }
            }

            Set<Integer> nextCandidates = new HashSet();

            for (int nextBox : candidates) {
                if (status[nextBox] == 1) {
                    queue.add(nextBox);
                } else {
                    nextCandidates.add(nextBox);
                }
            }

            candidates = nextCandidates;
        }

        return ans;
    }

    public int mySol(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Map<Integer, Set<Integer>> map = new HashMap();

        for (int i = 0; i < keys.length; i++) {
            map.put(i, new HashSet());

            for (int key : keys[i]) {
                map.get(i).add(key);
            }
        }

        boolean[] visit = new boolean[status.length];

        int ans = 0;

        for (int box : initialBoxes) {
            ans += dfs(status, candies, map, containedBoxes, box, visit);
        }

        return ans;
    }

    private int dfs(int[] status, int[] candies, Map<Integer, Set<Integer>> keys, int[][] containedBoxes, int box, boolean[] visit) {
        if (status[box] == 0) return 0;

        Set<Integer> canOpen = keys.get(box);

        int ans = candies[box];

        for (int nextBox : containedBoxes[box]) {
            if (status[nextBox] == 1 || canOpen.contains(nextBox)) {
                status[nextBox] = 1;

                ans += dfs(status, candies, keys, containedBoxes, nextBox, visit);
            }
        }

        return ans;
    }
}