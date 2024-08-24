class Solution {
    public String fractionAddition(String expression) {
        return mySol(expression);
    }

    public String mySol(String expression) {
        int base = 1;

        for (int i = 2; i <= 10; i++) {
            base *= i;
        }

        if (expression.charAt(0) != '-') {
            expression = "+" + expression;
        }

        List<String> fractions = new ArrayList();

        for (String s : expression.split("\\+")) {
            String[] ss = s.split("\\-");
            for (int i = 0; i < ss.length; i++) {
                // s - s
                // i == 0 => s, i > 0 => -s
                // -s -s
                // i == 0 => continue, i > 0 => -s
                if (i == 0 && ss[i].length() > 0) {
                    fractions.add(ss[i]);
                } else if (i > 0) {
                    fractions.add("-" + ss[i]);
                }
            }
        }

        int a = 0;

        for (String fraction : fractions) {
            String[] f = fraction.split("/");

            int numer = Integer.parseInt(f[0]);
            int deno = Integer.parseInt(f[1]);

            a += numer * (base / deno);
        }

        int[] ans = getIrreducible(a, base);

        return ans[0] + "/" + ans[1];
    }

    private int[] getIrreducible(int a, int b) {
        int factor = 2;

        while (factor <= 10) {
            while (a % factor == 0 && b % factor == 0) {
                a /= factor;
                b /= factor;
            }

            factor++;
        }

        return new int[] {a, b};
    }
}