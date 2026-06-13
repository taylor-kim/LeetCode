class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        return mySol(words, weights);
    }

    public String mySol(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            int sum = 0;

            for (char c : word.toCharArray()) {
                sum += weights[(c - 'a')];
            }

            sum %= 26;

            sb.append((char)(25 - sum + 'a'));
        }

        return sb.toString();
    }
}