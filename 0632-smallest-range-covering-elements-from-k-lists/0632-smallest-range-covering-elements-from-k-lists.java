class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        return mySol3_simillar_official_bf(nums);
    }

    public int[] mySol3_simillar_official_bf(List<List<Integer>> nums) {
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

    public int[] mySol2(List<List<Integer>> nums) {
        boolean isEmpty = false;

        int[] ans = new int[] {0, Integer.MAX_VALUE};

        int[] indices = new int[nums.size()];

        while (!isEmpty) {
            List<Integer> minRow = null;
            List<Integer> maxRow = null;

            int lMin = Integer.MAX_VALUE;
            int lMax = Integer.MIN_VALUE;
            int rMin = Integer.MAX_VALUE;
            int rMax = Integer.MIN_VALUE;

            for (List<Integer> row : nums) {
                if (row.size() == 0) {
                    isEmpty = true;
                    break;
                }

                if (lMin > row.get(0)) {
                    lMin = row.get(0);
                    minRow = row;
                }

                lMax = Math.max(lMax, row.get(0));

                rMin = Math.min(rMin, row.get(row.size() - 1));

                if (rMax < row.get(row.size() - 1)) {
                    rMax = row.get(row.size() - 1);
                    maxRow = row;
                }
            }

            // System.out.println(nums);

            if (!isEmpty) {
                // System.out.println(String.format("lMin:%d, lMax:%d, rMin:%d, rMax:%d", lMin, lMax, rMin, rMax));

                int ld = lMax - lMin;
                int rd = rMax - rMin;

                if (ld <= rd) {
                    maxRow.remove(maxRow.size() - 1);

                    if (ld < ans[1] - ans[0]) {
                        ans[0] = lMin;
                        ans[1] = lMax;
                    }
                } else {
                    minRow.remove(0);

                    if (rd < ans[1] - ans[0]) {
                        ans[0] = rMin;
                        ans[1] = rMax;
                    }
                }
            }

            if (lMin == rMin || lMax == rMax) {
                break;
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