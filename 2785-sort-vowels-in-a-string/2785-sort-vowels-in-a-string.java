class Solution {
    public String sortVowels(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        Set<Character> vowels = Set.of('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u');
        int[] freq = new int[10];

        char[] arr = s.toCharArray();

        for (char c : arr) {
            if (vowels.contains(c)) {
                freq[toInt(c)]++;
            }
        }

        int digit = 0;

        for (int i = 0; i < arr.length; i++) {
            if (vowels.contains(arr[i])) {
                while (digit < freq.length && freq[digit] == 0) {
                    digit++;
                }

                arr[i] = toChar(digit);
                freq[digit]--;
            }
        }

        return String.valueOf(arr);
    }

    private int toInt(char c) {
        switch (c) {
            case 'A': return 0;
            case 'E': return 1;
            case 'I': return 2;
            case 'O': return 3;
            case 'U': return 4;
            case 'a': return 5;
            case 'e': return 6;
            case 'i': return 7;
            case 'o': return 8;
            case 'u': return 9;
        }

        return -1;
    }

    private char toChar(int i) {
        switch (i) {
            case 0: return 'A';
            case 1: return 'E';
            case 2: return 'I';
            case 3: return 'O';
            case 4: return 'U';
            case 5: return 'a';
            case 6: return 'e';
            case 7: return 'i';
            case 8: return 'o';
            case 9: return 'u';
        }

        return '-';
    }
}