#include <stdio.h>
#include <string.h>
#include <ctype.h>
char keyword[17][10] = {"#include", "#define", "struct", "int", "float", "char", "string",
                        "main", "return", "if", "else", "else if", "for", "while", "stdio.h", "printf", "scanf"};
char symb[8] = {'(', ')', '{', '}', ';', '<', '>', '-'};
char oper[8] = {'+', '-', '*', '/', '%', '&', '|', '='};
int search_k(char arr[])
{
    for (int p = 0; p < 17; p++)
    {
        if (strcmp(arr, keyword[p]) == 0)
            return 1;
    }
    return 0;
}
int search(char c)
{
    for (int p = 0; p < 8; p++)
    {
        if (c == symb[p])
            return 1;
        else if (c == oper[p])
            return 2;
    }
    return 0;
}
void Syntax_Table()
{
    printf("Enter file name: ");
    FILE *f_ptr;
    char file[100], ch;
    scanf("%s", file);
    f_ptr = fopen(file, "r");
    char buf[200];
    int i, j, k;
    int add = 1000;
    if (f_ptr == NULL)
    {
        printf("File cannout be opened!\n");
        return;
    }
    else
    {
        i = 0;
        while ((ch = fgetc(f_ptr)) != EOF)
        {
            if (ch != '\n')
                buf[i++] = ch;
        }
    }
    buf[i] = '\0';
    j = strlen(buf);
    printf("SYMBOL\t\t\tTOKEN\t\t\tTYPE\t\t\tADDRESS\n");
    char str[50];
    int r = 0;
    k = 0;
    while (k < j)
    {
        if (search(buf[k]) == 1)
        {
            printf("%c\t\t\tPuncuator\t\tNULL\t\t\t%d\n", buf[k], add++);
            k++;
        }
        else if (search(buf[k]) == 2)
        {
            printf("%c\t\t\tOperator\t\tNULL\t\t\t%d\n", buf[k], add++);
            k++;
        }
        else if (buf[k] == '\"')
        {
            int p = k;
            char strr[10];
            int r1 = 0;
            while (buf[p + 1] != '\"')
            {
                strr[r1++] = buf[p++];
            }
            buf[r1] = buf[p];
            buf[++r1] = '\"';
            printf("%s\t\tConstant\t\tString\t\t\t%d\n", strr, add++);
            k = p + 2;
            memset(strr, 0, sizeof(strr));
        }
        else if (isdigit(buf[k]) > 0)
        {
            int p = k;
            char str2[10];
            int r2 = 0;
            int f = 0;
            while (isdigit(buf[p]) || buf[p] == '.')
            {
                if (buf[p] != '.')
                    str2[r2++] = buf[p++];
                else
                {
                    str2[r2++] = buf[p++];
                    f = 1;
                }
            }
            k = p;
            if (f == 0)
                printf("%s\t\t\tConstant\t\tint\t\t\t%d\n", str2, add++);
            else
                printf("%s\t\t\tConstant\t\tfloat\t\t\t%d\n", str2, add++);
            memset(str2, 0, sizeof(str2));
        }
        else
        {
            i = k;
            while (i < j && search(buf[i]) == 0 && buf[i] != ' ')
            {
                str[r++] = buf[i++];
            }
            str[r] = '\0';
            if (search_k(str) == 1)
            {
                printf("%s\t\t\tKeyword\t\t\tNULL\t\t\t%d\n", str, add++);
                r = 0;
                memset(str, 0, sizeof(str));
                k = i;
            }
            else
            {

                if (str[0] != '\0')
                    printf("%s\t\t\tIdentifier\t\tNULL\t\t\t%d\n", str, add++);
                memset(str, 0, sizeof(str));
                r = 0;
                k++;
            }
        }
    }
    fclose(f_ptr);
}
int main()
{
    Syntax_Table();
}