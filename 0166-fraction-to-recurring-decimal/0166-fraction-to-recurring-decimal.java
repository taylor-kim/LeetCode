class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        return others(numerator, denominator);
    }

    public String others(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        String sign = ((numerator > 0) ^ (denominator > 0) && numerator != 0) ? "-" : "";
        sb.append(sign);

        long a = Math.abs((long)numerator);
        long b = Math.abs((long)denominator);

        sb.append(a / b);
        a %= b;

        if (a == 0) {
            return sb.toString();
        }

        sb.append(".");

        Map<Long, Integer> map = new HashMap();

        while (a != 0) {
            a *= 10;
            sb.append(a / b);
            a %= b;
            if (map.containsKey(a)) {
                int index = map.get(a);
                sb.insert(index, "(");
                sb.append(")");
                break;
            } else {
                map.put(a, sb.length());
            }
        }

        return sb.toString();
    }

    public String mySol2(int numerator, int denominator) {
        double a = numerator * 1d;
        double b = denominator * 1d;

        int c = (numerator / denominator);
        double ans = a / b;

        return ans == c * 1d ? c + "" : (a / b) + "";
    }

    public String mySol(int numerator, int denominator) {
        StringBuilder num = new StringBuilder();
        StringBuilder fraction = new StringBuilder();

        boolean sign = false;

        int a = Math.abs(numerator);
        int b = Math.abs(denominator);

        while (a > b) {
            int q = a / b;
            a = a % b;

            num.append(q);
        }

        // if (num.length() == 0) num.append(0);

        Set<Double> set = new HashSet();

        double f = a * 1d;

        while (f != 0 && !set.contains(f)) {
            set.add(f);

            double q = f / b;
            fraction.append(q);
            f = f % b;
        }

        if (a == 0) {
            
        }

        return num.toString() + (fraction.length() > 0 ? "." + fraction.toString() : "");
    }
}