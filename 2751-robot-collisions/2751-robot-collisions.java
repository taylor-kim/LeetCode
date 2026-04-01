class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        return official(positions, healths, directions);
    }

    public List<Integer> official(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) indices[i] = i;

        Arrays.sort(indices, (a, b) -> {
            return positions[a] - positions[b];
        });

        Stack<Integer> stack = new Stack();

        for (int index : indices) {
            char c = directions.charAt(index);

            if (c == 'R') {
                stack.push(index);
            } else {
                while (!stack.isEmpty()) {
                    if (healths[stack.peek()] == healths[index]) {
                        healths[stack.pop()] = 0;
                        healths[index] = 0;
                        break;
                    } else if (healths[stack.peek()] > healths[index]) {
                        healths[stack.peek()]--;
                        healths[index] = 0;
                        break;
                    } else {
                        healths[stack.pop()] = 0;
                        healths[index]--;
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList();

        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                ans.add(healths[i]);
            }
        }

        return ans;
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