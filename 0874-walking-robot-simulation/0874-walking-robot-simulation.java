class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        return mySol_bruteForce_after_sol(commands, obstacles);
    }

    int[][] dirs = {
        {0,1}, {1,0}, {0,-1}, {-1,0}
    };

    public int mySol_bruteForce_after_sol(int[] commands, int[][] obstacles) {
        Map<Integer, List<Integer>> xs = new HashMap();
        Map<Integer, List<Integer>> ys = new HashMap();

        Arrays.sort(obstacles, (a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < obstacles.length; i++) {
            int[] obs = obstacles[i];
            xs.computeIfAbsent(obs[1], k -> new ArrayList()).add(obs[0]);
        }

        Arrays.sort(obstacles, (a, b) -> {
            return a[1] - b[1];
        });

        for (int i = 0; i < obstacles.length; i++) {
            int[] obs = obstacles[i];
            ys.computeIfAbsent(obs[0], k -> new ArrayList()).add(obs[1]);
        }

        int ans = 0;
        int[] pos = {0, 0};
        int dir = 0;

        for (int c : commands) {
            if (c == -2) {
                dir = (dir + 3) % 4;
                continue;
            } else if (c == -1) {
                dir = (dir + 1) % 4;
                continue;
            } else {
                for (int step = 0; step < c; step++) {
                    int nextX = pos[0] + dirs[dir][0];
                    int nextY = pos[1] + dirs[dir][1];

                    Map<Integer, List<Integer>> map = dir % 2 == 0 ? ys : xs;

                    int key = dir % 2 == 0 ? nextX : nextY;
                    int target = dir % 2 == 0 ? nextY : nextX;

                    if (!map.containsKey(key) || Collections.binarySearch(map.get(key), target) < 0) {
                        pos[0] = nextX;
                        pos[1] = nextY;
                    } else {
                        break;
                    }
                }

                ans = Math.max(ans, pos[0] * pos[0] + pos[1] * pos[1]);
            }
        }

        return ans;
    }

    public int mySol_bruteForce_fail(int[] commands, int[][] obstacles) {
        Map<Integer, List<Integer>> xs = new HashMap();
        Map<Integer, List<Integer>> ys = new HashMap();

        Arrays.sort(obstacles, (a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < obstacles.length; i++) {
            int[] obs = obstacles[i];
            xs.computeIfAbsent(obs[1], k -> new ArrayList()).add(obs[0]);
        }

        Arrays.sort(obstacles, (a, b) -> {
            return a[1] - b[1];
        });

        for (int i = 0; i < obstacles.length; i++) {
            int[] obs = obstacles[i];
            ys.computeIfAbsent(obs[0], k -> new ArrayList()).add(obs[1]);
        }

        return mySol_bruteForce(0, 0, 0, 0, commands, xs, ys);
    }

    public int mySol_bruteForce(int index, int x, int y, int dir, int[] commands
                , Map<Integer, List<Integer>> xs, Map<Integer, List<Integer>> ys) {
        // if (index >= commands.length) return x * x + y * y;
        if (index >= commands.length) return 0;

        int c = commands[index];

        int ans = 0;

        if (c == -2) {
            dir = (dir + 3) % 4;
            
            return mySol_bruteForce(index + 1, x, y, dir, commands, xs, ys);
        } else if (c == -1) {
            dir = (dir + 1) % 4;

            return mySol_bruteForce(index + 1, x, y, dir, commands, xs, ys);
        } else {
            if (dir % 2 == 0) {
                //y
                int delta = c;
                int sign = dir == 0 ? 1 : -1;
                int until = y + delta * sign;

                if (!ys.containsKey(x)) {
                    int nextY = until;
                    return Math.max(x * x + nextY * nextY, mySol_bruteForce(index + 1, x, nextY, dir, commands, xs, ys));
                }

                // System.out.println(String.format("y:%d, until:%d, sign:%d", y, until, sign));

                List<Integer> obs = ys.getOrDefault(x, new ArrayList());

                for (int nextY = y + sign; nextY != until; nextY += sign) {
                    int found = Collections.binarySearch(obs, nextY);

                    // System.out.println(String.format("target y:%d, found:%d", nextY, found));

                    if (found >= 0) {
                        return Math.max(ans, mySol_bruteForce(index + 1, x, nextY - sign, dir, commands, xs, ys));
                    }

                    ans = Math.max(ans, x * x + nextY * nextY);
                }
            } else {
                //x
                int delta = c;
                int sign = dir == 1 ? 1 : -1;
                int until = x + delta * sign;

                if (!xs.containsKey(y)) {
                    int nextX = until;
                    return Math.max(nextX * nextX + y * y, mySol_bruteForce(index + 1, nextX, y, dir, commands, xs, ys));
                }

                // System.out.println(String.format("x:%d, until:%d, sign:%d", x, until, sign));

                List<Integer> obs = xs.getOrDefault(y, new ArrayList());

                for (int nextX = x + sign; nextX != until; nextX += sign) {
                    int found = Collections.binarySearch(obs, nextX);

                    // System.out.println(String.format("found x : %d", found));

                    if (found >= 0) {
                        return Math.max(ans, mySol_bruteForce(index + 1, nextX - sign, y, dir, commands, xs, ys));
                    }

                    ans = Math.max(ans, nextX * nextX + y * y);
                }
            }
        }

        return ans;
    }

    public int mySol_fail(int[] commands, int[][] obstacles) {
        int[] moves = new int[4];
        int dir = 0;
        int[] location = {0, 0};
        int ans = 0;

        Map<Integer, List<Integer>> xs = new HashMap();
        Map<Integer, List<Integer>> xsr = new HashMap();
        Map<Integer, List<Integer>> ys = new HashMap();
        Map<Integer, List<Integer>> ysr = new HashMap();

        Arrays.sort(obstacles, (a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < obstacles.length; i++) {
            int[] obs = obstacles[i];
            xs.computeIfAbsent(obs[1], k -> new ArrayList()).add(obs[0]);

            int[] obsr = obstacles[obstacles.length - i - 1];
            xsr.computeIfAbsent(obsr[1], k -> new ArrayList()).add(obsr[0]);
        }

        Arrays.sort(obstacles, (a, b) -> {
            return a[1] - b[1];
        });

        for (int i = 0; i < obstacles.length; i++) {
            int[] obs = obstacles[i];
            ys.computeIfAbsent(obs[0], k -> new ArrayList()).add(obs[1]);

            int[] obsr = obstacles[obstacles.length - i - 1];
            ysr.computeIfAbsent(obsr[0], k -> new ArrayList()).add(obsr[1]);
        }

        for (int command : commands) {
            if (command == -2) {
                dir = (dir + 3) % 4;
            } else if (command == -1) {
                dir = (dir + 1) % 4;
            } else {
                if (dir % 2 == 0) {
                    // y move
                    int nextX = location[0];
                    int nextY = location[1] + command * (dir == 0 ? 1 : -1);

                    Map<Integer, List<Integer>> map = dir == 0 ? ys : ysr;

                    if (map.containsKey(nextX)) {
                        List<Integer> obs = map.get(nextX);

                        int fromIndex = Collections.binarySearch(obs, location[1]);
                        int toIndex = Collections.binarySearch(obs, nextY);

                        if (fromIndex != toIndex) {
                            nextY = obs.get(fromIndex + 1) + (dir == 0 ? -1 : 1);
                        }
                    }

                    location[1] = nextY;
                } else {
                    // x move
                    int nextX = location[0] + command * (dir == 1 ? 1 : -1);;
                    int nextY = location[1];

                    Map<Integer, List<Integer>> map = dir == 1 ? xs : xsr;

                    if (map.containsKey(nextY)) {
                        List<Integer> obs = map.get(nextY);

                        int fromIndex = Collections.binarySearch(obs, location[0]);
                        int toIndex = Collections.binarySearch(obs, nextX);

                        if (fromIndex != toIndex) {
                            nextX = obs.get(fromIndex + 1) + (dir == 1 ? -1 : 1);
                        }
                    }

                    location[0] = nextX;
                }

                ans = Math.max(ans, location[0] * location[0] + location[1] * location[1]);
            }
        }

        return ans;
    }
}