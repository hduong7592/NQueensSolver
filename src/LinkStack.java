/**
 * This is LinkStack class
 *
 * This class is used to create an ArrayList with the ItemNode
 *
 * @author Hieu Duong
 * @since 2018-02-15
 */

public class LinkStack<T> implements Stackable<T> {
    protected ItemNode top;
    private int stackSize;

    public LinkStack(){
        top = null;
        stackSize = 0;
    }

    public int getStackSize(){
        return stackSize;
    }

    @Override
    public void push(T data) {
        ItemNode<T> newNode = new ItemNode<>(data);
        newNode.setNext(top);
        top = newNode;
        stackSize+=1;

    }

    @Override
    public T pop() {
        ItemNode<T> current = top;
        if(current!=null) {
            top = current.getNext();
            stackSize -= 1;
            return current.getData();
        }
        else return null;
    }

    public T peak(){
        ItemNode<T> current = top;
        if(current!=null){
        return current.getData();}
        else return null;
    }

    public T getDataAtIndex(int index){
        ItemNode<T> current = top;
        int count = 0;
        while(current !=null){

            count++;
            if(count == index){
                return current.getData();
            }
            else return null;

        }
        return null;
    }

    @Override
    public String toString() {
        ItemNode<T> current = top;
        String result = "";
        while(current !=null){
            result+= current.getData()+" ";
            current = current.getNext();
        }

        return result;
    }
}
