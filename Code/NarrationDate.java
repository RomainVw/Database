package narrationmanager.model;


/**
A class representing a date without any limitation on the number of days or months.
**/
public class NarrationDate implements Comparable<NarrationDate>
{
  private int day;
  private int month;
  private int year;
	
  public NarrationDate(int day,int month,int year)
  {
    this.day=day;
    this.month=month;
    this.year=year;
  }
  
    /**
  Parses a string in the form yy-mm-dd and creates the corresponding NarrationDate.
  **/
  public NarrationDate(String stringRepresentation)
  {
    String[] split=stringRepresentation.split("-");
    
    day=Integer.parseInt(split[2]);
    month=Integer.parseInt(split[1]);
    year=Integer.parseInt(split[0]);
  }
  
  public int getDay()
  {
    return day;	  
  }
  
  public void setDay(int day)
  {
    this.day=day;	  
  }
  
  public int getMonth()
  {
    return month;
  }
  
  public void setMonth(int month)
  {
    this.month=month;	  
  }
  
  public int getYear()
  {
    return year;	  
  }
  
  public void setYear(int year)
  {
    this.year=year;
  }
  
  public String toString()
  {
    return year+"-"+month+"-"+day;	  
  }
  
  public int compareTo(NarrationDate other)
  {
    int rslt=year-other.getYear();
    
    if(rslt==0) rslt=month-other.getMonth();
    if(rslt==0) rslt=day-other.getDay();
    
    return rslt;
  }
}
