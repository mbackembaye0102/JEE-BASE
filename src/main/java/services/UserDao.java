package services;

import model.User;

import java.sql.ResultSet;

public class UserDao {
    public User logOn(String login, String password){
        User u=null;
        try{
         String sql="select * from utilisateur where login=? and password =?";
         DataBaseHelper db=  DataBaseHelper.getInstance();
         db.iniPreparedCmd(sql);
         db.getPstmt().setString(1, login);
         db.getPstmt().setString(2, password);
            ResultSet rs= db.My_ExecutePrepareQuery();
            if(rs.next()){
                u=new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }

        }
        catch(Exception e){
            e.getMessage();
        }
        return u;
    }


}
