class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        return mySol3(s);
    }

    public int mySol3(String s) {
        int n = s.length();
        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            pSum[i + 1] = pSum[i] + digit;
        }

        int ans = pSum[n];

        int left = 0;
        int mid = 0;
        int next = 0;
        
        while (next < n) {
            while (left < n && s.charAt(left) != '0') {
                left++;
            }

            if (mid < left) {
                mid = left;
            }

            while (mid < n && s.charAt(mid) != '1') {
                mid++;
            }

            next = mid;

            while (next < n && s.charAt(next) != '0') {
                next++;
            }

            int right = next;

            while (right < n && s.charAt(right) == '0') {
                right++;
            }

            if (next < n) {
                ans = Math.max(ans, pSum[left] + (right - left) + (pSum[n] - pSum[right]));
            }

            left = next;
            mid = right;
        }

        return ans;
    }

    public int mySol2_fail(String s) {
        int added = 0;
        if (s.charAt(0) == '0') {
            s = "1" + s;
            added++;
        }

        if (s.charAt(s.length() - 1) == '0') {
            s = s + "1";
            added++;
        }

        int n = s.length();

        int[] pSum = new int[n + 1];
        List<Integer> indices = new ArrayList();

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit == 1) {
                indices.add(i);
            }
            pSum[i + 1] = pSum[i] + digit;
        }

        int ans = pSum[n];

        int index = 0;
        
        while (index + 2 < indices.size()) {
            int left = indices.get(index);
            int mid = indices.get(index + 1);
            int right = indices.get(index + 2);

            if (mid - left > 1 && right - mid > 1) {
                int count = pSum[left] + (right - left + 1) + (pSum[n] - pSum[right + 1]);

                // if (left == 0) {
                //     count--;
                // }

                // if (right == n - 1) {
                //     count--;
                // }

                ans = Math.max(ans, count);
            }

            index++;
        }

        return ans - added;
    }

    public int mySol_fail(String s) {
        int n = s.length();
        int left = 0;
        int totalCountOne = 0;
        int countOne = 0;

        int[] pSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pSum[i + 1] = pSum[i] + (s.charAt(i) - '0');
        }

        int ans = pSum[n];

        while (left < n && s.charAt(left) != '0') {
            left++;
        }

        int right = left + 1;

        while (right < n) {
            if (s.charAt(right) == '1') {
                right++;
                continue;
            }

            if (right + 1 < n && s.charAt(right + 1) == '0') {
                right++;
                continue;
            }


            if (s.charAt(right) == '0') {
                if (pSum[right + 1] - pSum[left] > 0) {
                    System.out.println("left:%d, right:%d, %d, %d, %d"
                    .formatted(left, right, pSum[left], (right - left + 1), (pSum[n] - pSum[right + 1])));
                    ans = Math.max(pSum[left] + (right - left + 1) + (pSum[n] - pSum[right + 1]), ans);
                }

                left = right;
            }

            right++;
        }

        return ans;
    }
}