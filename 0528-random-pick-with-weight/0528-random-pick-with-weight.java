class Solution {
    private TreeMap<Double, Integer> probabilityMap = new TreeMap<>();
    private Random random = new Random();

    public Solution(int[] weights) {
        buildProbabilityMap(weights);
    }

    public int pickIndex() {
        double randomNumber = random.nextInt(100) + 1;
        return probabilityMap.floorEntry(randomNumber).getValue();
    }

    private void buildProbabilityMap(int[] weights) {
        int totalWeight = 0;

        for (int weight : weights) {
            totalWeight += weight;
        }

        double currentProbability = 1.0;

        for (int i = 0; i < weights.length; i++) {
            probabilityMap.put(currentProbability, i);
            currentProbability += ((double) weights[i] / totalWeight) * 100;
        }
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */