class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        return mySol(n, edges, succProb, start_node, end_node);
    }

    public double mySol(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap();
        double[] scores = new double[n];

        Arrays.fill(scores, -1d);

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.computeIfAbsent(edge[0], k -> new ArrayList()).add(new Pair(edge[1], succProb[i]));
            graph.computeIfAbsent(edge[1], k -> new ArrayList()).add(new Pair(edge[0], succProb[i]));
        }

        Queue<Pair<Integer, Double>> pq = new PriorityQueue<Pair<Integer, Double>>((a, b) -> {
            return b.getValue() > a.getValue() ? 1 : -1;
        });

        pq.add(new Pair(start, 1d));

        while (!pq.isEmpty()) {
            Pair<Integer, Double> pair = pq.poll();

            int node = pair.getKey();
            double score = pair.getValue();

            if (node == end) return score;

            if (!graph.containsKey(node)) continue;

            for (Pair<Integer, Double> p : graph.get(node)) {
                int nextNode = p.getKey();
                double nextScore = score * p.getValue();
                if (scores[nextNode] < nextScore) {
                    scores[nextNode] = nextScore;

                    Pair<Integer, Double> nextPair = new Pair(nextNode, nextScore);

                    pq.add(nextPair);
                }
            }
        }

        return 0d;
    }
}