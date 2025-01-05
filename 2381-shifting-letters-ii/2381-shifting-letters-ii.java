class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        return mySol(s, shifts);
    }

    public String mySol(String s, int[][] shifts) {
        int n = s.length();
        int[] moves = new int[n + 1];

        for (int[] shift : shifts) {
            int move = shift[2] == 0 ? -1 : 1;

            int openMove = (moves[shift[0]] + move + 26) % 26;
            moves[shift[0]] = openMove;

            int closeMove = (moves[shift[1] + 1] - move + 26) % 26;
            moves[shift[1] + 1] = closeMove;
        }

        StringBuilder sb = new StringBuilder();

        int move = 0;

        // System.out.println(moves);

        for (int i = 0; i < n; i++) {
            move = (move + moves[i] + 26) % 26;

            int next = ((s.charAt(i) - 'a') + move) % 26;

            // System.out.println(String.format("index:%d, move:%d, from:%c, to:%c", i, move, s.charAt(i), (char)(next + 'a')));

            sb.append((char)(next + 'a'));
        }

        return sb.toString();
    }
}