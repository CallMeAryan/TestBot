package net.liven.bot.Utils;

import java.sql.*;

import static net.liven.bot.DiscordBot.TableName;

public class DbFunctions {

    public Connection connect_to_db(String dbname,String user,String pass){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://135.181.116.220:25565/"+dbname,user,pass);
            if(conn!=null){
                System.out.println("Connection Established");
        
            }
            else{
                System.out.println("Connection Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query = "create table "+table_name+"(UserID varchar(99),NameColor varchar(99),PlusColor varchar(99),MessageColor varchar(99),primary key(UserID));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void addUser(Connection conn,String table_name,String UUID, String NameColor, String PlusColor, String MessageColor){
        Statement statement;
        try {
            String query=String.format("insert into %s(UserID,NameColor,PlusColor,MessageColor) values('%s','%s','%s','%s');",table_name,UUID,NameColor,PlusColor,MessageColor);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("added new player");
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public String getData(Connection conn, long UserID, String DataType){
        Statement statement;

        try {
            String query = "SELECT * FROM " + TableName +" WHERE userid=?;";
            PreparedStatement p1 = conn.prepareStatement(query);
            p1.setLong(1, UserID);
            ResultSet rs = p1.executeQuery();
            while (rs.next()){
                String data = String.valueOf(rs.getString(DataType));
                return data;
            }
//            while (rs.next()){
//
//            }
//
//
//            switch (DataType){
//                case "userid":
//                case "username":
//                case "ratings":
//            }

            while (rs.next()){
                String data = rs.getString(DataType);
                return data;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }


        return null;
    }

    public String getNameColor(Connection conn, String UserID){ //its getRating
        Statement statement;

        try {
            String query = "SELECT * FROM UserData WHERE userid=?;";
            PreparedStatement p1 = conn.prepareStatement(query);
            p1.setString(1, UserID);
            ResultSet rs = p1.executeQuery();
            while (rs.next()){
               String ratings = rs.getString("NameColor");
                return ratings;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }


        return null;
    }

    public void update_rating(Connection conn, long userID, int new_rating){
        Statement statement;
        try {
            String query = "update " + TableName +" set ratings=? where userid=?";
            PreparedStatement p1 = conn.prepareStatement(query);
            p1.setInt(1, new_rating);
            p1.setLong(2, userID);
            p1.executeUpdate();
            System.out.println("Data Updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
