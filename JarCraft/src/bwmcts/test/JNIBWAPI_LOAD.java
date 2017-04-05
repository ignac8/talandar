package bwmcts.test;

import jnibwapi.BWAPIEventListener;
import jnibwapi.JNIBWAPI;
import jnibwapi.types.BulletType;
import jnibwapi.types.DamageType;
import jnibwapi.types.DamageType.DamageTypes;
import jnibwapi.types.ExplosionType;
import jnibwapi.types.ExplosionType.ExplosionTypes;
import jnibwapi.types.OrderType;
import jnibwapi.types.RaceType;
import jnibwapi.types.TechType;
import jnibwapi.types.TechType.TechTypes;
import jnibwapi.types.UnitCommandType;
import jnibwapi.types.UnitSizeType;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;
import jnibwapi.types.UpgradeType;
import jnibwapi.types.UpgradeType.UpgradeTypes;
import jnibwapi.types.WeaponType;
import jnibwapi.types.WeaponType.WeaponTypes;

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

    public int[] getUnitTypes() {


        int[] data = new int[]{
                0, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Barracks.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 40, 0, 0, 0, 50, 0, 360, 2, 0, 1, 0, 50, 100, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 9, 8, 10, 128, 224, WeaponTypes.Gauss_Rifle.getID(), 1, WeaponTypes.Gauss_Rifle.getID(), 1, 400, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                20, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 200, 0, 0, 3, 50, 0, 1, 0, 0, 1, 0, 0, 200, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 9, 8, 10, 128, 224, WeaponTypes.Gauss_Rifle_Jim_Raynor.getID(), 1, WeaponTypes.Gauss_Rifle_Jim_Raynor.getID(), 1, 400, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Barracks.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 45, 0, 200, 0, 25, 75, 750, 2, 0, 1, 0, 175, 350, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 7, 10, 7, 11, 224, 288, WeaponTypes.C_10_Canister_Rifle.getID(), 1, WeaponTypes.C_10_Canister_Rifle.getID(), 1, 400, 1, 1, 40, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                16, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 250, 0, 250, 3, 50, 150, 1500, 0, 0, 1, 0, 0, 700, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 7, 10, 7, 11, 224, 352, WeaponTypes.C_10_Canister_Rifle_Sarah_Kerrigan.getID(), 1, WeaponTypes.C_10_Canister_Rifle_Sarah_Kerrigan.getID(), 1, 400, 1, 1, 40, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                99, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 200, 0, 250, 2, 200, 75, 1500, 0, 0, 1, 0, 0, 700, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 7, 10, 7, 11, 224, 320, WeaponTypes.C_10_Canister_Rifle_Samir_Duran.getID(), 1, WeaponTypes.C_10_Canister_Rifle_Samir_Duran.getID(), 1, 400, 1, 1, 40, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                104, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Carapace.getID(), 300, 0, 250, 3, 200, 75, 1500, 0, 0, 1, 0, 0, 700, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 7, 10, 7, 11, 224, 352, WeaponTypes.C_10_Canister_Rifle_Infested_Duran.getID(), 1, WeaponTypes.C_10_Canister_Rifle_Infested_Duran.getID(), 1, 400, 1, 1, 40, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                100, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 250, 0, 250, 3, 200, 75, 1500, 0, 0, 1, 0, 0, 700, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 7, 10, 7, 11, 224, 352, WeaponTypes.C_10_Canister_Rifle_Alexei_Stukov.getID(), 1, WeaponTypes.C_10_Canister_Rifle_Alexei_Stukov.getID(), 1, 400, 1, 1, 40, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                2, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Factory.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 80, 0, 0, 0, 75, 0, 450, 4, 0, 2, 0, 75, 150, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 16, 16, 15, 15, 160, 256, WeaponTypes.Fragmentation_Grenade.getID(), 1, WeaponTypes.None.getID(), 0, 640, 100, 14569, 40, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                19, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 300, 0, 0, 3, 150, 0, 900, 0, 0, 2, 0, 0, 300, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 16, 16, 15, 15, 160, 256, WeaponTypes.Fragmentation_Grenade_Jim_Raynor.getID(), 1, WeaponTypes.None.getID(), 0, 640, 100, 14569, 40, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                3, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Factory.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 125, 0, 0, 1, 100, 50, 600, 4, 0, 2, 0, 200, 400, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 192, 256, WeaponTypes.Twin_Autocannons.getID(), 1, WeaponTypes.Hellfire_Missile_Pack.getID(), 1, 457, 1, 1, 17, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                17, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 300, 0, 0, 3, 200, 100, 1200, 0, 0, 2, 0, 0, 800, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 192, 256, WeaponTypes.Twin_Autocannons_Alan_Schezar.getID(), 1, WeaponTypes.Hellfire_Missile_Pack_Alan_Schezar.getID(), 1, 457, 1, 1, 17, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                5, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Factory.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 150, 0, 0, 1, 150, 100, 750, 4, 0, 4, 0, 350, 700, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 256, 320, WeaponTypes.Arclite_Cannon.getID(), 1, WeaponTypes.None.getID(), 0, 400, 1, 1, 13, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                23, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 400, 0, 0, 3, 300, 200, 1500, 0, 0, 4, 0, 0, 1400, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 256, 320, WeaponTypes.Arclite_Cannon_Edmund_Duke.getID(), 1, WeaponTypes.None.getID(), 0, 400, 1, 1, 13, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                7, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Command_Center.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 60, 0, 0, 0, 50, 0, 300, 2, 0, 1, 0, 50, 100, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 11, 11, 11, 11, 32, 224, WeaponTypes.Fusion_Cutter.getID(), 1, WeaponTypes.None.getID(), 0, 492, 67, 12227, 40, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                8, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Starport.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 120, 0, 200, 0, 150, 100, 900, 4, 0, 255, 0, 400, 800, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 19, 15, 18, 14, 160, 224, WeaponTypes.Burst_Lasers.getID(), 1, WeaponTypes.Gemini_Missiles.getID(), 1, 667, 67, 21745, 40, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                21, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 500, 0, 250, 4, 400, 200, 1800, 0, 0, 255, 0, 0, 1600, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 19, 15, 18, 14, 160, 224, WeaponTypes.Burst_Lasers_Tom_Kazansky.getID(), 1, WeaponTypes.Gemini_Missiles_Tom_Kazansky.getID(), 1, 667, 67, 21745, 40, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                9, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Starport.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 200, 0, 200, 1, 100, 225, 1200, 4, 0, 255, 0, 625, 1250, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 32, 33, 32, 16, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 500, 50, 5120, 40, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                22, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 800, 0, 250, 4, 50, 600, 2400, 0, 0, 255, 0, 0, 2500, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 32, 33, 32, 16, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 500, 50, 5120, 40, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                11, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Starport.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 150, 0, 0, 1, 100, 100, 750, 4, 0, 255, 8, 300, 600, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 24, 16, 24, 20, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 547, 17, 37756, 20, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                12, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Starport.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 500, 0, 200, 3, 400, 300, 2000, 12, 0, 255, 0, 1200, 2400, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 37, 29, 37, 29, 192, 352, WeaponTypes.ATS_Laser_Battery.getID(), 1, WeaponTypes.ATA_Laser_Battery.getID(), 1, 250, 27, 7585, 20, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                27, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 1000, 0, 250, 4, 800, 600, 4800, 0, 0, 255, 0, 0, 4800, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 37, 29, 37, 29, 192, 256, WeaponTypes.ATS_Laser_Battery_Hero.getID(), 1, WeaponTypes.ATA_Laser_Battery_Hero.getID(), 1, 250, 27, 7585, 20, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                28, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 850, 0, 250, 4, 800, 600, 2400, 0, 0, 255, 0, 0, 4800, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 37, 29, 37, 29, 192, 352, WeaponTypes.ATS_Laser_Battery_Hyperion.getID(), 1, WeaponTypes.ATA_Laser_Battery_Hyperion.getID(), 1, 250, 27, 7585, 20, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                29, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 700, 0, 250, 4, 800, 600, 4800, 0, 0, 255, 0, 0, 4800, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 37, 29, 37, 29, 192, 352, WeaponTypes.ATS_Laser_Battery_Hero.getID(), 1, WeaponTypes.ATA_Laser_Battery_Hero.getID(), 1, 250, 27, 7585, 20, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                102, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 700, 0, 250, 4, 800, 600, 4800, 0, 0, 255, 0, 0, 4800, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 37, 29, 37, 29, 192, 352, WeaponTypes.ATS_Laser_Battery_Hero.getID(), 1, WeaponTypes.ATA_Laser_Battery_Hero.getID(), 1, 250, 27, 7585, 20, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                13, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 20, 0, 0, 0, 1, 0, 1, 0, 0, 255, 0, 0, 25, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 7, 7, 7, 7, 96, 96, WeaponTypes.Spider_Mines.getID(), 1, WeaponTypes.None.getID(), 0, 160, 1, 1, 127, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                14, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Nuclear_Silo.getID(), UpgradeTypes.None.getID(), 100, 0, 0, 0, 200, 200, 1500, 16, 0, 255, 0, 800, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 7, 14, 7, 14, 0, 96, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 3333, 33, 1103213, 127, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                30, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Factory.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 150, 0, 0, 1, 150, 100, 750, 4, 0, 255, 0, 0, 700, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 384, 320, WeaponTypes.Arclite_Shock_Cannon.getID(), 1, WeaponTypes.None.getID(), 0, 0, 1, 1, 40, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                25, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Vehicle_Plating.getID(), 400, 0, 0, 3, 300, 200, 1500, 0, 0, 255, 0, 0, 1400, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 384, 320, WeaponTypes.Arclite_Shock_Cannon_Edmund_Duke.getID(), 1, WeaponTypes.None.getID(), 0, 0, 1, 1, 40, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                32, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Barracks.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 50, 0, 0, 1, 50, 25, 360, 2, 0, 1, 0, 100, 200, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 11, 7, 11, 14, 96, 224, WeaponTypes.Flame_Thrower.getID(), 3, WeaponTypes.None.getID(), 0, 4, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                10, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 160, 0, 0, 3, 100, 50, 720, 0, 0, 1, 0, 0, 400, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 11, 7, 11, 14, 96, 224, WeaponTypes.Flame_Thrower_Gui_Montag.getID(), 3, WeaponTypes.None.getID(), 0, 4, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                33, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 0, 0, 0, 0, 0, 0, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 13, 13, 13, 17, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                34, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Barracks.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 60, 0, 200, 1, 50, 25, 450, 2, 0, 1, 0, 125, 250, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 9, 8, 10, 288, 288, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 40, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                15, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.Terran_Infantry_Armor.getID(), 40, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 9, 8, 10, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 40, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                35, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Hatchery.getID(), UpgradeTypes.Zerg_Carapace.getID(), 25, 0, 0, 10, 1, 1, 1, 0, 0, 255, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 8, 7, 7, 0, 128, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 1, 1, 20, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                36, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Carapace.getID(), 200, 0, 0, 10, 1, 1, 1, 0, 0, 255, 0, 0, 25, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 16, 16, 15, 15, 0, 128, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                37, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Carapace.getID(), 35, 0, 0, 0, 50, 0, 420, 1, 0, 1, 0, 25, 50, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 4, 7, 11, 96, 160, WeaponTypes.Claws.getID(), 1, WeaponTypes.None.getID(), 0, 549, 1, 1, 27, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                54, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Carapace.getID(), 120, 0, 0, 3, 100, 0, 840, 0, 0, 1, 0, 0, 100, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 4, 7, 11, 96, 160, WeaponTypes.Claws_Devouring_One.getID(), 1, WeaponTypes.None.getID(), 0, 549, 1, 1, 27, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                51, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Carapace.getID(), 400, 0, 250, 2, 200, 300, 1500, 0, 0, 1, 0, 0, 4000, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 7, 10, 7, 11, 96, 288, WeaponTypes.Claws_Infested_Kerrigan.getID(), 1, WeaponTypes.None.getID(), 0, 400, 1, 1, 40, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                38, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Carapace.getID(), 80, 0, 0, 0, 75, 25, 420, 2, 0, 2, 0, 125, 350, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 10, 10, 10, 12, 128, 192, WeaponTypes.Needle_Spines.getID(), 1, WeaponTypes.Needle_Spines.getID(), 1, 366, 1, 1, 27, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                53, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Carapace.getID(), 160, 0, 0, 2, 150, 50, 780, 0, 0, 2, 0, 0, 500, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 10, 10, 10, 12, 128, 256, WeaponTypes.Needle_Spines_Hunter_Killer.getID(), 1, WeaponTypes.Needle_Spines_Hunter_Killer.getID(), 1, 366, 1, 1, 27, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                39, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Carapace.getID(), 400, 0, 0, 1, 200, 200, 900, 8, 0, 4, 0, 650, 1300, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 19, 16, 18, 15, 96, 224, WeaponTypes.Kaiser_Blades.getID(), 1, WeaponTypes.None.getID(), 0, 512, 1, 1, 40, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                48, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Carapace.getID(), 800, 0, 0, 4, 400, 400, 1800, 0, 0, 4, 0, 0, 2600, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 19, 16, 18, 15, 96, 224, WeaponTypes.Kaiser_Blades_Torrasque.getID(), 1, WeaponTypes.None.getID(), 0, 512, 1, 1, 40, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                40, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Carapace.getID(), 30, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 25, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 9, 9, 9, 9, 96, 160, WeaponTypes.Toxic_Spores.getID(), 1, WeaponTypes.None.getID(), 0, 600, 1, 1, 27, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                41, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Carapace.getID(), 40, 0, 0, 0, 50, 0, 300, 2, 0, 1, 0, 50, 100, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 11, 11, 11, 11, 32, 224, WeaponTypes.Spines.getID(), 1, WeaponTypes.None.getID(), 0, 492, 67, 12227, 40, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                42, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 200, 0, 0, 0, 100, 0, 600, 0, 16, 255, 8, 100, 200, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 25, 25, 24, 24, 0, 288, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 83, 27, 840, 20, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                57, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 1000, 0, 0, 4, 200, 0, 1200, 0, 60, 255, 8, 0, 400, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 25, 25, 24, 24, 0, 352, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 83, 27, 840, 20, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                43, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 120, 0, 0, 0, 100, 100, 600, 4, 0, 255, 0, 300, 600, UnitSizeType.UnitSizeTypes.Small.getID(), 2, 2, 22, 22, 21, 21, 96, 224, WeaponTypes.Glave_Wurm.getID(), 1, WeaponTypes.Glave_Wurm.getID(), 1, 667, 67, 21745, 40, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                55, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 300, 0, 0, 3, 200, 200, 1200, 0, 0, 255, 0, 0, 1200, UnitSizeType.UnitSizeTypes.Small.getID(), 2, 2, 22, 22, 21, 21, 96, 224, WeaponTypes.Glave_Wurm_Kukulza.getID(), 1, WeaponTypes.Glave_Wurm_Kukulza.getID(), 1, 667, 67, 21745, 40, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                44, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Mutalisk.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 150, 0, 0, 2, 50, 100, 600, 4, 0, 255, 0, 550, 1100, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 22, 22, 21, 21, 256, 352, WeaponTypes.Acid_Spore.getID(), 1, WeaponTypes.None.getID(), 0, 250, 27, 7585, 20, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                56, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 400, 0, 0, 4, 100, 200, 1200, 0, 0, 255, 0, 0, 2200, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 22, 22, 21, 21, 256, 352, WeaponTypes.Acid_Spore_Kukulza.getID(), 1, WeaponTypes.None.getID(), 0, 250, 27, 7585, 20, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                45, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 120, 0, 200, 0, 100, 100, 750, 4, 0, 255, 0, 400, 800, UnitSizeType.UnitSizeTypes.Medium.getID(), 2, 2, 24, 24, 23, 23, 256, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 667, 67, 21745, 40, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                49, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 300, 0, 250, 3, 200, 300, 1500, 0, 0, 255, 0, 0, 1600, UnitSizeType.UnitSizeTypes.Medium.getID(), 2, 2, 24, 24, 23, 23, 256, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 667, 67, 21745, 40, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                46, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Carapace.getID(), 80, 0, 200, 1, 50, 150, 750, 4, 0, 2, 0, 225, 450, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 13, 12, 13, 12, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 27, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                52, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.Zerg_Carapace.getID(), 250, 0, 250, 3, 50, 200, 1500, 0, 0, 2, 0, 0, 900, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 13, 12, 13, 12, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 27, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                47, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Larva.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 25, 0, 0, 0, 25, 75, 450, 1, 0, 255, 0, 100, 200, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 12, 12, 11, 11, 96, 160, WeaponTypes.None.getID(), 0, WeaponTypes.Suicide_Scourge.getID(), 1, 667, 107, 13616, 40, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                50, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Infested_Command_Center.getID(), UpgradeTypes.Zerg_Carapace.getID(), 60, 0, 0, 0, 100, 50, 600, 2, 0, 1, 0, 200, 400, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 9, 8, 10, 96, 160, WeaponTypes.Suicide_Infested_Terran.getID(), 1, WeaponTypes.None.getID(), 0, 582, 1, 1, 40, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                58, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Starport.getID(), UpgradeTypes.Terran_Ship_Plating.getID(), 200, 0, 0, 2, 250, 125, 750, 6, 0, 255, 0, 400, 800, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 24, 16, 24, 20, 192, 256, WeaponTypes.None.getID(), 0, WeaponTypes.Halo_Rockets.getID(), 4, 660, 65, 21901, 30, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                59, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Mutalisk.getID(), UpgradeTypes.Zerg_Carapace.getID(), 200, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 1100, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 0, 128, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                60, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Stargate.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 100, 80, 200, 1, 150, 100, 600, 4, 0, 255, 0, 350, 700, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 18, 16, 17, 15, 288, 288, WeaponTypes.None.getID(), 0, WeaponTypes.Neutron_Flare.getID(), 1, 667, 67, 17067, 30, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                98, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 100, 60, 250, 0, 150, 100, 750, 0, 0, 255, 0, 0, 1300, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 18, 16, 17, 15, 288, 288, WeaponTypes.None.getID(), 0, WeaponTypes.Neutron_Flare.getID(), 1, 667, 67, 17067, 30, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                61, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Gateway.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 80, 40, 0, 1, 125, 100, 750, 4, 0, 2, 0, 325, 650, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 12, 6, 11, 19, 96, 224, WeaponTypes.Warp_Blades.getID(), 1, WeaponTypes.None.getID(), 0, 492, 27, 13474, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                74, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 40, 80, 0, 0, 150, 150, 750, 1, 0, 2, 0, 0, 400, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 12, 6, 11, 19, 96, 224, WeaponTypes.Warp_Blades_Hero.getID(), 1, WeaponTypes.None.getID(), 0, 492, 27, 13474, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                75, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 60, 400, 0, 0, 100, 300, 1500, 0, 0, 2, 0, 0, 800, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 12, 6, 11, 19, 96, 224, WeaponTypes.Warp_Blades_Zeratul.getID(), 1, WeaponTypes.None.getID(), 0, 492, 27, 13474, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                62, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Mutalisk.getID(), UpgradeTypes.Zerg_Flyer_Carapace.getID(), 250, 0, 0, 2, 150, 50, 600, 4, 0, 255, 0, 550, 1100, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 22, 22, 21, 21, 224, 320, WeaponTypes.None.getID(), 0, WeaponTypes.Corrosive_Acid.getID(), 1, 500, 48, 17067, 30, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                63, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Dark_Templar.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 25, 200, 200, 1, 0, 0, 300, 8, 0, 4, 0, 650, 1300, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 224, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 492, 160, 5120, 40, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                64, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Nexus.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 20, 20, 0, 0, 50, 0, 300, 2, 0, 1, 0, 50, 100, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 11, 11, 11, 11, 32, 256, WeaponTypes.Particle_Beam.getID(), 1, WeaponTypes.None.getID(), 0, 492, 67, 12227, 40, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                65, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Gateway.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 100, 60, 0, 1, 100, 0, 600, 4, 0, 2, 0, 100, 200, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 11, 5, 11, 13, 96, 224, WeaponTypes.Psi_Blades.getID(), 2, WeaponTypes.None.getID(), 0, 400, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                77, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 240, 240, 0, 2, 200, 0, 1200, 0, 0, 2, 0, 0, 400, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 11, 5, 11, 13, 96, 224, WeaponTypes.Psi_Blades_Fenix.getID(), 2, WeaponTypes.None.getID(), 0, 400, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                66, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Gateway.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 100, 80, 0, 1, 125, 50, 750, 4, 0, 4, 0, 250, 500, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 15, 15, 16, 16, 128, 256, WeaponTypes.Phase_Disruptor.getID(), 1, WeaponTypes.Phase_Disruptor.getID(), 1, 500, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                78, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 240, 240, 0, 3, 300, 100, 1500, 0, 0, 4, 0, 0, 1000, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 15, 15, 16, 16, 128, 256, WeaponTypes.Phase_Disruptor_Fenix.getID(), 1, WeaponTypes.Phase_Disruptor_Fenix.getID(), 1, 500, 1, 1, 40, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                67, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Gateway.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 40, 40, 200, 0, 50, 150, 750, 4, 0, 2, 0, 350, 700, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 12, 10, 11, 13, 96, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 320, 27, 13474, 40, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                79, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 80, 300, 250, 2, 100, 300, 1500, 0, 0, 2, 0, 0, 1400, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 12, 10, 11, 13, 96, 224, WeaponTypes.Psi_Assault.getID(), 1, WeaponTypes.None.getID(), 0, 320, 27, 13474, 40, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                87, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 80, 300, 250, 2, 100, 300, 1500, 0, 0, 2, 0, 0, 1400, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 12, 10, 11, 13, 96, 224, WeaponTypes.Psi_Assault.getID(), 1, WeaponTypes.None.getID(), 0, 320, 27, 13474, 40, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                68, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_High_Templar.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 10, 350, 0, 0, 0, 0, 300, 8, 0, 4, 0, 700, 1400, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 96, 256, WeaponTypes.Psionic_Shockwave.getID(), 1, WeaponTypes.Psionic_Shockwave.getID(), 1, 492, 160, 5120, 40, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                76, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 100, 800, 0, 3, 0, 0, 600, 0, 0, 4, 0, 0, 2800, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 96, 256, WeaponTypes.Psionic_Shockwave_Tassadar_Zeratul_Archon.getID(), 1, WeaponTypes.Psionic_Shockwave_Tassadar_Zeratul_Archon.getID(), 1, 492, 160, 5120, 40, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                69, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Robotics_Facility.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 80, 60, 0, 1, 200, 0, 900, 4, 0, 255, 8, 200, 400, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 1, 20, 16, 19, 15, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 443, 17, 37756, 20, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                70, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Stargate.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 150, 100, 0, 0, 275, 125, 1200, 6, 0, 255, 0, 650, 1300, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 1, 18, 16, 17, 15, 128, 256, WeaponTypes.Dual_Photon_Blasters.getID(), 1, WeaponTypes.Anti_Matter_Missiles.getID(), 1, 500, 48, 17067, 30, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                80, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 400, 400, 0, 3, 600, 300, 2400, 0, 0, 255, 0, 0, 2600, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 1, 18, 16, 17, 15, 128, 320, WeaponTypes.Dual_Photon_Blasters_Mojo.getID(), 1, WeaponTypes.Anti_Matter_Missiles_Mojo.getID(), 1, 500, 48, 17067, 30, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                88, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 250, 250, 0, 3, 600, 300, 2400, 0, 0, 255, 0, 0, 2400, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 1, 18, 16, 17, 15, 128, 320, WeaponTypes.Dual_Photon_Blasters_Artanis.getID(), 1, WeaponTypes.Anti_Matter_Missiles_Artanis.getID(), 1, 500, 48, 17067, 30, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                71, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Stargate.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 200, 150, 200, 1, 100, 350, 2400, 8, 0, 255, 0, 1025, 2050, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 22, 22, 21, 21, 160, 288, WeaponTypes.Phase_Disruptor_Cannon.getID(), 1, WeaponTypes.Phase_Disruptor_Cannon.getID(), 1, 500, 33, 24824, 40, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                86, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 600, 500, 250, 3, 50, 1000, 4800, 0, 0, 255, 0, 0, 4100, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 22, 22, 21, 21, 160, 288, WeaponTypes.Phase_Disruptor_Cannon_Danimoth.getID(), 1, WeaponTypes.Phase_Disruptor_Cannon_Danimoth.getID(), 1, 500, 33, 24824, 40, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                72, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Stargate.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 300, 150, 0, 4, 350, 250, 2100, 12, 0, 255, 0, 950, 1900, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 32, 32, 31, 31, 256, 352, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 333, 27, 13474, 20, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                82, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 800, 500, 0, 4, 700, 600, 4200, 0, 0, 255, 0, 0, 3800, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 32, 32, 31, 31, 256, 288, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 333, 27, 13474, 20, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                73, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Carrier.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 40, 40, 0, 0, 25, 0, 300, 0, 0, 255, 0, 30, 60, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 8, 8, 7, 7, 128, 192, WeaponTypes.Pulse_Cannon.getID(), 1, WeaponTypes.Pulse_Cannon.getID(), 1, 1333, 427, 13640, 40, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                83, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Robotics_Facility.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 100, 80, 0, 0, 200, 100, 1050, 8, 0, 4, 0, 400, 800, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 256, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 178, 1, 1, 20, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                81, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 200, 400, 0, 3, 400, 200, 1800, 0, 0, 4, 0, 0, 1600, UnitSizeType.UnitSizeTypes.Large.getID(), 1, 1, 16, 16, 15, 15, 256, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 178, 1, 1, 20, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                84, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Robotics_Facility.getID(), UpgradeTypes.Protoss_Air_Armor.getID(), 40, 20, 0, 0, 25, 75, 600, 2, 0, 255, 0, 225, 450, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 16, 16, 15, 15, 0, 288, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 333, 27, 13474, 20, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                85, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Reaver.getID(), UpgradeTypes.Protoss_Ground_Armor.getID(), 20, 10, 0, 0, 15, 0, 105, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 2, 2, 2, 2, 128, 160, WeaponTypes.Scarab.getID(), 1, WeaponTypes.None.getID(), 0, 1600, 1, 1, 27, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                89, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 60, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 16, 16, 15, 15, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 27, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                90, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 60, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 16, 16, 15, 15, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 27, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                93, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 60, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 16, 16, 15, 15, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 27, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                94, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 60, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 16, 16, 15, 15, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 500, 16, 51200, 14, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                95, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 60, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 16, 16, 15, 15, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 27, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                96, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 60, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 10, UnitSizeType.UnitSizeTypes.Small.getID(), 1, 1, 16, 16, 15, 15, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 400, 1, 1, 27, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                97, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Hydralisk.getID(), UpgradeTypes.Zerg_Carapace.getID(), 200, 0, 0, 10, 1, 1, 1, 0, 0, 255, 0, 0, 500, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 16, 16, 15, 15, 0, 128, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                103, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Hydralisk.getID(), UpgradeTypes.Zerg_Carapace.getID(), 125, 0, 0, 1, 50, 100, 600, 4, 0, 4, 0, 250, 500, UnitSizeType.UnitSizeTypes.Medium.getID(), 1, 1, 15, 15, 16, 16, 192, 256, WeaponTypes.Subterranean_Spines.getID(), 1, WeaponTypes.None.getID(), 0, 582, 1, 1, 40, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                105, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 800, 0, 0, 0, 250, 250, 2400, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 4, 3, 60, 40, 59, 39, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                106, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 1500, 0, 0, 1, 400, 0, 1800, 0, 20, 255, 0, 400, 1200, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 58, 41, 58, 41, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 100, 33, 2763, 27, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
                107, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Command_Center.getID(), UpgradeTypes.None.getID(), 500, 0, 200, 1, 50, 50, 600, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 37, 16, 31, 25, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
                108, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Command_Center.getID(), UpgradeTypes.None.getID(), 600, 0, 0, 1, 100, 100, 1200, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 37, 16, 31, 25, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
                109, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 500, 0, 0, 1, 100, 0, 600, 0, 16, 255, 0, 50, 150, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 38, 22, 38, 26, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                110, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 750, 0, 0, 1, 100, 0, 600, 0, 0, 255, 0, 50, 150, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 2, 56, 32, 56, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 33, 2763, 27, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                111, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 1000, 0, 0, 1, 150, 0, 1200, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 48, 40, 56, 32, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 100, 33, 2763, 27, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
                112, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 600, 0, 0, 1, 150, 0, 1200, 0, 0, 255, 0, 100, 300, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 40, 32, 44, 24, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                113, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 1250, 0, 0, 1, 200, 100, 1200, 0, 0, 255, 0, 200, 600, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 56, 40, 56, 40, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 100, 33, 2763, 27, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
                114, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 1300, 0, 0, 1, 150, 100, 1050, 0, 0, 255, 0, 200, 600, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 48, 40, 48, 38, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 100, 33, 2763, 27, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
                115, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Starport.getID(), UpgradeTypes.None.getID(), 500, 0, 0, 1, 50, 50, 600, 0, 0, 255, 0, 100, 300, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 47, 24, 28, 22, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
                116, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 850, 0, 0, 1, 100, 150, 900, 0, 0, 255, 0, 275, 825, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 48, 38, 48, 38, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 100, 33, 2763, 27, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
                117, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Science_Facility.getID(), UpgradeTypes.None.getID(), 750, 0, 0, 1, 50, 50, 600, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 47, 24, 28, 22, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
                118, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Science_Facility.getID(), UpgradeTypes.None.getID(), 600, 0, 0, 1, 50, 50, 600, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 47, 24, 28, 22, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
                120, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_Factory.getID(), UpgradeTypes.None.getID(), 750, 0, 0, 1, 50, 50, 600, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 39, 24, 31, 24, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0,
                122, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 850, 0, 0, 1, 125, 0, 900, 0, 0, 255, 0, 65, 195, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 48, 32, 48, 28, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 100, 33, 2763, 27, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
                123, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 750, 0, 0, 1, 100, 50, 1200, 0, 0, 255, 0, 100, 300, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 48, 32, 47, 22, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                124, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 200, 0, 0, 0, 75, 0, 450, 0, 0, 255, 0, 50, 150, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 16, 32, 16, 16, 224, 352, WeaponTypes.None.getID(), 0, WeaponTypes.Longbolt_Missile.getID(), 1, 0, 0, 0, 40, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                125, RaceType.RaceTypes.Terran.getID(), UnitTypes.Terran_SCV.getID(), UpgradeTypes.None.getID(), 350, 0, 0, 1, 100, 0, 450, 0, 0, 255, 4, 50, 150, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 32, 24, 32, 16, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 33, 2763, 27, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                126, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 700, 0, 0, 1, 800, 600, 4800, 0, 0, 255, 0, 0, 5000, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 48, 32, 47, 31, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                127, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 2000, 0, 0, 1, 200, 0, 900, 0, 0, 255, 0, 0, 5000, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                130, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 1500, 0, 0, 1, 1, 1, 1800, 0, 0, 255, 0, 300, 900, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 58, 41, 58, 41, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 100, 33, 2763, 27, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0,
                131, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 1250, 0, 0, 1, 300, 0, 1800, 0, 2, 255, 0, 300, 900, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 49, 32, 49, 32, 0, 288, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                132, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Hatchery.getID(), UpgradeTypes.None.getID(), 1800, 0, 0, 1, 150, 100, 1500, 0, 2, 255, 0, 100, 1200, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 49, 32, 49, 32, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                133, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Lair.getID(), UpgradeTypes.None.getID(), 2500, 0, 0, 1, 200, 150, 1800, 0, 2, 255, 0, 100, 1500, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 49, 32, 49, 32, 0, 352, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                134, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 250, 0, 0, 1, 150, 0, 600, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 32, 32, 31, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                135, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 850, 0, 0, 1, 100, 50, 600, 0, 0, 255, 0, 100, 300, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 40, 32, 40, 24, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                136, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 850, 0, 0, 1, 100, 100, 900, 0, 0, 255, 0, 150, 450, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 2, 48, 32, 48, 4, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                137, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Spire.getID(), UpgradeTypes.None.getID(), 1000, 0, 0, 1, 100, 150, 1800, 0, 0, 255, 0, 200, 1350, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 28, 32, 28, 24, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                138, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 850, 0, 0, 1, 150, 100, 900, 0, 0, 255, 0, 175, 525, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 38, 28, 32, 28, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                139, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 750, 0, 0, 1, 75, 0, 600, 0, 0, 255, 0, 40, 120, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 44, 32, 32, 20, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                140, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 600, 0, 0, 1, 150, 200, 1200, 0, 0, 255, 0, 275, 825, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 40, 32, 32, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                141, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 600, 0, 0, 1, 200, 150, 1800, 0, 0, 255, 0, 250, 750, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 28, 32, 28, 24, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                142, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 750, 0, 0, 1, 200, 0, 1200, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 36, 28, 40, 18, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                143, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 400, 0, 0, 0, 75, 0, 300, 0, 0, 255, 0, 40, 120, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 24, 24, 23, 23, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                144, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Creep_Colony.getID(), UpgradeTypes.None.getID(), 400, 0, 0, 0, 50, 0, 300, 0, 0, 255, 0, 25, 195, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 24, 24, 23, 23, 224, 320, WeaponTypes.None.getID(), 0, WeaponTypes.Seeker_Spores.getID(), 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                146, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Creep_Colony.getID(), UpgradeTypes.None.getID(), 300, 0, 0, 2, 50, 0, 300, 0, 0, 255, 0, 40, 240, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 24, 24, 23, 23, 224, 320, WeaponTypes.Subterranean_Tentacle.getID(), 1, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                147, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 5000, 0, 0, 1, 1, 1, 1, 0, 0, 255, 0, 0, 10000, UnitSizeType.UnitSizeTypes.Large.getID(), 5, 3, 80, 32, 79, 40, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                148, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 2500, 0, 0, 1, 1, 1, 1, 0, 0, 255, 0, 0, 10000, UnitSizeType.UnitSizeTypes.Large.getID(), 5, 3, 80, 32, 79, 40, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                149, RaceType.RaceTypes.Zerg.getID(), UnitTypes.Zerg_Drone.getID(), UpgradeTypes.None.getID(), 750, 0, 0, 1, 50, 0, 600, 0, 0, 255, 0, 25, 75, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 2, 64, 32, 63, 31, 0, 224, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                150, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 250, 0, 0, 1, 0, 0, 0, 0, 0, 255, 0, 0, 5000, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 32, 32, 31, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                151, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 1500, 0, 0, 1, 0, 0, 0, 0, 0, 255, 0, 0, 2500, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 40, 32, 32, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                152, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 1500, 0, 0, 1, 0, 0, 0, 0, 0, 255, 0, 0, 2500, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 40, 32, 32, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                154, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 750, 750, 0, 1, 400, 0, 1800, 0, 18, 255, 0, 400, 1200, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 56, 39, 56, 39, 0, 352, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                155, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 500, 500, 0, 1, 200, 200, 1200, 0, 0, 255, 0, 300, 900, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 36, 16, 40, 20, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                156, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 300, 300, 0, 0, 100, 0, 450, 0, 16, 255, 0, 50, 150, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 16, 12, 16, 20, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                157, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 450, 450, 0, 1, 100, 0, 600, 0, 0, 255, 0, 50, 150, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 2, 48, 32, 48, 24, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                159, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 250, 250, 0, 1, 50, 100, 450, 0, 0, 255, 0, 175, 525, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 44, 16, 44, 28, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                160, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 500, 500, 0, 1, 150, 0, 900, 0, 0, 255, 0, 75, 225, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 48, 32, 48, 40, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                162, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 100, 100, 0, 0, 150, 0, 750, 0, 0, 255, 0, 100, 300, UnitSizeType.UnitSizeTypes.Large.getID(), 2, 2, 20, 16, 20, 16, 224, 352, WeaponTypes.STS_Photon_Cannon.getID(), 1, WeaponTypes.STA_Photon_Cannon.getID(), 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                163, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 450, 450, 0, 1, 150, 100, 900, 0, 0, 255, 0, 200, 600, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 24, 24, 40, 24, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                164, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 500, 500, 0, 1, 200, 0, 900, 0, 0, 255, 0, 100, 300, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 40, 24, 40, 24, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                165, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 500, 500, 0, 1, 150, 200, 900, 0, 0, 255, 0, 250, 750, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 32, 24, 32, 24, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                166, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 550, 550, 0, 1, 150, 0, 600, 0, 0, 255, 0, 100, 300, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 36, 24, 36, 20, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                167, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 600, 600, 0, 1, 150, 150, 1050, 0, 0, 255, 0, 300, 900, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 48, 40, 48, 32, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                168, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 2000, 0, 0, 1, 150, 0, 1, 0, 0, 255, 0, 0, 5000, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 64, 48, 63, 47, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                169, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 500, 500, 0, 1, 300, 200, 900, 0, 0, 255, 0, 350, 1050, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 40, 32, 47, 24, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                170, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 500, 500, 0, 1, 200, 150, 900, 0, 0, 255, 0, 450, 1350, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 44, 28, 44, 28, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                171, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 450, 450, 0, 1, 150, 100, 450, 0, 0, 255, 0, 125, 375, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 32, 32, 32, 20, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                172, RaceType.RaceTypes.Protoss.getID(), UnitTypes.Protoss_Probe.getID(), UpgradeTypes.None.getID(), 200, 200, 200, 1, 100, 0, 450, 0, 0, 255, 0, 50, 150, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 32, 16, 32, 16, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                173, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 1, 250, 0, 1, 0, 0, 255, 0, 0, 2500, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 64, 48, 63, 47, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                174, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 1500, 0, 0, 1, 250, 0, 1, 0, 0, 255, 0, 0, 5000, UnitSizeType.UnitSizeTypes.Large.getID(), 7, 3, 112, 48, 111, 47, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                175, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 5000, 0, 0, 1, 1500, 500, 4800, 0, 0, 255, 0, 0, 5000, UnitSizeType.UnitSizeTypes.Large.getID(), 5, 4, 80, 34, 79, 63, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                176, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 10, 10, UnitSizeType.UnitSizeTypes.Independent.getID(), 2, 1, 32, 16, 31, 15, 0, 288, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                188, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 10, 10, UnitSizeType.UnitSizeTypes.Independent.getID(), 4, 2, 64, 32, 63, 31, 0, 288, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                189, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 700, 0, 0, 1, 600, 200, 2400, 0, 0, 255, 0, 0, 2000, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                190, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 2000, 0, 0, 1, 1000, 400, 4800, 0, 0, 255, 0, 0, 3600, UnitSizeType.UnitSizeTypes.Large.getID(), 5, 3, 80, 38, 69, 47, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                200, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 800, 0, 0, 1, 200, 50, 2400, 0, 0, 255, 0, 0, 600, UnitSizeType.UnitSizeTypes.Large.getID(), 4, 3, 56, 28, 63, 43, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                201, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 2500, 0, 0, 1, 1000, 500, 2400, 0, 0, 255, 0, 0, 4000, UnitSizeType.UnitSizeTypes.Large.getID(), 3, 2, 48, 32, 47, 31, 0, 320, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0,
                194, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 250, 0, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                195, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 50, 50, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                196, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 100, 100, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                197, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 250, 0, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                198, RaceType.RaceTypes.Terran.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 50, 50, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                199, RaceType.RaceTypes.Protoss.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 100000, 0, 0, 0, 100, 100, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 3, 2, 48, 32, 47, 31, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                202, RaceType.RaceTypes.Zerg.getID(), UnitTypes.None.getID(), UpgradeTypes.None.getID(), 800, 0, 0, 0, 250, 200, 2400, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 5, 5, 80, 80, 79, 79, 0, 256, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                128, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 10000, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 16, 16, 15, 15, 0, 160, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                129, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 10000, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 16, 16, 15, 15, 0, 160, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                215, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 10000, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 16, 16, 15, 15, 0, 160, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                216, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 800, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 16, 16, 15, 15, 0, 160, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                217, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 800, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 16, 16, 15, 15, 0, 160, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                218, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 800, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 16, 16, 15, 15, 0, 160, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                219, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 800, 0, 0, 0, 1, 1, 1, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.Independent.getID(), 1, 1, 16, 16, 15, 15, 0, 160, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                228, 0, UnitTypes.None.getID(), UpgradeTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                229, 0, UnitTypes.Unknown.getID(), UpgradeTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 255, 0, 0, 0, UnitSizeType.UnitSizeTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, WeaponTypes.None.getID(), 0, WeaponTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0


        };
        return data;
    }

    public int[] getTechTypes() {

        int[] data = new int[]{0, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 0, UnitTypes.Terran_Academy.getID(), WeaponTypes.None.getID(), 0, 0,
                1, RaceType.RaceTypes.Terran.getID(), 200, 200, 1500, 100, UnitTypes.Terran_Covert_Ops.getID(), WeaponTypes.Lockdown.getID(), 0, 0,
                2, RaceType.RaceTypes.Terran.getID(), 200, 200, 1800, 100, UnitTypes.Terran_Science_Facility.getID(), WeaponTypes.EMP_Shockwave.getID(), 0, 0,
                3, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 0, UnitTypes.Terran_Machine_Shop.getID(), WeaponTypes.Spider_Mines.getID(), 0, 0,
                4, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 50, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                5, RaceType.RaceTypes.Terran.getID(), 150, 150, 1200, 0, UnitTypes.Terran_Machine_Shop.getID(), WeaponTypes.None.getID(), 0, 0,
                6, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 100, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                7, RaceType.RaceTypes.Terran.getID(), 200, 200, 1200, 75, UnitTypes.Terran_Science_Facility.getID(), WeaponTypes.Irradiate.getID(), 0, 0,
                8, RaceType.RaceTypes.Terran.getID(), 100, 100, 1800, 150, UnitTypes.Terran_Physics_Lab.getID(), WeaponTypes.Yamato_Gun.getID(), 0, 0,
                9, RaceType.RaceTypes.Terran.getID(), 150, 150, 1500, 25, UnitTypes.Terran_Control_Tower.getID(), WeaponTypes.None.getID(), 0, 0,
                10, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 25, UnitTypes.Terran_Covert_Ops.getID(), WeaponTypes.None.getID(), 0, 0,
                11, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1200, 0, UnitTypes.Zerg_Hatchery.getID(), WeaponTypes.None.getID(), 0, 0,
                12, RaceType.RaceTypes.Zerg.getID(), 0, 0, 0, 0, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                13, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1200, 150, UnitTypes.Zerg_Queens_Nest.getID(), WeaponTypes.Spawn_Broodlings.getID(), 0, 0,
                14, RaceType.RaceTypes.Zerg.getID(), 0, 0, 0, 100, UnitTypes.None.getID(), WeaponTypes.Dark_Swarm.getID(), 0, 0,
                15, RaceType.RaceTypes.Zerg.getID(), 200, 200, 1500, 150, UnitTypes.Zerg_Defiler_Mound.getID(), WeaponTypes.Plague.getID(), 0, 0,
                16, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1500, 0, UnitTypes.Zerg_Defiler_Mound.getID(), WeaponTypes.Consume.getID(), 0, 0,
                17, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1200, 75, UnitTypes.Zerg_Queens_Nest.getID(), WeaponTypes.Ensnare.getID(), 0, 0,
                18, RaceType.RaceTypes.Zerg.getID(), 0, 0, 0, 75, UnitTypes.None.getID(), WeaponTypes.Parasite.getID(), 0, 0,
                19, RaceType.RaceTypes.Protoss.getID(), 200, 200, 1800, 75, UnitTypes.Protoss_Templar_Archives.getID(), WeaponTypes.Psionic_Storm.getID(), 0, 0,
                20, RaceType.RaceTypes.Protoss.getID(), 150, 150, 1200, 100, UnitTypes.Protoss_Templar_Archives.getID(), WeaponTypes.None.getID(), 0, 0,
                21, RaceType.RaceTypes.Protoss.getID(), 150, 150, 1800, 150, UnitTypes.Protoss_Arbiter_Tribunal.getID(), WeaponTypes.None.getID(), 0, 0,
                22, RaceType.RaceTypes.Protoss.getID(), 150, 150, 1500, 100, UnitTypes.Protoss_Arbiter_Tribunal.getID(), WeaponTypes.Stasis_Field.getID(), 0, 0,
                23, RaceType.RaceTypes.Protoss.getID(), 0, 0, 0, 0, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                24, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 50, UnitTypes.Terran_Academy.getID(), WeaponTypes.Restoration.getID(), 0, 0,
                25, RaceType.RaceTypes.Protoss.getID(), 200, 200, 1200, 125, UnitTypes.Protoss_Fleet_Beacon.getID(), WeaponTypes.Disruption_Web.getID(), 0, 0,
                26, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                27, RaceType.RaceTypes.Protoss.getID(), 200, 200, 1800, 150, UnitTypes.Protoss_Templar_Archives.getID(), WeaponTypes.Mind_Control.getID(), 0, 0,
                28, RaceType.RaceTypes.Protoss.getID(), 0, 0, 0, 0, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                29, RaceType.RaceTypes.Protoss.getID(), 0, 0, 0, 50, UnitTypes.None.getID(), WeaponTypes.Feedback.getID(), 0, 0,
                30, RaceType.RaceTypes.Terran.getID(), 100, 100, 1800, 75, UnitTypes.Terran_Academy.getID(), WeaponTypes.Optical_Flare.getID(), 0, 0,
                31, RaceType.RaceTypes.Protoss.getID(), 100, 100, 1500, 100, UnitTypes.Protoss_Templar_Archives.getID(), WeaponTypes.Maelstrom.getID(), 0, 0,
                32, RaceType.RaceTypes.Zerg.getID(), 200, 200, 1800, 0, UnitTypes.Zerg_Hydralisk_Den.getID(), WeaponTypes.None.getID(), 0, 0,
                33, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                34, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 1, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                35, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                36, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                37, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                38, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                39, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                40, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                41, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                42, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                43, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                44, 0, 0, 0, 0, 0, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                45, 0, 0, 0, 0, 0, UnitTypes.None.getID(), WeaponTypes.None.getID(), 0, 0,
                46, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 0, UnitTypes.None.getID(), WeaponTypes.Nuclear_Strike.getID(), 0, 0
        };


        return data;
    }

    public int[] getUpgradeTypes() {

        int[] data = new int[]{
                0, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitTypes.Terran_Engineering_Bay.getID(),
                1, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitTypes.Terran_Armory.getID(),
                2, RaceType.RaceTypes.Terran.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitTypes.Terran_Armory.getID(),
                3, RaceType.RaceTypes.Zerg.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitTypes.Zerg_Evolution_Chamber.getID(),
                4, RaceType.RaceTypes.Zerg.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitTypes.Zerg_Spire.getID(),
                5, RaceType.RaceTypes.Protoss.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitTypes.Protoss_Forge.getID(),
                6, RaceType.RaceTypes.Protoss.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitTypes.Protoss_Cybernetics_Core.getID(),
                7, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitTypes.Terran_Engineering_Bay.getID(),
                8, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitTypes.Terran_Armory.getID(),
                9, RaceType.RaceTypes.Terran.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitTypes.Terran_Armory.getID(),
                10, RaceType.RaceTypes.Zerg.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitTypes.Zerg_Evolution_Chamber.getID(),
                11, RaceType.RaceTypes.Zerg.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitTypes.Zerg_Evolution_Chamber.getID(),
                12, RaceType.RaceTypes.Zerg.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitTypes.Zerg_Spire.getID(),
                13, RaceType.RaceTypes.Protoss.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitTypes.Protoss_Forge.getID(),
                14, RaceType.RaceTypes.Protoss.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitTypes.Protoss_Cybernetics_Core.getID(),
                15, RaceType.RaceTypes.Protoss.getID(), 200, 100, 200, 100, 4000, 480, 3, UnitTypes.Protoss_Forge.getID(),
                16, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 1500, 0, 1, UnitTypes.Terran_Academy.getID(),
                17, RaceType.RaceTypes.Terran.getID(), 100, 0, 100, 0, 1500, 0, 1, UnitTypes.Terran_Machine_Shop.getID(),
                18, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                19, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Terran_Science_Facility.getID(),
                20, RaceType.RaceTypes.Terran.getID(), 100, 0, 100, 0, 2500, 0, 1, UnitTypes.Terran_Covert_Ops.getID(),
                21, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Terran_Covert_Ops.getID(),
                22, RaceType.RaceTypes.Terran.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitTypes.Terran_Control_Tower.getID(),
                23, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Terran_Physics_Lab.getID(),
                24, RaceType.RaceTypes.Zerg.getID(), 200, 0, 200, 0, 2400, 0, 1, UnitTypes.Zerg_Lair.getID(),
                25, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitTypes.Zerg_Lair.getID(),
                26, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitTypes.Zerg_Lair.getID(),
                27, RaceType.RaceTypes.Zerg.getID(), 100, 0, 100, 0, 1500, 0, 1, UnitTypes.Zerg_Spawning_Pool.getID(),
                28, RaceType.RaceTypes.Zerg.getID(), 200, 0, 200, 0, 1500, 0, 1, UnitTypes.Zerg_Spawning_Pool.getID(),
                29, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 1500, 0, 1, UnitTypes.Zerg_Hydralisk_Den.getID(),
                30, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 1500, 0, 1, UnitTypes.Zerg_Hydralisk_Den.getID(),
                31, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Zerg_Queens_Nest.getID(),
                32, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Zerg_Defiler_Mound.getID(),
                33, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Protoss_Cybernetics_Core.getID(),
                34, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitTypes.Protoss_Citadel_of_Adun.getID(),
                35, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitTypes.Protoss_Robotics_Support_Bay.getID(),
                36, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitTypes.Protoss_Robotics_Support_Bay.getID(),
                37, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitTypes.Protoss_Robotics_Support_Bay.getID(),
                38, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitTypes.Protoss_Observatory.getID(),
                39, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitTypes.Protoss_Observatory.getID(),
                40, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Protoss_Templar_Archives.getID(),
                41, RaceType.RaceTypes.Protoss.getID(), 100, 0, 100, 0, 2500, 0, 1, UnitTypes.Protoss_Fleet_Beacon.getID(),
                42, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitTypes.Protoss_Fleet_Beacon.getID(),
                43, RaceType.RaceTypes.Protoss.getID(), 100, 0, 100, 0, 1500, 0, 1, UnitTypes.Protoss_Fleet_Beacon.getID(),
                44, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Protoss_Arbiter_Tribunal.getID(),
                45, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                46, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                47, RaceType.RaceTypes.Protoss.getID(), 100, 0, 100, 0, 2500, 0, 1, UnitTypes.Protoss_Fleet_Beacon.getID(),
                48, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                49, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Protoss_Templar_Archives.getID(),
                50, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                51, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitTypes.Terran_Academy.getID(),
                52, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitTypes.Zerg_Ultralisk_Cavern.getID(),
                53, RaceType.RaceTypes.Zerg.getID(), 200, 0, 200, 0, 2000, 0, 1, UnitTypes.Zerg_Ultralisk_Cavern.getID(),
                54, RaceType.RaceTypes.Terran.getID(), 100, 0, 100, 0, 2000, 0, 1, UnitTypes.Terran_Machine_Shop.getID(),
                55, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                56, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                57, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                58, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                59, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                60, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                61, 0, 0, 0, 0, 0, 0, 0, 0, UnitTypes.None.getID(),
                62, 0, 0, 0, 0, 0, 0, 0, 0, UnitTypes.None.getID()


        };


        return data;
    }

    public int[] getWeaponTypes() {


        int[] data = new int[]{
                0, TechTypes.None.getID(), UnitTypes.Terran_Marine.getID(), 6, 1, 15, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                1, TechTypes.None.getID(), UnitTypes.Hero_Jim_Raynor_Marine.getID(), 18, 1, 15, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                2, TechTypes.None.getID(), UnitTypes.Terran_Ghost.getID(), 10, 1, 22, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                3, TechTypes.None.getID(), UnitTypes.Hero_Sarah_Kerrigan.getID(), 30, 1, 22, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                112, TechTypes.None.getID(), UnitTypes.Hero_Samir_Duran.getID(), 25, 1, 22, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                113, TechTypes.None.getID(), UnitTypes.Hero_Infested_Duran.getID(), 25, 1, 22, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                116, TechTypes.None.getID(), UnitTypes.Hero_Alexei_Stukov.getID(), 30, 1, 22, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                4, TechTypes.None.getID(), UnitTypes.Terran_Vulture.getID(), 20, 2, 30, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                5, TechTypes.None.getID(), UnitTypes.Hero_Jim_Raynor_Vulture.getID(), 30, 2, 22, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                6, TechTypes.Spider_Mines.getID(), UnitTypes.Terran_Vulture_Spider_Mine.getID(), 125, 0, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Radial_Splash.getID(), 0, 10, 50, 75, 100, 0, 1, 0, 0, 1, 0, 0, 0, 0,
                7, TechTypes.None.getID(), UnitTypes.Terran_Goliath.getID(), 12, 1, 22, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                9, TechTypes.None.getID(), UnitTypes.Hero_Alan_Schezar.getID(), 24, 1, 22, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                8, TechTypes.None.getID(), UnitTypes.Terran_Goliath.getID(), 10, 2, 22, 2, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                10, TechTypes.None.getID(), UnitTypes.Hero_Alan_Schezar.getID(), 20, 1, 22, 2, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                11, TechTypes.None.getID(), UnitTypes.Terran_Siege_Tank_Tank_Mode.getID(), 30, 3, 37, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                12, TechTypes.None.getID(), UnitTypes.Hero_Edmund_Duke_Tank_Mode.getID(), 70, 3, 37, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                13, TechTypes.None.getID(), UnitTypes.Terran_SCV.getID(), 5, 1, 15, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 10, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                15, TechTypes.None.getID(), UnitTypes.Terran_Wraith.getID(), 20, 2, 22, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                17, TechTypes.None.getID(), UnitTypes.Hero_Tom_Kazansky.getID(), 40, 2, 22, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                16, TechTypes.None.getID(), UnitTypes.Terran_Wraith.getID(), 8, 1, 30, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                18, TechTypes.None.getID(), UnitTypes.Hero_Tom_Kazansky.getID(), 16, 1, 30, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                19, TechTypes.None.getID(), UnitTypes.Terran_Battlecruiser.getID(), 25, 3, 30, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                21, TechTypes.None.getID(), UnitTypes.Hero_Norad_II.getID(), 50, 3, 30, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                23, TechTypes.None.getID(), UnitTypes.Hero_Hyperion.getID(), 30, 3, 22, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                20, TechTypes.None.getID(), UnitTypes.Terran_Battlecruiser.getID(), 25, 3, 30, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                22, TechTypes.None.getID(), UnitTypes.Hero_Norad_II.getID(), 50, 3, 30, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                24, TechTypes.None.getID(), UnitTypes.Hero_Hyperion.getID(), 30, 3, 22, 1, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                25, TechTypes.None.getID(), UnitTypes.Terran_Firebat.getID(), 8, 1, 22, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Enemy_Splash.getID(), 0, 32, 15, 20, 25, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                26, TechTypes.None.getID(), UnitTypes.Terran_Firebat.getID(), 16, 1, 22, 1, UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Enemy_Splash.getID(), 0, 32, 15, 20, 25, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                27, TechTypes.None.getID(), UnitTypes.Terran_Siege_Tank_Siege_Mode.getID(), 70, 5, 75, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Radial_Splash.getID(), 64, 384, 10, 25, 40, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                28, TechTypes.None.getID(), UnitTypes.Hero_Edmund_Duke_Siege_Mode.getID(), 150, 5, 75, 1, UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Radial_Splash.getID(), 64, 384, 10, 25, 40, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                29, TechTypes.None.getID(), UnitTypes.Terran_Missile_Turret.getID(), 20, 0, 15, 1, UpgradeTypes.None.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                30, TechTypes.Yamato_Gun.getID(), UnitTypes.Terran_Battlecruiser.getID(), 260, 0, 15, 1, UpgradeTypes.None.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Yamato_Gun.getID(), 0, 320, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                31, TechTypes.Nuclear_Strike.getID(), UnitTypes.Terran_Ghost.getID(), 600, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Nuclear_Missile.getID(), 0, 3, 128, 192, 256, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                32, TechTypes.Lockdown.getID(), UnitTypes.Terran_Ghost.getID(), 0, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.Lockdown.getID(), 0, 256, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0,
                33, TechTypes.EMP_Shockwave.getID(), UnitTypes.Terran_Science_Vessel.getID(), 0, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Concussive.getID(), ExplosionTypes.EMP_Shockwave.getID(), 0, 256, 64, 64, 64, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                34, TechTypes.Irradiate.getID(), UnitTypes.Terran_Science_Vessel.getID(), 250, 0, 75, 1, UpgradeTypes.None.getID(), DamageTypes.Ignore_Armor.getID(), ExplosionTypes.Irradiate.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                35, TechTypes.None.getID(), UnitTypes.Zerg_Zergling.getID(), 5, 1, 8, 1, UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                36, TechTypes.None.getID(), UnitTypes.Hero_Devouring_One.getID(), 10, 1, 8, 1, UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                37, TechTypes.None.getID(), UnitTypes.Hero_Infested_Kerrigan.getID(), 50, 1, 15, 1, UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                38, TechTypes.None.getID(), UnitTypes.Zerg_Hydralisk.getID(), 10, 1, 15, 1, UpgradeTypes.Zerg_Missile_Attacks.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                39, TechTypes.None.getID(), UnitTypes.Hero_Hunter_Killer.getID(), 20, 1, 15, 1, UpgradeTypes.Zerg_Missile_Attacks.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                40, TechTypes.None.getID(), UnitTypes.Zerg_Ultralisk.getID(), 20, 3, 15, 1, UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                41, TechTypes.None.getID(), UnitTypes.Hero_Torrasque.getID(), 50, 3, 15, 1, UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                42, TechTypes.None.getID(), UnitTypes.Zerg_Broodling.getID(), 4, 1, 15, 1, UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                43, TechTypes.None.getID(), UnitTypes.Zerg_Drone.getID(), 5, 0, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 32, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                46, TechTypes.None.getID(), UnitTypes.Zerg_Guardian.getID(), 20, 2, 30, 1, UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 256, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                47, TechTypes.None.getID(), UnitTypes.Hero_Kukulza_Guardian.getID(), 40, 2, 30, 1, UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 256, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                48, TechTypes.None.getID(), UnitTypes.Zerg_Mutalisk.getID(), 9, 1, 30, 1, UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 96, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                49, TechTypes.None.getID(), UnitTypes.Hero_Kukulza_Mutalisk.getID(), 18, 1, 30, 1, UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 96, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                52, TechTypes.None.getID(), UnitTypes.Zerg_Spore_Colony.getID(), 15, 0, 15, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                53, TechTypes.None.getID(), UnitTypes.Zerg_Sunken_Colony.getID(), 40, 0, 32, 1, UpgradeTypes.None.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                54, TechTypes.None.getID(), UnitTypes.Zerg_Infested_Terran.getID(), 500, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Radial_Splash.getID(), 0, 3, 20, 40, 60, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                55, TechTypes.None.getID(), UnitTypes.Zerg_Scourge.getID(), 110, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                56, TechTypes.Parasite.getID(), UnitTypes.Zerg_Queen.getID(), 0, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Parasite.getID(), 0, 384, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0,
                57, TechTypes.Spawn_Broodlings.getID(), UnitTypes.Zerg_Queen.getID(), 0, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Broodlings.getID(), 0, 288, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0,
                58, TechTypes.Ensnare.getID(), UnitTypes.Zerg_Queen.getID(), 0, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Ensnare.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                59, TechTypes.Dark_Swarm.getID(), UnitTypes.Zerg_Defiler.getID(), 0, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Dark_Swarm.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                60, TechTypes.Plague.getID(), UnitTypes.Zerg_Defiler.getID(), 300, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Plague.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                61, TechTypes.Consume.getID(), UnitTypes.Zerg_Defiler.getID(), 0, 0, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Consume.getID(), 0, 16, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1,
                62, TechTypes.None.getID(), UnitTypes.Protoss_Probe.getID(), 5, 0, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 32, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                64, TechTypes.None.getID(), UnitTypes.Protoss_Zealot.getID(), 8, 1, 22, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                65, TechTypes.None.getID(), UnitTypes.Hero_Fenix_Zealot.getID(), 20, 1, 22, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                66, TechTypes.None.getID(), UnitTypes.Protoss_Dragoon.getID(), 20, 2, 30, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                67, TechTypes.None.getID(), UnitTypes.Hero_Fenix_Dragoon.getID(), 45, 2, 22, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                69, TechTypes.None.getID(), UnitTypes.Hero_Tassadar.getID(), 20, 1, 22, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 96, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                70, TechTypes.None.getID(), UnitTypes.Protoss_Archon.getID(), 30, 3, 20, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Enemy_Splash.getID(), 0, 64, 3, 15, 30, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                71, TechTypes.None.getID(), UnitTypes.Hero_Tassadar_Zeratul_Archon.getID(), 60, 3, 20, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Enemy_Splash.getID(), 0, 64, 3, 15, 30, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                73, TechTypes.None.getID(), UnitTypes.Protoss_Scout.getID(), 8, 1, 30, 1, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                75, TechTypes.None.getID(), UnitTypes.Hero_Mojo.getID(), 20, 1, 30, 1, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                114, TechTypes.None.getID(), UnitTypes.Hero_Artanis.getID(), 20, 1, 30, 1, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                74, TechTypes.None.getID(), UnitTypes.Protoss_Scout.getID(), 14, 1, 22, 2, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                76, TechTypes.None.getID(), UnitTypes.Hero_Mojo.getID(), 28, 1, 22, 2, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                115, TechTypes.None.getID(), UnitTypes.Hero_Artanis.getID(), 28, 1, 22, 2, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                77, TechTypes.None.getID(), UnitTypes.Protoss_Arbiter.getID(), 10, 1, 45, 1, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                78, TechTypes.None.getID(), UnitTypes.Hero_Danimoth.getID(), 20, 1, 45, 1, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                79, TechTypes.None.getID(), UnitTypes.Protoss_Interceptor.getID(), 6, 1, 1, 1, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                80, TechTypes.None.getID(), UnitTypes.Protoss_Photon_Cannon.getID(), 20, 0, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                81, TechTypes.None.getID(), UnitTypes.Protoss_Photon_Cannon.getID(), 20, 0, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                82, TechTypes.None.getID(), UnitTypes.Protoss_Scarab.getID(), 100, 25, 1, 1, UpgradeTypes.Scarab_Damage.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Enemy_Splash.getID(), 0, 128, 20, 40, 60, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                83, TechTypes.Stasis_Field.getID(), UnitTypes.Protoss_Arbiter.getID(), 0, 1, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Stasis_Field.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                84, TechTypes.Psionic_Storm.getID(), UnitTypes.Protoss_High_Templar.getID(), 14, 1, 45, 1, UpgradeTypes.None.getID(), DamageTypes.Ignore_Armor.getID(), ExplosionTypes.Radial_Splash.getID(), 0, 288, 48, 48, 48, 1, 1, 0, 0, 1, 0, 1, 0, 0,
                100, TechTypes.None.getID(), UnitTypes.Protoss_Corsair.getID(), 5, 1, 8, 1, UpgradeTypes.Protoss_Air_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Air_Splash.getID(), 0, 160, 5, 50, 100, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                101, TechTypes.Disruption_Web.getID(), UnitTypes.Protoss_Corsair.getID(), 0, 0, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Ignore_Armor.getID(), ExplosionTypes.Disruption_Web.getID(), 0, 288, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                102, TechTypes.Restoration.getID(), UnitTypes.Terran_Medic.getID(), 20, 0, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Ignore_Armor.getID(), ExplosionTypes.Restoration.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                103, TechTypes.None.getID(), UnitTypes.Terran_Valkyrie.getID(), 6, 1, 64, 2, UpgradeTypes.Terran_Ship_Weapons.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Air_Splash.getID(), 0, 192, 5, 50, 100, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                104, TechTypes.None.getID(), UnitTypes.Zerg_Devourer.getID(), 25, 2, 100, 1, UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageTypes.Explosive.getID(), ExplosionTypes.Corrosive_Acid.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                105, TechTypes.Mind_Control.getID(), UnitTypes.Protoss_Dark_Archon.getID(), 8, 1, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Mind_Control.getID(), 0, 256, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                106, TechTypes.Feedback.getID(), UnitTypes.Protoss_Dark_Archon.getID(), 8, 1, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Ignore_Armor.getID(), ExplosionTypes.Feedback.getID(), 0, 320, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                107, TechTypes.Optical_Flare.getID(), UnitTypes.Terran_Medic.getID(), 8, 1, 22, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Optical_Flare.getID(), 0, 288, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                108, TechTypes.Maelstrom.getID(), UnitTypes.Protoss_Dark_Archon.getID(), 0, 1, 1, 1, UpgradeTypes.None.getID(), DamageTypes.Independent.getID(), ExplosionTypes.Maelstrom.getID(), 0, 320, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                109, TechTypes.None.getID(), UnitTypes.Zerg_Lurker.getID(), 20, 2, 37, 1, UpgradeTypes.Zerg_Missile_Attacks.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Enemy_Splash.getID(), 0, 192, 20, 20, 20, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                111, TechTypes.None.getID(), UnitTypes.Protoss_Dark_Templar.getID(), 40, 3, 30, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                86, TechTypes.None.getID(), UnitTypes.Hero_Dark_Templar.getID(), 45, 1, 30, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                85, TechTypes.None.getID(), UnitTypes.Hero_Zeratul.getID(), 100, 1, 22, 1, UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageTypes.Normal.getID(), ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                130, TechTypes.None.getID(), UnitTypes.None.getID(), 0, 0, 0, 0, UpgradeTypes.None.getID(), DamageTypes.None.getID(), ExplosionTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                131, TechTypes.None.getID(), UnitTypes.None.getID(), 0, 0, 0, 0, UpgradeTypes.None.getID(), DamageTypes.None.getID(), ExplosionTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0


        };
        return data;
    }

    public int[] getUnitSizeTypes() {
        int[] data = new int[]{0, 1, 3, 4, 5};
        return data;
    }

    public int[] getBulletTypes() {
        int[] data = new int[211];
        for (int i = 0; i < 211; i++) {
            data[i] = i;
        }
        return data;
    }

    public int[] getDamageTypes() {
        int[] data = new int[]{0, 1, 3, 4, 5, 6};
        return data;
    }

    public int[] getExplosionTypes() {
        int[] data = new int[26];
        for (int i = 0; i < 26; i++) {
            data[i] = i;
        }
        return data;
    }

    public int[] getUnitCommandTypes() {
        int[] data = new int[45];
        for (int i = 0; i < 45; i++) {
            data[i] = i;
        }
        return data;
    }

    public int[] getOrderTypes() {
        int[] data = new int[]{0, 1, 2, 3, 4, 5, 6, 10, 12, 13, 14, 15, 16, 17, 18, 20, 23, 24, 27, 29, 30, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43,
                44, 46, 47, 49, 50, 51, 55, 58, 63, 64, 65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 94, 95, 96, 97, 98, 99, 101, 102, 103, 104, 105, 106, 107, 109, 110, 111, 112, 113, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124,
                125, 127, 128, 129, 131, 132, 133, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, 156, 157, 158, 159,
                160, 161, 162, 163, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190
        };

        return data;
    }


    public void loadTypeData() {
        // unit types
        int[] unitTypeData = getUnitTypes();
        for (int index = 0; index < unitTypeData.length; index += UnitType.numAttributes) {
            UnitType type = new UnitType(unitTypeData, index);
            type.setName(UnitType.UnitTypes.values()[type.getID()].name());
            unitTypes.put(type.getID(), type);
        }

        // tech types
        int[] techTypeData = getTechTypes();
        for (int index = 0; index < techTypeData.length; index += TechType.numAttributes) {
            TechType type = new TechType(techTypeData, index);
            type.setName(TechType.TechTypes.values()[type.getID()].name());
            techTypes.put(type.getID(), type);
        }

        // upgrade types
        int[] upgradeTypeData = getUpgradeTypes();
        for (int index = 0; index < upgradeTypeData.length; index += UpgradeType.numAttributes) {
            UpgradeType type = new UpgradeType(upgradeTypeData, index);
            type.setName(UpgradeType.UpgradeTypes.values()[type.getID()].name());
            upgradeTypes.put(type.getID(), type);
        }

        // weapon types
        int[] weaponTypeData = getWeaponTypes();
        for (int index = 0; index < weaponTypeData.length; index += WeaponType.numAttributes) {
            WeaponType type = new WeaponType(weaponTypeData, index);
            type.setName(WeaponType.WeaponTypes.values()[type.getID()].name());
            weaponTypes.put(type.getID(), type);
        }

        // unit size types
        int[] unitSizeTypeData = getUnitSizeTypes();
        for (int index = 0; index < unitSizeTypeData.length; index += UnitSizeType.numAttributes) {
            UnitSizeType type = new UnitSizeType(unitSizeTypeData, index);
            type.setName(UnitSizeType.UnitSizes.values()[type.getID()].name());
            unitSizeTypes.put(type.getID(), type);
        }

        // bullet types
        int[] bulletTypeData = getBulletTypes();
        for (int index = 0; index < bulletTypeData.length; index += BulletType.numAttributes) {
            BulletType type = new BulletType(bulletTypeData, index);

            type.setName(BulletType.BulletTypes.values()[type.getID()].name());
            bulletTypes.put(type.getID(), type);
        }

        // damage types
        int[] damageTypeData = getDamageTypes();
        for (int index = 0; index < damageTypeData.length; index += DamageType.numAttributes) {
            DamageType type = new DamageType(damageTypeData, index);
            type.setName(DamageType.DamageTypes.values()[index].name());
            damageTypes.put(type.getID(), type);
        }

        // explosion types
        int[] explosionTypeData = getExplosionTypes();
        for (int index = 0; index < explosionTypeData.length; index += ExplosionType.numAttributes) {
            ExplosionType type = new ExplosionType(explosionTypeData, index);
            type.setName(ExplosionType.ExplosionTypes.values()[type.getID()].name());
            explosionTypes.put(type.getID(), type);
        }

        // unitCommand types
        int[] unitCommandTypeData = getUnitCommandTypes();
        for (int index = 0; index < unitCommandTypeData.length; index += UnitCommandType.numAttributes) {
            UnitCommandType type = new UnitCommandType(unitCommandTypeData, index);
            type.setName(UnitCommandType.UnitCommandTypes.values()[type.getID()].name());
            unitCommandTypes.put(type.getID(), type);
        }

        // order types
        int[] orderTypeData = getOrderTypes();
        for (int index = 0; index < orderTypeData.length; index += OrderType.numAttributes) {
            OrderType type = new OrderType(orderTypeData, index);
            type.setName(OrderType.OrderTypeTypes.values()[index].name());
//			System.out.println("ID: "+ type.getID()+" Name: "+ type.getName());
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
