class Solution {
    public String compressedString(String word) {
        return my_oldSol(word);
    }

    public String official_clean(String word) {
        int pos = 0;
        int count = 0;

        StringBuilder sb = new StringBuilder();

        while (pos < word.length()) {
            char c = word.charAt(pos);
            count = 0;

            while (pos < word.length()
                && count < 9
                && c == word.charAt(pos)) {
                pos++;
                count++;
            }

            sb.append(count).append(c);
        }

        return sb.toString();
    }

    public String mySol(String word) {
        int n = word.length();

        if (n == 0) return "";

        StringBuilder sb = new StringBuilder();

        char prev = word.charAt(0);
        int count = 1;

        for (int i = 1; i < n; i++) {
            char c = word.charAt(i);

            if (prev == c) {
                count++;

                if (count == 10) {
                    sb.append(9).append(c);
                    count = 1;
                }
            } else {
                sb.append(count).append(prev);
                prev = c;
                count = 1;
            }
        }

        sb.append(count).append(prev);

        return sb.toString();
    }

    public String my_oldSol(String word) {
        char prev = word.charAt(0);
        
        StringBuilder sb = new StringBuilder();
        
        int index = 0;
        int count = 0;
        
        while (index < word.length()) {
            prev = word.charAt(index);
            
            while (index < word.length() && prev == word.charAt(index)) {
                count++;
                index++;
            }
            
            if (count != 0) {
                int n = count / 9;
                int odd = count % 9;

                while (n-- > 0) {
                    sb.append("9").append(prev);
                }
                
                if (odd > 0) {
                    sb.append(odd).append(prev);
                }
            }
            
            count = 0;
        }
        
        return sb.toString();
    }
}