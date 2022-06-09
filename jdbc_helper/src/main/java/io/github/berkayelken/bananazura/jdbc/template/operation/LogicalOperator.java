package io.github.berkayelken.bananazura.jdbc.template.operation;

public enum LogicalOperator {
	EQUALS{
		public String getOperator() {
			return "=";
		}
	}, NOT_EQUAL{
		public String getOperator() {
			return "<>";
		}
	}, LESS_THAN {
		public String getOperator() {
			return "<";
		}
	}, LESS_THAN_OR_EQAUL{
		public String getOperator() {
			return "<=";
		}
	}, GREATER_THAN {
		public String getOperator() {
			return ">";
		}
	}, GREATER_THAN_OR_EQAUL{
		public String getOperator() {
			return ">=";
		}
	}, LIKE {
		public String getOperator() {
			return "LIKE";
		}
	};

	public abstract String getOperator();
}
