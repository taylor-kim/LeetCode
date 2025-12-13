class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        return mySol2(code, businessLine, isActive);
    }

    public List<String> mySol2(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;

        Map<String, Integer> allowedBusiness = Map.of(
            "electronics", 0, 
            "grocery", 1, 
            "pharmacy", 2,
            "restaurant", 3
        );

        List<Integer> list = IntStream.range(0, n)
            .filter(i -> isActive[i])
            .filter(i -> allowedBusiness.containsKey(businessLine[i]))
            .filter(i -> isAllowed(code[i]))
            .boxed()
            .toList();

        list = new ArrayList(list);

        Collections.sort(list, (a, b) -> {
            int businessA = allowedBusiness.get(businessLine[a]);
            int businessB = allowedBusiness.get(businessLine[b]);

            if (businessA != businessB) return businessA - businessB;

            return code[a].compareTo(code[b]);
        });

        return list.stream()
                .map(i -> code[i])
                .toList();
    }

    public List<String> mySol_wtf_stream(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;

        Map<String, Integer> allowedBusiness = Map.of(
            "electronics", 0, 
            "grocery", 1, 
            "pharmacy", 2,
            "restaurant", 3
        );

        return IntStream.range(0, n)
            .filter(i -> isActive[i])
            .filter(i -> allowedBusiness.containsKey(businessLine[i]))
            .filter(i -> isAllowed(code[i]))
            .mapToObj(i -> i)
            // .sorted(
            //     Comparator.comparingInt(i -> allowedBusiness.get(businessLine[i]))
            //         .thenComparing(i -> code[i.intValue()])
            // )
            .map(i -> code[i])
            .toList();
    }

    private boolean isAllowed(String s) {
        return !s.isEmpty() && s.replaceAll("[a-zA-Z0-9_]", "").length() == 0;
    }
}