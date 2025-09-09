class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        return mySol2(n, delay, forget);
    }

    public int mySol2(int n, int delay, int forget) {
        Queue<int[]> sharing = new LinkedList();
        Queue<int[]> discover = new LinkedList();

        discover.add(new int[] {1, 1});

        int day = 1;
        long ans = 1;
        int mod = (int)1e9 + 7;

        while (day <= n) {
            while (!discover.isEmpty() && discover.peek()[0] + delay == day) {
                sharing.add(discover.poll());
            }

            while (!sharing.isEmpty() && sharing.peek()[0] + forget == day) {
                ans = (ans - sharing.poll()[1] + mod) % mod;
            }

            if (!sharing.isEmpty()) {
                long known = 0;

                for (int[] data : sharing) {
                    known = (known + data[1]) % mod;
                }

                ans = (ans + known) % mod;

                discover.add(new int[] {day, (int)known});
            }

            // System.out.println(String.format("day:%d, ans:%d, discover:%d, sharing:%d", day, ans, discover.size(), sharing.size()));

            day++;
        }

        return (int)ans;
    }

    public int mySol_mle(int n, int delay, int forget) {
        Queue<Integer> sharing = new LinkedList();
        Queue<Integer> discover = new LinkedList();

        discover.add(1);

        int day = 1;
        int ans = 1;
        int mod = (int)1e9 + 7;

        while (day <= n) {
            Queue<Integer> newbies = new LinkedList();

            while (!discover.isEmpty() && discover.peek() + delay == day) {
                sharing.add(discover.poll());
            }

            while (!sharing.isEmpty() && sharing.peek() + forget == day) {
                sharing.poll();
                ans--;
            }

            if (!sharing.isEmpty()) {
                ans = (ans + sharing.size()) % mod;

                for (int i = 0; i < sharing.size(); i++) {
                    discover.add(day);
                }
            }

            // ans = (ans + sharing.size()) % mod;

            // if (!newbies.isEmpty()) {
            //     discover.addAll(newbies);
            //     // ans = (ans + newbies.size()) % mod;
            // }

            // System.out.println(String.format("day:%d, ans:%d, discover:%d, sharing:%d", day, ans, discover.size(), sharing.size()));

            day++;
        }

        return ans;
    }
}