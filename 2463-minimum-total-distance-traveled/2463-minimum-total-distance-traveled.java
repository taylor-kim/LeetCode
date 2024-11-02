class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        return official_topdown(robot, factory);
    }

    public long official_topdown(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> {
            return a[0] - b[0];
        });

        List<Integer> factoryPositions = new ArrayList();

        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }

        return dp(0, 0, robot, factoryPositions, new Long[robot.size()][factoryPositions.size()]);
    }

    private long dp(int rIndex, int fIndex, List<Integer> robot, List<Integer> fact, Long[][] memo) {
        if (rIndex >= robot.size()) return 0l;

        if (fIndex >= fact.size()) return (long)1e12;

        if (memo[rIndex][fIndex] != null) return memo[rIndex][fIndex];

        long include = dp(rIndex + 1, fIndex + 1, robot, fact, memo) + Math.abs(robot.get(rIndex) - fact.get(fIndex));

        long exclude = dp(rIndex, fIndex + 1, robot, fact, memo);

        return memo[rIndex][fIndex] = Math.min(include, exclude);
    }

    public long mySol2_fail(List<Integer> robot, int[][] factory) {
        TreeMap<Integer, int[]> treeMap = new TreeMap();

        Collections.sort(robot);

        for (int[] f : factory) {
            treeMap.put(f[0], f);
        }

        return topdown(robot, treeMap, 0);
    }

    private long topdown(List<Integer> robot, TreeMap<Integer, int[]> treeMap, int index) {
        if (index >= robot.size()) return 0l;

        int r = robot.get(index);

        Integer lo = treeMap.floorKey(r);
        Integer hi = treeMap.ceilingKey(r);

        long ans = Long.MAX_VALUE;

        if (lo != null) {
            int current = move(lo, r, treeMap);
            long next = topdown(robot, treeMap, index + 1);

            if (next >= 0) {
                ans = Math.min(ans, current + next);
            }

            back(lo, treeMap);
        }
        
        if (hi != null) {
            int current = move(hi, r, treeMap);
            long next = topdown(robot, treeMap, index + 1);

            if (next >= 0) {
                ans = Math.min(ans, current + next);
            }

            back(hi, treeMap);
        }

        return ans != Long.MAX_VALUE ? ans : -1l;
    }

    public long mySol_fail(List<Integer> robot, int[][] factory) {
        TreeMap<Integer, int[]> treeMap = new TreeMap();

        for (int[] f : factory) {
            treeMap.put(f[0], f);
        }

        long ans = 0;

        for (int r : robot) {
            Integer lo = treeMap.floorKey(r);
            Integer hi = treeMap.ceilingKey(r);

            if (lo != null && hi != null) {
                int[] f = null;
                int key = -1;
                int diff = 0;

                if (Math.abs(lo - r) < Math.abs(hi - r)) {
                    ans += move(lo, r, treeMap);
                } else {
                    ans += move(hi, r, treeMap);
                }
            } else if (lo != null) {
                ans += move(lo, r, treeMap);
            } else if (hi != null) {
                ans += move(hi, r, treeMap);
            }
        }

        return ans;
    }

    private int move(int f, int r, TreeMap<Integer, int[]> treeMap) {
        int[] factory = treeMap.get(f);

        factory[1]--;

        if (factory[1] == 0) {
            treeMap.remove(f);
        }
        
        return Math.abs(f - r);
    }

    private void back(int f, TreeMap<Integer, int[]> map) {
        map.computeIfAbsent(f, k -> new int[] {f, 0})[1]++;
    }
}