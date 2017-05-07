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
import java.util.List;

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

    private HashMap<Integer, UnitType> unitTypes = new HashMap<>();
    private HashMap<Integer, TechType> techTypes = new HashMap<>();
    private HashMap<Integer, UpgradeType> upgradeTypes = new HashMap<>();
    private HashMap<Integer, WeaponType> weaponTypes = new HashMap<>();
    private HashMap<Integer, UnitSizeType> unitSizeTypes = new HashMap<>();
    private HashMap<Integer, BulletType> bulletTypes = new HashMap<>();
    private HashMap<Integer, DamageType> damageTypes = new HashMap<>();
    private HashMap<Integer, ExplosionType> explosionTypes = new HashMap<>();
    private HashMap<Integer, UnitCommandType> unitCommandTypes = new HashMap<>();
    private HashMap<Integer, OrderType> orderTypes = new HashMap<>();

    public JNIBWAPI_LOAD(BWAPIEventListener listener) {
        super(listener, false, false);
    }

    public JNIBWAPI_LOAD() {
        super(new EmptyBWAPIEventListener(), false, false);
    }


    public void loadTypeData() {
        // unit types
        List<String> unitTypeJsons = loadFile(UNIT_TYPES_CLASS);
        for (String unitTypeJson : unitTypeJsons) {
            UnitType unitType = fromJson(unitTypeJson, UnitType.class);
            unitType.putIntoMap();
            unitTypes.put(unitType.getID(), unitType);
        }

        // tech types
        List<String> techTypeJsons = loadFile(TECH_TYPES_CLASS);
        for (String techTypeJson : techTypeJsons) {
            TechType techType = fromJson(techTypeJson, TechType.class);
            techType.putIntoMap();
            techTypes.put(techType.getID(), techType);
        }

        // upgrade types
        List<String> upgradeTypeJsons = loadFile(UPGRADE_TYPES_CLASS);
        for (String upgradeTypeJson : upgradeTypeJsons) {
            UpgradeType upgradeType = fromJson(upgradeTypeJson, UpgradeType.class);
            upgradeType.putIntoMap();
            upgradeTypes.put(upgradeType.getID(), upgradeType);
        }

        // weapon types
        List<String> weaponTypeJsons = loadFile(WEAPON_TYPES_CLASS);
        for (String weaponTypeJson : weaponTypeJsons) {
            WeaponType weaponType = fromJson(weaponTypeJson, WeaponType.class);
            weaponType.putIntoMap();
            weaponTypes.put(weaponType.getID(), weaponType);
        }

        // unit size types
        List<String> unitSizeTypeJsons = loadFile(UNIT_SIZE_TYPES_CLASS);
        for (String unitSizeTypeJson : unitSizeTypeJsons) {
            UnitSizeType unitSizeType = fromJson(unitSizeTypeJson, UnitSizeType.class);
            unitSizeType.putIntoMap();
            unitSizeTypes.put(unitSizeType.getID(), unitSizeType);
        }

        // bullet types
        List<String> bulletTypeJsons = loadFile(BULLET_TYPES_CLASS);
        for (String bulletTypeJson : bulletTypeJsons) {
            BulletType bulletType = fromJson(bulletTypeJson, BulletType.class);
            bulletType.putIntoMap();
            bulletTypes.put(bulletType.getID(), bulletType);
        }

        // damage types
        List<String> damageTypeJsons = loadFile(DAMAGE_TYPES_CLASS);
        for (String damageTypeJson : damageTypeJsons) {
            DamageType damageType = fromJson(damageTypeJson, DamageType.class);
            damageType.putIntoMap();
            damageTypes.put(damageType.getID(), damageType);
        }

        // explosion types
        List<String> explosionTypeJsons = loadFile(EXPLOSION_TYPES_CLASS);
        for (String explosionTypeJson : explosionTypeJsons) {
            ExplosionType explosionType = fromJson(explosionTypeJson, ExplosionType.class);
            explosionType.putIntoMap();
            explosionTypes.put(explosionType.getID(), explosionType);
        }

        // unitCommand types
        List<String> unitCommandTypeJsons = loadFile(UNIT_COMMAND_TYPES_CLASS);
        for (String unitCommandTypeJson : unitCommandTypeJsons) {
            UnitCommandType unitCommandType = fromJson(unitCommandTypeJson, UnitCommandType.class);
            unitCommandType.putIntoMap();
            unitCommandTypes.put(unitCommandType.getID(), unitCommandType);
        }

        // order types
        List<String> orderTypeJsons = loadFile(ORDER_TYPES_CLASS);
        for (String orderTypeJson : orderTypeJsons) {
            OrderType orderType = fromJson(orderTypeJson, OrderType.class);
            orderType.putIntoMap();
            orderTypes.put(orderType.getID(), orderType);
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