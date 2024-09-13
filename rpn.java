import java.util.Stack;

public class rpn {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for(int i=0;i<tokens.length;i++){
            String c = tokens[i];
            if(c == "+"|| c=="*"||c=="-"||c=="/"){
                int op1 = st.pop();
                int op2 = st.pop();
                int r = 0;
                switch(c){
                    case "+":
                        r = op1 + op2;
                        break;
                    case "-":
                        r = op2-op1;
                        break;
                    case "*":
                        r=op1*op2;
                        break;
                    case "/":
                        r=op1/op2;
                        break;
                }
                st.push(r);
            }else{
                st.push(Integer.parseInt(c));
            }
        }
        return st.pop();
    }
    public  void main(String[] args) {
        // String[] tokens = {"1","2","+","3","*","4","-"};
        String[] tokens = {"2","1","+","3","*"};
        System.out.println(evalRPN(tokens));
    }
}
