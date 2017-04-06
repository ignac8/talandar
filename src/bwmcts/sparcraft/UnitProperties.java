/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft;

import jnibwapi.JNIBWAPI;
import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;
import jnibwapi.types.UpgradeType;
import jnibwapi.types.UpgradeType.UpgradeTypes;

public class UnitProperties {
    static UnitProperties[] props = new UnitProperties[256];

    UnitType type;
    int pixelShift = 10;
    UpgradeType capacityUpgrade;
    UpgradeType extraArmorUpgrade;
    UpgradeType maxEnergyUpgrade;
    UpgradeType sightUpgrade;
    UpgradeType speedUpgrade;

    int[] capacity = new int[2];
    int[] extraArmor = new int[2];
    int[] maxEnergy = new int[2];
    int[] sightRange = new int[2];
    int[] speed = new int[2];


    public UnitProperties(JNIBWAPI bwapi) {
        capacityUpgrade = bwapi.getUpgradeType(UpgradeTypes.None.getID());
        maxEnergyUpgrade = bwapi.getUpgradeType(UpgradeTypes.None.getID());
        sightUpgrade = bwapi.getUpgradeType(UpgradeTypes.None.getID());
        extraArmorUpgrade = bwapi.getUpgradeType(UpgradeTypes.None.getID());
        speedUpgrade = bwapi.getUpgradeType(UpgradeTypes.None.getID());
        capacity[0] = capacity[1] = 0;
    }

    public static void Init(JNIBWAPI bwapi) {
        for (UnitType type : UnitTypes.getAllUnitTypes()) {
            props[type.getID()] = new UnitProperties(bwapi);
            props[type.getID()].SetType(bwapi.getUnitType(type.getID()));
        }

        double standardSpeed = bwapi.getUnitType(UnitTypes.Terran_SCV.getID()).getTopSpeed();

        props[UnitTypes.Terran_Ghost.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Moebius_Reactor.getID()));
        props[UnitTypes.Terran_Ghost.getID()].SetSightUpgrade(bwapi.getUpgradeType(UpgradeTypes.Ocular_Implants.getID()), 11);

        props[UnitTypes.Terran_Medic.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Caduceus_Reactor.getID()));

        props[UnitTypes.Terran_Vulture.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Ion_Thrusters.getID()), standardSpeed * 1.881);

        props[UnitTypes.Terran_Wraith.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Apollo_Reactor.getID()));

        props[UnitTypes.Terran_Battlecruiser.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Colossus_Reactor.getID()));
        props[UnitTypes.Terran_Science_Vessel.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Titan_Reactor.getID()));


        props[UnitTypes.Zerg_Zergling.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Metabolic_Boost.getID()), standardSpeed * 1.615);

        props[UnitTypes.Zerg_Hydralisk.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Muscular_Augments.getID()), standardSpeed * 1.105);

        props[UnitTypes.Zerg_Ultralisk.getID()].SetExtraArmorUpgrade(bwapi.getUpgradeType(UpgradeTypes.Chitinous_Plating.getID()), 2);
        props[UnitTypes.Zerg_Ultralisk.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Anabolic_Synthesis.getID()), standardSpeed * 1.556);

        props[UnitTypes.Zerg_Defiler.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Metasynaptic_Node.getID()));

        props[UnitTypes.Zerg_Overlord.getID()].SetSightUpgrade(bwapi.getUpgradeType(UpgradeTypes.Antennae.getID()), 11);
        props[UnitTypes.Zerg_Overlord.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Pneumatized_Carapace.getID()), bwapi.getUnitType(UnitTypes.Protoss_Carrier.getID()).getTopSpeed());

        props[UnitTypes.Zerg_Queen.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Gamete_Meiosis.getID()));


        props[UnitTypes.Protoss_Zealot.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Leg_Enhancements.getID()), standardSpeed * 1.167);

        props[UnitTypes.Protoss_High_Templar.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Khaydarin_Amulet.getID()));

        props[UnitTypes.Protoss_Reaver.getID()].SetCapacityUpgrade(bwapi.getUpgradeType(UpgradeTypes.Reaver_Capacity.getID()), 5, 10);

        props[UnitTypes.Protoss_Dark_Archon.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Argus_Talisman.getID()));

        props[UnitTypes.Protoss_Observer.getID()].SetSightUpgrade(bwapi.getUpgradeType(UpgradeTypes.Sensor_Array.getID()), 11);
        props[UnitTypes.Protoss_Observer.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Gravitic_Boosters.getID()), bwapi.getUnitType(UnitTypes.Protoss_Corsair.getID()).getTopSpeed());

        props[UnitTypes.Protoss_Shuttle.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Gravitic_Drive.getID()), bwapi.getUnitType(UnitTypes.Protoss_Corsair.getID()).getTopSpeed());

        props[UnitTypes.Protoss_Scout.getID()].SetSightUpgrade(bwapi.getUpgradeType(UpgradeTypes.Apial_Sensors.getID()), 10);
        props[UnitTypes.Protoss_Scout.getID()].SetSpeedUpgrade(bwapi.getUpgradeType(UpgradeTypes.Gravitic_Thrusters.getID()), bwapi.getUnitType(UnitTypes.Protoss_Corsair.getID()).getTopSpeed());

        props[UnitTypes.Protoss_Corsair.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Argus_Jewel.getID()));

        props[UnitTypes.Protoss_Carrier.getID()].SetCapacityUpgrade(bwapi.getUpgradeType(UpgradeTypes.Carrier_Capacity.getID()), 4, 8);

        props[UnitTypes.Protoss_Arbiter.getID()].SetEnergyUpgrade(bwapi.getUpgradeType(UpgradeTypes.Khaydarin_Core.getID()));
    }

    public static UnitProperties Get(UnitType type2) {
        // TODO Auto-generated method stub
        return props[type2.getID()];
    }

    public static UnitProperties Get(int unitTypeID) {
        return props[unitTypeID];
    }

    public void SetType(UnitType type) {
        if (type != null) {
            this.type = type;

            maxEnergy[0] = maxEnergy[1] = type.getMaxEnergy();
            sightRange[0] = sightRange[1] = type.getSightRange() << pixelShift;
            extraArmor[0] = extraArmor[1] = 0;
            speed[0] = speed[1] = (int) ((1 << pixelShift) * type.getTopSpeed());
        }
    }

    public void SetSpeedUpgrade(UpgradeType upgrade, double rate) {
        if (upgrade != null) {
            speedUpgrade = upgrade;
            speed[1] = (int) ((1 << pixelShift) * rate);
        }
    }

    public void SetCapacityUpgrade(UpgradeType upgrade, int capacity0, int capacity1) {
        if (upgrade != null) {
            capacityUpgrade = upgrade;
            capacity[0] = capacity0;
            capacity[1] = capacity1;
        }
    }

    public void SetEnergyUpgrade(UpgradeType upgrade) {
        if (upgrade != null) {
            maxEnergyUpgrade = upgrade;
            maxEnergy[1] = 250;
        }
    }

    public void SetSightUpgrade(UpgradeType upgrade, int range) {
        if (upgrade != null) {
            sightUpgrade = upgrade;
            sightRange[1] = (range << 5) << pixelShift;
        }
    }

    public void SetExtraArmorUpgrade(UpgradeType upgrade, int amount) {
        if (upgrade != null) {
            extraArmorUpgrade = upgrade;
            extraArmor[1] = amount;
        }
    }

    public int GetArmor(PlayerProperties player) {
        return type.getArmor() + player.GetUpgradeLevel(type.getArmorUpgradeID()) + extraArmor[player.GetUpgradeLevel(extraArmorUpgrade)];
    }

    public int GetCapacity(PlayerProperties player) {
        return capacity[player.GetUpgradeLevel(capacityUpgrade)];
    }

    public int GetMaxEnergy(PlayerProperties player) {
        return maxEnergy[player.GetUpgradeLevel(maxEnergyUpgrade)];
    }

    public int GetSight(PlayerProperties player) {
        return sightRange[player.GetUpgradeLevel(sightUpgrade)];
    }

    public int GetSpeed(PlayerProperties player) {
        return speed[player.GetUpgradeLevel(speedUpgrade)];
    }

    public WeaponProperties GetGroundWeapon() {
        return WeaponProperties.Get(type.getGroundWeaponID());
    }

    public WeaponProperties GetAirWeapon() {
        return WeaponProperties.Get(type.getAirWeaponID());
    }
}
