class Process:
    def __init__(self, pid, arrival_time, burst_time, priority=0):
        self.pid = pid
        self.arrival_time = arrival_time
        self.burst_time = burst_time
        self.remaining_time = burst_time
        self.priority = priority
        self.completion_time = 0
        self.turnaround_time = 0
        self.waiting_time = 0

def srtf_scheduling(processes):
    time = 0
    completed_processes = 0
    n = len(processes)
    while completed_processes < n:
        # Find the process with the shortest remaining time at the current time
        current_process = None
        for process in processes:
            if process.arrival_time <= time and process.remaining_time > 0:
                if current_process is None or process.remaining_time < current_process.remaining_time:
                    current_process = process

        if current_process is not None:
            current_process.remaining_time -= 1
            time += 1

            if current_process.remaining_time == 0:
                current_process.completion_time = time
                current_process.turnaround_time = time - current_process.arrival_time
                current_process.waiting_time = current_process.turnaround_time - current_process.burst_time
                completed_processes += 1
        else:
            time += 1

    print("SRTF Scheduling:")
    print_schedule(processes)

def priority_scheduling(processes, preemptive=True):
    time = 0
    completed_processes = 0
    n = len(processes)
    while completed_processes < n:
        # Find the highest priority process at the current time
        current_process = None
        for process in processes:
            if process.arrival_time <= time and process.remaining_time > 0:
                if current_process is None or (process.priority < current_process.priority) or \
                        (preemptive and process.priority == current_process.priority and process.remaining_time < current_process.remaining_time):
                    current_process = process

        if current_process is not None:
            time += 1
            current_process.remaining_time -= 1

            if current_process.remaining_time == 0:
                current_process.completion_time = time
                current_process.turnaround_time = time - current_process.arrival_time
                current_process.waiting_time = current_process.turnaround_time - current_process.burst_time
                completed_processes += 1
        else:
            time += 1

    scheduling_type = "Preemptive" if preemptive else "Non-Preemptive"
    print(f"Priority {scheduling_type} Scheduling:")
    print_schedule(processes)

def print_schedule(processes):
    total_waiting_time = 0
    total_turnaround_time = 0

    print("\nProcessID | ArrivalTime | BurstTime | CompletionTime | TurnAroundTime | WaitingTime")
    for process in processes:
        total_waiting_time += process.waiting_time
        total_turnaround_time += process.turnaround_time
        print(f"{process.pid:>8} | {process.arrival_time:>11} | {process.burst_time:>9} | {process.completion_time:>14} | {process.turnaround_time:>14} | {process.waiting_time:>11}")

    print(f"\nAverage Turnaround Time: {total_turnaround_time / len(processes):.2f}")
    print(f"Average Waiting Time: {total_waiting_time / len(processes):.2f}\n")

def main():
    n = int(input("Enter the number of processes: "))
    processes = []
    for i in range(n):
        pid = input(f"Enter Process ID for Process {i + 1}: ")
        arrival_time = int(input(f"Enter Arrival Time for Process {i + 1}: "))
        burst_time = int(input(f"Enter Burst Time for Process {i + 1}: "))
        priority = int(input(f"Enter Priority for Process {i + 1} (lower number means higher priority, only for Priority Scheduling): "))
        processes.append(Process(pid, arrival_time, burst_time, priority))

    # Copy the list of processes for each algorithm to ensure they start fresh
    processes_for_srtf = [Process(p.pid, p.arrival_time, p.burst_time) for p in processes]
    processes_for_priority_preemptive = [Process(p.pid, p.arrival_time, p.burst_time, p.priority) for p in processes]
    processes_for_priority_non_preemptive = [Process(p.pid, p.arrival_time, p.burst_time, p.priority) for p in processes]

    srtf_scheduling(processes_for_srtf)
    priority_scheduling(processes_for_priority_preemptive, preemptive=True)
    priority_scheduling(processes_for_priority_non_preemptive, preemptive=False)

if __name__ == "__main__":
    main()
