class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        return mySol(positions, healths, directions);
    }

    public List<Integer> mySol(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[][] datas = new Integer[n][4];

        for (int i = 0; i < n; i++) {
            datas[i][0] = i;
            datas[i][1] = positions[i];
            datas[i][2] = healths[i];
            datas[i][3] = directions.charAt(i) == 'L' ? 0 : 1;
        }

        Arrays.sort(datas, (a, b) -> {
            return a[1] - b[1];
        });

        List<Integer> list = new ArrayList();
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < n; i++) {
            boolean isR = datas[i][3] == 1;

            if (isR) {
                stack.push(i);
            } else {
                boolean survived = true;

                while (!stack.isEmpty()) {
                    if ((int)datas[stack.peek()][2] == (int)datas[i][2]) {
                        stack.pop();
                        survived = false;
                        break;
                    } else if (datas[stack.peek()][2] > datas[i][2]) {
                        datas[stack.peek()][2]--;
                        survived = false;
                        break;
                    } else {
                        stack.pop();
                        datas[i][2]--;
                    }
                }

                if (survived) {
                    list.add(i);
                }
            }
        }

        list.addAll(stack);

        Collections.sort(list, (a, b) -> {
            return datas[a][0] - datas[b][0];
        });

        return list.stream().map(i -> datas[i][2]).toList();
    }
}