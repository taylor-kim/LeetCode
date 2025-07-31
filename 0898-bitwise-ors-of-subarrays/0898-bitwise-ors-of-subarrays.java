class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        return official(arr);
    }

    public int official(int[] arr) {
        Set<Integer> ans = new HashSet();
        Set<Integer> set = new HashSet();
        set.add(0);

        for (int num : arr) {
            Set<Integer> nextSet = new HashSet();

            for (int or : set) {
                nextSet.add(or | num);
            }
            nextSet.add(num);
            set = nextSet;
            ans.addAll(set);
        }

        return ans.size();
    }

    public int mySol2_fail(int[] arr) {
        int n = arr.length;
        int[] bits = new int[32];
        int ans = 0;
        Set<Integer> set = new HashSet();

        int or = 0;

        for (int i = n - 1; i >= 0; i--) {
            int num = arr[i];
            set.add(num);
            boolean found = false;
            or |= num;
            set.add(or);

            for (int j = 0; j < bits.length; j++) {
                int bit = 1 << j;

                if ((num & bit) != 0 && bits[j] == 0) {
                    found = true;
                    bits[j]++;
                }
            }

            if (found) ans++;
        }

        return set.size();
    }

    public int mySol_bf(int[] arr) {
        int n = arr.length;
        int ans = 0;

        Set<Integer> set = new HashSet();

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            set.add(num);

            for (int j = i + 1; j < n; j++) {
                num |= arr[j];

                set.add(num);
            }
        }

        return set.size();
    }

    public int mySol_hold(int[] arr) {
        int n = arr.length;
        int ans = 0;

        Set<Integer> numbers = new HashSet();

        int[] bits = new int[32];

        int left = 0;

        for (int right = 0; right < n; right++) {
            int num = arr[right];

            if (numbers.add(num)) ans++;

            setNumber(bits, num, 1);

            int or = getNumber(bits);

            while (numbers.contains(getNumber(bits))) {
                
            }
        }

        return ans;
    }

    private void setNumber(int[] bits, int num, int delta) {
        for (int i = 0; i < bits.length; i++) {
            int bit = 1 << i;

            if ((num & bit) != 0) {
                bits[i] += delta;
            }
        }
    }

    private int getNumber(int[] bits) {
        int num = 0;

        for (int i = 0; i < bits.length; i++) {
            if (bits[i] != 0) {
                num |= 1 << i;
            }
        }

        return num;
    }
}