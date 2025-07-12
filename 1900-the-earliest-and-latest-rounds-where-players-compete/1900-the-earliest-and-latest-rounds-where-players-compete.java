class Solution {
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        return mySol(n, firstPlayer, secondPlayer);
    }

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    public int[] mySol(int n, int f, int s) {
        List<Integer> list = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());

        bf(n, list, f, s, 1, 1);

        return new int[] {min, max};
    }

    public void bf(int originSize, List<Integer> list, int f, int s, int left, int round) {
        if (f + s == originSize + 1) {
            min = Math.min(min, round);
            max = Math.max(max, round);

            return;
        }

        // left + right == n + 1
        int right = originSize + 1 - left;

        if (left >= right) {
            // make a new list and give f and s a new number respectively.
            // and call bf with new originSize
            List<Integer> nextList = new ArrayList();

            // 1,2,f,4,5,s,7,8,9
            int nextF = -1;
            int nextS = -1;

            for (int i = 0; i < list.size(); i++) {
                int p = list.get(i);

                if (p < 0) {
                    if (-p == f || -p == s) {
                        return;
                    }
                } else {
                    nextList.add(nextList.size() + 1);

                    if (nextF < 0 && p == f) {
                        nextF = nextList.size();
                    } else if (nextS < 0 && p == s) {
                        nextS = nextList.size();
                    }
                }
            }

            bf(nextList.size(), nextList, nextF, nextS, 1, round + 1);

            return;
        }

        int leftPlayer = list.get(left - 1);
        int rightPlayer = list.get(right - 1);

        if (leftPlayer == f || leftPlayer == s) {
            list.set(right - 1, -rightPlayer);

            bf(originSize, list, f, s, left + 1, round);

            list.set(right - 1, rightPlayer);
        } else if (rightPlayer == f || rightPlayer == s) {
            list.set(left - 1, -leftPlayer);

            bf(originSize, list, f, s, left + 1, round);

            list.set(left - 1, leftPlayer);
        } else {
            list.set(right - 1, -rightPlayer);

            bf(originSize, list, f, s, left + 1, round);

            list.set(right - 1, rightPlayer);

            list.set(left - 1, -leftPlayer);

            bf(originSize, list, f, s, left + 1, round);

            list.set(left - 1, leftPlayer);
        }
    }

    public void topdown_hold(int n, int f, int s, int round) {
        // if (f == s) return;

        // if (f + s == n + 1) {
        //     min = Math.min(min, round);
        //     max = Math.max(max, round);

        //     return;
        // }

        // // for (int i = 1; i <= n; i++) {
        // //     int j = n - i + 1;
        // // }

        // int mid = n / 2 + (n % 2);

        // if (f <= mid && mid < s) {

        // }

        // if (mid < f) {
        //     // both are on the right side

        //     ........ mid ... f.. s

        // } else if (s < mid) {
        //     // both are on the left side
        //     ... f.. s mid ......
        // } else {
        //     // 1 <= f <= mid && mid <= second <= n

        //     .... f .... mid .... s ...
        // }

        // if (n % 2 == 0) {
        //     // n == 8
        //     int mid = n / 2;

        //     // mid = 4
        //     // 1,2,3,[4],5,6,7,8
        // } else {
        //     // n == 9
        //     int mid = n / 2 + 1;

        //     // mid = 5
        //     // 1,2,3,4,[5],6,7,8,9
        // }

        
    }

    /**
    n => 
    n / 2
    n / 4
    n / 8

    earliest

    case 1: both are on the left side.
        => 5 : 5
        => 1,2,f,s,5,6,7,8,9,10
        => 1,2,f,s,5
        => f,s,5
        => f,s

        => 1,2,f,s,5
        => 1,x,f,s,x
        => 1,f,s
        => f,s

        => 1,2,f,s,5,6,7,8,9,10
        => x,x,f,s,x,6,x,x,9,10

        => f,s,6,9,10
        => f,s,6
        => f,s
    case 2: first is on the left side, second is on the right side.
        => 1,2,f,4,5,6,7,8,9,s,11,12,13
        => x,x,f,x,5,6,7,8,9,s,x,12,13

        => f,7,8,9,s,12,13
        => f,7,x,9,s,x,x

        => f,7,9,s
    case 3: both are on the right side.



    */

    /**
    1, 2, 3, 4, 5, 6
    1, 2, x, 4, x, x

    1, 2, 4

    2, 4

    */

    /**
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
    1, 2, 3, 4, 5, 6
    1, 2, 4
    2, 4

    // f:5, s:10
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
    x, x, x, x, 5, 6, x, 8, 9, 10, 11

    5, 6, 8, 9, 10, 11
    5, x, x, 9, 10, x

    5, 9, 10

    ... 5 ... 10 ....
    3            4

    2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
    x, 3, x, 5, 6, 7, 8, 9, 10, x, 12, x, 14

    2, 4 
    2, 3
    1, 3
    1, 2
    0, 1

    n = 11

    1-11
    2-10
    3-09
    4-08
    5-07
    6-06
     */
}