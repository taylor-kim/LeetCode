class Solution {
    public long dividePlayers(int[] skill) {
        return mySol(skill);
    }

    public long mySol(int[] skill) {
        int n = skill.length;
        int teams = n / 2;
        long sum = 0;

        for (int num : skill) sum += num;

        // if (sum % n != 0) return -1;

        int target = (int)(sum / teams);

        // System.out.println(String.format("sum:%d, teams:%d, target:%d", sum, teams, target));

        Map<Integer, Integer> map = new HashMap();

        long ans = 0;

        for (int num : skill) {
            int complement = target - num;

            if (map.containsKey(complement)) {
                // System.out.println(String.format("num:%d, complement:%d", num, complement));
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