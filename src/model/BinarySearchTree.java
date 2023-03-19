package model;

public class BinarySearchTree {

    private Player root;

    public BinarySearchTree(){

    }

    public Player getRoot() {
        return root;
    }

    public void insert(Player player){
        if(root==null){
            root=player;
        }else{
            insert(root,player);
        }
    }

    private void insert(Player root,Player player){
        if(search(player.getScore())!=null){
            return;
        }
        if(player.getScore()<root.getScore()){
            if(root.getLeft()==null){
                root.setLeft(player);
            }else{
                insert(root.getLeft(),player);
            }
        }else{
            if(root.getRight()==null){
                root.setRight(player);
            }else{
                insert(root.getRight(),player);
            }
        }
    }

    public String printReverse(){
        return printReverse(root);
    }

    private String printReverse(Player current){
        if(current==null){
            return "";
        }
        return  printReverse(current.getRight())+" "+current.getSymbol()+" "+current.getScore()+"\n "+printReverse(current.getLeft());

    }



    public Player search(double score){
        return search(root,score);
    }

    private Player search(Player current,double score){
        if(current==null){
            return null;
        }
        if(current.getScore()==score){
            return current;
        }
        if(current.getScore()>score){
            return search(current.getLeft(),score);
        }else{
            return search(current.getRight(),score);
        }
    }

    public void setRoot(Player root) {
        this.root = root;
    }
}
