class Solution {
    public long dividePlayers(int[] skill) {
        return official_sort(skill);
    }

    public long official_sort(int[] skill) {
        int n = skill.length;

        Arrays.sort(skill);

        int target = skill[0] + skill[n - 1];

        long ans = 0;

        for (int i = 0; i < n / 2; i++) {
            int one = skill[i];
            int two = skill[n - i - 1];

            if (target != one + two) return -1;

            ans += (long)one * (long)two;
        }

        return ans;
    }

    public long mySol(int[] skill) {
        int n = skill.length;
        int teams = n / 2;
        long sum = 0;

        for (int num : skill) sum += num;

        int target = (int)(sum / teams);

        Map<Integer, Integer> map = new HashMap();

        long ans = 0;

        for (int num : skill) {
            int complement = target - num;

            if (map.containsKey(complement)) {
                ans += num * complement;

                map.put(complement, map.get(complement) - 1);

                if (map.get(complement) == 0) {
                    map.remove(complement);
                }
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        return map.size() == 0 ? ans : -1;
    }
}