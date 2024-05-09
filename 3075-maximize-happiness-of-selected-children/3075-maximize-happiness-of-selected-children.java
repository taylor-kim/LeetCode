class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        return official_sort(happiness, k);
    }

    public long official_heap(int[] happiness, int k) {
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int h : happiness) {
            pq.add(h);
        }

        long ans = 0;
        int turn = 0;

        for (int i = 0; i < k; i++) {
            ans += Math.max(pq.poll() - turn, 0);

            turn++;
        }

        return ans;
    }

    public long official_sort(int[] happiness, int k) {
        int n = happiness.length;

        Integer[] happinessArray = new Integer[n];
        for(int i = 0; i < n; i++) {
            happinessArray[i] = happiness[i];
        }
        
        Arrays.sort(happinessArray, Collections.reverseOrder());

        long ans = 0;
        int turns = 0;

        for(int i = 0; i < k; i++) {
            // Adjust happiness and ensure it's not negative
            ans += Math.max(happinessArray[i] - turns, 0);  

             // Increment turns for the next iteration
            turns++; 
        }
        
        return ans;
    }

    public long mySol(int[] happiness, int k) {
        int n = happiness.length;
        happiness = IntStream.of(happiness).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();

        int reduce = 0;

        long ans = 0;

        for (int i = 0; i < n && k > 0; i++) {
            int sum = 0;

            sum = Math.max(0, happiness[i] - reduce);

            if (sum == 0) break;

            ans += sum;

            k--;
            reduce++;
        }

        return ans;
    }
}