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

import static utils.FilePaths.BULLET_TYPES_CLASS;
import static utils.FilePaths.DAMAGE_TYPES_CLASS;
import static utils.FilePaths.EXPLOSION_TYPES_CLASS;
import static utils.FilePaths.ORDER_TYPES_CLASS;
import static utils.FilePaths.TECH_TYPES_CLASS;
import static utils.FilePaths.UNIT_COMMAND_TYPES_CLASS;
import static utils.FilePaths.UNIT_SIZE_TYPES_CLASS;
import static utils.FilePaths.UNIT_TYPES_CLASS;
import static utils.FilePaths.UPGRADE_TYPES_CLASS;
import static utils.FilePaths.WEAPON_TYPES_CLASS;
import static utils.FileUtils.fromJson;
import static utils.FileUtils.loadFile;

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

    private HashMap<Integer, UnitType> unitTypesMap = new HashMap<>();
    private HashMap<Integer, TechType> techTypesMap = new HashMap<>();
    private HashMap<Integer, UpgradeType> upgradeTypesMap = new HashMap<>();
    private HashMap<Integer, WeaponType> weaponTypesMap = new HashMap<>();
    private HashMap<Integer, UnitSizeType> unitSizeTypesMap = new HashMap<>();
    private HashMap<Integer, BulletType> bulletTypesMap = new HashMap<>();
    private HashMap<Integer, DamageType> damageTypesMap = new HashMap<>();
    private HashMap<Integer, ExplosionType> explosionTypesMap = new HashMap<>();
    private HashMap<Integer, UnitCommandType> unitCommandTypesMap = new HashMap<>();
    private HashMap<Integer, OrderType> orderTypesMap = new HashMap<>();

    public JNIBWAPI_LOAD(BWAPIEventListener listener) {
        super(listener, false, false);
    }

    public JNIBWAPI_LOAD() {
        super(new EmptyBWAPIEventListener(), false, false);
    }


    public void loadTypeData() {
        // unit types
        String unitTypeJson = loadFile(UNIT_TYPES_CLASS);
        UnitType[] unitTypes = fromJson(unitTypeJson, UnitType[].class);
        for (UnitType unitType : unitTypes) {
            unitType.putIntoMap();
            unitTypesMap.put(unitType.getID(), unitType);
        }

        // tech types
        String techTypeJson = loadFile(TECH_TYPES_CLASS);
        TechType[] techTypes = fromJson(techTypeJson, TechType[].class);
        for (TechType techType : techTypes) {
            techType.putIntoMap();
            techTypesMap.put(techType.getID(), techType);
        }
        
        // upgrade types
        String upgradeTypeJson = loadFile(UPGRADE_TYPES_CLASS);
        UpgradeType[] upgradeTypes = fromJson(upgradeTypeJson, UpgradeType[].class);
        for (UpgradeType upgradeType : upgradeTypes) {
            upgradeType.putIntoMap();
            upgradeTypesMap.put(upgradeType.getID(), upgradeType);
        }

        // weapon types
        String weaponTypeJson = loadFile(WEAPON_TYPES_CLASS);
        WeaponType[] weaponTypes = fromJson(weaponTypeJson, WeaponType[].class);
        for (WeaponType weaponType : weaponTypes) {
            weaponType.putIntoMap();
            weaponTypesMap.put(weaponType.getID(), weaponType);
        }

        // unitSize types
        String unitSizeTypeJson = loadFile(UNIT_SIZE_TYPES_CLASS);
        UnitSizeType[] unitSizeTypes = fromJson(unitSizeTypeJson, UnitSizeType[].class);
        for (UnitSizeType unitSizeType : unitSizeTypes) {
            unitSizeType.putIntoMap();
            unitSizeTypesMap.put(unitSizeType.getID(), unitSizeType);
        }

        // bullet types
        String bulletTypeJson = loadFile(BULLET_TYPES_CLASS);
        BulletType[] bulletTypes = fromJson(bulletTypeJson, BulletType[].class);
        for (BulletType bulletType : bulletTypes) {
            bulletType.putIntoMap();
            bulletTypesMap.put(bulletType.getID(), bulletType);
        }

        // damage types
        String damageTypeJson = loadFile(DAMAGE_TYPES_CLASS);
        DamageType[] damageTypes = fromJson(damageTypeJson, DamageType[].class);
        for (DamageType damageType : damageTypes) {
            damageType.putIntoMap();
            damageTypesMap.put(damageType.getID(), damageType);
        }

        // explosion types
        String explosionTypeJson = loadFile(EXPLOSION_TYPES_CLASS);
        ExplosionType[] explosionTypes = fromJson(explosionTypeJson, ExplosionType[].class);
        for (ExplosionType explosionType : explosionTypes) {
            explosionType.putIntoMap();
            explosionTypesMap.put(explosionType.getID(), explosionType);
        }

        // unitCommand types
        String unitCommandTypeJson = loadFile(UNIT_COMMAND_TYPES_CLASS);
        UnitCommandType[] unitCommandTypes = fromJson(unitCommandTypeJson, UnitCommandType[].class);
        for (UnitCommandType unitCommandType : unitCommandTypes) {
            unitCommandType.putIntoMap();
            unitCommandTypesMap.put(unitCommandType.getID(), unitCommandType);
        }

        // order types
        String orderTypeJson = loadFile(ORDER_TYPES_CLASS);
        OrderType[] orderTypes = fromJson(orderTypeJson, OrderType[].class);
        for (OrderType orderType : orderTypes) {
            orderType.putIntoMap();
            orderTypesMap.put(orderType.getID(), orderType);
        }
    }

    // type data accessors
    public UnitType getUnitType(int unitID) {
        return unitTypesMap.get(unitID);
    }

    public TechType getTechType(int techID) {
        return techTypesMap.get(techID);
    }

    public UpgradeType getUpgradeType(int upgradeID) {
        return upgradeTypesMap.get(upgradeID);
    }

    public WeaponType getWeaponType(int weaponID) {
        return weaponTypesMap.get(weaponID);
    }

    public UnitSizeType getUnitSizeType(int sizeID) {
        return unitSizeTypesMap.get(sizeID);
    }

    public BulletType getBulletType(int bulletID) {
        return bulletTypesMap.get(bulletID);
    }

    public DamageType getDamageType(int damageID) {
        return damageTypesMap.get(damageID);
    }

    public ExplosionType getExplosionType(int explosionID) {
        return explosionTypesMap.get(explosionID);
    }

    public UnitCommandType getUnitCommandType(int unitCommandID) {
        return unitCommandTypesMap.get(unitCommandID);
    }

    public OrderType getOrderType(int orderID) {
        return orderTypesMap.get(orderID);
    }

    public Collection<UnitType> unitTypes() {
        return unitTypesMap.values();
    }

    public Collection<TechType> techTypes() {
        return techTypesMap.values();
    }

    public Collection<UpgradeType> upgradeTypes() {
        return upgradeTypesMap.values();
    }

    public Collection<WeaponType> weaponTypes() {
        return weaponTypesMap.values();
    }

    public Collection<UnitSizeType> unitSizeTypes() {
        return unitSizeTypesMap.values();
    }

    public Collection<BulletType> bulletTypes() {
        return bulletTypesMap.values();
    }

    public Collection<DamageType> damageTypes() {
        return damageTypesMap.values();
    }

    public Collection<ExplosionType> explosionTypes() {
        return explosionTypesMap.values();
    }

    public Collection<UnitCommandType> unitCommandTypes() {
        return unitCommandTypesMap.values();
    }

    public Collection<OrderType> orderTypes() {
        return orderTypesMap.values();
    }

}