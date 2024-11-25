class Solution {
    public int slidingPuzzle(int[][] board) {
        return mySol2(board);
    }

    public int mySol2(int[][] board) {
        Map<Integer, List<Integer>> moves = new HashMap();

        moves.put(0, Arrays.asList(1, 3));
        moves.put(1, Arrays.asList(0, 2, 4));
        moves.put(2, Arrays.asList(1, 5));
        moves.put(3, Arrays.asList(0, 4));
        moves.put(4, Arrays.asList(3, 1, 5));
        moves.put(5, Arrays.asList(4, 2));

        StringBuilder sb = new StringBuilder();

        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }

        Queue<String> queue = new LinkedList();
        Set<String> visit = new HashSet();

        queue.add(sb.toString());
        visit.add(sb.toString());

        int turn = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String s = queue.poll();

                if (s.equals("123450")) {
                    return turn;
                }

                int index = s.indexOf("0");

                for (int next : moves.get(index)) {
                    char[] arr = s.toCharArray();
                    char c = arr[next];
                    arr[next] = '0';
                    arr[index] = c;

                    String nextMove = new String(arr);

                    if (visit.add(nextMove)) {
                        queue.add(nextMove);
                    }
                }
            }

            turn++;
        }

        return -1;
    }

    public int mySol_fail(int[][] board) {
        // 0, 1, 2
        // 3, 4, 5

        Map<Integer, List<Integer>> moves = new HashMap();

        moves.put(0, Arrays.asList(1, 3));
        moves.put(1, Arrays.asList(0, 2, 4));
        moves.put(2, Arrays.asList(1, 5));
        moves.put(3, Arrays.asList(0, 4));
        moves.put(4, Arrays.asList(3, 1, 5));
        moves.put(5, Arrays.asList(4, 2));

        StringBuilder sb = new StringBuilder();

        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }

        return mySol(sb, moves, new HashSet());
    }

    public int mySol(StringBuilder status, Map<Integer, List<Integer>> moves, Set<String> visit) {
        String s = status.toString();

        if (!visit.add(s)) {
            return -1;
        }

        // System.out.println(s + ", " + visit);

        if (s.equals("123450")) return 0;

        int index = s.indexOf("0");

        int ans = Integer.MAX_VALUE;

        for (int next : moves.get(index)) {
            char c = status.charAt(next);

            status.setCharAt(index, c);
            status.setCharAt(next, '0');

            int sub = mySol(status, moves, visit);

            if (sub >= 0) {
                ans = Math.min(ans, 1 + sub);
            }

            status.setCharAt(index, '0');
            status.setCharAt(next, c);
        }

        visit.remove(s);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}