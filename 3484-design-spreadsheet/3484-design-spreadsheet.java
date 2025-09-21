class Spreadsheet {
    List<Map<Integer, String>> sheet;

    public Spreadsheet(int rows) {
        sheet = new ArrayList(rows);

        for (int i = 0; i <= rows; i++) {
            sheet.add(new HashMap());
        }
    }
    
    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - 'A';
        int row = cell.charAt(1) - '0';

        sheet.get(row).put(col, String.valueOf(value));
    }
    
    public void resetCell(String cell) {
        int col = cell.charAt(0) - 'A';
        int row = cell.charAt(1) - '0';

        sheet.get(row).put(col, "0");
    }
    
    public int getValue(String formula) {
        if (formula.startsWith("=")) {
            String[] refs = devide(formula);

            return getValue(refs[0]) + getValue(refs[1]);
        } else if ('A' <= formula.charAt(0) && formula.charAt(0) <= 'Z') {
            int col = formula.charAt(0) - 'A';
            int row = formula.charAt(1) - '0';

            return Integer.parseInt(sheet.get(row).getOrDefault(col, "0"));
        } else {
            return Integer.parseInt(formula);
        }
    }

    private String[] devide(String formula) {
        String[] ret = {"", ""};

        int i = 1;

        while (formula.charAt(i) != '+') {
            ret[0] = ret[0] + formula.charAt(i++);
        }

        i++;

        while (i < formula.length()) {
            ret[1] = ret[1] + formula.charAt(i++);
        }

        return ret;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */