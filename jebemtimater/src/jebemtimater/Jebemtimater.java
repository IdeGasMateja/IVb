
package jebemtimater;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jebemtimater {

    public static void main(String[] args) {
        Banka banka=new Banka();
        try {
            banka.connect();
            banka.jedigovna();
            banka.transfer(2000.0, 2);
            banka.listKomitenata();
            banka.insert("MarkoHeHe", "Odegas");
            System.out.println("\n\n");
            banka.listKomitenata();
            banka.obrisi();
            System.out.println("\n\n");
            banka.listKomitenata();
            banka.suma();
        } catch (Exception e) {
            System.out.println("Greska");
        }
        finally{
            try{
                banka.disconnect();
            }
            catch(Exception e){}
        }
    }
    
}
