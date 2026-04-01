class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        // 创建一个下标数组，对下标数组排序，这样不会打乱输入顺序
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> positions[i] - positions[j]);

        int[] st = new int[n];
        int top = -1;
        for (int i : idx) {
            if (directions.charAt(i) == 'R') { // 机器人 i 向右
                st[++top] = i;
                continue;
            }
            while (top >= 0) { // 栈顶机器人向右
                int j = st[top];
                if (healths[j] > healths[i]) { // 栈顶机器人的健康度大
                    healths[i] = 0; // 移除机器人 i
                    healths[j]--;
                    break;
                }
                if (healths[j] == healths[i]) { // 健康度一样大，都移除
                    healths[i] = 0;
                    healths[j] = 0;
                    top--;
                    break;
                }
                // 机器人 i 的健康度大
                healths[i]--;
                healths[j] = 0; // 移除机器人 j
                top--;
            }
        }

        // 返回幸存机器人的健康度
        List<Integer> ans = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                ans.add(h);
            }
        }
        return ans;
    }
}
