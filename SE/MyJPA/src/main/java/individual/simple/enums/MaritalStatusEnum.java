package individual.simple.enums;

public enum MaritalStatusEnum {
	SINGLE("S", "Single"), MARRIED("M", "Married");

	private String code;

	private String desc;

	private MaritalStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static MaritalStatusEnum valueOfCode(String code) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].code == code) {
				return values()[i];
			}
		}
		return null;
	}
}
