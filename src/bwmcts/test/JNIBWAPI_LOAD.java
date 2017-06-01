package bwmcts.test;

import bwmcts.sparcraft.AnimationFrameData;
import bwmcts.sparcraft.PlayerProperties;
import bwmcts.sparcraft.UnitProperties;
import bwmcts.sparcraft.WeaponProperties;
import jnibwapi.BWAPIEventListener;
import jnibwapi.JNIBWAPI;
import jnibwapi.types.BulletType;
import jnibwapi.types.DamageType;
import jnibwapi.types.ExplosionType;
import jnibwapi.types.OrderType;
import jnibwapi.types.RaceType;
import jnibwapi.types.TechType;
import jnibwapi.types.UnitCommandType;
import jnibwapi.types.UnitSizeType;
import jnibwapi.types.UnitType;
import jnibwapi.types.UpgradeType;
import jnibwapi.types.WeaponType;

import static utils.FilePaths.BULLET_TYPES_CLASS;
import static utils.FilePaths.DAMAGE_TYPES_CLASS;
import static utils.FilePaths.EXPLOSION_TYPES_CLASS;
import static utils.FilePaths.ORDER_TYPES_CLASS;
import static utils.FilePaths.RACE_TYPES_CLASS;
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

    private static JNIBWAPI bwapi;

    public JNIBWAPI_LOAD(BWAPIEventListener listener) {
        super(listener, false, false);
    }

    public JNIBWAPI_LOAD() {
        super(new EmptyBWAPIEventListener(), false, false);
    }

    public static void initialize() {
        if (bwapi == null) {
            bwapi = new JNIBWAPI_LOAD();
            bwapi.loadTypeData();
            AnimationFrameData.Init();
            PlayerProperties.Init();
            WeaponProperties.Init(bwapi);
            UnitProperties.Init(bwapi);
        }
    }

    public void loadTypeData() {
        // race types
        String raceTypesJson = loadFile(RACE_TYPES_CLASS);
        RaceType[] raceTypes = fromJson(raceTypesJson, RaceType[].class);
        for (RaceType raceType : raceTypes) {
            int id = raceType.getID();
            RaceType.RaceTypes.getRaceType(id).initialize(raceType);
        }

        // unit types
        String unitTypesJson = loadFile(UNIT_TYPES_CLASS);
        UnitType[] unitTypes = fromJson(unitTypesJson, UnitType[].class);
        for (UnitType unitType : unitTypes) {
            int id = unitType.getID();
            UnitType.UnitTypes.getUnitType(id).initialize(unitType);
        }

        // tech types
        String techTypesJson = loadFile(TECH_TYPES_CLASS);
        TechType[] techTypes = fromJson(techTypesJson, TechType[].class);
        for (TechType techType : techTypes) {
            int id = techType.getID();
            TechType.TechTypes.getTechType(id).initialize(techType);
        }

        // upgrade types
        String upgradeTypesJson = loadFile(UPGRADE_TYPES_CLASS);
        UpgradeType[] upgradeTypes = fromJson(upgradeTypesJson, UpgradeType[].class);
        for (UpgradeType upgradeType : upgradeTypes) {
            int id = upgradeType.getID();
            UpgradeType.UpgradeTypes.getUpgradeType(id).initialize(upgradeType);
        }

        // weapon types
        String weaponTypesJson = loadFile(WEAPON_TYPES_CLASS);
        WeaponType[] weaponTypes = fromJson(weaponTypesJson, WeaponType[].class);
        for (WeaponType weaponType : weaponTypes) {
            int id = weaponType.getID();
            WeaponType.WeaponTypes.getWeaponType(id).initialize(weaponType);
        }

        // unitSize types
        String unitSizeTypesJson = loadFile(UNIT_SIZE_TYPES_CLASS);
        UnitSizeType[] unitSizeTypes = fromJson(unitSizeTypesJson, UnitSizeType[].class);
        for (UnitSizeType unitSizeType : unitSizeTypes) {
            int id = unitSizeType.getID();
            UnitSizeType.UnitSizeTypes.getUnitSizeType(id).initialize(unitSizeType);
        }

        // bullet types
        String bulletTypesJson = loadFile(BULLET_TYPES_CLASS);
        BulletType[] bulletTypes = fromJson(bulletTypesJson, BulletType[].class);
        for (BulletType bulletType : bulletTypes) {
            int id = bulletType.getID();
            BulletType.BulletTypes.getBulletType(id).initialize(bulletType);
        }

        // damage types
        String damageTypesJson = loadFile(DAMAGE_TYPES_CLASS);
        DamageType[] damageTypes = fromJson(damageTypesJson, DamageType[].class);
        for (DamageType damageType : damageTypes) {
            int id = damageType.getID();
            DamageType.DamageTypes.getDamageType(id).initialize(damageType);
        }

        // explosion types
        String explosionTypesJson = loadFile(EXPLOSION_TYPES_CLASS);
        ExplosionType[] explosionTypes = fromJson(explosionTypesJson, ExplosionType[].class);
        for (ExplosionType explosionType : explosionTypes) {
            int id = explosionType.getID();
            ExplosionType.ExplosionTypes.getExplosionType(id).initialize(explosionType);
        }

        // unitCommand types
        String unitCommandTypesJson = loadFile(UNIT_COMMAND_TYPES_CLASS);
        UnitCommandType[] unitCommandTypes = fromJson(unitCommandTypesJson, UnitCommandType[].class);
        for (UnitCommandType unitCommandType : unitCommandTypes) {
            int id = unitCommandType.getID();
            UnitCommandType.UnitCommandTypes.getUnitCommandType(id).initialize(unitCommandType);
        }

        // order types
        String orderTypesJson = loadFile(ORDER_TYPES_CLASS);
        OrderType[] orderTypes = fromJson(orderTypesJson, OrderType[].class);
        for (OrderType orderType : orderTypes) {
            int id = orderType.getID();
            OrderType.OrderTypes.getOrderType(id).initialize(orderType);
        }

        // event types - no extra data to load
    }
}