class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        return mySol(times, targetFriend);
    }
    
    public int mySol(int[][] times, int targetFriend) {
        Integer[][] arr = new Integer[times.length][3];

        for (int i = 0; i < times.length; i++) {
            int[] t = times[i];

            arr[i][0] = t[0];
            arr[i][1] = t[1];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        int chair = 0;

        Queue<Integer> returnedChairs = new PriorityQueue();

        TreeMap<Integer, List<Integer>> treeMap = new TreeMap();

        int ans = -1;

        for (int i = 0; i < arr.length; i++) {
            Integer[] d = arr[i];

            int a = d[0];
            int l = d[1];
            int fNum = d[2];

            while (treeMap.floorKey(a) != null) {
                Integer key = treeMap.floorKey(a);

                for (int c : treeMap.get(key)) {
                    returnedChairs.add(c);
                }

                // returnedChairs.addAll(treeMap.get(key));

                treeMap.remove(key);
            }
            
            if (returnedChairs.isEmpty()) {
                ans = chair++;
            } else {
                ans = returnedChairs.poll();
            }

            treeMap.computeIfAbsent(l, k -> new ArrayList()).add(ans);

            if (fNum == targetFriend) {
                return ans;
            }
        }

        return ans;
    }
}