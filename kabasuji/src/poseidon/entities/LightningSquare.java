package poseidon.entities;

public class LightningSquare extends Square{

	LightningSquare(Boolean isFilled) {
		super(isFilled);
	}
	void fillSquare() {
		this.isFilled = true;
	}

}
