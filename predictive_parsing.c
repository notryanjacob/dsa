#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_STACK 100
#define MAX_INPUT 100
#define NUM_NON_TERMINALS 3
#define NUM_TERMINALS 6

// Non-terminals
enum NonTerminal
{
    E,
    T,
    F
};

// Terminals
enum Terminal
{
    PLUS,
    MULT,
    LPAREN,
    RPAREN,
    ID,
    END
};

// Production rules
char *productions[NUM_NON_TERMINALS][NUM_TERMINALS] = {
    {"TK", NULL, "TK", NULL, "TK", NULL}, // E -> TK
    {NULL, NULL, "FK", NULL, "FK", NULL}, // T -> FK
    {NULL, NULL, "(E)", NULL, "i", NULL}  // F -> (E) | id
};

// Parsing table
int parse_table[NUM_NON_TERMINALS][NUM_TERMINALS] = {
    {0, -1, 0, -1, 0, -1},  // E -> TK
    {-1, -1, 1, -1, 1, -1}, // T -> FK
    {-1, -1, 2, -1, 2, -1}  // F -> (E) | id
};

char stack[MAX_STACK];
int top = -1;

void push(char c)
{
    if (top < MAX_STACK - 1)
    {
        stack[++top] = c;
    }
    else
    {
        printf("Stack overflow\n");
        exit(1);
    }
}

char pop()
{
    if (top >= 0)
    {
        return stack[top--];
    }
    else
    {
        printf("Stack underflow\n");
        exit(1);
    }
}

int get_terminal(char c)
{
    switch (c)
    {
    case '+':
        return PLUS;
    case '*':
        return MULT;
    case '(':
        return LPAREN;
    case ')':
        return RPAREN;
    case 'i':
        return ID;
    case '$':
        return END;
    default:
        return -1;
    }
}

void parse(char *input)
{
    int input_index = 0;
    push('$');
    push('E');

    while (top >= 0)
    {
        char stack_top = pop();
        char current_input = input[input_index];

        if (stack_top == current_input)
        {
            input_index++;
            if (current_input == '$')
            {
                printf("Parsing successful\n");
                return;
            }
        }
        else if (isupper(stack_top)) // Non-terminal
        {
            int non_terminal = stack_top - 'E';
            int terminal = get_terminal(current_input);

            if (terminal == -1)
            {
                printf("Invalid input symbol: %c\n", current_input);
                return;
            }

            int production = parse_table[non_terminal][terminal];

            if (production == -1)
            {
                printf("Parsing error: No production for %c and %c\n", stack_top, current_input);
                return;
            }

            char *rule = productions[non_terminal][terminal];
            if (rule != NULL)
            {
                for (int i = strlen(rule) - 1; i >= 0; i--)
                {
                    if (rule[i] != 'e')
                    {
                        push(rule[i]);
                    }
                }
            }
        }
        else
        {
            printf("Parsing error: Mismatch between %c and %c\n", stack_top, current_input);
            return;
        }
    }
}

void print_parsing_steps(char *input)
{
    char stack[MAX_STACK] = "$E";
    int stack_top = 1;
    int input_index = 0;

    printf("%-12s %-12s Action\n", "Stack", "Input");
    printf("------------------------------------\n");

    while (stack_top >= 0)
    {
        // Print current state
        printf("%-12s %-12s ", stack, input + input_index);

        char stack_symbol = stack[stack_top];
        char current_input = input[input_index];

        if (stack_symbol == current_input)
        {
            if (current_input == '$')
            {
                printf("Accept\n");
                return;
            }
            printf("Match %c\n", current_input);
            stack_top--;
            input_index++;
        }
        else if (isupper(stack_symbol))
        {
            int non_terminal = stack_symbol - 'E';
            int terminal = get_terminal(current_input);

            if (terminal == -1)
            {
                printf("Error: Invalid input symbol\n");
                return;
            }

            int production = parse_table[non_terminal][terminal];

            if (production == -1)
            {
                printf("Error: No production\n");
                return;
            }

            char *rule = productions[non_terminal][terminal];
            printf("Expand %c → %s\n", stack_symbol, rule ? rule : "ε");

            stack_top--;
            if (rule != NULL)
            {
                for (int i = strlen(rule) - 1; i >= 0; i--)
                {
                    if (rule[i] != 'e')
                    {
                        stack[++stack_top] = rule[i];
                    }
                }
            }
        }
        else
        {
            printf("Error: Mismatch\n");
            return;
        }

        // Update stack string
        stack[stack_top + 1] = '\0';
    }
}

int main()
{
    char input[MAX_INPUT];
    printf("Enter input string (end with $): ");
    scanf("%s", input);
    parse(input);
    return 0;
}
