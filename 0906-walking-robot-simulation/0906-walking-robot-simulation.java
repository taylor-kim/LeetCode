class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        return try_20260406(commands, obstacles);
    }

    public int try_20260406(int[] commands, int[][] obstacles) {
        int ans = 0;

        Map<Integer, TreeSet<Integer>> obsH = new HashMap();
        Map<Integer, TreeSet<Integer>> obsV = new HashMap();

        for (int[] obs : obstacles) {
            obsV.computeIfAbsent(obs[0], k -> new TreeSet<>()).add(obs[1]);
            obsH.computeIfAbsent(obs[1], k -> new TreeSet<>()).add(obs[0]);
        }

        Robot robot = new Robot();
        TreeSet<Integer> emptySet = new TreeSet();

        for (int command : commands) {
            if (command == -2) {
                robot.turnLeft();
            } else if (command == -1) {
                robot.turnRight();
            } else {
                if (robot.current == Robot.Dir.NORTH) {
                    Integer nextObs = obsV.getOrDefault(robot.x, emptySet).higher(robot.y);
                    nextObs = nextObs != null ? nextObs : Integer.MAX_VALUE;

                    robot.y = Math.min(robot.y + command, nextObs - 1);
                } else if (robot.current == Robot.Dir.SOUTH) {
                    Integer nextObs = obsV.getOrDefault(robot.x, emptySet).lower(robot.y);
                    nextObs = nextObs != null ? nextObs : Integer.MIN_VALUE;

                    robot.y = Math.max(robot.y - command, nextObs + 1);
                } else if (robot.current == Robot.Dir.EAST) {
                    Integer nextObs = obsH.getOrDefault(robot.y, emptySet).higher(robot.x);
                    nextObs = nextObs != null ? nextObs : Integer.MAX_VALUE;

                    robot.x = Math.min(robot.x + command, nextObs - 1);
                } else {
                    Integer nextObs = obsH.getOrDefault(robot.y, emptySet).lower(robot.x);
                    nextObs = nextObs != null ? nextObs : Integer.MIN_VALUE;

                    robot.x = Math.max(robot.x - command, nextObs + 1);
                }
            }

            ans = Math.max(ans, robot.calc());
        }

        return ans;
    }

    private class Robot {
        private int x = 0;
        private int y = 0;

        private Dir current = Dir.NORTH;

        private enum Dir {
            NORTH,
            SOUTH,
            WEST,
            EAST,
        }

        private Map<Dir, Dir> toLeft = Map.of(
            Dir.NORTH, Dir.WEST, Dir.WEST, Dir.SOUTH, Dir.SOUTH, Dir.EAST, Dir.EAST, Dir.NORTH
        );

        private Map<Dir, Dir> toRight = Map.of(
            Dir.NORTH, Dir.EAST, Dir.EAST, Dir.SOUTH, Dir.SOUTH, Dir.WEST, Dir.WEST, Dir.NORTH
        );

        private void turnLeft() {
            this.current = toLeft.get(this.current);
        }

        private void turnRight() {
            this.current = toRight.get(this.current);
        }

        private int calc() {
            return x * x + y * y;
        }
    }
}