package com.atuldwivedi.oap.paypal;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Atul Dwivedi
 * <p>
 * @see <a href="https://leetcode.com/discuss/interview-question/1194238/paypal-max-tasks-that-can-be-completed-in-given-budget">Solution</a>
 */
public class PerformMaxTasksWithinGivenBudget {

    static int solve(int n, int t, int[][] task) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        Arrays.sort(task, Comparator.comparingInt(o -> o[0]));
        int pQueueSum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int totalTime = t;
            int distance = 2 * task[i][0];
            int remainingTime = totalTime - distance;
            int currEffort = task[i][1];
            if (remainingTime < 0) {
                break;
            }
            while (pQueueSum > remainingTime) {
                pQueueSum -= pQueue.poll();
            }
            if (pQueue.isEmpty() && remainingTime > currEffort) {
                pQueue.add(currEffort);
                pQueueSum += currEffort;
            } else if (pQueueSum + currEffort <= remainingTime) {
                pQueue.add(currEffort);
                pQueueSum += currEffort;
            } else {
                Integer currMax = pQueue.peek();
                if (currMax != null && currMax > currEffort) {
                    pQueue.poll();
                    pQueue.add(currEffort);
                    pQueueSum = pQueueSum - currMax + currEffort;
                }
            }
            max = Math.max(max, pQueue.size());
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] tasks = {{2, 8}, {4, 5}, {5, 1}};
        int maxTasks = solve(3, 16, tasks);
        System.out.println(maxTasks);
    }
}
