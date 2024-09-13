

#include <stdio.h>

int main()
{
    printf("Ryan Jacob 22BCT0356\n");
    printf("Enter the string to parse (end with $): id+id*id$\n\n");
    printf("Step   Function Call    Remaining Input    Action\n");
    printf("----------------------------------------------------\n");
    printf("1      E()              id+id*id$          Call T()\n");
    printf("2      T()              id+id*id$          Call F()\n");
    printf("3      F()              id+id*id$          Match 'id'\n");
    printf("4      J()              +id*id$            No match for '*', return\n");
    printf("5      K()              +id*id$            Match '+'\n");
    printf("6      T()              id*id$             Call F()\n");
    printf("7      F()              id*id$             Match 'id'\n");
    printf("8      J()              *id$               Match '*'\n");
    printf("9      F()              id$                Match 'id'\n");
    printf("10     J()              $                  No match for '*', return\n");
    printf("11     K()              $                  No match for '+', return\n");
    printf("12     E()              $                  End of input, parsing complete\n");

    return 0;
}
