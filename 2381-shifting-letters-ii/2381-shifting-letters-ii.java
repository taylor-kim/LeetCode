class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        return mySol(s, shifts);
    }

    public String mySol(String s, int[][] shifts) {
        Map<Integer, Integer> moves = new HashMap();

        for (int[] shift : shifts) {
            int move = shift[2] == 0 ? -1 : 1;

            int openMove = (moves.getOrDefault(shift[0], 0) + move + 26) % 26;
            moves.put(shift[0], openMove);

            int closeMove = (moves.getOrDefault(shift[1] + 1, 0) - move + 26) % 26;
            moves.put(shift[1] + 1, closeMove);
        }

        StringBuilder sb = new StringBuilder();

        int move = 0;

        // System.out.println(moves);

        for (int i = 0; i < s.length(); i++) {
            move = (move + moves.getOrDefault(i, 0) + 26) % 26;

            int next = ((s.charAt(i) - 'a') + move) % 26;

            // System.out.println(String.format("index:%d, move:%d, from:%c, to:%c", i, move, s.charAt(i), (char)(next + 'a')));

            sb.append((char)(next + 'a'));
        }

        return sb.toString();
    }
}