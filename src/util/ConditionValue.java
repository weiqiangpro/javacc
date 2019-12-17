package util;

import java.util.ArrayList;

public class ConditionValue {
	public ArrayList<QTInfo> trueChain = new ArrayList<QTInfo>();
	public ArrayList<QTInfo> falseChain = new ArrayList<QTInfo>();
	
	public void mergeTrue(QTInfo qtTrue){
		trueChain.add(qtTrue);
	}
	public void mergeFalse(QTInfo qtFalse){
		falseChain.add(qtFalse);
	}
	
	public void mergeTrue(ConditionValue cValue1){
		trueChain.addAll(cValue1.trueChain);
		
	}
	
	public void mergeFalse(ConditionValue cValue1){
		falseChain.addAll(cValue1.falseChain);
		
	}
	
	public void backpatchTrueChain(int result){
		for(QTInfo info : trueChain) 
			info.setResult(result);
	}
	public void backpatchFalseChain(int result){
		for(QTInfo info : falseChain) 
			info.setResult(result);
	}
}
