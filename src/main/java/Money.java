
public class Money{
    public int euro;
    public int cents;

    public Money(int euro, int cents) {
        this.euro = euro;
        this.cents = cents;
    }

    public int getEuro() {
        return euro;
    }

    public int getCents() {
        return cents;
    }

    public void setEuro(int euro) {
        this.euro = euro;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }

    public void add(Money money){
           this.cents=cents+money.getCents();
           this.euro=euro+money.getEuro();
        if (cents >= 100) {
            this.euro++;
            this.cents=this.cents-100;
        }
    }

    public boolean isBiggerOrEquals(Money money){
        boolean result=false;
        if (this.euro>money.getEuro()) {
            result= true;
        }
        else if (this.euro==money.getEuro()) {
           if (this.cents>money.getCents()){
               result= true;
           } else if (this.cents<money.getCents()){
               result= false;
           } else if (this.cents==money.getCents()){
               result= true;
           }
        }   else if (this.euro<money.getEuro()){
            result= false;
        }
        return result;
    }



    @Override
    public String toString(){
          return "Euro: "+euro+" Cents: "+cents;
    }
}
