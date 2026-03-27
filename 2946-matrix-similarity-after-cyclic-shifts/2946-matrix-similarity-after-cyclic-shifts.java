class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        return mySol(mat, k);
    }
    
    public boolean mySol(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;

        k %= m;

        for (int i = 0; i < n; i++) {
            Map<Integer, List<Integer>> map = new HashMap();

            for (int j = 0; j < m; j++) {
                map.computeIfAbsent(mat[i][j], key -> new ArrayList()).add(j);
            }

            if (map.size() != 1 && !isCyclical(m, k, map, i % 2 == 0)) return false;
        }

        // k == 3
        // 0,1,2,3,4

        // ?,0,1,2,3,1
        // [1, 4], (4 + 3 % 5) == 2
        // [1, 4], (4 + 3 + 5 % 5) == 2

        // 0,1,2,3,4,5,6

        // 1,2,3,4,5,6,7

        return true;
    }

    // 0,2,0,2,0,2

    private boolean isCyclical(int n, int k, Map<Integer, List<Integer>> map, boolean asc) {
        for (List<Integer> indices : map.values()) {
            if (!asc) {
                Collections.reverse(indices);
            }

            Set<Integer> set = new HashSet(indices);

            for (int i = 0; i < indices.size(); i++) {
                int index = indices.get(i);

                if (!set.contains((index + k + n) % n)) return false;
            }
        }

        return true;
    }
}