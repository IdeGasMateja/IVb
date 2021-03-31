package jebemtimater;

import java.sql.*;

public class Banka {
    Connection connection;
    static String connectionString = "jdbc:sqlite:Banka.db";
    private String upit;
    public void connect() throws SQLException
    {
        this.connection=DriverManager.getConnection(connectionString);
    }
    //fff
    public void disconnect() throws SQLException
    {
        if(connection!=null && !connection.isClosed())
        this.connection.close();
    }
    
    public void listAllAccounts() throws SQLException
    {String upit="SELECT r.IdRac, r.Stanje, r.IdKom, k.Naziv FROM Racun r, Komitent k WHERE k.IdKom=r.IdKom";
    try(Statement st=connection.createStatement();
    ResultSet rs=st.executeQuery(upit);)
    {
    while(rs.next()){
        int idRac=rs.getInt(1);
        double Stanje;
        Stanje = rs.getDouble(2);
        int idkom=rs.getInt("IdKom");
        String naziv=rs.getString(4);
        System.out.println(idRac+"\t"+Stanje+"\t"+idkom+"\t"+naziv+"\t");
    }
    }
    }
    
    public void listKomitenata() throws SQLException
    {String upit="SELECT * FROM Komitent";
    try(Statement st=connection.createStatement();
    ResultSet rs=st.executeQuery(upit);)
    {
    while(rs.next()){
        int idkom=rs.getInt(1);
        String naziv=rs.getString(2);
        String adresa=rs.getString(3);
        System.out.println(idkom+"\t"+naziv+"\t"+adresa+"\t");
    }
    }
    }
    
    public void jedigovna()
    {
        System.out.println("Jedi govna");
    }
    public void transfer(double amount, int idrac) throws SQLException
    {
        String upit="UPDATE Racun SET Stanje=Stanje+"+amount+" WHERE IdRac="+idrac;
        Statement st=connection.createStatement();
        st.executeUpdate(upit);
    }
    public void suma() throws SQLException
    {
        String upit="SELECT sum(Stanje) AS s FROM Racun";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        int resenje=rs.getInt("s");
        System.out.println("\n"+resenje);
    }
    
    public int getL() throws SQLException
    {
        String upit="SELECT MAX(IdKom) FROM Komitent";
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery(upit);
        return(rs.getInt(1)+1);
    }
    public void insert(String ime, String ulica) throws SQLException
    {
        connection.setAutoCommit(false);
        int poslednji=this.getL();//vraca sledeci IdKom, ovaj deo radi
        String upit="INSERT INTO Komitent VALUES(?,?,?)";
        PreparedStatement st=connection.prepareStatement(upit);
        st.setInt(1,poslednji);
        st.setString(2,"?");
        st.setString(3,ulica);
        st.execute();
        connection.commit();
        connection.setAutoCommit(true);
    }
    public void obrisi() throws SQLException
    {
        int poslednji=this.getL()-1;
        String upit="DELETE FROM Komitent WHERE IdKom="+poslednji;
        Statement st=connection.createStatement();
        st.execute(upit);
    }
}
