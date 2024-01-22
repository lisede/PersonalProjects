package Driver;

//Array-based stack of dynamic size
public class ArrayStack<T> implements Stack<T>{

    private final int increase = 5;
    private T[] mystack;
    private int top;
    private int size;
    

    public ArrayStack(){
        this(5);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int size){
        this.mystack = (T[]) new Object[size];
        this.top = -1;
        this.size = size;
    }

    @Override
    public int size(){
        return top+1;
    }

    public int Size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return this.top == -1;
    }

    @Override
    public T top(){
        if (this.isEmpty()){
            return null;
        }

        T temp = (T) this.mystack[top];
        return temp;
    }

    @Override
    public void push(T input){
        if (top == this.mystack.length-1){
            this.increase();
        }
        this.top++;
        this.mystack[top] = input;
    }

    @Override
    public T pop(){
        if (top == this.mystack.length-6){
            this.reduce();
        }

        if (this.isEmpty()){
            return null;
        }
        T temp = (T)this.mystack[top];
        this.mystack[top] = null;
        this.top--;
        return temp;
    }

    @SuppressWarnings("unchecked")
    private void increase(){
        // System.out.println("STACK OVERFLOW");
        Object[] t = new Object[this.mystack.length+increase];
        for (int i = 0; i<this.mystack.length; i++){
            t[i] = this.mystack[i];
        }
        this.mystack = (T[]) t;
        this.size = t.length;
    }

    @SuppressWarnings("unchecked")
    private void reduce(){
        Object[] t = new Object[this.mystack.length-increase];
        for (int i = 0; i <= this.top; i++){
            t[i] = this.mystack[i];
        }
        this.mystack = (T[]) t;
        this.size = t.length;
    }
 


} 
