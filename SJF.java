import java.util.*;

public class SJF {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            int n, i;
            float avgWaitingTime = 0, avgTurnaroundTime = 0;
            System.out.print("Enter the number of processes: ");
            n = input.nextInt();

            int[] burstTime = new int[n];
            int[] waitingTime = new int[n];
            int[] turnaroundTime = new int[n];
            int[] arrivalTime = new int[n];
            int[] processOrder = new int[n];
            boolean[] completed = new boolean[n];

            System.out.println("Enter arrival time and burst time for each process:");
            for (i = 0; i < n; i++) {
                System.out.print("Process " + (i+1) + " Arrival Time: ");
                arrivalTime[i] = input.nextInt();
                System.out.print("Process " + (i+1) + " Burst Time: ");
                burstTime[i] = input.nextInt();
            }

            // Initialize process order as -1
            Arrays.fill(processOrder, -1);

            int currentTime = 0, remaining = n;

            while (remaining > 0) {
                int shortestJobIndex = -1, shortestJobTime = Integer.MAX_VALUE;

                for (i = 0; i < n; i++) {
                    if (!completed[i] && arrivalTime[i] <= currentTime && burstTime[i] < shortestJobTime) {
                        shortestJobIndex = i;
                        shortestJobTime = burstTime[i];
                    }
                }

                if (shortestJobIndex == -1) {
                    currentTime++;
                } else {
                    processOrder[n-remaining] = shortestJobIndex+1;
                    waitingTime[shortestJobIndex] = currentTime - arrivalTime[shortestJobIndex];
                    turnaroundTime[shortestJobIndex] = waitingTime[shortestJobIndex] + burstTime[shortestJobIndex];
                    avgWaitingTime += waitingTime[shortestJobIndex];
                    avgTurnaroundTime += turnaroundTime[shortestJobIndex];
                    completed[shortestJobIndex] = true;
                    remaining--;
                    currentTime += burstTime[shortestJobIndex];
                }
            }

            System.out.println("\nProcess Order: " + Arrays.toString(processOrder));
            System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");

            for (i = 0; i < n; i++) {
                System.out.println((i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
            }

            avgWaitingTime /= n;
            avgTurnaroundTime /= n;

            System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
            System.out.println("Average Turnaround Time: " + avgTurnaroundTime);

            input.close();
        }
    }
}