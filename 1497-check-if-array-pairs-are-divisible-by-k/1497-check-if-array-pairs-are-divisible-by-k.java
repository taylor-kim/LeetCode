class Solution {
    public boolean canArrange(int[] arr, int k) {
        return official_hashing_and_counting(arr, k);
    }

    public boolean official_hashing_and_counting(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap();

        for (int num : arr) {
            num = mod(num, k);
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        for (int num : arr) {
            num = mod(num, k);

            if (num == 0) {
                if (counter.get(num) % 2 == 1) {
                    return false;
                }
            } else if (
                !Objects.equals(
                    counter.get(num),
                    counter.get(k - num)
                )
            ) {
                return false;
            }
        }

        return true;
    }

    public boolean mySol2(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap();

        int matching = 0;

        for (int i = 0; i < arr.length; i++) {
            int num = mod(arr[i], k);
            int complement = mod(k - num, k);
            
            if (map.containsKey(complement)) {
                map.put(complement, map.get(complement) - 1);

                if (map.get(complement) == 0) {
                    map.remove(complement);
                }

                matching++;
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        return arr.length / 2 == matching;
    }

    private int mod(int num, int m) {
        num %= m;

        return num < 0 ? num + m : num;
    }

    public boolean mySol(int[] arr, int k) {
        return mySol_bf(arr, k, new boolean[arr.length]);
    }

    public boolean mySol_bf(int[] arr, int k, boolean[] visit) {
        boolean ans = false;

        int index = 0;

        while (index < arr.length && visit[index]) {
            index++;
        }

        if (index >= arr.length) return true;

        visit[index] = true;

        for (int i = 0; i < arr.length; i++) {
            if (visit[i]) continue;

            boolean sub = false;

            if ((arr[index] + arr[i]) % k == 0) {
                visit[i] = true;

                sub = mySol_bf(arr, k, visit);

                visit[i] = false;
            }

            ans |= sub;
        }

        visit[index] = false;

        return ans;
    }
}