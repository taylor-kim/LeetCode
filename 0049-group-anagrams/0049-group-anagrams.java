class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        return try_20240726_others(strs);
    }

    public List<List<String>> try_20240726_others(String[] strs) {
        Map<String, List<String>> map = new HashMap();

        for (String s : strs) {
            char[] freq = new char[26];

            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            String key = new String(freq);

            map.computeIfAbsent(key, k -> new ArrayList()).add(s);
        }

        return new ArrayList(map.values());
    }

    public List<List<String>> try_20240726(String[] strs) {
        Map<String, List<String>> map = new HashMap();

        for (String s : strs) {
            int[] freq = new int[26];

            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder key = new StringBuilder();

            for (int i = 0; i < freq.length; i++) {
                if (freq[i] > 0) {
                    key.append(String.valueOf((char)i + 'a').repeat(freq[i]));
                }
            }

            map.computeIfAbsent(key.toString(), k -> new ArrayList()).add(s);
        }

        return new ArrayList(map.values());
    }
}