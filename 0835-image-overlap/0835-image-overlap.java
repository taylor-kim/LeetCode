class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        return gemini(img1, img2);
    }

    public int gemini(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        // 1. img1과 img2에서 1의 위치 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) list1.add(new int[]{i, j});
                if (img2[i][j] == 1) list2.add(new int[]{i, j});
            }
        }

        // 2. 두 이미지의 1들 간 오프셋(이동량) 카운트
        Map<String, Integer> map = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                int dr = p1[0] - p2[0];
                int dc = p1[1] - p2[1];
                String key = dr + "," + dc;

                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }

    public int mySol_tle(int[][] img1, int[][] img2) {
        int n = img1.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sub = dfs(img1, img2, i, j, i, j, new boolean[n][n]);
                ans = Math.max(ans, sub);
            }
        }

        return ans;
    }

    private int dfs(int[][] img1, int[][] img2, int startI, int startJ, int i, int j, boolean[][] visit) {
        int n = img1.length;

        if (i >= n || j >= n || visit[i][j]) return 0;

        visit[i][j] = true;

        int max = 0;

        boolean log = false;

        // if (startI == 0 && startJ == 0 && i == 1 && j == 1) {
        //     log = true;
        // }

        for (int ii = 0; ii < n; ii++) {
            for (int jj = 0; jj < n; jj++) {
                int count = 0;
                int deltaI = 0;

                for (int i1 = startI; i1 <= i; i1++) {
                    if (ii + deltaI >= n) break;

                    int deltaJ = 0;
                    for (int j1 = startJ; j1 <= j; j1++) {
                        if (jj + deltaJ >= n) break;

                        if (img1[i1][j1] == 1 && img2[ii + deltaI][jj + deltaJ++] == 1) {
                            count++;
                            // if (log) {
                            //     System.out.println("i1:%d, j1:%d, ii:%d, deltaI:%d, jj:%d, deltaJ:%d".formatted(i1, j1, ii, deltaI, jj, deltaJ));
                            // }
                        }
                    }
                    deltaI++;
                }

                // if (log) {
                //     System.out.println("ii:%d, jj:%d, count:%d\n".formatted(ii, jj, count));
                // }

                max = Math.max(max, count);
            }
        }

        int a = dfs(img1, img2, startI, startJ, i + 1, j, visit);
        int b = dfs(img1, img2, startI, startJ, i, j + 1, visit);

        return Math.max(max, Math.max(a, b));
    }
}