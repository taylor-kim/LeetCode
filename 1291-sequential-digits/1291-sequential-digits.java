class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        return practice_sw(low, high);
    }

    public List<Integer> practice_sw(int lo, int hi) {
        String s = "123456789";
        int minLength = String.valueOf(lo).length();
        int maxLength = String.valueOf(hi).length();

        List<Integer> list = new ArrayList();

        for (int length = minLength; length <= maxLength; length++) {
            for (int start = 0; start <= s.length() - length; start++) {
                int num = Integer.parseInt(s.substring(start, start + length));

                if (lo <= num && num <= hi) {
                    list.add(num);
                }
            }
        }

        return list;
    }

    public List<Integer> try_bf(int low, int high) {
        Map<Integer, List<Integer>> map = new HashMap();

        for (int i = 1; i < 10; i++) {
            int num = i;
            int length = 1;

            while (num <= high) {
                if (low <= num && num <= high) {
                    map.computeIfAbsent(length, k -> new ArrayList()).add(num);
                }

                int digit = num % 10;

                if (digit == 9) break;

                num = num * 10 + digit + 1;
                length++;
            }
        }

        List<Integer> list = new ArrayList();

        for (int i = 1; i < 10; i++) {
            if (map.containsKey(i)) {
                list.addAll(map.get(i));
            }
        }

        return list;
    }

    public List<Integer> mySol_hold(int low, int high) {
        List<Integer> list = new ArrayList();

        backtrack("" + low, "" + high, false, 0, 0, list);

        return list;
    }

    private void backtrack(String lo, String hi, boolean carry, int index, int num, List<Integer> list) {
        if (index >= hi.length()) return;

        int prev = num % 10;
        int plusOne = prev + 1;

        int from = index >= lo.length() ? 0 : lo.charAt(index) - '0';
        int to = hi.charAt(index) - '0';

        // 400
        // 1234

        // 1
    }
}