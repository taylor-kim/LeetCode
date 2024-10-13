class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        return official_sliding_window(nums);
    }

    public int[] official_sliding_window(List<List<Integer>> nums) {
        List<int[]> list = new ArrayList();

        for (int i = 0; i < nums.size(); i++) {
            for (int num : nums.get(i)) {
                list.add(new int[] {num, i});
            }
        }

        Collections.sort(list, (a, b) -> {
            return a[0] - b[0];
        });

        int left = 0;
        int count = 0;
        Map<Integer, Integer> freq = new HashMap();
        int[] ans = new int[] {0, Integer.MAX_VALUE};

        for (int right = 0; right < list.size(); right++) {
            int rightNum = list.get(right)[0];
            int rightRow = list.get(right)[1];

            freq.put(rightRow, freq.getOrDefault(rightRow, 0) + 1);

            if (freq.get(rightRow) == 1) count++;

            while (count == nums.size()) {
                int leftNum = list.get(left)[0];
                int leftRow = list.get(left)[1];

                if (rightNum - leftNum < ans[1] - ans[0]) {
                    ans[0] = leftNum;
                    ans[1] = rightNum;
                }

                freq.put(leftRow, freq.get(leftRow) - 1);

                if (freq.get(leftRow) == 0) count--;

                left++;
            }
        }

        return ans;
    }

    public int[] official_pq(List<List<Integer>> nums) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i).get(0);
            pq.add(new int[] {num, i, 0});
            max = Math.max(max, num);
        }

        int left = 0;
        int right = Integer.MAX_VALUE;

        while (pq.size() == nums.size()) {
            int num = pq.peek()[0], row = pq.peek()[1], col = pq.poll()[2];

            if (max - num < right - left) {
                left = num;
                right = max;
            }

            if (col + 1 < nums.get(row).size()) {
                int next = nums.get(row).get(col + 1);
                pq.add(new int[] {next, row, col + 1});
                max = Math.max(max, next);
            }
        }

        return new int[] {left, right};
    }

    public int[] official_bf(List<List<Integer>> nums) {
        boolean isEmpty = false;

        int[] ans = new int[] {0, Integer.MAX_VALUE};

        int[] indices = new int[nums.size()];

        while (true) {
            int target = 0;

            int lMin = Integer.MAX_VALUE;
            int lMax = Integer.MIN_VALUE;

            for (int i = 0; i < nums.size(); i++) {
                List<Integer> row = nums.get(i);

                int num = nums.get(i).get(indices[i]);

                if (lMin > num) {
                    lMin = num;
                    target = i;
                }

                lMax = Math.max(lMax, num);
            }

            int diff = lMax - lMin;

            if (diff < ans[1] - ans[0]) {
                ans[0] = lMin;
                ans[1] = lMax;
            }

            if (++indices[target] == nums.get(target).size()) break;
        }

        return ans;
    }

    public int[] mySol2_after_official_bf_fail(List<List<Integer>> nums) {
        int[] ans = new int[] {0, Integer.MAX_VALUE};

        int[] lIndices = new int[nums.size()];
        int[] rIndices = new int[nums.size()];

        for (int i = 0; i < nums.size(); i++) {
            rIndices[i] = nums.get(i).size() - 1;
        }

        while (true) {
            int minRow = 0;
            int maxRow = 0;

            int lMin = Integer.MAX_VALUE;
            int lMax = Integer.MIN_VALUE;
            int rMin = Integer.MAX_VALUE;
            int rMax = Integer.MIN_VALUE;

            for (int i = 0; i < nums.size(); i++) {
                List<Integer> row = nums.get(i);

                int lNum = row.get(lIndices[i]);

                if (lMin > lNum) {
                    lMin = lNum;
                    minRow = i;
                }

                lMax = Math.max(lMax, lNum);

                int rNum = row.get(rIndices[i]);

                rMin = Math.min(rMin, rNum);

                if (rMax < rNum) {
                    rMax = rNum;
                    maxRow = i;
                }
            }

            int ld = lMax - lMin;
            int rd = rMax - rMin;

            System.out.println(String.format("lMin:%d, lMax:%d, rMin:%d, rMax:%d, ld:%d, rd:%d", lMin, lMax, rMin, rMax, ld, rd));

            if (ld <= rd) {
                if (ld < ans[1] - ans[0]) {
                    ans[0] = lMin;
                    ans[1] = lMax;
                }

                if (lIndices[maxRow] > --rIndices[maxRow]) break;
            } else {
                if (rd < ans[1] - ans[0]) {
                    ans[0] = rMin;
                    ans[1] = rMax;
                }

                if (++lIndices[maxRow] > rIndices[maxRow]) break;
            }
        }

        return ans;
    }

    public int[] mySol_fail(List<List<Integer>> nums) {
        List<Integer> all = new ArrayList();

        for (List<Integer> row : nums) {
            all.addAll(row);
        }

        Collections.sort(all);

        System.out.println(all);

        int[] ans = {all.get(0), all.get(all.size() - 1)};

        int left = 0;
        int right = all.size() - 1;

        while (true) {

            if (left == right) break;

            int ld = all.get(left + 1) - all.get(left);
            int rd = all.get(right) - all.get(right - 1);

            if (ld <= rd && hasOne(nums, all.get(left), all.get(right - 1))) {
                right--;
            } else if (hasOne(nums, all.get(left + 1), all.get(right))) {
                left++;
            } else {
                break;
            }
        }

        return new int[] {all.get(left), all.get(right)};
    }

    boolean hasOne(List<List<Integer>> nums, int left, int right) {
        System.out.println(String.format("left:%d, right:%d", left, right));
        for (List<Integer> row : nums) {
            int start = Collections.binarySearch(row, left);
            int end = Collections.binarySearch(row, right);

            if (start >= 0) continue;
            if (end >= 0) continue;

            if (start < 0) start = -(start + 1);
            if (end < 0) end = -(end + 1);

            // if (end == 0) {
            //     System.out.println(String.format("%s, no with left:%d, right:%d", row, left, right));
            //     return false;
            // }

            if (end == 0 || start >= row.size() || start == end) {
                // System.out.println(String.format("%s, no with left:%d, right:%d", row, left, right));
                return false;
            }
            // System.out.println(String.format("%s, no with left:%d, right:%d", row, left, right));
        }

        return true;
    }
}