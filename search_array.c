#include<stdio.h>
#include<time.h>

int linearSearch(int arr[], int element, int size){
    printf("Linear Search\n");
    for(int i=0;i<size;i++){
        if(arr[i]==element){
            return i;
            break;
        }
    }
    return -1;
}

int binarySearch(int arr[],int element, int size){
    printf("Binary Search\n");
    int low,mid,high;
    low=0;
    high = size-1;

    while(low<=high){
        mid = (low+high)/2;
        if(arr[mid]==element)
            return mid;
        if(arr[mid]<element){
            low = mid+1;
        }
        else{
            high = mid-1;
        }
    }
    return -1;

}

int main(){
    int arr[] = {1,3,5,56,64,73,123,225,444,525};
    int size = sizeof(arr)/sizeof(int);
    int element = 73;
    double time_spent = 0.0;
    clock_t begin = clock();
    //int searchIndex = linearSearch(arr, element, size);
    int searchIndex = binarySearch(arr, element, size);
    clock_t end = clock();
    time_spent += (double)(end - begin) / CLOCKS_PER_SEC;  //finding diff and then dividing by CLOCK_PER_SEC to get diff in seconds
    
    printf("Element %d was found in index %d \n", element, searchIndex);
    printf("Time spent  : %f",time_spent);

}