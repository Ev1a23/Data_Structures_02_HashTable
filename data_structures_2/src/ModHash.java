// username1: yoavmalichi
// id1: 208392290
// name1: Yoav Malichi

// username2: eviatars
// id2: 322623182
// name2: Eviatar Shemesh

import java.util.concurrent.ThreadLocalRandom;

public class ModHash {
	private long a;
	private long b;
	private long p;
	private int m;

	private ModHash(int m, long p, long a, long b) {
		this.m = m;
		this.p = p;
		this.a = a;
		this.b = b;
	}

	public static ModHash GetFunc(int m, long p){
		long a = ThreadLocalRandom.current().nextLong(1, p);
		long b = ThreadLocalRandom.current().nextLong(0, p);
		return new ModHash(m, p, a, b);
	}

	public int Hash(long key) {
		return (int)(((this.a * key + this.b) % p) % m);
	}
}
