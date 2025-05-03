class Solution {
    public String pushDominoes(String dominoes) {
        return mySol(dominoes);
    }

    public String mySol(String dominoes) {
        int n = dominoes.length();
        int[] toRight = new int[n];
        int[] toLeft = new int[n];

        toRight[0] = dominoes.charAt(0) == 'R' ? 1 : 0;
        toLeft[n - 1] = dominoes.charAt(n - 1) == 'L' ? 1 : 0;

        for (int i = 0; i < n; i++) {
            int j = n - i - 1;

            char lc = dominoes.charAt(i);
            char rc = dominoes.charAt(j);

            // toRight[i] = lc != 'L' ? toRight[i - 1] + (lc == 'R' ? 1 : 0) : 0;
            // toLeft[j] = rc != 'R' ? toLeft[j + 1] + (rc == 'L' ? 1 : 0) : 0;

            if (lc == 'R') {
                toRight[i] = 1;
            } else if (lc == '.' && i - 1 >= 0 && toRight[i - 1] != 0) {
                toRight[i] = toRight[i - 1] + 1;
            }

            if (rc == 'L') {
                toLeft[j] = 1;
            } else if (rc == '.' && j + 1 < n && toLeft[j + 1] != 0) {
                toLeft[j] = toLeft[j + 1] + 1;
            }
        }

        StringBuilder sb = new StringBuilder();

        // System.out.println(Arrays.toString(toRight));
        // System.out.println(Arrays.toString(toLeft));

        //".L.R...LR..L.."
        //"00012340123000"
        //"21004321032100"
        //""

        for (int i = 0; i < n; i++) {
            if (toRight[i] == toLeft[i]) {
                sb.append(".");
            } else if (toRight[i] == 0) {
                sb.append("L");
            } else if (toLeft[i] == 0) {
                sb.append("R");
            } else {
                sb.append(toRight[i] < toLeft[i] ? "R" : "L");
            }
        }

        return sb.toString();
    }
}