class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        return official_pq(quality, wage, k);
    }
    
    public double official_pq(int[] quality, int[] wage, int k) {
        List<Pair<Double, Integer>> wageToQualityRatio = new ArrayList();

        for (int i = 0; i < quality.length; i++) {
            wageToQualityRatio.add(new Pair((double) wage[i] / quality[i], quality[i]));
        }

        Collections.sort(wageToQualityRatio, Comparator.comparingDouble(Pair::getKey));

        double ans = Double.MAX_VALUE;

        Queue<Integer> pq = new PriorityQueue();

        double totalQuality = 0;

        for (Pair<Double, Integer> pair : wageToQualityRatio) {
            pq.add(-pair.getValue());
            totalQuality += pair.getValue();

            if (pq.size() > k) {
                totalQuality += pq.poll();
            }

            if (pq.size() == k) {
                ans = Math.min(ans, totalQuality * pair.getKey());
            }
        }

        return ans;
    }

    int base = -1;
    double bw = 0.0;

    public double try_topdown_fail(int[] quality, int[] wage, int k) {
        double ans = Double.MAX_VALUE;

        for (int i = 0; i < quality.length; i++) {
            base = i;
            bw = wage[i] * 1.0;
            double other = try_topdown(quality, wage, 0, k);

            System.out.println(bw);
            ans = Math.min(ans, bw + other);
        }

        return ans;
    }

    public double try_topdown(int[] quality, int[] wage, int index, int k) {
        if (k == 0) return 0.0;
        if (index >= quality.length) return Double.MAX_VALUE;
        if (index == base) return try_topdown(quality, wage, index + 1, k);

        int q = quality[index];
        int w = wage[index];

        double rate = 1.0 * quality[base] / q;

        double adjBaseWage = w * rate;
        double adjWage = 0.0;
        double originBw = bw;

        if (bw < adjBaseWage) {
            bw = adjBaseWage;

            adjWage = w;
        } else {
            adjWage = bw / rate;
        }

        double include = try_topdown(quality, wage, index + 1, k - 1);

        if (include != Double.MAX_VALUE) {
            include += adjWage;
        }

        double exclude = try_topdown(quality, wage, index + 1, k);

        return Math.min(include, exclude);
    }
}