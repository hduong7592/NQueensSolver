/**
 * This is SolutionList class
 *
 * This class extends from LinkStack class
 *
 * This class has the override method toString to display the solution in the format of [Row, Col]
 *
 * @author Hieu Duong
 * @since 2018-02-15
 */


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
