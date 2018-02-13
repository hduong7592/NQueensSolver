public class ItemNode<T>{
    private T datum;
    private ItemNode<T> next;

    public ItemNode(T d){
        datum = d;
        next=null;
    }

    public void setNext(ItemNode<T> in){
        next = in;
    }

    public ItemNode<T> getNext(){return next;}
    public T getData(){return datum;}

}