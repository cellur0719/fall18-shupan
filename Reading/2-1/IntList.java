public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
    /*Return the size of the list using ... recursion */
    public int size(){
        if(rest==null){
          return 1;
        }
        else{
          return 1+this.rest.size();
        }
    }
    /*Return the size of the list using ... iterations/ no recursions */
    public int iterativeSize(){
      IntList p;
      int iter;
      p=this;
      iter=0;
      while(p!=null){
        p=p.rest;
        iter+=1;
      }
      return iter;
    }

    /*Return the ith item of the list */
    public int get(int i){
      IntList p=this;
      for (int j=0;j<i;j++){
        p=p.rest;
      }
      return p.first;
    }
    /*Return the ith item of the list using recursion*/
    public int recursiveGet(int i){
      if(i==0){
        return first;
      }
      else return rest.recursiveGet(i-1);
    }

    public static void main(String[] args) {
      IntList L = new IntList(15, null);
      L = new IntList(10, L);
      L = new IntList(5, L);
      System.out.println(L.size());
      System.out.println(L.iterativeSize());
      System.out.println(L.get(2));
      System.out.println(L.recursiveGet(2));
    }
}