package pl.mobilla.plumber;

import java.util.Random;

public class PlumberLeaker {
    private static final Random random = new Random();
	private boolean nastyOne;
	
	public boolean isNastyOne() {
		return nastyOne;
	}

	private char[] holder;
	
	private PlumberLeaker(char[] holder, boolean nastyOne) {
		this.holder = holder;
		this.nastyOne = nastyOne;
	}

	static PlumberLeaker create() {
		return new PlumberLeaker(new char[1 + random.nextInt(65535)], random.nextBoolean());
	}
}
