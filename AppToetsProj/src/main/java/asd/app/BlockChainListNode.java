package asd.app;
public class BlockChainListNode extends Node<Transaction> {
    private int balance;

    public BlockChainListNode(Transaction value, BlockChainListNode next, int balance){
        super(value,next);
        this.balance = balance;
    }

    public int getBalance(){
        return balance;
    }


}
