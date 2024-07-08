class Solution {
    public int findTheWinner(int n, int k) {
        return mySol(n, k);
    }

    public int mySol(int n, int k) {
        List<Integer> list = new ArrayList();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        return getWinner(list, 0, k);
    }

    private int getWinner(List<Integer> list, int start, int k) {
        if (list.size() == 1) return list.get(0);

        int n = list.size();

        int target = start + k - 1;

        list.remove(target % n);

        return getWinner(list, target % n, k);
    }
}