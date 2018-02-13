public class LinkArray<T> extends LinkStack<T> {
    public LinkArray(){
        super();
    }

    @Override
    public String toString() {
        ItemNode current = top;
        String result = "";
        while (current!=null){
            int[] array = (int[])current.getData();
            result+= "["+array[0]+","+array[1]+"]";
            current = current.getNext();
        }
        return result;
    }
}
