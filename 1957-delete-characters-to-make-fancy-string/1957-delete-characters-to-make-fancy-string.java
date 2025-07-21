class Solution {
    public String makeFancyString(String s) {
        return mySol(s);
    }

    public String mySol(String s) {
        if (s.length() < 3) return s;

        StringBuilder ans = new StringBuilder();
        ans.append(s.charAt(0));
        ans.append(s.charAt(1));

        for (int i = 2; i < s.length(); i++) {
            char c = s.charAt(i);

            if (ans.charAt(ans.length() - 1) != c || ans.charAt(ans.length() - 2) != c) {
                ans.append(c);
            }
        }

        return ans.toString();
    }
}