package bwmcts.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
        super(listener, false);
    }


    public void loadTypeData() {
        // unit types
        int[] unitTypeData = readDataFromFile("unitTypes");
        for (int index = 0; index < unitTypeData.length; index += UnitType.numAttributes) {
            UnitType type = new UnitType(unitTypeData[index]);
            type.initialize(unitTypeData, index, null, null);
            unitTypes.put(type.getID(), type);
        }

        // tech types
        int[] techTypeData = readDataFromFile("techTypes");
        for (int index = 0; index < techTypeData.length; index += TechType.numAttributes) {
            TechType type = new TechType(techTypeData[index]);
            type.initialize(techTypeData, index, null);
            techTypes.put(type.getID(), type);
        }

        // upgrade types
        int[] upgradeTypeData = readDataFromFile("upgradeTypes");
        for (int index = 0; index < upgradeTypeData.length; index += UpgradeType.numAttributes) {
            UpgradeType type = new UpgradeType(upgradeTypeData[index]);
            type.initialize(upgradeTypeData, index, null);
            upgradeTypes.put(type.getID(), type);
        }

        // weapon types
        int[] weaponTypeData = readDataFromFile("weaponTypes");
        for (int index = 0; index < weaponTypeData.length; index += WeaponType.numAttributes) {
            WeaponType type = new WeaponType(weaponTypeData[index]);
            type.initialize(weaponTypeData, index, null);
            weaponTypes.put(type.getID(), type);
        }

        // unit size types
        int[] unitSizeTypeData = readDataFromFile("unitSizeTypes");
        for (int index = 0; index < unitSizeTypeData.length; index += UnitSizeType.numAttributes) {
            UnitSizeType type = new UnitSizeType(unitSizeTypeData[index]);
            type.initialize(unitSizeTypeData, index, null);
            unitSizeTypes.put(type.getID(), type);
        }

        // bullet types
        int[] bulletTypeData = readDataFromFile("bulletTypes");
        for (int index = 0; index < bulletTypeData.length; index += BulletType.numAttributes) {
            BulletType type = new BulletType(bulletTypeData[index]);
            type.initialize(bulletTypeData, index, null);
            bulletTypes.put(type.getID(), type);
        }

        // damage types
        int[] damageTypeData = readDataFromFile("damageTypes");
        for (int index = 0; index < damageTypeData.length; index += DamageType.numAttributes) {
            DamageType type = new DamageType(damageTypeData[index]);
            type.initialize(damageTypeData, index, null);
            damageTypes.put(type.getID(), type);
        }

        // explosion types
        int[] explosionTypeData = readDataFromFile("explosionTypes");
        for (int index = 0; index < explosionTypeData.length; index += ExplosionType.numAttributes) {
            ExplosionType type = new ExplosionType(explosionTypeData[index]);
            type.initialize(explosionTypeData, index, null);
            explosionTypes.put(type.getID(), type);
        }

        // unitCommand types
        int[] unitCommandData = readDataFromFile("unitCommandTypes");
        for (int index = 0; index < unitCommandData.length; index += UnitCommandType.numAttributes) {
            UnitCommandType type = new UnitCommandType(unitCommandData[index]);
            type.initialize(unitCommandData, index, null);
            unitCommandTypes.put(type.getID(), type);
        }

        // order types
        int[] orderTypeData = readDataFromFile("orderTypes");
        for (int index = 0; index < orderTypeData.length; index += OrderType.numAttributes) {
            OrderType type = new OrderType(orderTypeData[index]);
            type.initialize(orderTypeData, index, null);
            orderTypes.put(type.getID(), type);
        }
    }

    private int[] readDataFromFile(String filename) {
        try {
            //String s = Files.readAllLines(Paths.get("res/" + filename + ".json")).stream().reduce((a, b) -> a + b).get();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("res\\" + filename + ".json")));
            String readLine = "";
            StringBuilder result = new StringBuilder();
            while ((readLine = bufferedReader.readLine()) != null) {
                result.append(readLine);
            }
            Gson gson = new Gson();
            List<Integer> list = gson.fromJson(result.toString(), new TypeToken<List<Integer>>() {
            }.getType());
            return list.stream().mapToInt(a -> a).toArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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