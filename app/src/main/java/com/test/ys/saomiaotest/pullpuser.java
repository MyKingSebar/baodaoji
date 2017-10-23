package com.test.ys.saomiaotest;

import android.util.Xml;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;

public class pullpuser
{
  public static String save1301(List<Request1301> paramList)
    throws Exception
  {
    StringWriter localStringWriter = new StringWriter();
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    localXmlSerializer.setOutput(localStringWriter);
    localXmlSerializer.startDocument("UTF-8", Boolean.valueOf(true));
    for(Request1301 localRequest1301:paramList){
      localXmlSerializer.startTag(null, "Request");
      localXmlSerializer.startTag(null, "TradeCode");
      localXmlSerializer.text(localRequest1301.getTradeCode());
      localXmlSerializer.endTag(null, "TradeCode");
      localXmlSerializer.startTag(null, "ExtOrgCode");
      localXmlSerializer.text(localRequest1301.getExtOrgCode());
      localXmlSerializer.endTag(null, "ExtOrgCode");
      localXmlSerializer.startTag(null, "ClientType");
      localXmlSerializer.text(localRequest1301.getClientType());
      localXmlSerializer.endTag(null, "ClientType");
      localXmlSerializer.startTag(null, "HospitalId");
      localXmlSerializer.text(localRequest1301.getHospitalId());
      localXmlSerializer.endTag(null, "HospitalId");
      localXmlSerializer.startTag(null, "ExtUserID");
      localXmlSerializer.text(localRequest1301.getExtUserID());
      localXmlSerializer.endTag(null, "ExtUserID");
      localXmlSerializer.startTag(null, "PatientID");
      localXmlSerializer.text(localRequest1301.getPatientID());
      localXmlSerializer.endTag(null, "PatientID");
      localXmlSerializer.startTag(null, "ServiceName");
      localXmlSerializer.text(localRequest1301.getServiceName());
      localXmlSerializer.endTag(null, "ServiceName");
      localXmlSerializer.startTag(null, "IDType");
      localXmlSerializer.text(localRequest1301.getIDType());
      localXmlSerializer.endTag(null, "IDType");
      localXmlSerializer.startTag(null, "MachineID");
      localXmlSerializer.text(localRequest1301.getMachineID());
      localXmlSerializer.endTag(null, "MachineID");
      localXmlSerializer.startTag(null, "ZoneID");
      localXmlSerializer.text(localRequest1301.getZoneID());
      localXmlSerializer.endTag(null, "ZoneID");
      localXmlSerializer.startTag(null, "AdmNo");
      localXmlSerializer.text(localRequest1301.getAdmNo());
      localXmlSerializer.endTag(null, "AdmNo");
      localXmlSerializer.endTag(null, "Request");
    }

    localXmlSerializer.endDocument();
    return String.valueOf(localStringWriter);
  }
  
  public static String save3300(List<Request3030> paramList)
    throws Exception
  {
    StringWriter localStringWriter = new StringWriter();
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    localXmlSerializer.setOutput(localStringWriter);
    localXmlSerializer.startDocument("UTF-8", Boolean.valueOf(true));
    for(Request3030 localRequest3030:paramList){
      localXmlSerializer.startTag(null, "Request");
      localXmlSerializer.startTag(null, "TradeCode");
      localXmlSerializer.text(localRequest3030.getTradeCode());
      localXmlSerializer.endTag(null, "TradeCode");
      localXmlSerializer.startTag(null, "TransactionId");
      localXmlSerializer.text(localRequest3030.getTransactionId());
      localXmlSerializer.endTag(null, "TransactionId");
      localXmlSerializer.startTag(null, "ExtOrgCode");
      localXmlSerializer.text(localRequest3030.getExtOrgCode());
      localXmlSerializer.endTag(null, "ExtOrgCode");
      localXmlSerializer.startTag(null, "ClientType");
      localXmlSerializer.text(localRequest3030.getClientType());
      localXmlSerializer.endTag(null, "ClientType");
      localXmlSerializer.startTag(null, "TerminalID");
      localXmlSerializer.text(localRequest3030.getTerminalID());
      localXmlSerializer.endTag(null, "TerminalID");
      localXmlSerializer.startTag(null, "HospitalId");
      localXmlSerializer.text(localRequest3030.getHospitalId());
      localXmlSerializer.endTag(null, "HospitalId");
      localXmlSerializer.startTag(null, "ExtUserID");
      localXmlSerializer.text(localRequest3030.getExtUserID());
      localXmlSerializer.endTag(null, "ExtUserID");
      localXmlSerializer.startTag(null, "PatientCard");
      localXmlSerializer.text(localRequest3030.getPatientCard());
      localXmlSerializer.endTag(null, "PatientCard");
      localXmlSerializer.startTag(null, "CardType");
      localXmlSerializer.text(localRequest3030.getCardType());
      localXmlSerializer.endTag(null, "CardType");
      localXmlSerializer.startTag(null, "PatientID");
      localXmlSerializer.text(localRequest3030.getPatientID());
      localXmlSerializer.endTag(null, "PatientID");
      localXmlSerializer.startTag(null, "TerminalID");
      localXmlSerializer.text(localRequest3030.getTerminalID());
      localXmlSerializer.endTag(null, "TerminalID");
      localXmlSerializer.startTag(null, "IDNo");
      localXmlSerializer.text(localRequest3030.getIDNo());
      localXmlSerializer.endTag(null, "IDNo");
      localXmlSerializer.startTag(null, "PatientName");
      localXmlSerializer.text(localRequest3030.getPatientName());
      localXmlSerializer.endTag(null, "PatientName");
      localXmlSerializer.endTag(null, "Request");
    }

    localXmlSerializer.endDocument();
    return String.valueOf(localStringWriter);
  }
}

