package util;

import java.util.ArrayList;



public class QTList {
	public ArrayList<QTInfo> QTList = new ArrayList<QTInfo>();
	public static boolean flag = true;

	public void addQTInfo(QTInfo info) {
		QTList.add(info);
	}

	public void addQTInfo(int index, QTInfo info) {
		QTList.add(index, info);
	}

	public QTInfo get(int index) {
		return (QTInfo) QTList.get(index);
	}

	public QTInfo remove(int index) {
		return QTList.remove(index - 1);
	}

	public void clear() {
		QTList.clear();
		QTInfo.size = 0;
	}

	public void printQTTable() {
		for(QTInfo info : QTList) 
			System.out.println(info.toString());
	}
	

	public String getMes() {
		String str = "";
		for(QTInfo info : QTList) 
			str += info.toString()+"\n";
		return str;
	}

}
