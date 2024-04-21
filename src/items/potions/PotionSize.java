package items.potions;

public enum PotionSize {
	SMALL(10),
	MEDIUM(20),
	LARGE(30);

	private final int amount;

	PotionSize(int healAmount) {

		this.amount = healAmount;
	}

	public int getAmount() {

		return amount;
	}
}
