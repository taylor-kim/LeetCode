class Solution {
    public int maximumScore(List<Integer> nums, int k) {
        return mySol(nums, k);
    }

    public int mySol(List<Integer> nums, int k) {
        Integer max = nums.stream().mapToInt(n -> n).max().getAsInt();

        List<Integer> primes = getPrimes(max);

        int n = nums.size();

        int[] scores = new int[n];
        List<Integer>[] buckets = new List[max + 1];

        for (int i = 0; i < n; i++) {
            // scores[i] = getScore(nums.get(i), primes);
            scores[i] = getScore2(nums.get(i));

            if (buckets[nums.get(i)] == null) {
                buckets[nums.get(i)] = new LinkedList();
            }

            buckets[nums.get(i)].add(i);
        }

        // print(Arrays.toString(scores));

        int num = max;
        long ans = 1;
        int mod = (int)1e9 + 7;

        int[] toRight = new int[n];
        Arrays.fill(toRight, n);

        int[] toLeft = new int[n];

        Stack<Integer> rStack = new Stack();
        Stack<Integer> lStack = new Stack();

        for (int i = 0; i < n; i++) {
            int lScore = scores[i];

            while (!rStack.isEmpty() && scores[rStack.peek()] < lScore) {
                toRight[rStack.pop()] = i;
            }

            rStack.push(i);

            int right = n - i - 1;
            int rScore = scores[right];

            while (!lStack.isEmpty() && scores[lStack.peek()] <= rScore) {
                if (scores[lStack.peek()] == rScore) {
                    // toLeft[lStack.peek()] = lStack.peek();
                    // lStack.pop();
                    toLeft[lStack.pop()] = right + 1;
                } else {
                    toLeft[lStack.pop()] = right + 1;
                }
            }

            lStack.push(right);
        }

        // print(Arrays.toString(toRight));
        // print(Arrays.toString(toLeft));

        while (num >= 1 && k > 0) {
            if (buckets[num] == null) {
                num--;
                continue;
            }

            while (buckets[num] != null && k > 0) {
                // int start = buckets[num].remove(0);

                // int end = Math.max(rightmost(scores, start), start);

                // int count = Math.min(end - start + 1, k);

                // print(String.format("num:%d, end:%d, start:%d, count:%d, k:%d", num, end, start, count, k));

                int index = buckets[num].remove(0);

                // int count = Math.min((toRight[index] - toLeft[index]), k);

                long left = (long)(index - toLeft[index]);
                long right = (long)(toRight[index] - index - 1);

                long count = Math.min((left + right + 1) + (left * right), k);

                // print(String.format("index:%d, num:%d, right:%d, left:%d, count:%d, k:%d"
                //                     , index, num, right, left, count, k));

                long multi = power2(num, count, mod);

                ans = (ans * multi) % mod;

                if (buckets[num].size() == 0) {
                    buckets[num] = null;
                }

                k -= count;
            }

            num--;
        }

        // long test = power(22011, 1, mod);
        // test = (test * power(14858, 5, mod)) % mod;

        // print(test + "");

        return (int)ans;
    }

    private List<Integer> getPrimes(int max) {
        boolean[] nums = new boolean[max + 1];
        Arrays.fill(nums, true);
        nums[0] = nums[1] = false;

        List<Integer> primes = new ArrayList();

        for (int i = 2; i <= max; i++) {
            if (!nums[i]) continue;

            for (int j = i * i; j > 0 && j <= max; j += i * 2) {
                nums[j] = false;
            }
        }

        for (int i = 2; i <= max; i++) {
            if (nums[i]) primes.add(i);
        }

        return primes;
    }

    private int getScore(int num, List<Integer> primes) {
        int score = 0;

        int index = 0;

        while (num > 1) {
            int primeFactor = primes.get(index++);

            if (num % primeFactor != 0) continue;

            score++;

            while (num > 1 && num % primeFactor == 0) {
                num /= primeFactor;
            }
        }

        return score;
    }

    private int getScore2(int num) {
        int score = 0;

        // Handle factor of 2 separately
        if (num % 2 == 0) {
            score++;
            while (num % 2 == 0) {
                num /= 2;
            }
        }

        // Handle odd factors from 3 upwards
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                score++;  // i is a unique prime factor
                while (num % i == 0) {
                    num /= i;
                }
            }
        }

        // If num is still greater than 1, it is a prime number
        if (num > 1) {
            score++;
        }

        return score;
    }


    private int rightmost(int[] scores, int lo) {
        int hi = scores.length;
        int target = scores[lo];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (scores[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        lo--;

        return lo;
    }

    private long power(int base, int count, int mod) {
        long ans = 1l;

        while (count-- > 0) {
            ans = (ans * base) % mod;
        }

        return ans;
    }

    private long power2(int base, long count, int mod) {
        long ans = 1;
        long x = base % mod;  // Handle the case where base is larger than mod

        while (count > 0) {
            if (count % 2 == 1) {  // If count is odd, multiply by the base
                ans = (ans * x) % mod;
            }
            x = (x * x) % mod;  // Square the base
            count /= 2;  // Divide count by 2
        }

        return ans;
    }

    private void print(String s) {
        System.out.println(s);
    }
}