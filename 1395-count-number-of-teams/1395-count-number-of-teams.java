class Solution {
    public int numTeams(int[] rating) {
        return mySol_nnn(rating);
    }

    public int mySol_topdown2(int[] rating) {
        return mySol_topdown2(rating, 0, 1, 2);
    }

    public int mySol_topdown2(int[] rating, int i, int j, int k) {
        if (k >= rating.length) return 0;

        int ans = 0;

        int inc = rating[i] < rating[j] && rating[j] < rating[k] ? 1 : 0;
        int dec = rating[i] > rating[j] && rating[j] > rating[k] ? 1 : 0;

        int delta = inc + dec;

        ans += mySol_topdown2(rating, i, j, k + 1);
        ans += mySol_topdown2(rating, i, k, k + 1);
        ans += mySol_topdown2(rating, j, k, k + 1);

        return delta + ans;
    }

    public int mySol_nnn(int[] rating) {
        int n = rating.length;
        int ans = 0;

        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (rating[i] < rating[j] && rating[j] < rating[k]) ans++;

                    if (rating[i] > rating[j] && rating[j] > rating[k]) ans++;
                }
            }
        }

        return ans;
    }

    public int mySol_lis(int[] rating) {
        int n = rating.length;
        int[] inc = new int[n];
        int[] dec = new int[n];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            inc[i] = 1;
            dec[i] = 1;
            for (int j = 0; j < i; j++) {
                if (rating[j] < rating[i]) {
                    inc[i] = Math.max(inc[i], inc[j] + 1);
                } else if (rating[j] > rating[i]) {
                    dec[i] = Math.max(dec[i], dec[j] + 1);
                }

                if (inc[i] >= 3) ans++;
                if (dec[i] >= 3) ans++;
            }
        }

        System.out.println(Arrays.toString(inc));
        System.out.println(Arrays.toString(dec));

        for (int i = 0; i < n; i++) {
            // if (inc[i] >= 3)
        }

        return ans;
    }

    public int mySol_topdown_fail(int[] rating) {
        return mySol_topdown(0, 1, 0, rating);
    }

    public int mySol_topdown(int prev, int index, int count, int[] rating) {
        if (index >= rating.length) return count >= 3 ? count - 2 : 0;

        int ans = 0;

        if (rating[prev] < rating[index]) {
            if (count == 0) {
                ans += mySol_topdown(index, index + 1, 2, rating);
            } else {
                ans += mySol_topdown(index, index + 1, count + 1, rating);
            }
        }

        ans += mySol_topdown(prev, index + 1, 0, rating);
        ans += mySol_topdown(index, index + 1, 0, rating);

        return ans;
    }

    public int mySol_fail(int[] rating) {
        Deque<Integer> queue = new LinkedList();

        int ans = 0;

        for (int num : rating) {
            while (!queue.isEmpty() && queue.peekLast() > num) {
                queue.pollLast();
            }

            queue.addLast(num);

            if (queue.size() >= 3) {
                System.out.println(queue);
                ans++;
            }
        }

        System.out.println(ans);

        queue.clear();

        for (int num : rating) {
            while (!queue.isEmpty() && queue.peekLast() > -num) {
                queue.pollLast();
            }

            queue.addLast(-num);

            if (queue.size() >= 3) {
                System.out.println(queue);
                ans++;
            }
        }

        return ans;
    }
}