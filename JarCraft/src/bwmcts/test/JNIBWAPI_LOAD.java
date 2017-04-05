package bwmcts.test;

import jnibwapi.BWAPIEventListener;
import jnibwapi.JNIBWAPI;
import jnibwapi.types.BulletType;
import jnibwapi.types.DamageType;
import jnibwapi.types.ExplosionType;
import jnibwapi.types.OrderType;
import jnibwapi.types.TechType;
import jnibwapi.types.UnitCommandType;
import jnibwapi.types.UnitSizeType;
import jnibwapi.types.UnitType;
import jnibwapi.types.UpgradeType;
import jnibwapi.types.WeaponType;

import java.util.Collection;
import java.util.HashMap;

/**
 * JNI interface for the Brood War API.
 * <p/>
 * This focus of this interface is to provide the callback and game state query
 * functionality in BWAPI. Utility functions such as can buildHere have not
 * yet been implemented.
 * <p/>
 * Note: for thread safety and game state sanity, all native calls should be invoked from the callback methods.
 * <p/>
 * For BWAPI documentation see: http://code.google.com/p/bwapi/
 * <p/>
 * API Pages
 * Game: http://code.google.com/p/bwapi/wiki/Game
 * Unit: http://code.google.com/p/bwapi/wiki/Unit
 */
public class JNIBWAPI_LOAD extends JNIBWAPI {

    private HashMap<Integer, UnitType> unitTypes = new HashMap<Integer, UnitType>();
    private HashMap<Integer, TechType> techTypes = new HashMap<Integer, TechType>();
    private HashMap<Integer, UpgradeType> upgradeTypes = new HashMap<Integer, UpgradeType>();
    private HashMap<Integer, WeaponType> weaponTypes = new HashMap<Integer, WeaponType>();
    private HashMap<Integer, UnitSizeType> unitSizeTypes = new HashMap<Integer, UnitSizeType>();
    private HashMap<Integer, BulletType> bulletTypes = new HashMap<Integer, BulletType>();
    private HashMap<Integer, DamageType> damageTypes = new HashMap<Integer, DamageType>();
    private HashMap<Integer, ExplosionType> explosionTypes = new HashMap<Integer, ExplosionType>();
    private HashMap<Integer, UnitCommandType> unitCommandTypes = new HashMap<Integer, UnitCommandType>();
    private HashMap<Integer, OrderType> orderTypes = new HashMap<Integer, OrderType>();

    public JNIBWAPI_LOAD(BWAPIEventListener listener) {
        super(listener, false);

    }


    public void loadTypeData() {
        // unit types

        int[] unitTypesData1 = UnitTypeData1.getUnitTypes();
        int[] unitTypesData2 = UnitTypeData2.getUnitTypes();
        int[] unitTypeData = new int[unitTypesData1.length + unitTypesData2.length];
        System.arraycopy(unitTypesData1, 0, unitTypeData, 0, unitTypesData1.length);
        System.arraycopy(unitTypesData2, 0, unitTypeData, unitTypesData1.length, unitTypesData2.length);
        
        for (int index = 0; index < unitTypeData.length; index += UnitType.numAttributes) {
            UnitType type = new UnitType(index);
            type.initialize(unitTypeData, index, null, new int[0]);
            unitTypes.put(type.getID(), type);
        }

        // tech types
        int[] techTypeData = Data.getTechTypes();
        for (int index = 0; index < techTypeData.length; index += TechType.numAttributes) {
            TechType type = new TechType(index);
            type.initialize(techTypeData, index, null);
            techTypes.put(type.getID(), type);
        }

        // upgrade types
        int[] upgradeTypeData = Data.getUpgradeTypes();
        for (int index = 0; index < upgradeTypeData.length; index += UpgradeType.numAttributes) {
            UpgradeType type = new UpgradeType(index);
            type.initialize(upgradeTypeData, index, null);
            upgradeTypes.put(type.getID(), type);
        }

        // weapon types
        int[] weaponTypeData = Data.getWeaponTypes();
        for (int index = 0; index < weaponTypeData.length; index += WeaponType.numAttributes) {
            WeaponType type = new WeaponType(index);
            type.initialize(weaponTypeData, index, null);
            weaponTypes.put(type.getID(), type);
        }

        // unit size types
        int[] unitSizeTypeData = Data.getUnitSizeTypes();
        for (int index = 0; index < unitSizeTypeData.length; index += UnitSizeType.numAttributes) {
            UnitSizeType type = new UnitSizeType(index);
            type.initialize(unitSizeTypeData, index, null);
            unitSizeTypes.put(type.getID(), type);
        }

        // bullet types
        int[] bulletTypeData = Data.getBulletTypes();
        for (int index = 0; index < bulletTypeData.length; index += BulletType.numAttributes) {
            BulletType type = new BulletType(index);
            type.initialize(bulletTypeData, index, null);
            bulletTypes.put(type.getID(), type);
        }

        // damage types
        int[] damageTypeData = Data.getDamageTypes();
        for (int index = 0; index < damageTypeData.length; index += DamageType.numAttributes) {
            DamageType type = new DamageType(index);
            type.initialize(damageTypeData, index, null);
            damageTypes.put(type.getID(), type);
        }

        // explosion types
        int[] explosionTypeData = Data.getExplosionTypes();
        for (int index = 0; index < explosionTypeData.length; index += ExplosionType.numAttributes) {
            ExplosionType type = new ExplosionType(index);
            type.initialize(explosionTypeData, index, null);
            explosionTypes.put(type.getID(), type);
        }

        // unitCommand types
        int[] unitCommandData = Data.getUnitCommandTypes();
        for (int index = 0; index < unitCommandData.length; index += UnitCommandType.numAttributes) {
            UnitCommandType type = new UnitCommandType(index);
            type.initialize(unitCommandData, index, null);
            unitCommandTypes.put(type.getID(), type);
        }

        // order types
        int[] orderTypeData = Data.getOrderTypes();
        for (int index = 0; index < orderTypeData.length; index += OrderType.numAttributes) {
            OrderType type = new OrderType(index);
            type.initialize(orderTypeData, index, null);
            orderTypes.put(type.getID(), type);
        }
    }

    // type data accessors
    public UnitType getUnitType(int unitID) {
        return unitTypes.get(unitID);
    }

    public TechType getTechType(int techID) {
        return techTypes.get(techID);
    }

    public UpgradeType getUpgradeType(int upgradeID) {
        return upgradeTypes.get(upgradeID);
    }

    public WeaponType getWeaponType(int weaponID) {
        return weaponTypes.get(weaponID);
    }

    public UnitSizeType getUnitSizeType(int sizeID) {
        return unitSizeTypes.get(sizeID);
    }

    public BulletType getBulletType(int bulletID) {
        return bulletTypes.get(bulletID);
    }

    public DamageType getDamageType(int damageID) {
        return damageTypes.get(damageID);
    }

    public ExplosionType getExplosionType(int explosionID) {
        return explosionTypes.get(explosionID);
    }

    public UnitCommandType getUnitCommandType(int unitCommandID) {
        return unitCommandTypes.get(unitCommandID);
    }

    public OrderType getOrderType(int orderID) {
        return orderTypes.get(orderID);
    }

    public Collection<UnitType> unitTypes() {
        return unitTypes.values();
    }

    public Collection<TechType> techTypes() {
        return techTypes.values();
    }

    public Collection<UpgradeType> upgradeTypes() {
        return upgradeTypes.values();
    }

    public Collection<WeaponType> weaponTypes() {
        return weaponTypes.values();
    }

    public Collection<UnitSizeType> unitSizeTypes() {
        return unitSizeTypes.values();
    }

    public Collection<BulletType> bulletTypes() {
        return bulletTypes.values();
    }

    public Collection<DamageType> damageTypes() {
        return damageTypes.values();
    }

    public Collection<ExplosionType> explosionTypes() {
        return explosionTypes.values();
    }

    public Collection<UnitCommandType> unitCommandTypes() {
        return unitCommandTypes.values();
    }

    public Collection<OrderType> orderTypes() {
        return orderTypes.values();
    }

}