class RecursiveDescentParser:
    def __init__(self, grammar, start_symbol):
        self.grammar = grammar
        self.start_symbol = start_symbol
        self.input_string = None
        self.position = 0

    def parse(self, input_string):
        self.input_string = input_string + '$'
        self.position = 0
        success = self.parse_non_terminal(self.start_symbol)
        return success and self.input_string[self.position] == '$'

    def parse_non_terminal(self, non_terminal):


        for rule in self.grammar[non_terminal]:
            saved_position = self.position
            if self.match_rule(rule):

                return True
            self.position = saved_position
        

        return False

    def match_rule(self, rule):
        if rule == ['ε']:
            # Handle epsilon (empty) production
            return True

        for symbol in rule:
            if symbol.isupper():
                if not self.parse_non_terminal(symbol):
                    return False
            else:
                if not self.match_terminal(symbol):
                    return False
        return True

    def match_terminal(self, terminal):
        # Handle matching for multi-character terminals like 'id'
        terminal_length = len(terminal)
        if self.input_string[self.position:self.position + terminal_length] == terminal:
            self.position += terminal_length
            return True
        return False

if __name__ == "__main__":
    print("Ryan Jacob")
    grammar = {
        'E': [['T', 'R']],
        'R': [['+', 'T', 'R'], ['ε']],
        'T': [['F', 'Y']],
        'Y': [['*', 'F', 'Y'], ['ε']],
        'F': [['(', 'E', ')'], ['id']]
    }

    start_symbol = 'E'

    # Create the parser
    parser = RecursiveDescentParser(grammar, start_symbol)

    # Input string to be parsed
    input_string = input("Enter the string to parse: ")

    # Parse the string and print the result
    if parser.parse(input_string):
        print("\nString is accepted. ")
    else:
        print("\nString is not accepted.")