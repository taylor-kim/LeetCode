class StockPrice {
    private TreeMap<Integer, Integer> priceByTime = new TreeMap<>();
    private TreeMap<Integer, Set<Integer>> timesByPrice = new TreeMap<>();

    public void update(int timestamp, int price) {
        if (priceByTime.containsKey(timestamp)) {
            removeOldPrice(timestamp);
        }

        addNewPrice(timestamp, price);
    }

    public int current() {
        return priceByTime.lastEntry().getValue();
    }

    public int maximum() {
        return timesByPrice.lastKey();
    }

    public int minimum() {
        return timesByPrice.firstKey();
    }

    private void removeOldPrice(int timestamp) {
        int prevPrice = priceByTime.get(timestamp);
        Set<Integer> times = timesByPrice.get(prevPrice);
        times.remove(timestamp);

        if (times.isEmpty()) {
            timesByPrice.remove(prevPrice);
        }
    }

    private void addNewPrice(int timestamp, int price) {
        timesByPrice.computeIfAbsent(price, k -> new HashSet<>()).add(timestamp);
        priceByTime.put(timestamp, price);
    }
}
