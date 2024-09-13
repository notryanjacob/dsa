#include <stdio.h>
#include <string.h>
#include <ctype.h>
char keywords[][10] = {"scanf", "printf", "if", "else", "for", "while", "#include", "stdio.h", "return", "int", "main"};
void print(char *op, char var[])
{
    printf("%s\t\t\t%s\n", op, var);
}
void print_c(char op, char var1[])
{
    printf("%c\t\t\t%s\n", op, var1);
}
int search(char a)
{
    char oper[] = {'+', '-', '/', '%', '*'};
    char symb[] = {'<', '>', '(', ')', '{', '}', ';', '='};
    for (int i = 0; i < 5; i++)
        if (a == oper[i])
            return 1;
    for (int i = 0; i < 8; i++)
    {
        if (a == symb[i])
            return 2;
    }
    return 0;
}
int search_k(char arr[])
{
    for (int i = 0; i < 11; i++)
    {
        if (strcmp(arr, keywords[i]) == 0)
            return 1;
    }
    return 0;
}
void Lexical_Analyzer()
{
    char buffer[500], ch;
    FILE *fp;
    char file[100];
    printf("Enter the file name: ");
    scanf("%s", file);
    printf("Symbol\t\t\tToken\n");
    fp = fopen(file, "r");
    if (fp == NULL)
    {
        printf("Can't open the file\n");
        return;
    }
    else
    {
        int j = 0;
        while ((ch = fgetc(fp)) != EOF)
        {
            if (ch != '\n')
                buffer[j++] = (char)ch;
        }
    }
    char token[50];
    int n = strlen(buffer);
    int j = 0;
    for (int i = 0; i < n; i++)
    {
        // printf("buffer[i] = %c\n",buffer[i]);
        if (search(buffer[i]) == 1)
            print_c(buffer[i], "Operator");
        else if (search(buffer[i]) == 2)
            print_c(buffer[i], "Symbol");
        else if (isdigit(buffer[i]))
            print_c(buffer[i], "constant");
        else
        {
            // printf("%s\n",token);
            if (search(buffer[i + 1]) != 0 && buffer[i] != ' ')
            {
                token[j++] = buffer[i];
                if (search_k(token) == 1)
                {
                    print(token, "keyword");
                    memset(token, 0, sizeof(token));
                    j = 0;
                }
                else
                {
                    print(token, "identifier");
                    memset(token, 0, sizeof(token));
                    j = 0;
                }
            }
            else if (buffer[i] != ' ')
            {
                token[j++] = buffer[i];
            }
            else
            {
                if (search_k(token) == 1)
                {
                    print(token, "keyword");
                    memset(token, 0, sizeof(token));
                    j = 0;
                }
                else if (strcmp(token, " ") != 0 && j != 0)
                {
                    print(token, "Identifier");
                    memset(token, 0, sizeof(token));
                    j = 0;
                }
            }
        }
    }
}
int main()
{
    Lexical_Analyzer();
}