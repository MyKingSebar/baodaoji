package com.test.ys.saomiaotest;

public class Response1301
{
  private String Name = "";
  private String PatientID = "";
  private String ResultCode = "";
  private String Sex = "";
  private String Tip = "";
  
  public String getName()
  {
    return this.Name;
  }
  
  public String getPatientID()
  {
    return this.PatientID;
  }
  
  public String getResultCode()
  {
    return this.ResultCode;
  }
  
  public String getSex()
  {
    return this.Sex;
  }
  
  public String getTip()
  {
    return this.Tip;
  }
  
  public void setName(String paramString)
  {
    this.Name = paramString;
  }
  
  public void setPatientID(String paramString)
  {
    this.PatientID = paramString;
  }
  
  public void setResultCode(String paramString)
  {
    this.ResultCode = paramString;
  }
  
  public void setSex(String paramString)
  {
    this.Sex = paramString;
  }
  
  public void setTip(String paramString)
  {
    this.Tip = paramString;
  }
  
  public String toString()
  {
    return "Response1301{ResultCode='" + this.ResultCode + '\'' + ", Tip='" + this.Tip + '\'' + ", PatientID='" + this.PatientID + '\'' + ", Name='" + this.Name + '\'' + ", Sex='" + this.Sex + '\'' + '}';
  }
}


