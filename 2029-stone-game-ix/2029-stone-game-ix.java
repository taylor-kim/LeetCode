class Solution {
    public boolean stoneGameIX(int[] stones) {
        return editorial(stones);
    }

    public boolean editorial(int[] stones) {
        int cnt0 = 0,
            cnt1 = 0,
            cnt2 = 0;
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) {
                ++cnt0;
            } else if (type == 1) {
                ++cnt1;
            } else {
                ++cnt2;
            }
        }
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }

    public boolean mySol_fail(int[] stones) {
        int[] arr = new int[3];
        for (int num : stones) {
            arr[num % 3]++;
        }

        System.out.println(Arrays.toString(arr));

        return !bobwin(arr, 0, true);
    }

    private boolean bobwin(int[] arr, int sum, boolean alice) {
        if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) {
            return true;
        }

        boolean[] ans = new boolean[3];

        if (arr[0] != 0 && (sum % 3) != 0) {
            arr[0]--;

            ans[0] = bobwin(arr, sum, !alice);

            arr[0]++;
        }

        if (arr[1] != 0 && ((sum + 1) % 3) != 0) {
            arr[1]--;

            ans[1] = bobwin(arr, (sum + 1) % 3, !alice);

            arr[1]++;
        }

        if (arr[2] != 0 && ((sum + 2) % 3) != 0) {
            arr[2]--;

            ans[2] = bobwin(arr, (sum + 2) % 3, !alice);

            arr[2]++;
        }

        if (alice) {
            return !(ans[0] && ans[1] && ans[2]);
        } else {
            return ans[0] || ans[1] || ans[2];
        }
    }
}