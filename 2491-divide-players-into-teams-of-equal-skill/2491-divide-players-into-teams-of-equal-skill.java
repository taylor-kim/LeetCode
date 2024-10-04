class Solution {
    public long dividePlayers(int[] skill) {
        return official_freq_map(skill);
    }

    public long official_freq_map(int[] skill) {
        int n = skill.length;
        int totalSkill = 0;
        Map<Integer, Integer> skillMap = new HashMap<>();

        // Calculate total skill and build frequency map
        for (int s : skill) {
            totalSkill += s;
            skillMap.put(s, skillMap.getOrDefault(s, 0) + 1);
        }

        // Check if total skill can be evenly distributed among teams
        if (totalSkill % (n / 2) != 0) {
            return -1;
        }

        int targetSkill = totalSkill / (n / 2);
        long totalChemistry = 0;

        // Iterate through unique skill values
        for (int currSkill : skillMap.keySet()) {
            int currFreq = skillMap.get(currSkill);
            int partnerSkill = targetSkill - currSkill;

            // Check if valid partner skill exists with matching frequency
            if (
                !skillMap.containsKey(partnerSkill) ||
                currFreq != skillMap.get(partnerSkill)
            ) {
                return -1;
            }

            // Calculate chemistry for all pairs with this skill
            totalChemistry +=
            (long) currSkill * (long) partnerSkill * (long) currFreq;
        }

        // Return half of total chemistry (as each pair is counted twice)
        return totalChemistry / 2;
    }

    public long official_freq_array(int[] skill) {
        int n = skill.length;
        int sum = 0;
        int[] freq = new int[1001];

        for (int num : skill) {
            sum += num;
            freq[num]++;
        }

        if (sum % (n / 2) != 0) return -1;

        int target = sum / (n / 2);

        long ans = 0;

        for (int num : skill) {
            int complement = target - num;

            if (freq[complement]-- == 0) return -1;

            ans += num * complement;
        }

        return ans / 2;
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