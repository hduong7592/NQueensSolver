public class SolutionList<T> extends LinkStack<T> {
    public SolutionList(){
        super();
    }

    @Override
    public String toString() {
        ItemNode<T> current = top;
        String result = "";
        int i=8;
        while(current !=null){
            i--;
            result+= "["+current.getData()+","+i+"] ";
            current = current.getNext();
        }

        return result;
    }
}
