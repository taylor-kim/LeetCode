class Solution {
    public int numTilePossibilities(String tiles) {
        return mySol(tiles);
    }

    public int mySol(String tiles) {
        char[] arr = tiles.toCharArray();
        Arrays.sort(arr);

        Set<String> set = new HashSet();

        topdown(arr, 0, new boolean[arr.length], new StringBuilder(), set);

        return set.size();
    }

    private void topdown(char[] arr, int index, boolean[] visit, StringBuilder sb, Set<String> set) {
        if (index >= arr.length) return;

        if (visit[index]) return;

        sb.append(arr[index]);

        if (set.add(sb.toString())) {
            visit[index] = true;

            for (int i = 0; i < arr.length; i++) {
                if (visit[i]) continue;

                topdown(arr, i, visit, sb, set);
            }

            visit[index] = false;
        }

        sb.setLength(sb.length() - 1);

        topdown(arr, index + 1, visit, sb, set);
    }
}