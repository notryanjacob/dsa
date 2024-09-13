def calculate_CT_sjf(n, A, B):
    P = list(range(n))
    R = B[:]
    C = [0] * n
    T = [0] * n
    W = [0] * n
    t = 0
    completed = 0
    p_queue = []
    
    while completed < n:
        for i in range(n):
            if A[i] <= t and i not in p_queue and R[i] > 0:
                p_queue.append(i)
        
        if p_queue:
            cp = min(p_queue, key=lambda x: R[x])
            p_queue.remove(cp)
            t += R[cp]
            C[cp] = t
            T[cp] = C[cp] - A[cp]
            W[cp] = T[cp] - B[cp]
            R[cp] = 0
            completed += 1
        else:
            t += 1
    
    return C, T, W

def calculate_averages(W, T):
    avg_W = sum(W) / len(W)
    avg_T = sum(T) / len(T)
    return avg_W, avg_T

def print_results(case_num, algo, ID, A, B, C, T, W, avg_W, avg_T):
    print(f"\nTest Case {case_num} - {algo}")
    print("Process\tAT\tBT\tCT\tTAT\tWT")
    
    for i in range(len(ID)):
        print(f"{ID[i]}\t{A[i]}\t{B[i]}\t{C[i]}\t{T[i]}\t{W[i]}")
    
    print(f"Average WT: {avg_W:.2f}")
    print(f"Average TAT: {avg_T:.2f}")

def main_sjf():
    test_cases = [
        ([0, 2, 4], [3, 5, 2]),
        ([1, 1, 1], [3, 2, 1]),
        ([0, 1, 2], [2, 2, 2]),
        ([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], [10, 8, 6, 4, 2, 10, 8, 6, 4, 2]),
        ([0, 2, 4], [1, 10, 100]),
    ]
    
    for case_num, (A, B) in enumerate(test_cases, 1):
        n = len(A)
        ID = list(range(1, n + 1))
        C, T, W = calculate_CT_sjf(n, A, B)
        avg_W, avg_T = calculate_averages(W, T)
        print_results(case_num, "Non-Preemptive SJF", ID, A, B, C, T, W, avg_W, avg_T)

if __name__ == "__main__":
    main_sjf()
