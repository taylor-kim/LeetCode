class Solution {
    public long countOfSubstrings(String word, int k) {
        return official_sw2(word, k);
    }

    public long official_sw2(String word, int k) {
        return atLeastK(word, k) - atLeastK(word, k + 1);
    }

    public long atLeastK(String word, int k) {
        char[] arr = word.toCharArray();
        
        Map<Character, Integer> vowels = new HashMap();
        int consonants = 0;

        int left = 0;
        long ans = 0;

        for (int right = 0; right < arr.length; right++) {
            char c = arr[right];

            if (isVowel(c)) {
                vowels.put(c, vowels.getOrDefault(c, 0) + 1);
            } else {
                consonants++;
            }

            while (vowels.size() == 5 && consonants >= k) {
                ans += arr.length - right;

                char removed = arr[left++];

                if (isVowel(removed)) {
                    remove(vowels, removed);
                } else {
                    consonants--;
                }
            }
        }

        return ans;
    }

    public long official_sw(String word, int k) {
        char[] arr = word.toCharArray();
        
        Map<Character, Integer> vowels = new HashMap();
        int consonants = 0;
        int[] nextConsonant = new int[arr.length];
        int nextConsonantIndex = arr.length;

        for (int i = arr.length - 1; i >= 0; i--) {
            nextConsonant[i] = nextConsonantIndex;

            if (!isVowel(arr[i])) {
                nextConsonantIndex = i;
            }
        }

        int left = 0;
        long ans = 0;

        for (int right = 0; right < arr.length; right++) {
            char c = arr[right];

            if (isVowel(c)) {
                vowels.put(c, vowels.getOrDefault(c, 0) + 1);
            } else {
                consonants++;
            }

            while (consonants > k) {
                char removed = arr[left++];

                if (isVowel(removed)) {
                    remove(vowels, removed);
                } else {
                    consonants--;
                }
            }

            while (left < arr.length && vowels.size() == 5 && consonants == k) {
                ans += nextConsonant[right] - right;

                char removed = arr[left++];

                if (isVowel(removed)) {
                    remove(vowels, removed);
                } else {
                    consonants--;
                }
            }
        }

        return ans;
    }

    public long mySol_fail(String word, int k) {
        char[] arr = word.toCharArray();
        
        Map<Character, Integer> vowels = new HashMap();
        LinkedList<Integer> consonants = new LinkedList();

        int left = 0;
        int right = 0;
        long ans = 0;

        while (right < arr.length) {
            while (right < arr.length && (consonants.size() < k || isVowel(arr[right]))) {
                char c = arr[right++];

                if (isVowel(c)) {
                    vowels.put(c, vowels.getOrDefault(c, 0) + 1);
                } else {
                    consonants.add(right);
                }
            }

            while (left < arr.length && vowels.size() == 5 && consonants.size() == k) {
                ans += consonants.size();
            }
        }

        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private void remove(Map<Character, Integer> map, char c) {
        map.put(c, map.get(c) - 1);

        if (map.get(c) == 0) {
            map.remove(c);
        }
    }

    private int getCount(int left, int right, int k, Map<Character, Integer> map, LinkedList<Integer> consonants) {
        if (map.size() < 5) return 0;

        return 0;
    }
}