// username1: yoavmalichi
// id1: 208392290
// name1: Yoav Malichi

// username2: eviatars
// id2: 322623182
// name2: Eviatar Shemesh

import java.util.Random;

public class QPHashTable extends OAHashTable {
	private ModHash h;

	public QPHashTable(int m, long p) {
		super(m);
		this.h = ModHash.GetFunc(m, p);
	}
	
	@Override
	public int Hash(long x, int i) {
		return (int)(((long)(this.h.Hash(x)) + ((long)i * (long)i)) % this.m);
	}
}
