class Solution {
    public long minimumSteps(String s) {
        return mySol3(s);
    }

    public long mySol3(String s) {
        int n = s.length();
        long ans = 0;
        int last = n - 1;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ans += last-- - i;
            }
        }
        
        return ans;
    }

    public long mySol2(String s) {
        int n = s.length();
        List<Integer> blacks = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') blacks.add(i);
        }

        if (blacks.size() == n) return 0;

        long ans = 0;

        int last = n - 1;

        for (int i : blacks) {
            ans += last - i;
            last--;
        }

        return ans;
    }

    public long mySol_aborted(String s) {
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