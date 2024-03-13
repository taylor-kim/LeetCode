class Solution {
    public String customSortString(String order, String s) {
        return official_custom_comparator(order, s);
    }

    public String official_custom_comparator(String order, String s) {
        Character[] arr = new Character[s.length()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = s.charAt(i);
        }

        Arrays.sort(arr, (a, b) -> {
            return order.indexOf(a) - order.indexOf(b);
        });

        StringBuilder sb = new StringBuilder();

        for (char c : arr) {
            sb.append(c);
        }

        return sb.toString();
    }

    public String mySol(String order, String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder matches = new StringBuilder();
        StringBuilder others = new StringBuilder();

        int matchCount = 0;

        for (char c : order.toCharArray()) {
            if (freq[c - 'a'] == 0) {
                continue;
            }

            while (freq[c - 'a']-- > 0) {
                matches.append(c);
            }
        }

        for (int i = 0; i < 26; i++) {
            while (freq[i]-- > 0) {
                others.append((char)(i + 'a'));
            }
        }

        return matches.toString() + others.toString();
    }
}