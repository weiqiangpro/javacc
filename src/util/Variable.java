package util;

public class Variable {
	public String image;
	public String type;
	public int col;
	public int line;

	public Variable(String type, String image, int col) {
		this.image = image;

		this.type = type;
		this.col = col;
	}

	public Variable(String type, String image) {
		this.image = image;

		this.type = type;
	}

	public Variable() {

	}

	@Override
	public String toString() {
		return "变量  (" + "   变量名:'" + image + '\'' + ",   类型='" + type + '\'' + ",   行=" + line + ",   列=" + col + ')';
	}
}
