class Solution {
    public int sumFourDivisors(int[] nums) {
        return mySol2_fail(nums);
    }

    public int after_hint(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int limit = (int)Math.sqrt(nums[i]) + 1;

            // System.out.println("num:%d, limit:%d".formatted(num, limit));

            int count = 0;
            int sum = 0;

            for (int divisor = 2; divisor < limit; divisor++) {
                if (num % divisor != 0) continue;

                int quotient = num / divisor;

                if (quotient == divisor) {
                    count++;
                    break;
                } else {
                    count += 2;
                    sum += quotient + divisor;
                }
            }

            if (count == 2) {
                ans += sum + 1 + num;
            }
        }

        return ans;
    }

    public int mySol2_fail(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);

        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (!primes[i]) continue;

            for (int j = i * i; j <= max; j += i) {
                primes[j] = false;
            }
        }

        List<Integer> divisors = new ArrayList();

        for (int i = 2; i <= max; i++) {
            if (primes[i]) divisors.add(i);
        }

        int ans = 0;

        for (int num : nums) {
            if (num <= 4 || primes[num]) continue;

            Set<Integer> set = new HashSet();
            int sum = 0;
            int limit = (int)Math.sqrt(num) + 1;

            boolean success = true;

            for (int divisor : divisors) {
                if (divisor >= limit) break;
                if (num % divisor != 0) continue;

                int quotient = num / divisor;

                if (divisor < quotient 
                    && set.add(quotient) && set.add(divisor) 
                    && (primes[quotient] || divisor * divisor == quotient)) {
                    sum = quotient + divisor;
                } else {
                    success = false;
                    break;
                }
            }

            if (set.size() == 2 && success) {
                // System.out.println("sum:%d, num:%d".formatted(sum, num));
                ans += 1 + num + sum;
            }
        }

        return ans;
    }

    public int mySol_fail(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        // List<Integer> primes = getPrimes(max);

        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (!primes[i]) continue;

            for (int j = i * i; j <= max; j += i) {
                primes[j] = false;
            }
        }

        List<Integer> divisors = new ArrayList();

        for (int i = 2; i <= max; i++) {
            if (primes[i]) divisors.add(i);
        }

        int ans = 0;

        outer: for (int num : nums) {
            if (num <= 4 || primes[num]) continue;

            int sum = 0;
            int quotient = num;

            for (int divisor : divisors) {
                if (divisor >= quotient) break;
                if (num % divisor != 0) continue;

                quotient = num / divisor;

                if (divisor == quotient) continue outer;
                if (sum != 0) continue outer;

                sum += divisor + quotient;
            }

            ans += sum + 1 + num;
        }

        return ans;
    }

    private List<Integer> getPrimes(int max) {
        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for (int i = 2; i * i <= max; i++) {
            if (!primes[i]) continue;

            for (int j = i; j <= max; j += i) {
                primes[j] = false;
            }
        }

        List<Integer> list = new ArrayList();

        for (int i = 2; i <= max; i++) {
            if (primes[i]) list.add(i);
        }

        return list;
    }
}