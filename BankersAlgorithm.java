public class BankersAlgorithm {

    private int numberOfProcesses;
    private int numberOfResources;
    private int[] available;
    private int[][] max;
    private int[][] allocation;
    private int[][] need;

    public BankersAlgorithm(int numberOfProcesses, int numberOfResources, int[] available, int[][] max, int[][] allocation) {
        this.numberOfProcesses = numberOfProcesses;
        this.numberOfResources = numberOfResources;
        this.available = available;
        this.max = max;
        this.allocation = allocation;
        this.need = new int[numberOfProcesses][numberOfResources];
        calculateNeed();
    }

    private void calculateNeed() {
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    private boolean isSafe() {
        boolean[] finish = new boolean[numberOfProcesses];
        int[] work = available.clone();

        while (true) {
            boolean found = false;

            for (int i = 0; i < numberOfProcesses; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < numberOfResources; j++) {
                        if (need[i][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == numberOfResources) {
                        for (j = 0; j < numberOfResources; j++) {
                            work[j] += allocation[i][j];
                        }
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                for (int i = 0; i < numberOfProcesses; i++) {
                    if (!finish[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    public boolean requestResources(int processNumber, int[] request) {
        for (int i = 0; i < numberOfResources; i++) {
            if (request[i] > need[processNumber][i]) {
                System.out.println("Process has exceeded its maximum claim.");
                return false;
            }
            if (request[i] > available[i]) {
                System.out.println("Resources are not available.");
                return false;
            }
        }

        for (int i = 0; i < numberOfResources; i++) {
            available[i] -= request[i];
            allocation[processNumber][i] += request[i];
            need[processNumber][i] -= request[i];
        }

        if (isSafe()) {
            return true;
        } else {
            for (int i = 0; i < numberOfResources; i++) {
                available[i] += request[i];
                allocation[processNumber][i] -= request[i];
                need[processNumber][i] += request[i];
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int numberOfProcesses = 5;
        int numberOfResources = 3;

        int[] available = {3, 3, 2};

        int[][] max = {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 2},
            {4, 3, 3}
        };

        int[][] allocation = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}
        };

        BankersAlgorithm ba = new BankersAlgorithm(numberOfProcesses, numberOfResources, available, max, allocation);

        int[] request1 = {1, 0, 2};
        System.out.println("Requesting resources for P1: " + ba.requestResources(1, request1));

        int[] request2 = {3, 3, 0};
        System.out.println("Requesting resources for P4: " + ba.requestResources(4, request2));
    }
}
