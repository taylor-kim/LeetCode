class Solution {
    public List<Integer> lexicalOrder(int n) {
        return mySol(n);
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

        // int limit = Math.min((start * 10) - 1, n);
        
        // for (int i = start + 1; i <= limit; i++) {
        //     topdown(i * 10, n, ans);
        // }
    }
}