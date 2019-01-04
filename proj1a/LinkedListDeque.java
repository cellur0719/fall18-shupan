public class LinkedListDeque<T>{
    private TNode sentinel;
    private int size;

    public class TNode{
        public T item;
        public TNode next;
        public TNode prev;

        public TNode(){
            item=null;
            next=null;
            prev=null;
        }

        public TNode(TNode p, T i, TNode n){
            item=i;
            next=n;
            prev=p;
        }

        public T getRecursive(int index){
            if(index>size-1 || index<0) return null;
            TNode p=this;
            if(index==0){
                return next.item;
            }
            return next.getRecursive(index-1);
        }
    }



    public LinkedListDeque(){
        sentinel=new TNode();
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;
    }

    public void addFirst(T x){
        sentinel.next = new TNode(sentinel,x, sentinel.next);
        if(size==0){
            sentinel.prev=sentinel.next;
        }
        sentinel.next.next.prev=sentinel.next;
        size+=1;

    }

    public void addLast(T x){
        size+=1;
        sentinel.prev.next=new TNode(sentinel.prev,x,sentinel);
        sentinel.prev=sentinel.prev.next;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
       return size==0;
    }

    public void printDeque(){
        if(size == 0) ;
        else{
            TNode p=sentinel;
            while (p.next!=sentinel){
                System.out.print(p.next.item+" ");
                p=p.next;
            }
            System.out.println();
        }
    }


    public T removeFirst(){
        if(size==0) return null;
        size-=1;
        T first=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        sentinel.next.prev=sentinel;
        return first;

    }

    public T removeLast(){
       if(size==0) return null;
       size-=1;
       T last=sentinel.prev.item;
       sentinel.prev=sentinel.prev.prev;
       sentinel.prev.next=sentinel;
       return last;
    }

    public T get(int index){
        if(index>size-1 || index<0) return null;
        TNode p=sentinel;
        while(index!=0){
            p=p.next;
            index-=1;
        }
        return p.next.item;
    }

    public T getRecursive(int index){
       return sentinel.getRecursive(index);
    }

    public static void main(String[] args){
        LinkedListDeque<String> shup=new LinkedListDeque<>();
        shup.addFirst("panshu");
        shup.addLast("shibushi");
        shup.addLast("bendan");
        System.out.println(shup.getRecursive(3));
        System.out.println(shup.removeLast());
        System.out.println(shup.removeFirst());
        shup.printDeque();
        System.out.println(shup.size());
    }

}