class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        return mySol(n, buildings);
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