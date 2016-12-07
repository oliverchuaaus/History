/*
 * Created on Sep 25, 2004
 */
package com.tougher.util;

import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.*;
import org.apache.struts.action.*;


/**
 * @author Toffer
 */
public class StrutsUtil {
	private static final String[] monthsCode={"00","01","02","03","04","05","06","07","08","09","10","11","12"};
	private static final String[] monthsName={"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private static final String[] datesCode={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private static final String[] datesName={"","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private static final ArrayList monthsCodeAl=new ArrayList();
	private static final ArrayList monthsNameAl=new ArrayList();
	private static final ArrayList datesCodeAl=new ArrayList();
	private static final ArrayList datesNameAl=new ArrayList();

	private static void extract(ArrayList al, String[] array){
		if (al.isEmpty()){
			for (int i=0;i<array.length;i++){
				al.add(array[i]);
			}
		}
	}
	
	public static ArrayList getMonthsCode(){ 
		extract(monthsCodeAl,monthsCode);
		return monthsCodeAl;
	}
	public static ArrayList getMonthsName(){ 
		extract(monthsNameAl,monthsName);
		return monthsNameAl;
	}
	public static ArrayList getDatesCode(){ 
		extract(datesCodeAl,datesCode);
		return datesCodeAl;
	}
	public static ArrayList getDatesName(){ 
		extract(datesNameAl,datesName);
		return datesNameAl;
	}
	
	
	public static void extractErrors(HttpServletRequest request, InvalidInputException iie){
		ActionErrors aes=(ActionErrors)request.getAttribute(Globals.ERROR_KEY);
		if (aes==null){ 
			aes=new ActionErrors();
			request.setAttribute(Globals.ERROR_KEY, aes);
		}
	
		ArrayList keyList=iie.getExceptionKeyList();
		for (int i=0;i<keyList.size();i++){
			String key=(String)keyList.get(i);
			ArrayList errorDetailList=(ArrayList)iie.getExceptionList(key);
			for (int j=0;j<errorDetailList.size();j++){
				InvalidInputException.ErrorDetail ed=(InvalidInputException.ErrorDetail)errorDetailList.get(j);
				ActionMessage ae=new ActionMessage(ed.getKey(),ed.getValues());
				aes.add(key,ae);			
			}
		}
		
	}
}
