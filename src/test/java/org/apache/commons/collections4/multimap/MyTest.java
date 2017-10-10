package org.apache.commons.collections4.multimap;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.SetValuedMap;
import org.apache.commons.collections4.map.AbstractMapTest;
import org.junit.Test;

public class MyTest extends AbstractMapTest<String, Collection<String>> {

    public MyTest() {
        super("");
    }

    @Test
    public void testSomething() {
        assertTrue(getClass() != null);
    }

    @Override
    public Map<String, Collection<String>> makeObject() {
        return new HashSetValuedHashMap<String, String>().asMap();
    }

    @Override
    public Map<String, Collection<String>> makeConfirmedMap() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Collection<String>> makeFullMap() {
        return makeFullMap2().asMap();
    }

    protected MultiValuedMap<String, String> makeFullMap2() {
        final MultiValuedMap<String, String> map = new HashSetValuedHashMap<String, String>();
        addSampleMappings(map);
        return map;
    }

    protected void addSampleMappings(MultiValuedMap<? super String, ? super String> map) {
        final String[] keys = getSampleKeys();
        final String[] values = getSampleValues2();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
    }

    @Override
    public String[] getSampleKeys() {
        String[] samplekeys = getSampleKeys2();
        String[] finalKeys = new String[3];
        for (int i = 0; i < 3; i++) {
            finalKeys[i] = samplekeys[i * 2];
        }
        return (String[]) finalKeys;
    }

    public String[] getSampleKeys2() {
        final String[] result = new String[] {
                "one", "one", "two", "two",
                "three", "three"
        };
        return (String[]) result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<String>[] getSampleValues() {
        boolean isSetValuedMap = MyTest.this.getMap() instanceof SetValuedMap;
        String[] sampleValues = getSampleValues2();
        Collection<String>[] colArr = new Collection[3];
        for(int i = 0; i < 3; i++) {
            Collection<String> coll = Arrays.asList(sampleValues[i*2], sampleValues[i*2 + 1]);
            colArr[i] = isSetValuedMap ? new HashSet<>(coll) : coll;
        }
        return colArr;
    }

    public String[] getSampleValues2() {
        final String[] result = new String[] {
                "uno", "un", "dos", "deux",
                "tres", "trois"
        };
        return (String[]) result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<String>[] getNewSampleValues() {
        boolean isSetValuedMap = MyTest.this.getMap() instanceof SetValuedMap;
        Object[] sampleValues = { "ein", "ek", "zwei", "duey", "drei", "teen" };
        Collection<String>[] colArr = new Collection[3];
        for (int i = 0; i < 3; i++) {
            Collection<String> coll = Arrays.asList((String) sampleValues[i * 2], (String) sampleValues[i * 2 + 1]);
            colArr[i] = isSetValuedMap ? new HashSet<>(coll) : coll;
        }
        return colArr;
    }

    @Override
    public boolean isAllowNullKey() {
        return MyTest.this.isAllowNullKey();
    }

    @Override
    public boolean isPutAddSupported() {
        return false;
    }

    @Override
    public boolean isPutChangeSupported() {
        return false;
    }

    @Override
    public boolean isRemoveSupported() {
        return MyTest.this.isRemoveSupported();
    }
    
    @Override
    public boolean areEqualElementsDistinguishable() {
        // work-around for a problem with the EntrySet: the entries contain
        // the wrapped collection, which will be automatically cleared
        // when the associated key is removed from the map as the collection
        // is not cached atm.
        return true;
    }

    @Override
    public boolean isTestSerialization() {
        return false;
    }
}