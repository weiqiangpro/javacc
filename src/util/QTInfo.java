package util;

//������Ԫʽ����Ϣ
public class QTInfo {

	public static int size = 0; // ��Ԫʽȫ�ָ���
	private int innerId; // ��ǰ��ԪʽID
	private String operator;
	private String arg1;
	private String arg2;
	private String result;

	public QTInfo(String operator, String arg1, String arg2, String result) {
		super();
		this.innerId = ++size;
		this.operator = operator;
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.result = result;
	}

	public QTInfo(String operator, String arg1, String arg2, int result) {
		this(operator, arg1, arg2, result + "");
	}

	public String getOperator() {
		return this.operator;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setResult(int result) {
		this.result = "" + result;
	}

	public String getResult() {
		return this.result;
	}

	public void setInnerId(int innerID) {
		this.innerId = innerID;
	}

	public int getInnerIdSeqen() {
		return size;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%-3s", this.innerId) + ":(" + String.format("%-3s", this.operator) + ", " + String.format("%-3s", this.arg1)
				+ ", " + String.format("%-3s", this.arg2) + ", " + String.format("%-3s", this.result) + ")";
	}
}