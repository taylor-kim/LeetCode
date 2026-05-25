class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n+1];
        int p0 = 1;
        int[] p = new int[primes.length];
        int[] num = new int[primes.length];
        Arrays.fill(num, 1);
        while(p0 <= n){
            int min = Arrays.stream(num).min().getAsInt();
            ugly[p0++] = min;
            for (int i = 0; i < num.length; i++){
                if (min == num[i]){
                    num[i] = num[i] = (int) Math.min((long) primes[i] * ugly[++p[i]], Integer.MAX_VALUE);
                }
            }
        }
        return ugly[n];
    }
} 