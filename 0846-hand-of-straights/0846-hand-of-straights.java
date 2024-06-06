class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        return official_greedy_n(hand, groupSize);
    }

    public boolean official_greedy_n(int[] hand, int groupSize) {
        Map<Integer, Integer> freq = new HashMap();

        for (int num : hand) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : hand) {
            int start = num;

            while (freq.getOrDefault(start - 1, 0) > 0) {
                start--;
            }

            while (start <= num) {
                while (freq.getOrDefault(start, 0) > 0) {
                    for (int end = start; end < groupSize + start; end++) {
                        if (freq.getOrDefault(end, 0) == 0) return false;

                        freq.put(end, freq.get(end) - 1);
                    }
                }
                start++;
            }
        }

        return true;
    }

    public boolean mySol_map_freq(int[] hand, int groupSize) {
        Map<Integer, Integer> freq = new HashMap();

        Arrays.sort(hand);

        for (int num : hand) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int index = 0;
        int made = 0;

        while (freq.size() > 0) {
            while (index < hand.length && freq.getOrDefault(hand[index], 0) == 0) {
                freq.remove(hand[index++]);
            }

            if (index >= hand.length) break;

            int start = hand[index];

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

    public boolean mySol_treemap_freq(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> freq = new TreeMap();

        for (int num : hand) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int made = 0;

        while (freq.size() > 0) {
            int start = freq.firstKey();

            if (freq.get(start) == 0) {
                // freq.remove(start);
                // continue;
                return false;
            }

            int end = start;

            while (end < start + groupSize) {
                if (freq.getOrDefault(end, 0) == 0) return false;

                freq.put(end, freq.get(end) - 1);

                if (freq.get(end) == 0) freq.remove(end);

                end++;
            }

            // if (end == start + groupSize) {
            //     made++;
            // } else {
            //     return false;
            // }
        }

        // return hand.length == groupSize * made;
        return true;
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