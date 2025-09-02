class Solution {
    public int numberOfPairs(int[][] points) {
        return official_bf(points);
    }

    public int official_bf(int[][] points) {
        int n = points.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int[] a = points[i];
            for (int j = 0; j < n; j++) {
                int[] b = points[j];
                if (i == j || !(a[0] <= b[0] && a[1] >= b[1])) {
                    continue;
                }

                boolean ok = true;

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;

                    int[] c = points[k];

                    if (a[0] <= c[0] && c[0] <= b[0]
                        && a[1] >= c[1] && c[1] >= b[1]) {
                        ok = false;
                        break;
                    }
                }

                if (ok) ans++;
            }
        }

        return ans;
    }

    public int mySol_bf_fail(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : b[1] - a[1];
        });

        int ans = 0;

        for (int i = 0; i < points.length; i++) {
            Stack<Integer> stack = new Stack();

            for (int j = 0; j < i; j++) {
                // while (!stack.isEmpty() && points[stack.peek()][1] ? points[j][1]) 
                stack.push(j);
            }
        }

        return ans;
    }

    public int mySol_fail(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : b[1] - a[1];
        });

        int ans = 0;

        Stack<Integer> stack = new Stack();

        for (int i = points.length - 1; i >= 0; i--) {

            while (!stack.isEmpty() && points[stack.peek()][1] <= points[i][1]) {
                stack.pop();
                ans++;
            }
            
            stack.push(i);
        }

        return ans;
    }
}