import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
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
}