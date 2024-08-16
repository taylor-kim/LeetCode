class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        return mySol(arrays);
    }
    
    
    public int mySol(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        int secMin = Integer.MAX_VALUE;
        int secMax = Integer.MIN_VALUE;

        boolean isSameList = false;

        for (List<Integer> list : arrays) {
            int localMin = list.get(0);
            int localMax = list.get(list.size() - 1);

            boolean minChanged = false;

            if (min > localMin) {
                secMin = min;
                min = localMin;
                minChanged = true;
            } else if (secMin > localMin) {
                secMin = localMin;
            }

            boolean maxChanged = false;

            if (max < localMax) {
                secMax = max;
                max = localMax;
                maxChanged = true;
            } else if (secMax < localMax) {
                secMax = localMax;
            }

            if (minChanged || maxChanged) {
                isSameList = minChanged && maxChanged;
            }

            // System.out.println(String.format("min:%d, max:%d, secMin:%d, secMax:%d, isSmameList:%b"
            // , min, max, secMin, secMax, isSameList));
        }

        if (isSameList) {
            return Math.max(max - secMin, secMax - min);
        } else {
            return max - min;
        }
    }
}