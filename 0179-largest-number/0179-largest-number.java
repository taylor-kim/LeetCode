class Solution {
    public String largestNumber(int[] nums) {
        return moreThanOfficial(nums);
    }

    public String moreThanOfficial(int[] nums) {
        Integer[] arr = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                int paddingFor2 = 10;
                int paddingFor1 = 10;

                while (paddingFor1 <= i2) {
                    paddingFor1 *= 10;
                }

                while (paddingFor2 <= i1) {
                    paddingFor2 *= 10;
                }

                return ((i2 * paddingFor2) + i1) - ((i1 * paddingFor1) + i2);
            }
        });

        if (arr[0] == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        for (Integer num : arr) {
            sb.append(num);
        }

        return sb.toString();
    }

    public String official_fucking_simple(int[] nums) {
        String[] arrString = Arrays.stream(nums)
        .boxed()
        .map(String::valueOf)
        .toArray(String[]::new);

        Arrays.sort(arrString, (a, b) -> {
            return (b + a).compareTo(a + b);
        });

        StringBuilder ans = new StringBuilder();

        for (String s : arrString) ans.append(s);

        return ans.charAt(0) == '0' ? "0" : ans.toString();
    }

    public String mySol3(int[] nums) {
        Integer[] arrInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(arrInteger, (a, b) -> {
            String s1 = String.valueOf(a);
            String s2 = String.valueOf(b);

            int n = Math.min(s1.length(), s2.length());

            int index = 0;

            while (index < n) {
                char c1 = s1.charAt(index);
                char c2 = s2.charAt(index);

                if (c1 != c2) {
                    return c2 - c1;
                }

                index++;
            }

            int compare = s1.length() > s2.length() ? -1 : 1;

            String s = compare == -1 ? s1 : s2;
            String partA = s.substring(0, index);
            String partB = s.substring(index);

            int a1 = Integer.parseInt(partB + partA);
            int a2 = Integer.parseInt(partA + partB);

            // System.out.println(String.format("a1:%d, a2:%d, compare:%d", a1, a2, compare));

            return a1 > a2 ? compare : -compare;

            // if (s1.length() > s2.length()) {
            //     String remain = s1.substring(index);

            //     int a1 = Integer.parseInt(remain + s2);
            //     int a2 = Integer.parseInt(s2 + remain);

            //     return a1 > a2 ? -1 : 1;
            // } else {
            //     String remain = s2.substring(index);

            //     int a1 = Integer.parseInt(s1 + remain);
            //     int a2 = Integer.parseInt(remain + s1);

            //     return a1 > a2 ? -1 : 1;
            // }
        });

        StringBuilder ans = new StringBuilder();

        for (int num : arrInteger) {
            ans.append(num);
        }

        while (ans.length() > 1 && ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }

        return ans.toString();
    }

    public String mySol2(int[] nums) {
        Integer[] arrInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(arrInteger, (a, b) -> {
            // long aFirst = Long.parseLong(String.valueOf(a) + String.valueOf(b));
            // long bFirst = Long.parseLong(String.valueOf(b) + String.valueOf(a));

            // return aFirst >= bFirst ? -1 : 1;

            Long aFirst = Long.parseLong(String.valueOf(a) + String.valueOf(b));
            Long bFirst = Long.parseLong(String.valueOf(b) + String.valueOf(a));

            return bFirst.compareTo(aFirst);
        });

        StringBuilder ans = new StringBuilder();

        for (int num : arrInteger) {
            ans.append(num);
        }

        while (ans.length() > 1 && ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }

        return ans.toString();
    }

    public String mySol_fail(int[] nums) {
        Integer[] arrInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(arrInteger, (a, b) -> {
            String s1 = String.valueOf(a);
            String s2 = String.valueOf(b);

            int n = Math.min(s1.length(), s2.length());

            int index = 0;

            while (index < n) {
                char c1 = s1.charAt(index);
                char c2 = s2.charAt(index);

                if (c1 != c2) {
                    return c2 - c1;
                }

                index++;
            }

            // 8308,830 8308830
            // 830,8308 8308308

            // 8908,890 8908890
            // 890,8908 8908908

            // 34, 3435 343435
            // 3435, 34 343534

            if (index < s1.length()) {
                int index2 = 0;

                while (index < s1.length()) {
                    char c1 = s1.charAt(index++);
                    char c2 = s1.charAt(index2++);

                    if (c2 == c1) continue;

                    return c1 > c2 ? -1 : 1;
                }

                char c1 = s1.charAt(index - 1);
                char c2 = s1.charAt(index2);

                return c1 > c2 ? -1 : 1;
            } else if (index < s2.length()) {
                int index2 = 0;

                while (index < s2.length()) {
                    char c1 = s2.charAt(index2++);
                    char c2 = s2.charAt(index++);

                    if (c2 == c1) continue;

                    return c1 > c2 ? -1 : 1;
                }

                char c1 = s2.charAt(index2);
                char c2 = s2.charAt(index - 1);

                return c1 > c2 ? -1 : 1;
            } else {
                return 0;
            }
        });

        StringBuilder ans = new StringBuilder();

        for (int num : arrInteger) {
            ans.append(num);
        }

        while (ans.length() > 1 && ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }

        return ans.toString();
    }
}