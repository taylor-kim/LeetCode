class Robot {
    int width;
    int height;
    int circle;
    int x;
    int y;

    int[] dirs = {1, 1, -1, -1};
    String[] dirNames = {"East", "North", "West", "South"};
    int dir = 0;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.circle = width * 2 + height * 2 - 4;
        this.x = 0;
        this.y = 0;
    }
    
    public void step(int num) {
        while (num > 0) {
            if (dir % 2 == 0) { // horizontal move
                int nextX = x + (num * dirs[dir]);

                if (nextX < 0) {
                    num = -nextX;
                    nextX = 0;
                    dir = (dir + 1) % 4;
                } else if (nextX >= width) {
                    num = nextX - width + 1;
                    nextX = width - 1;
                    dir = (dir + 1) % 4;
                } else {
                    num = 0;
                }

                x = nextX;
            } else { // vertical move
                int nextY = y + (num * dirs[dir]);

                if (nextY < 0) {
                    num = -nextY;
                    nextY = 0;
                    dir = (dir + 1) % 4;
                } else if (nextY >= height) {
                    num = nextY - height + 1;
                    nextY = height - 1;
                    dir = (dir + 1) % 4;
                } else {
                    num = 0;
                }

                y = nextY;
            }

            if (num > circle) {
                num %= circle;

                if (num == 0) {
                    dir = (dir + 3) % 4;
                }
            }
            // System.out.println("num:%d, mod:%d, circle:%d".formatted(num, num % circle, circle));
        }
    }
    
    public int[] getPos() {
        return new int[] {x, y};
    }
    
    public String getDir() {
        return dirNames[dir];
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */