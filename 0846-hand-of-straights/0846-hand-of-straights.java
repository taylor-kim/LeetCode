class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        return mySol_freq(hand, groupSize);
    }

    public boolean mySol_freq(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> freq = new TreeMap();

        for (int num : hand) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int made = 0;

        while (freq.size() > 0) {
            int start = freq.firstKey();

            if (freq.get(start) == 0) {
                freq.remove(start);
                continue;
            }

            int end = start;

            while (end < start + groupSize) {
                if (freq.getOrDefault(end, 0) == 0) break;

                freq.put(end, freq.get(end) - 1);
                end++;
            }

            if (end == start + groupSize) {
                made++;
            } else {
                return false;
            }
        }

        return hand.length == groupSize * made;
    }

    public boolean mySol_bucket_MLE(int[] hand, int groupSize) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : hand) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] bucket = new int[max + 1 - min];

        for (int num : hand) {
            bucket[num - min]++;
        }

        int num = min;
        int made = 0;

        while (num <= max - groupSize + 1) {
            if (bucket[num - min] <= 0) {
                num++;
                continue;
            }

            int consecutive = 0;
            int seq = num;

            while (seq < groupSize + num) {
                // System.out.print(String.format("bucket[%d]:%d, ", seq, bucket[seq]));
                if (bucket[seq - min]-- <= 0) break;

                seq++;
            }

            if (seq < groupSize + num) {
                return false;
            } else {
                made++;
            }

            // System.out.println("");
        }

        // System.out.println(String.format("length:%d, groupSize:%d, made:%d", hand.length, groupSize, made));

        return hand.length == made * groupSize;
    }
}