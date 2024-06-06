class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        return mySol(hand, groupSize);
    }

    public boolean mySol(int[] hand, int groupSize) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : hand) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] bucket = new int[max + 1];

        for (int num : hand) {
            bucket[num]++;
        }

        int num = min;
        int made = 0;

        while (num <= max - groupSize + 1) {
            if (bucket[num] <= 0) {
                num++;
                continue;
            }

            int consecutive = 0;
            int seq = num;

            while (seq < groupSize + num) {
                // System.out.print(String.format("bucket[%d]:%d, ", seq, bucket[seq]));
                if (bucket[seq]-- <= 0) break;

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