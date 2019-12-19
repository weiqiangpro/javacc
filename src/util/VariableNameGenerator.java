package util;

public class VariableNameGenerator {

	private static final String VAR_PREFIX = "T";
	private static int sequenceId = 0;

	public static String genVariableName() {
		++sequenceId;
		return VAR_PREFIX + sequenceId;
	}

	public static void clear() {
		QTInfo.size = 0;
		sequenceId = 0;
	}
}
