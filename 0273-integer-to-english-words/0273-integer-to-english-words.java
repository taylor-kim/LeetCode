class Solution {
    public String numberToWords(int num) {
        return mySol(num);
    }

    public String mySol(int num) {
        if (num == 0) return "Zero";

        String[] lt10 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] lt20 = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] lt100 = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        StringBuilder ans = new StringBuilder();

        int unit = 0;

        String[] units = {"", "Thousand ", "Million ", "Billion "};

        while (num > 0) {
            StringBuilder sb = new StringBuilder();

            int digit3 = num % 1000;

            if (digit3 >= 100) {
                sb.append(lt10[digit3 / 100]).append(" Hundred").append(" ");
            }

            int digit2 = num % 100;

            if (digit2 < 10) {
                if (digit2 != 0) {
                    sb.append(lt10[digit2]).append(" ");
                }
            } else if (digit2 < 20) {
                sb.append(lt20[digit2 % 10]).append(" ");
            } else {
                sb.append(lt100[digit2 / 10 - 2]).append(" ");

                if (digit2 % 10 != 0) {
                    sb.append(lt10[digit2 % 10]).append(" ");
                }
            }

            if (sb.length() > 0) {
                sb.append(units[unit]);
            }

            unit++;

            num /= 1000;

            ans.insert(0, sb.toString());
        }

        if (ans.charAt(ans.length() - 1) == ' ') {
            ans.setLength(ans.length() - 1);
        }

        return ans.toString();
    }
}