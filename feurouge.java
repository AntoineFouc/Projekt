public class feurouge extends obstacle{
    
    protected int voiebloq;
    protected int temps;
    
    public feurouge(int uneposx,int uneposy,int unevoiebloq,int untemps){
        super(3,3,uneposx,uneposy);
        voiebloq=unevoiebloq;
        temps=untemps;
    }
}
