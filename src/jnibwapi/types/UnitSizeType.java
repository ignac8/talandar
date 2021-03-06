package jnibwapi.types;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a StarCraft unit size type.
 * <p>
 * For a description of fields see: http://code.google.com/p/bwapi/wiki/UnitSizeType
 */
public class UnitSizeType {

    public static final int numAttributes = 1;
    private static Map<Integer, UnitSizeType> idToUnitSizeType = new LinkedHashMap<>();
    private String name;
    private int ID;

    public UnitSizeType(int ID) {
        this.ID = ID;
        idToUnitSizeType.put(ID, this);
    }

    public void initialize(int[] data, int index, String name) {
        if (ID != data[index++])
            throw new IllegalArgumentException();
        this.name = name;
    }

    public void initialize(UnitSizeType unitSizeType) {
        if (ID != unitSizeType.ID)
            throw new IllegalArgumentException();
        name = unitSizeType.name;
    }

    @Override
    public String toString() {
        return getName() + " (" + getID() + ")";
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public static class UnitSizeTypes {
        public static final UnitSizeType Independent = new UnitSizeType(0);
        public static final UnitSizeType Small = new UnitSizeType(1);
        public static final UnitSizeType Medium = new UnitSizeType(2);
        public static final UnitSizeType Large = new UnitSizeType(3);
        public static final UnitSizeType None = new UnitSizeType(4);
        public static final UnitSizeType Unknown = new UnitSizeType(5);

        public static UnitSizeType getUnitSizeType(int id) {
            return idToUnitSizeType.get(id);
        }

        public static Collection<UnitSizeType> getAllUnitSizeTypes() {
            return Collections.unmodifiableCollection(idToUnitSizeType.values());
        }
    }

}
