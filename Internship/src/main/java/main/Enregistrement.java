package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class Enregistrement implements JavaDelegate{
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		Statement stmt = conn.createStatement();
		
		String createTableString = "CREATE TABLE IF NOT EXISTS STAGE (ID INT NOT NULL IDENTITY(0, 1) PRIMARY KEY, SUJET VARCHAR(255), DATED VARCHAR(255), DATEF VARCHAR(255), REMUNERATION LONG, NOMENTREPRISE VARCHAR(255), MAIL VARCHAR(255) );";
		stmt.execute(createTableString);
		
		String sujet = (String) execution.getVariable("sujet");
		
		Date d1 = (Date)execution.getVariable("datedeb");
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		int year1 = cal1.get(Calendar.YEAR);
		int month1 = cal1.get(Calendar.MONTH);
		int day1 = cal1.get(Calendar.DAY_OF_MONTH);
		String dateD = year1+"-"+month1+"-"+day1;
		
		Date d2 = (Date)execution.getVariable("datefin");
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		int year2 = cal2.get(Calendar.YEAR);
		int month2 = cal2.get(Calendar.MONTH);
		int day2 = cal2.get(Calendar.DAY_OF_MONTH);
		String dateF = year2+"-"+month2+"-"+day2;
		
		long remuneration = (Long) execution.getVariable("salaire");
		
		String nomEntreprise = (String) execution.getVariable("nomEntreprise");
		
		String mail = (String) execution.getVariable("mailTuteurEntreprise");
		
		Statement st = conn.createStatement();
		st.execute("INSERT INTO \"STAGE\" (SUJET, DATED, DATEF, REMUNERATION, NOMENTREPRISE, MAIL) VALUES('"+sujet+"','"+dateD+"','"+dateF+"',"+remuneration+",'"+nomEntreprise+"','"+mail+"')");
		ResultSet resultSet = st.executeQuery("SELECT *  FROM STAGE"); 
		resultSet.last();
		System.out.println();
		System.out.println();
		System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getDate(3) + " - " + resultSet.getDate(4) + " - " +resultSet.getLong(5) + " - " + resultSet.getString(6) + " - " + resultSet.getString(7));
	}

}
