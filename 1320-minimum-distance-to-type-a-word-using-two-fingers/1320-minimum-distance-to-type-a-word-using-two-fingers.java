class Solution {
    public int minimumDistance(String word) {
        return mySol(word);
    }

    public int mySol(String word) {
        /**
        char leftOn, char rightOn, int index

        [index] = Math.min(
            [index - 1] + distance(getChar(leftOn), getChar(index)),
            [index - 1] + distance(getChar(rightOn), getChar(index))
        )
        */

        char c = 'A';

        Map<Character, int[]> keyboard = new HashMap();

        for (int i = 0; i < 26; i++) {
            int row = i / 6;
            int col = i % 6;

            keyboard.put(c++, new int[] {row, col});
        }

        // System.out.println("A:%d, Z+1:%d".formatted((int)'A', (int)c));

        return topdown(word, 0, c, c, keyboard, new Integer[word.length()][27][27]);
    }

    private int topdown(String word, int index, char f1, char f2, Map<Character, int[]> keyboard, Integer[][][] memo) {
        if (index >= word.length()) return 0;

        if (memo[index][f1 - 'A'][f2 - 'A'] != null) {
            return memo[index][f1 - 'A'][f2 - 'A'];
        }

        char c = word.charAt(index);

        int cost1 = topdown(word, index + 1, c, f2, keyboard, memo) + getCost(keyboard, c, f1);
        int cost2 = topdown(word, index + 1, f1, c, keyboard, memo) + getCost(keyboard, c, f2);

        // System.out.println("index:%d, f1:%c, f2:%c, c:%c, cost1:%d, cost2:%d".formatted(index, f1, f2, c, cost1, cost2));

        return memo[index][f1 - 'A'][f2 - 'A'] = Math.min(cost1, cost2);
    }

    private int getCost(Map<Character, int[]> keyboard, char c1, char c2) {
        if (c1 == c2 || (c1 == '[' || c2 == '[')) return 0;

        int[] pos1 = keyboard.get(c1);
        int[] pos2 = keyboard.get(c2);

        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }
}