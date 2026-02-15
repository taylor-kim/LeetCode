class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        return gpt_help(nums, k, dist);
    }

    public long gpt_help(int[] nums, int k, int dist) {
        MyData myData = new MyData(k, dist);

        for (int i = 1; i < k; i++) {
            myData.add(nums[i]);
        }

        long ans = myData.sum;
        int n = nums.length;
        int left = 1;

        for (int right = k; right < n; right++) {
            int num = nums[right];

            myData.add(num);

            if (right - left > dist) {
                myData.delete(nums[left++]);
            }

            myData.adjust();

            if (myData.smallSize == k - 1) {
                ans = Math.min(ans, myData.sum);
            }
        }

        return nums[0] + ans;
    }

    class MyData {
        private TreeMap<Integer, Integer> small = new TreeMap();
        private TreeMap<Integer, Integer> large = new TreeMap();

        int smallSize = 0;
        int largeSize = 0;

        long sum = 0;
        int k;
        int dist;

        public MyData(int k, int dist) {
            this.k = k;
            this.dist = dist;
        }

        public void add(int num) {
            if (smallSize < k - 1) {
                small.put(num, small.getOrDefault(num, 0) + 1);
                smallSize++;
                sum += num;
            } else if (num < small.lastKey()) {
                small.put(num, small.getOrDefault(num, 0) + 1);
                sum += num;

                int delete = small.lastKey();
                small.put(delete, small.get(delete) - 1);
                sum -= delete;

                if (small.get(delete) == 0) {
                    small.remove(delete);
                }

                large.put(delete, large.getOrDefault(delete, 0) + 1);
                largeSize++;
            } else {
                large.put(num, large.getOrDefault(num, 0) + 1);
                largeSize++;
            }
        }

        public void delete(int num) {
            if (small.containsKey(num)) {
                deleteSmall(num);
            } else if (large.containsKey(num)) {
                deleteLarge(num);
            }
        }

        private void deleteSmall(int num) {
            small.put(num, small.get(num) - 1);
            if (small.get(num) == 0) {
                small.remove(num);
            }
            smallSize--;
            sum -= num;
        }

        private void deleteLarge(int num) {
            large.put(num, large.get(num) - 1);
            if (large.get(num) == 0) {
                large.remove(num);
            }
            largeSize--;
        }

        public void adjust() {
            while (smallSize < k - 1 && largeSize > 0) {
                int num = large.firstKey();
                deleteLarge(num);
                add(num);
            }
        }
    }

    public long mySol_tle(int[] nums, int k, int dist) {
        // k = 10
        // 8 <= dist <= 8
        // 1,2,3,4,5,6,7,8,9,10
        // second = 1 ~ (n - (k - 2) - 1);
        // index of kth = k - 1 ~ Math.min(n, second + dist)
        // nums[second] + nums[kth]; + sumOfMin()

        long sum = 0;

        List<Integer> list = new ArrayList();

        for (int i = 1; i < k; i++) {
            sum += nums[i];
            add(list, nums[i]);
        }

        long ans = sum;

        int n = nums.length;
        int left = 1;

        print(list + ", " + sum);

        for (int right = k; right < n; right++) {
            int num = nums[right];

            int inserted = add(list, num);

            if (inserted < k - 1) {
                sum += num;
                if (list.size() > k - 1) {
                    sum -= list.get(k - 1);
                }
            }

            if (right - left > dist) {
                int remove = nums[left++];
                int removeIndex = rightmost(list, remove);

                list.remove(removeIndex);

                if (removeIndex < k - 1) {
                    sum -= remove;

                    if (list.size() >= k - 1) {
                        sum += list.get(k - 2);
                    }
                }
            }

            print(list + ", " + sum);

            ans = Math.min(ans, sum);
        }

        return nums[0] + ans;
    }

    private int rightmost(List<Integer> list, int target) {
        int lo = 0;
        int hi = list.size();

        // find 4
        // 1, 5

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (list.get(mid) <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo - 1;
    }

    private int add(List<Integer> list, int num) {
        int index = rightmost(list, num) + 1;

        if (index == list.size()) {
            list.add(num);
        } else {
            list.add(index, num);
        }

        return index;
    }

    private void print(Object s) {
        // System.out.println(s);
    }
}