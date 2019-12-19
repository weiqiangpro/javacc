package util;

import java.util.ArrayList;
import java.util.List;

import exception.VariableException;

public class Table {
	public String type;
	public String name;
	public int num;
	public int line;
	public int col;
	public List<Variable> tokens = new ArrayList<>();

	public void add(Variable v) {
		for (Variable token : tokens) {
			if (token.image.equals(v.image))
				throw new VariableException("变量" + v.image + "已定义,位置为第" + v.line + "行，第" + v.col + "列");
		}
		tokens.add(v);
	}

	public void print() {
		System.out.println(this.toString());
		for (Variable token : tokens) {
			System.out.println(token.toString());
		}
	}

	public String getMes() {
		String str = "";
		str += this.toString() + "\n";
		for (Variable token : tokens)
			str += token.toString() + "\n";
		return str;
	}

	public void con(Variable v) {
		if (Constants.isNum(v.image))
			return;
		for (Variable token : tokens) {
			if (token.image.equals(v.image)) {
				return;
			}
		}
		throw new VariableException("变量" + v.image + "未定义,位置为第" + v.line + "行，第" + v.col + "列");
	}

	@Override
	public String toString() {
		return "方法  (" + "  类型:'" + type + '\'' + ",    方法名:'" + name + '\'' + ",    参数个数:" + num + '\'' + ')';
	}
}
