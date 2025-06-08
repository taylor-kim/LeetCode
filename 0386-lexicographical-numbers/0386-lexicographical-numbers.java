class Solution {
    public List<Integer> lexicalOrder(int n) {
        return official_no_extra_space(n);
    }

    public List<Integer> official_no_extra_space(int n) {
        List<Integer> ans = new ArrayList();

        int num = 1;

        for (int i = 0; i < n; i++) {
            ans.add(num);

            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num >= n) {
                    num /= 10;
                }

                num++;
            }
        }

        return ans;
    }

    public List<Integer> mySol(int n) {
        List<Integer> ans = new ArrayList();

        topdown(1, n, ans);

        return ans;
    }

    private void topdown(int start, int n, List<Integer> ans) {
        if (start > n) return;

        ans.add(start);
        topdown(start * 10, n, ans);

        if (start % 10 < 9) {
            topdown(start + 1, n, ans);
        }
    }
}