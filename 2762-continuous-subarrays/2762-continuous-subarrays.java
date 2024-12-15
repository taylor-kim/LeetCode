class Solution {
    public long continuousSubarrays(int[] nums) {
        return try_monotonic_deque(nums);
    }

    public long try_monotonic_deque(int[] nums) {
        Deque<Integer> minHeap = new ArrayDeque();
        Deque<Integer> maxHeap = new ArrayDeque();

        int left = 0;
        long ans = 0;

        for (int right = 0; right < nums.length; right++) {
            while (!minHeap.isEmpty() && nums[minHeap.peekLast()] > nums[right]) {
                minHeap.pollLast();
            }
            minHeap.addLast(right);

            while (!maxHeap.isEmpty() && nums[maxHeap.peekLast()] < nums[right]) {
                maxHeap.pollLast();
            }
            maxHeap.addLast(right);

            while (!minHeap.isEmpty() && !maxHeap.isEmpty() 
                    && nums[maxHeap.peekFirst()] - nums[minHeap.peekFirst()] > 2) {

                if (minHeap.peekFirst() < maxHeap.peekFirst()) {
                    left = minHeap.pollFirst() + 1;
                } else {
                    left = maxHeap.pollFirst() + 1;
                }
            }

            ans += right - left + 1;
        }

        return ans;
    }

    public long official_min_max_heap(int[] nums) {
        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> {
            return nums[a] != nums[b] ? nums[a] - nums[b] : a - b;
        });
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            return nums[b] != nums[a] ? nums[b] - nums[a] : a - b;
        });

        int left = 0;
        long ans = 0;

        for (int right = 0; right < nums.length; right++) {
            minHeap.add(right);
            maxHeap.add(right);

            while (left < right && nums[maxHeap.peek()] - nums[minHeap.peek()] > 2) {
                left++;

                while (!minHeap.isEmpty() && minHeap.peek() < left) {
                    minHeap.poll();
                }

                while (!maxHeap.isEmpty() && maxHeap.peek() < left) {
                    maxHeap.poll();
                }
            }

            // System.out.println(String.format("left:%d, right:%d, min:%s, max:%s", left, right, minHeap, maxHeap));

            ans += right - left + 1;
        }

        return ans;
    }

    public long official_treemap(int[] nums) {
        long ans = 0;
        int left = 0;

        TreeMap<Integer, Integer> freq = new TreeMap();

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            while (freq.lastKey() - freq.firstKey() > 2) {
                int ln = nums[left++];
                freq.put(ln, freq.get(ln) - 1);

                if (freq.get(ln) == 0) freq.remove(ln);
            }

            ans += (right - left) + 1;
        }

        return ans;
    }

    public long mySol(int[] nums) {
        long ans = 0;
        int left = 0;

        Map<Integer, Integer> freq = new HashMap();

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            freq.put(num, freq.getOrDefault(num, 0) + 1);

            int[] minMax = getMinMax3(freq);

            while (Math.abs(num - minMax[0]) > 2 || Math.abs(num - minMax[1]) > 2) {
                int ln = nums[left++];
                freq.put(ln, freq.get(ln) - 1);

                if (freq.get(ln) == 0) freq.remove(ln);

                minMax = getMinMax3(freq);
            }

            ans += (right - left) + 1;
        }

        return ans;
    }

    private int[] getMinMax3(Map<Integer, Integer> map) {
        int[] minMax = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        
        for (Integer key : map.keySet()) {
            minMax[0] = Math.min(minMax[0], key);
            minMax[1] = Math.max(minMax[1], key);
        }

        return minMax;
    }

    private int[] getMinMax2(int[] freq, int[] prevMinMax) {
        int[] minMax = {-1, -1};

        System.out.println(String.format("prevMinMax:%s", Arrays.toString(prevMinMax)));

        for (int i = prevMinMax[0]; i <= prevMinMax[1]; i++) {
            if (freq[i] > 0) {
                minMax[0] = i;
                break;
            }
        }

        for (int i = prevMinMax[1]; i >= prevMinMax[0]; i--) {
            if (freq[i] > 0) {
                minMax[1] = i;
                break;
            }
        }

        return minMax;
    }

    private int[] getMinMax(int[] nums, int left, int right) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = left; i <= right; i++) {
            int num = nums[i];
            min = Math.min(num, min);
            max = Math.max(num, max);
        }

        return new int[] {min, max};
    }
}