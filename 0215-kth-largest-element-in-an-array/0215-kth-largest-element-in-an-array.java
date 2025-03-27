class Solution {
    public int findKthLargest(int[] nums, int k) {
        // return quickSelect(nums, 0, nums.length - 1, k);
        return official_counting_sort(nums, k);
    }

    public int official_counting_sort(int[] nums, int k) {
        int n = nums.length;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int[] count = new int[max - min + 1];

        for (int num : nums) {
            count[num - min]++;
        }

        for (int i = max - min; i >= 0; i--) {
            k -= count[i];
            if (k <= 0) {
                return i + min;
            }
        }

        return -1;
    }

    public int try_bucket_sort(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        List<Integer> list = new ArrayList();

        int kthSmallest = n - k + 1;

        for (int i = min; i <= max && list.size() < kthSmallest; i++) {
            for (int j = 0; j < freq.getOrDefault(i, 0); j++) {
                list.add(i);
            }
        }

        return list.get(kthSmallest - 1);
    }

    int quickSelect(int[] nums, int low, int high, int k) {
        int pivot = low;

        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, pivot++, j);
            }
        }
        // System.out.println(Arrays.toString(nums));
        swap(nums, pivot, high);
        // System.out.println(Arrays.toString(nums) + "\n\n");
        
        // count the nums that are > pivot from high
        int count = high - pivot + 1;
        // pivot is the one!
        if (count == k) return nums[pivot];
        // pivot is too small, so it must be on the right
        if (count > k) return quickSelect(nums, pivot + 1, high, k);
        // pivot is too big, so it must be on the left
        return quickSelect(nums, low, pivot - 1, k - count);
    }

    public int myQuickSelect(int[] nums, int k) {
        return myQuickSelect(nums, nums.length - k, 0, nums.length - 1);
    }

    public int myQuickSelect(int[] nums, int k, int lo, int hi) {
        int pivot = select(nums, lo, hi);

        if (pivot < k) {
            return myQuickSelect(nums, k, pivot + 1, hi);
        } else if (pivot > k) {
            return myQuickSelect(nums, k, lo, pivot - 1);
        } else {
            return nums[pivot];
        }
    }

    private int select(int[] nums, int lo, int hi) {
        int pivot = lo;
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (i < hi && nums[pivot] > nums[++i]);
            while (j > lo && nums[pivot] < nums[--j]);

            if (i >= j) break;

            swap(nums, i, j);
        }

        swap(nums, pivot, j);

        return j;
    }

    public int nLogN(int[] nums, int k) {
        Arrays.sort(nums);

        return nums[nums.length - k];
    }

    public int nLogK(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

        for (int num : nums) {
            queue.offer(num);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        return queue.peek();
    }

    public int nSol1(int[] nums, int k) {
        // this shuffle makes sol faster.
        // shuffle(nums);

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int j = partition2(nums, lo, hi);

            if (j < k) {
                lo = j + 1;
            } else if (j > k){
                hi = j - 1;
            } else {
                break;
            }
        }

        return nums[k];
    }

    private int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (i < hi && a[++i] < a[lo]);
            while (j > lo && a[lo] < a[--j]);

            if (i >= j) {
                break;
            }

            swap(a, i, j);
        }

        swap(a, lo, j);

        return j;
    }

    private int partition2(int[] a, int lo, int hi) {
        int pi = lo;
        
        for (int i = lo; i < hi; i++) {
            if (a[i] < a[hi]) {
                swap(a, pi++, i);
            }
        }

        swap(a, pi, hi);

        return pi;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void shuffle(int a[]) {
        Random random = new Random();

        for (int i = 1; i < a.length; i++) {
            int r = random.nextInt(i + 1);
            swap(a, i, r);
        }
    }
}