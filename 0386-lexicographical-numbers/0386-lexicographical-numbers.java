class Solution {
    public List<Integer> lexicalOrder(int n) {
        return mySol2(n);
    }

    public List<Integer> mySol2(int n) {
        List<Integer> ans = new ArrayList();

        for (int i = 1; i < 10; i++) {
            mySol2_dfs(i, n, ans);
        }

        return ans;
    }

    public void mySol2_dfs(int num, int n, List<Integer> ans) {
        if (num > n) return;

        ans.add(num);

        for (int i = 0; i < 10; i++) {
            mySol2_dfs(num * 10 + i, n, ans);
        }
    }    

    public List<Integer> mySol_fail(int n) {
        List<Integer> ans = new ArrayList();

        for (int i = 1; i < 10; i++) {
            int num = i;
            int power = 10;
            ans.add(num);
            for (int digit = 0; digit < 10; digit++) {
                int next = num * power;

                // if (next)
            }
        }

        return ans;
    }
}