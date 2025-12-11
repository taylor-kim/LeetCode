class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        return editorial(n, buildings);
    }

    public int editorial(int n, int[][] buildings) {
        int[] minRow = new int[n + 1];
        int[] maxRow = new int[n + 1];
        int[] minCol = new int[n + 1];
        int[] maxCol = new int[n + 1];

        Arrays.fill(minRow, n + 1);
        Arrays.fill(minCol, n + 1);

        for (int[] pos : buildings) {
            int x = pos[0];
            int y = pos[1];

            minRow[y] = Math.min(minRow[y], x);
            maxRow[y] = Math.max(maxRow[y], x);

            minCol[x] = Math.min(minCol[x], y);
            maxCol[x] = Math.max(maxCol[x], y);
        }

        int ans = 0;

        for (int[] pos : buildings) {
            int x = pos[0];
            int y = pos[1];

            if (minRow[y] < x && x < maxRow[y] && minCol[x] < y && y < maxCol[x]) {
                ans++;
            }
        }

        return ans;
    }

    public int mySol(int n, int[][] buildings) {
        Map<Integer, List<Integer>> xAxisBaseMap = new HashMap();
        Map<Integer, List<Integer>> yAxisBaseMap = new HashMap();

        for (int[] pos : buildings) {
            int x = pos[0];
            int y = pos[1];

            xAxisBaseMap.computeIfAbsent(x, k -> new ArrayList()).add(y);
            yAxisBaseMap.computeIfAbsent(y, k -> new ArrayList()).add(x);
        }

        for (List<Integer> list : xAxisBaseMap.values()) {
            Collections.sort(list);
        }

        for (List<Integer> list : yAxisBaseMap.values()) {
            Collections.sort(list);
        }

        int ans = 0;

        for (int[] pos : buildings) {
            int x = pos[0];
            int y = pos[1];

            List<Integer> yList = xAxisBaseMap.get(x);
            int yPos = Collections.binarySearch(yList, y);

            if (yPos == 0 || yPos == yList.size() - 1) continue;

            List<Integer> xList = yAxisBaseMap.get(y);
            int xPos = Collections.binarySearch(xList, x);

            if (xPos == 0 || xPos == xList.size() - 1) continue;

            ans++;
        }

        return ans;
    }
}