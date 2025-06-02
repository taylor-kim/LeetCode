class Solution {
    public int candy(int[] ratings) {
        return try_linear2(ratings);
    }

    public int try_linear3(int[] ratings) {
        int n = ratings.length;

        int[] leftToRight = new int[n];
        int[] rightToLeft = new int[n];

        leftToRight[0] = 1;
        rightToLeft[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            int j = n - i - 1;

            if (ratings[i - 1] < ratings[i]) {
                leftToRight[i] = leftToRight[i - 1] + 1;
            } else {
                leftToRight[i] = 1;
            }

            if (ratings[j] > ratings[j + 1]) {
                rightToLeft[j] = rightToLeft[j] + 1;
            } else {
                rightToLeft[j] = 1;
            }
        }

        int ans = 0;

        System.out.println(Arrays.toString(leftToRight));
        System.out.println(Arrays.toString(rightToLeft));

        for (int i = 0; i < n; i++) {
            ans += Math.max(leftToRight[i], rightToLeft[i]);
        }

        return ans;
    }

    public int try_linear2(int[] ratings) {
        int n = ratings.length;
        int[] given = new int[n];

        int ans = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && ratings[stack.peek()] <= ratings[i]) {
                int count = 1;

                int index = stack.pop();

                if (index - 1 >= 0 && ratings[index - 1] < ratings[index]) {
                    count = given[index - 1] + 1;
                }

                if (index + 1 < n && ratings[index] > ratings[index + 1]) {
                    count = Math.max(count, given[index + 1] + 1);
                }

                ans += given[index] = count;
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            int count = 1;

            if (index - 1 >= 0 && ratings[index - 1] < ratings[index]) {
                count = given[index - 1] + 1;
            }

            if (index + 1 < n && ratings[index] > ratings[index + 1]) {
                count = Math.max(count, given[index + 1] + 1);
            }

            ans += given[index] = count;
        }

        return ans;
    }

    public int try_linear(int[] ratings) {
        int n = ratings.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int r : ratings) {
            min = Math.min(min, r);
            max = Math.max(max, r);
        }

        List<Integer>[] data = new List[max - min + 1];

        for (int i = 0; i < n; i++) {
            if (data[ratings[i] - min] == null) {
                data[ratings[i] - min] = new ArrayList();
            }

            data[ratings[i] - min].add(i);
        }

        int r = 0;

        int[] given = new int[n];

        int ans = 0;

        while (r <= max - min) {
            if (data[r] == null) {
                r++;
                continue;
            }

            for (int index : data[r]) {
                int count = 1;

                if (index - 1 >= 0 && ratings[index - 1] < ratings[index]) {
                    count = given[index - 1] + 1;
                }

                if (index + 1 < n && ratings[index] > ratings[index + 1]) {
                    count = Math.max(count, given[index + 1] + 1);
                }

                ans += given[index] = count;
            }

            r++;
        }

        return ans;
    }

    public int mySol(int[] ratings) {
        int n = ratings.length;

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });

        for (int i = 0; i < n; i++) {
            pq.add(new int[] {ratings[i], i});
        }

        int[] given = new int[n];

        int ans = 0;

        while (!pq.isEmpty()) {
            int[] data = pq.poll();

            int rating = data[0];
            int index = data[1];

            int count = 1;

            if (index - 1 >= 0 && ratings[index - 1] < ratings[index]) {
                count = given[index - 1] + 1;
            }

            if (index + 1 < n && ratings[index] > ratings[index + 1]) {
                count = Math.max(count, given[index + 1] + 1);
            }

            ans += given[index] = count;
        }

        return ans;
    }
}