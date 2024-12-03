class Solution {
    public String addSpaces(String s, int[] spaces) {
        return official_two_pointers(s, spaces);
    }

    public String official_two_pointers(String s, int[] spaces) {
        StringBuilder ans = new StringBuilder();
        int spaceIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            if (spaceIndex < spaces.length && spaces[spaceIndex] == i) {
                ans.append(' ');
                spaceIndex++;
            }

            ans.append(s.charAt(i));
        }

        return ans.toString();
    }

    public String mySol(String s, int[] spaces) {
        StringBuilder ans = new StringBuilder();

        int start = 0;

        for (int space : spaces) {
            ans.append(s.substring(start, space)).append(" ");
            start = space;
        }

        if (start < s.length()) {
            ans.append(s.substring(start));
        }

        return ans.toString();
    }
}