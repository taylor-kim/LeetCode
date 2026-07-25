class Solution {
    private int[][] bitPrefix;

    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;

        // 1. 비트별 누적합 전처리 O(31 * N)
        // bitPrefix[b][idx] : 0부터 idx-1 위치까지 b번째 비트가 1인 원소의 개수
        bitPrefix = new int[31][n + 1];
        for (int i = 0; i < n; i++) {
            for (int b = 0; b < 31; b++) {
                bitPrefix[b][i + 1] = bitPrefix[b][i] + ((nums[i] >> b) & 1);
            }
        }

        long totalCount = 0;

        // 2. 각 시작점 i에 대해 이진 탐색
        for (int i = 0; i < n; i++) {
            // AND(i, mid) >= k 가 유지되는 가장 오른쪽 위치 R
            int right = searchBoundary(i, n, k);

            // AND(i, mid) >= k + 1 이 유지되는 가장 오른쪽 위치
            // 이 위치 + 1 이 바로 AND(i, mid) == k 가 시작하는 첫 위치 L
            int left = searchBoundary(i, n, k + 1) + 1;

            // L ~ R 구간이 유효하고, 실제 AND 값이 k가 맞다면 개수 누적
            if (left <= right && getRangeAnd(i, right) == k) {
                totalCount += (right - left + 1);
            }
        }

        return totalCount;
    }

    // [start, end] 구간의 AND 값을 O(31) 만에 계산
    private int getRangeAnd(int start, int end) {
        int len = end - start + 1;
        int res = 0;
        for (int b = 0; b < 31; b++) {
            int ones = bitPrefix[b][end + 1] - bitPrefix[b][start];
            if (ones == len) { // 구간 내 모든 원소의 b번째 비트가 1인 경우
                res |= (1 << b);
            }
        }
        return res;
    }

    // AND(i, mid) >= target 을 만족하는 가장 오른쪽 index를 찾는 이진 탐색
    private int searchBoundary(int i, int n, long target) {
        int low = i, high = n - 1;
        int ans = i - 1; // 조건에 맞는 위치가 없을 때의 기본값

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // mid를 오른쪽으로 늘려갈수록 AND 값은 감소하거나 유지됨
            if (getRangeAnd(i, mid) >= target) {
                ans = mid;      // 조건 만족 시 일단 기록하고
                low = mid + 1;  // 더 오른쪽 경계가 있는지 탐색
            } else {
                high = mid - 1; // target 미만으로 떨어졌으므로 왼쪽으로 축소
            }
        }
        return ans;
    }
}