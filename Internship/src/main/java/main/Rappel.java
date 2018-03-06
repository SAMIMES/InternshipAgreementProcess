package main;

import java.util.Calendar;
import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class Rappel implements JavaDelegate{
	public void execute(DelegateExecution execution) throws Exception {
		Date d = (Date)execution.getVariable("dateSoutenance");
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		if(day>7){
			c1.set(year,  month, day-7); 
			c2.set(year,  month, day-3); 
			c3.set(year,  month, day); 
		}
		else if(day>3){
			c1.set(year,  month-1, 31-(7-day)); 
			c2.set(year,  month, day-3); 
			c3.set(year,  month, day); 
		}
		else {
			c1.set(year,  month-1, 31-(7-day)); 
			c2.set(year,  month-1, 31-(3-day)); 
			c3.set(year,  month, day);
		}
		execution.setVariable("rappel1", c1.get(Calendar.YEAR)+"-"+(c1.get(Calendar.MONTH)+1)+"-"+c1.get(Calendar.DAY_OF_MONTH));
		execution.setVariable("rappel2", c2.get(Calendar.YEAR)+"-"+(c2.get(Calendar.MONTH)+1)+"-"+c2.get(Calendar.DAY_OF_MONTH));
		execution.setVariable("rappel3", c3.get(Calendar.YEAR)+"-"+(c3.get(Calendar.MONTH)+1)+"-"+c3.get(Calendar.DAY_OF_MONTH));
		
	}
}
