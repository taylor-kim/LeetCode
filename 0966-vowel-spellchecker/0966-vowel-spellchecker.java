class Solution {
    private Map<String, List<String>> indexMap = new HashMap<>();

    public String[] spellchecker(String[] wordlist, String[] queries) {
        String[] result = new String[queries.length];

        for (String word : wordlist) {
            String key = word.toLowerCase();

            if (!indexMap.containsKey(key)) {
                indexMap.put(key, new ArrayList<>());
            }

            indexMap.get(key).add(word);

            String secondKey = removeVowels(key);

            if (!indexMap.containsKey(secondKey)) {
                indexMap.put(secondKey, new ArrayList<>());
            }

            indexMap.get(secondKey).add(word);
        }

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            String replaced = findValidWord(query);

            result[i] = replaced;
        }

        return result;
    }

    private String findValidWord(String query) {
        String lowerQuery = query.toLowerCase();

        if (indexMap.containsKey(lowerQuery)) {
            List<String> candidates = indexMap.get(lowerQuery);

            if (candidates.contains(query)) {
                return query;
            }

            for (String word : candidates) {
                if (word.equalsIgnoreCase(query)) {
                    return word;
                }
            }
        }

        String removeVowelKey = removeVowels(lowerQuery);

        if (indexMap.containsKey(removeVowelKey)) {
            List<String> candidates = indexMap.get(removeVowelKey);

            return candidates.get(0);
        }

        return "";
    }

    private String removeVowels(String s) {
//        return s.replaceAll("[aeiou]", "_");

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            sb.append(isVowel(c) ? '_' : c);
        }

        return sb.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}