// username1: yoavmalichi
// id1: 208392290
// name1: Yoav Malichi

// username2: eviatars
// id2: 322623182
// name2: Eviatar Shemesh

import java.util.Random;

public class DoubleHashTable extends OAHashTable {
	private ModHash h1;
	private ModHash h2;

	public DoubleHashTable(int m, long p) {
		super(m);
		this.h1 = ModHash.GetFunc(m, p);
		this.h2 = ModHash.GetFunc(m - 1, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return (int)(((long)this.h1.Hash(x) + ((long)i * ((long)this.h2.Hash(x) + 1))) % this.m);
	}
	
}
