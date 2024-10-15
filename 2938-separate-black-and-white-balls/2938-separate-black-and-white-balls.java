class Solution {
    public long minimumSteps(String s) {
        return trick(s);
    }

    public long trick(String s) {
        int n = s.length();
        List<Integer> blacks = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') blacks.add(i);
        }

        if (blacks.size() == n) return 0;

        int ans = 0;

        int last = n - 1;

        for (int i : blacks) {
            ans += last - i;
            last--;
        }

        return ans;
    }

    public long mySol(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        int left = 0;
        int right = n - 1;
        int index = n - 1;

        int ans = 0;

        while (right >= 0) {
            while (right >= 0 && arr[right] == '1') {
                right--;
            }

            int nextRight = right - 1;
        }

        return ans;
    }
}