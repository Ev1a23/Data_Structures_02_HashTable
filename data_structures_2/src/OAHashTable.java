// username1: yoavmalichi
// id1: 208392290
// name1: Yoav Malichi

// username2: eviatars
// id2: 322623182
// name2: Eviatar Shemesh

public abstract class OAHashTable implements IHashTable {

	private final HashTableElement DELETED_HTE;

	private HashTableElement [] table;
	protected int m; // The table size (for easiness of accessing and similarity to formulas)

	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.m = m;
		this.DELETED_HTE = new HashTableElement(-1, -1);
	}
	
	
	@Override
	public HashTableElement Find(long key) {
		int i = 0;
		HashTableElement hte;
		while (i < this.m) {
			int j = this.Hash(key, i);
			hte = this.table[j];
			if (hte == null) {
				return null;
			}
			else {
				if (hte.GetKey() == key) {
					return hte;
				}
			}
			i += 1;
		}
		return null;
	}



	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		long key = hte.GetKey();
		if (Find(key) != null) {
			throw new KeyAlreadyExistsException(hte);
		}
		int i = 0;
		HashTableElement hteCurr;
		while(i < this.m)
		{
			int j = this.Hash(key, i);
			hteCurr = this.table[j];
			if (hteCurr == null || hteCurr.GetKey() == -1)
			{
				this.table[j] = hte;
				return;
			}
			i += 1;
		}
		throw new TableIsFullException(hte);
	}


	
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int i = 0;
		HashTableElement hte;
		while (i < this.m) {
			int j = this.Hash(key, i);
			hte = this.table[j];
			if (hte == null) {
				throw new KeyDoesntExistException(key);
			}
			else {
				if (hte.GetKey() == key) {
					this.table[j] = this.DELETED_HTE;
					return;
				}
			}
			i += 1;
		}
		throw new KeyDoesntExistException(key);
	}
	
	/**
	 * 
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);

}
