#include<stdio.h>
#include<stdlib.h>  //for malloc

struct Node{
    int data;
    struct Node *next;
};

void LinkedListTravel(struct Node *ptr){
    while(ptr!=NULL){
        printf("Element %d\n", ptr->data);
        ptr  = ptr->next;
    }
}
int main(){
    struct Node *head;
    struct Node *second;
    struct Node *third;

    //Allocate memory for nodes in the Linked Lists in Heap
    head = (struct Node*)malloc(sizeof(struct Node));
    second = (struct Node*)malloc(sizeof(struct Node));
    third = (struct Node*)malloc(sizeof(struct Node));
    
    //Linking first and second nodes
    head->data =7;
    head->next = second;

    //Linking second and third nodes
    second->data =11;
    second->next = third;

    //Terminate List at third node
    third->data =66;
    third->next = NULL;

    LinkedListTravel(head);
    return 0;
}

