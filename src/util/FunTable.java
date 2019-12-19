package util;

import java.util.ArrayList;
import java.util.List;

import exception.FunException;

public class FunTable {
	public List<Table> tableList = new ArrayList<Table>();

	public void add(Table table) {

		for (Table table1 : tableList) {
			if (table1.name.equals(table.name))
				throw new FunException("方法" + table.name + "()已存在\n" + "已声明的方法在第" + table1.line + "行,第" + table1.col
						+ "列\n" + "该方法在第" + table.line + "行,第" + table.col + "列");
		}
		tableList.add(table);
	}

	public void print() {
		for (Table table : tableList) {
			table.print();
		}
	}

	public String getMes() {
		String str = "";
		for(int i = 0;i < tableList.size();i++) {
			if(i!=0)
				str +="\n";
			str += tableList.get(i).getMes();
		}
		return str;
	}

	public void con(Variable fun, int n) {
		for (Table table : tableList) {
			if (table.name.equals(fun.image)) {
				if (table.num != n)
					throw new FunException("方法" + fun.image + "()的参数个数出现错误,现为" + n + "个,应为" + table.num + "个,位置为第"
							+ fun.line + "行,第" + fun.col + "列");
				else
					return;
			}
		}
		throw new FunException("方法" + fun.image + "()未定义,位置为第" + fun.line + "行,第" + fun.col + "列");

	}
}
