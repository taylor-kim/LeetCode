class Solution {
    public int trapRainWater(int[][] heightMap) {
        return after_editorial(heightMap);
    }

    public int after_editorial(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return heightMap[a[0]][a[1]] - heightMap[b[0]][b[1]];
        });

        boolean[][] boundaries = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            boundaries[i][0] = true;
            boundaries[i][n - 1] = true;

            pq.add(new int[] {i, 0});
            pq.add(new int[] {i, n - 1});
        }

        for (int j = 0; j < n; j++) {
            boundaries[0][j] = true;
            boundaries[m - 1][j] = true;

            pq.add(new int[] {0, j});
            pq.add(new int[] {m - 1, j});
        }

        int ans = 0;

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        while (!pq.isEmpty()) {
            int y = pq.peek()[0];
            int x = pq.poll()[1];

            for (int[] d : dirs) {
                int ny = y + d[0];
                int nx = x + d[1];

                if (ny >= 0 && ny < m && nx >= 0 && nx < n && !boundaries[ny][nx]) {
                    boundaries[ny][nx] = true;
                    
                    if (heightMap[ny][nx] < heightMap[y][x]) {
                        ans += heightMap[y][x] - heightMap[ny][nx];
                        heightMap[ny][nx] = heightMap[y][x];
                    }

                    pq.add(new int[] {ny, nx});
                }
            }
        }

        return ans;
    }

    public int mySol2_after_fail(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        for (int[] row : heightMap) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("");

        int[][] dirs = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
        };

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return heightMap[a[0]][a[1]] - heightMap[b[0]][b[1]];
        });

        TreeMap<Integer, Integer> map = new TreeMap();
        Set<String> visit = new HashSet();

        for (int i = 0; i < m; i++) {
            pq.add(new int[] {i, 0});
            pq.add(new int[] {i, n - 1});

            visit.add(i + "_" + 0);
            visit.add(i + "_" + (n - 1));

            put(map, heightMap[i][0], 1);
            put(map, heightMap[i][n - 1], 1);
        }

        for (int j = 1; j < n - 1; j++) {
            pq.add(new int[] {0, j});
            pq.add(new int[] {m - 1, j});

            visit.add(0 + "_" + j);
            visit.add((m - 1) + "_" + j);

            put(map, heightMap[0][j], 1);
            put(map, heightMap[m - 1][j], 1);
        }

        while (!pq.isEmpty()) {
            int[] pos = pq.poll();
            int y = pos[0];
            int x = pos[1];

            boolean foundBetter = false;

            for (int[] d : dirs) {
                int ny = y + d[0];
                int nx = x + d[1];

                if (ny >= 0 && nx >= 0 && ny < m && nx < n && heightMap[y][x] <= heightMap[ny][nx] && visit.add(ny + "_" + nx)) {
                    pq.add(new int[] {ny, nx});
                    put(map, heightMap[ny][nx], 1);
                    foundBetter = true;
                }
            }

            if (foundBetter) {
                put(map, heightMap[y][x], -1);
            }
        }

        // System.out.println(map);

        Integer min = map.firstKey();

        int ans = 0;

        Set<String> rain = new HashSet();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit.contains(i + "_" + j) && !rain.contains(i + "_" + j)) {
                    // System.out.println(String.format("i:%d, j:%d, val:%d", i, j, heightMap[i][j]));
                    // ans += Math.max(min - heightMap[i][j], 0);

                    Queue<int[]> queue = new LinkedList();
                    int minOuter = Integer.MAX_VALUE;
                    List<Integer> list = new ArrayList();

                    queue.add(new int[] {i, j});
                    rain.add(i + "_" + j);

                    while (!queue.isEmpty()) {
                        int y = queue.peek()[0];
                        int x = queue.poll()[1];

                        list.add(heightMap[y][x]);

                        for (int[] d : dirs) {
                            int ny = y + d[0];
                            int nx = x + d[1];

                            if (visit.contains(ny + "_" + nx)) {
                                minOuter = Math.min(minOuter, heightMap[ny][nx]);
                            } else if (ny >= 0 && nx >= 0 && ny < m && nx < n && rain.add(ny + "_" + nx)) {
                                queue.add(new int[] {ny, nx});
                            }
                        }
                    }

                    System.out.println(String.format("minOuter:%d, list:%s", minOuter, list));

                    for (int value : list) {
                        ans += Math.max(minOuter - value, 0);
                    }
                }
            }
        }

        return ans;
    }

    private void put(Map<Integer, Integer> map, int val, int delta) {
        map.put(val, map.getOrDefault(val, 0) + delta);

        if (map.get(val) == 0) {
            map.remove(val);
        }
    }

    public int mySol_fail(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        for (int[] row : heightMap) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("");

        int[][] dp = new int[m][n];

        for (int i = 1; i < m - 1; i++) {
            int[] leftToRight = new int[n];
            int[] rightToLeft = new int[n];

            for (int left = 0; left < n; left++) {
                int right = n - left - 1;
                if (left > 0) {
                    leftToRight[left] = Math.max(leftToRight[left - 1], heightMap[i][left]);
                    rightToLeft[right] = Math.max(rightToLeft[right + 1], heightMap[i][right]);
                } else {
                    leftToRight[left] = heightMap[i][left];
                    rightToLeft[right] = heightMap[i][right];
                }
            }

            for (int left = 0; left < n; left++) {
                dp[i][left] = Math.min(leftToRight[left], rightToLeft[left]) - heightMap[i][left];
            }

            System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println("");

        int[][] dp2 = new int[n][m];

        for (int j = 1; j < n - 1; j++) {
            int[] leftToRight = new int[m];
            int[] rightToLeft = new int[m];

            for (int left = 0; left < m; left++) {
                int right = m - left - 1;
                if (left > 0) {
                    leftToRight[left] = Math.max(leftToRight[left - 1], heightMap[left][j]);
                    rightToLeft[right] = Math.max(rightToLeft[right + 1], heightMap[right][j]);
                } else {
                    leftToRight[left] = heightMap[left][j];
                    rightToLeft[right] = heightMap[right][j];
                }
            }

            for (int left = 0; left < m; left++) {
                dp2[j][left] = Math.min(leftToRight[left], rightToLeft[left]) - heightMap[left][j];
            }

            System.out.println(Arrays.toString(dp2[j]));
        }

        System.out.println("");

        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(String.format("dp[%d][%d]:%d, dp2[%d][%d]:%d", i, j, dp[i][j], j, i, dp2[j][i]));
                ans += Math.min(dp[i][j], dp2[j][i]);
            }

            System.out.println("");
        }

        return ans;
    }
}