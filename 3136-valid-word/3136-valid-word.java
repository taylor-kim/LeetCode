class Solution {
    public boolean isValid(String word) {
        return mySol(word);
    }

    public boolean mySol(String word) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        boolean vowel = false;
        boolean consonant = false;

        for (char c : word.toCharArray()) {
            if (!(
                ('0' <= c && c <= '9') 
                || ('a' <= c && c <= 'z')
                || ('A' <= c && c <= 'Z')
            )) return false;

            if (c < '0' || '9' < c) {
                if (vowels.contains(c)) {
                    vowel = true;
                } else {
                    consonant = true;
                }
            }
        }

        // System.out.println(String.format("v:%b, c:%b", vowel, consonant));

        return word.length() >= 3 && vowel && consonant;
    }
}