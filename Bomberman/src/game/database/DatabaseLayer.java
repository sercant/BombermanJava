package game.database;

import game.factories.HighscoreGet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseLayer implements HighscoreGet{

 private Connection connection = null;

 public void closeConnection() {
  try {
   connection.close();
   connection = null;
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 public List<String> getAllTimeScores() {

  boolean connected = false;
  if (connection == null)
   connected = tryConnect();

  if (!connected)
   return null;

  List<String> highscores = new ArrayList<String>();

  try {
   Statement statement = connection.createStatement();
   String query = "select * from User order by user_id desc";
   ResultSet result = statement.executeQuery(query);

   while (result.next()) {
    highscores.add(result.getString("username") + " - " + result.getString("score") + " - " + result.getString("achievement_date"));
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  closeConnection();

  return highscores;
 }

 public boolean insertScore(String name, int score){
  boolean connected = false;
  if (connection == null)
   connected = tryConnect();

  if (!connected)
   return false;

  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  Date date = new Date();

  try {

   Statement statement = connection.createStatement();
   String query = "insert into User(username,score,achievement_date)"
     + "VALUES("
     + "'"
     + name
     + "',"
     + score
     + ",'"
     + dateFormat.format(date).toString()
     + "' )";
//   String query = "insert into User(username,score,achievement_date) values('sercan', 123, '123123')";
   statement.executeUpdate(query);

   closeConnection();
  } catch (Exception e) {
   e.printStackTrace();
   return false;
  }
  return true;
 }

 private boolean tryConnect() {
  try {
	  Class.forName("org.sqlite.JDBC");
  } catch (ClassNotFoundException e) {
   System.err.println("Locate your JDBC Driver!");
   e.printStackTrace();
   return false;
  }
  try {
   connection = DriverManager.getConnection("jdbc:sqlite:db/highscores.db");

  } catch (SQLException e) {
   System.err.println("Connection Failed! Check output console");
   e.printStackTrace();
   return false;
  }

  if (connection != null) {
   System.out.println("Connection established!");
  } else {
   System.err.println("Failed to make connection!");
  }

  return true;
 }
}