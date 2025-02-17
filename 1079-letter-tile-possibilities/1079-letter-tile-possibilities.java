class Solution {
    public int numTilePossibilities(String tiles) {
        return mySol(tiles);
    }

    public int mySol(String tiles) {
        char[] arr = tiles.toCharArray();
        Arrays.sort(arr);

        Set<Long> set = new HashSet();

        topdown(arr, 0, new boolean[arr.length], 0L, set);

        // System.out.println(set);

        // StringBuilder sb = new StringBuilder();
        // long mask = (int)Math.pow(2, 5) - 1;

        // System.out.println(String.format("mask:%d", mask));

        // for (long num : set) {
            
        //     while (num > 0) {
        //         int digit = (int)(num & mask);
        //         num = num >> 5;

        //         if (digit == 0) continue;

        //         char c = (char)(digit + 'A' - 1);
        //         sb.append(c);
        //     }

        //     System.out.println(sb.reverse().toString());
        //     sb.setLength(0);
        // }

        return set.size();
    }

    private void topdown(char[] arr, int index, boolean[] visit, long seq, Set<Long> set) {
        if (index >= arr.length) return;

        if (visit[index]) return;

        long mask = (arr[index] - 'A' + 1);

        long seqNext = seq;

        if (seqNext != 0) {
            seqNext <<= 5;
        }

        seqNext |= mask;

        if (set.add(seqNext)) {
            visit[index] = true;

            for (int i = 0; i < arr.length; i++) {
                if (visit[i]) continue;

                topdown(arr, i, visit, seqNext, set);
            }

            visit[index] = false;
        }

        topdown(arr, index + 1, visit, seq, set);
    }

    private String bs(long num) {
        return Long.toBinaryString(num);
    }
}