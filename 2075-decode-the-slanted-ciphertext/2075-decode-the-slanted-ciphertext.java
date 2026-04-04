class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        return mySol2_simplified(encodedText, rows);
    }

    public String mySol2_simplified(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;

        StringBuilder ans = new StringBuilder();

        for (int startCol = 0; startCol < cols; startCol++) {
            int j = startCol;

            for (int i = 0; i < rows && j < cols; i++) {
                int index = i * cols + j++;

                ans.append(encodedText.charAt(index));
            }
        }

        return ans.toString().replaceAll("\\s*$", "");
    }

    public String mySol2(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;

        // char[][] mat = new char[rows][cols];

        // for (int i = 0; i < n; i++) {
        //     int r = i / cols;
        //     int c = i % cols;
        //     mat[r][c] = encodedText.charAt(i);
        // }

        // for (char[] r : mat) {
        //     System.out.println(Arrays.toString(r));
        // }

        // System.out.println("n:%d, rows:%d, cols:%d".formatted(n, rows, cols));

        StringBuilder ans = new StringBuilder();

        for (int startCol = 0; startCol < cols; startCol++) {
            int j = startCol;
            int delta = cols - j - 1;
            int maxRow = Math.min(delta, rows - 1);
            int maxCol = Math.min(j + delta, j + rows - 1);
            int index = maxRow * cols + maxCol;

            // System.out.println("j:%d, delta:%d, maxRow:%d, maxCol:%d, index:%d".formatted(j, delta, maxRow, maxCol, index));

            // if (encodedText.charAt(index) == ' ') return ans.toString();

            for (int i = 0; i < rows && j < cols; i++) {
                index = i * cols + j++;

                // System.out.println("i:%d, j:%d, index:%d, c:%c".formatted(i, j, index, encodedText.charAt(index)));
                // System.out.println("i:%d, j:%d, index:%d".formatted(i, j, index));

                ans.append(encodedText.charAt(index));
            }
        }

        return ans.toString().replaceAll("\\s*$", "");
    }

    public String mySol_fail(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;

        StringBuilder[] arr = new StringBuilder[cols];

        for (int i = 0; i < arr.length; i++) arr[i] = new StringBuilder();

        int index = 0;

        for (char c : encodedText.toCharArray()) {
            if (c != ' ') {
                arr[index++].append(c);
            } else {
                index = 0;
            }
        }

        StringBuilder ans = new StringBuilder();

        for (StringBuilder sb : arr) {
            ans.append(sb);
        }

        return ans.toString();
    }
}