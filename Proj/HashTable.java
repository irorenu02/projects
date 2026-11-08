package Proj;

/**
 * Custom hash table with separate chaining for collision resolution.
 * Maps integer slotId → ParkingSlot.
 * No Java HashMap used.
 * Load-factor threshold: 0.75 → triggers rehash.
 */
public class HashTable {

    private static class Entry {
        int         key;
        ParkingSlot value;
        Entry       next;

        Entry(int key, ParkingSlot value, Entry next) {
            this.key   = key;
            this.value = value;
            this.next  = next;
        }
    }

    private Entry[] buckets;
    private int     count;
    private int     capacity;
    private static final double LOAD_FACTOR = 0.75;

    public HashTable(int initialCapacity) {
        capacity = initialCapacity;
        buckets  = new Entry[capacity];
        count    = 0;
    }

    /** Insert or update a slot record — O(1) average. */
    public void put(int slotId, ParkingSlot slot) {
        int idx = hash(slotId);
        for (Entry e = buckets[idx]; e != null; e = e.next) {
            if (e.key == slotId) { e.value = slot; return; }
        }
        buckets[idx] = new Entry(slotId, slot, buckets[idx]);
        count++;
        if ((double) count / capacity >= LOAD_FACTOR) rehash();
    }

    /** Retrieve a slot by ID — O(1) average. */
    public ParkingSlot get(int slotId) {
        int idx = hash(slotId);
        for (Entry e = buckets[idx]; e != null; e = e.next) {
            if (e.key == slotId) return e.value;
        }
        return null;
    }

    /** Remove a slot record — O(1) average. */
    public void remove(int slotId) {
        int idx = hash(slotId);
        Entry prev = null;
        Entry curr = buckets[idx];
        while (curr != null) {
            if (curr.key == slotId) {
                if (prev == null) buckets[idx] = curr.next;
                else              prev.next    = curr.next;
                count--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public int size() { return count; }

    // ── private helpers ────────────────────────────────────────────

    private int hash(int key) {
        // Multiplicative hashing — good distribution for integer keys
        int h = key * 0x9e3779b9;
        h = h ^ (h >>> 16);
        return Math.abs(h) % capacity;
    }

    private void rehash() {
        int      newCapacity = capacity * 2;
        Entry[]  newBuckets  = new Entry[newCapacity];
        for (int i = 0; i < capacity; i++) {
            for (Entry e = buckets[i]; e != null; e = e.next) {
                int newIdx = Math.abs(e.key * 0x9e3779b9 ^ ((e.key * 0x9e3779b9) >>> 16)) % newCapacity;
                newBuckets[newIdx] = new Entry(e.key, e.value, newBuckets[newIdx]);
            }
        }
        buckets  = newBuckets;
        capacity = newCapacity;
    }
}
