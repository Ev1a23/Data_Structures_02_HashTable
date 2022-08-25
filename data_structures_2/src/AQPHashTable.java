// username1: yoavmalichi
// id1: 208392290
// name1: Yoav Malichi

// username2: eviatars
// id2: 322623182
// name2: Eviatar Shemesh

import java.util.Random;


public class AQPHashTable extends OAHashTable {
	private ModHash h;

	public AQPHashTable(int m, long p) {
		super(m);
		this.h = ModHash.GetFunc(m,p);
	}
	
	@Override
	public int Hash(long x, int i) {
		if (i % 2 == 0) {
			return (int)((((long)this.h.Hash(x)+(long)Math.pow(i,2))%this.m));
		}
		else {
			return (int)((((long)this.h.Hash(x)-(long)Math.pow(i,2))%this.m + (long)this.m)%this.m);
		}

	}
}
