class Solution {
    public String addSpaces(String s, int[] spaces) {
        return mySol(s, spaces);
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

        // if (ans.charAt(ans.length() - 1) == ' ') {
        //     ans.setLength(ans.length() - 1);
        // }

        return ans.toString();
    }
}