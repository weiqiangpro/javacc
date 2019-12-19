package util;

import exception.SwitchException;

import java.util.ArrayList;
import java.util.List;

public class SwitchTable {
	private List<Variable> cases = new ArrayList<>();

	public void add(Variable v) {
		for (Variable c : cases) {
			if (c.image.equals(v.image))
				throw new SwitchException("字符" + v.image + "已在该switch-case结构中定义\n位置为第" + v.line + "行第" + v.col + "列");
		}
		cases.add(v);
	}
}
