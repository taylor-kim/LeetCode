class Solution {
    public int countSeniors(String[] details) {
        return mySol(details);
    }

    public int mySol(String[] details) {
        int count = 0;

        for (String p : details) {
            int first = p.charAt(11) - '0';

            if (first > 6) count++;
            else if (first == 6 && p.charAt(12) - '0' > 0) count++;
        }

        return count;
    }
}