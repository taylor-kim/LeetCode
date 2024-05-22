class Solution {
    public List<List<String>> partition(String s) {
        return try_20240522(s);
    }

    public List<List<String>> try_20240522(String s) {
        List<List<String>> result = new ArrayList();

        try_20240522(s, 0, result, new ArrayList());

        return result;
    }

    public void try_20240522(String s, int index, List<List<String>> result, List<String> list) {
        if (index >= s.length()) {
            result.add(new ArrayList(list));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                list.add(s.substring(index, i + 1));
                try_20240522(s, i + 1, result, list);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }

        return true;
    }

    public List<List<String>> niceSolWithDP(String s) {
        List<List<String>> result = new ArrayList();
        boolean[][] dp = new boolean[s.length()][s.length()];

        niceSolWithDP(s, result, new ArrayList(), dp, 0);

        return result;
    }

    public void niceSolWithDP(String s, List<List<String>> result, List<String> list, boolean[][] dp, int start) {
        if (start >= s.length()) {
            result.add(new ArrayList(list));
        } else {
            for (int end = start; end < s.length(); end++) {
                if ((s.charAt(start) == s.charAt(end)) && (end - start <= 2 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;
                    list.add(s.substring(start, end + 1));
                    niceSolWithDP(s, result, list, dp, end + 1);
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    public List<List<String>> mySol(String s) {
        List<List<String>> result = new ArrayList();

        mySol(s, result, new ArrayList(), 0);

        return result;
    }

    public void mySol(String s, List<List<String>> result, List<String> list, int index) {
        if (index >= s.length()) {
            result.add(new ArrayList(list));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);

            if (isPalindrome(sub)) {
                list.add(sub);

                mySol(s, result, list, i + 1);

                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }

        return true;
    }
}