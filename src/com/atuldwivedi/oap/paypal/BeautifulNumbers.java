package com.atuldwivedi.oap.paypal;

import java.util.HashMap;

/**
 * @author Atul Dwivedi
 * @see <a href="https://leetcode.com/discuss/interview-question/1194238/paypal-max-tasks-that-can-be-completed-in-given-budget">Reference</a>
 */
public class BeautifulNumbers {

    static long solve(int l, int r) {
        long sum = 0;
        HashMap<Integer, Boolean> hm = new HashMap<>();

        for (int i = l; i <= r; i++) {
            if (hm.containsKey(i) && hm.get(i)) {
                sum += i;
            } else if (!hm.containsKey(i) && isBeautiful(i, hm)) {
                sum += i;
            }
        }

        return sum;
    }

    public static Boolean isBeautiful(int i, HashMap<Integer, Boolean> hm) {
        int cur = i;

        if (i == 0) {
            return false;
        }

        if (hm.containsKey(i)) {
            return hm.get(i);
        }

        hm.put(i, false);
        int temp = 0;

        while (i > 0) {
            int rem = i % 10;
            temp += rem * rem;
            i /= 10;
        }

        if (temp == 1) {
            hm.put(cur, true);
            return true;
        }

        Boolean flag = isBeautiful(temp, hm);
        hm.put(cur, flag);

        return flag;
    }
}
