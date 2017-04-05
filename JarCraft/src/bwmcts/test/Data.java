package bwmcts.test;

import jnibwapi.types.DamageType;
import jnibwapi.types.ExplosionType;
import jnibwapi.types.RaceType;
import jnibwapi.types.TechType;
import jnibwapi.types.UnitType;
import jnibwapi.types.UpgradeType;
import jnibwapi.types.WeaponType;


public class Data {

    public static int[] getTechTypes() {

        int[] data = new int[]{0, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 0, UnitType.UnitTypes.Terran_Academy.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                1, RaceType.RaceTypes.Terran.getID(), 200, 200, 1500, 100, UnitType.UnitTypes.Terran_Covert_Ops.getID(), WeaponType.WeaponTypes.Lockdown.getID(), 0, 0,
                2, RaceType.RaceTypes.Terran.getID(), 200, 200, 1800, 100, UnitType.UnitTypes.Terran_Science_Facility.getID(), WeaponType.WeaponTypes.EMP_Shockwave.getID(), 0, 0,
                3, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 0, UnitType.UnitTypes.Terran_Machine_Shop.getID(), WeaponType.WeaponTypes.Spider_Mines.getID(), 0, 0,
                4, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 50, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                5, RaceType.RaceTypes.Terran.getID(), 150, 150, 1200, 0, UnitType.UnitTypes.Terran_Machine_Shop.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                6, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 100, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                7, RaceType.RaceTypes.Terran.getID(), 200, 200, 1200, 75, UnitType.UnitTypes.Terran_Science_Facility.getID(), WeaponType.WeaponTypes.Irradiate.getID(), 0, 0,
                8, RaceType.RaceTypes.Terran.getID(), 100, 100, 1800, 150, UnitType.UnitTypes.Terran_Physics_Lab.getID(), WeaponType.WeaponTypes.Yamato_Gun.getID(), 0, 0,
                9, RaceType.RaceTypes.Terran.getID(), 150, 150, 1500, 25, UnitType.UnitTypes.Terran_Control_Tower.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                10, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 25, UnitType.UnitTypes.Terran_Covert_Ops.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                11, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1200, 0, UnitType.UnitTypes.Zerg_Hatchery.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                12, RaceType.RaceTypes.Zerg.getID(), 0, 0, 0, 0, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                13, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1200, 150, UnitType.UnitTypes.Zerg_Queens_Nest.getID(), WeaponType.WeaponTypes.Spawn_Broodlings.getID(), 0, 0,
                14, RaceType.RaceTypes.Zerg.getID(), 0, 0, 0, 100, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.Dark_Swarm.getID(), 0, 0,
                15, RaceType.RaceTypes.Zerg.getID(), 200, 200, 1500, 150, UnitType.UnitTypes.Zerg_Defiler_Mound.getID(), WeaponType.WeaponTypes.Plague.getID(), 0, 0,
                16, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1500, 0, UnitType.UnitTypes.Zerg_Defiler_Mound.getID(), WeaponType.WeaponTypes.Consume.getID(), 0, 0,
                17, RaceType.RaceTypes.Zerg.getID(), 100, 100, 1200, 75, UnitType.UnitTypes.Zerg_Queens_Nest.getID(), WeaponType.WeaponTypes.Ensnare.getID(), 0, 0,
                18, RaceType.RaceTypes.Zerg.getID(), 0, 0, 0, 75, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.Parasite.getID(), 0, 0,
                19, RaceType.RaceTypes.Protoss.getID(), 200, 200, 1800, 75, UnitType.UnitTypes.Protoss_Templar_Archives.getID(), WeaponType.WeaponTypes.Psionic_Storm.getID(), 0, 0,
                20, RaceType.RaceTypes.Protoss.getID(), 150, 150, 1200, 100, UnitType.UnitTypes.Protoss_Templar_Archives.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                21, RaceType.RaceTypes.Protoss.getID(), 150, 150, 1800, 150, UnitType.UnitTypes.Protoss_Arbiter_Tribunal.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                22, RaceType.RaceTypes.Protoss.getID(), 150, 150, 1500, 100, UnitType.UnitTypes.Protoss_Arbiter_Tribunal.getID(), WeaponType.WeaponTypes.Stasis_Field.getID(), 0, 0,
                23, RaceType.RaceTypes.Protoss.getID(), 0, 0, 0, 0, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                24, RaceType.RaceTypes.Terran.getID(), 100, 100, 1200, 50, UnitType.UnitTypes.Terran_Academy.getID(), WeaponType.WeaponTypes.Restoration.getID(), 0, 0,
                25, RaceType.RaceTypes.Protoss.getID(), 200, 200, 1200, 125, UnitType.UnitTypes.Protoss_Fleet_Beacon.getID(), WeaponType.WeaponTypes.Disruption_Web.getID(), 0, 0,
                26, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                27, RaceType.RaceTypes.Protoss.getID(), 200, 200, 1800, 150, UnitType.UnitTypes.Protoss_Templar_Archives.getID(), WeaponType.WeaponTypes.Mind_Control.getID(), 0, 0,
                28, RaceType.RaceTypes.Protoss.getID(), 0, 0, 0, 0, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                29, RaceType.RaceTypes.Protoss.getID(), 0, 0, 0, 50, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.Feedback.getID(), 0, 0,
                30, RaceType.RaceTypes.Terran.getID(), 100, 100, 1800, 75, UnitType.UnitTypes.Terran_Academy.getID(), WeaponType.WeaponTypes.Optical_Flare.getID(), 0, 0,
                31, RaceType.RaceTypes.Protoss.getID(), 100, 100, 1500, 100, UnitType.UnitTypes.Protoss_Templar_Archives.getID(), WeaponType.WeaponTypes.Maelstrom.getID(), 0, 0,
                32, RaceType.RaceTypes.Zerg.getID(), 200, 200, 1800, 0, UnitType.UnitTypes.Zerg_Hydralisk_Den.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                33, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                34, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 1, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                35, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                36, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                37, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                38, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                39, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                40, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                41, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                42, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                43, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                44, 0, 0, 0, 0, 0, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                45, 0, 0, 0, 0, 0, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.None.getID(), 0, 0,
                46, RaceType.RaceTypes.Terran.getID(), 0, 0, 0, 0, UnitType.UnitTypes.None.getID(), WeaponType.WeaponTypes.Nuclear_Strike.getID(), 0, 0
        };


        return data;
    }

    public static int[] getUpgradeTypes() {

        int[] data = new int[]{
                0, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitType.UnitTypes.Terran_Engineering_Bay.getID(),
                1, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitType.UnitTypes.Terran_Armory.getID(),
                2, RaceType.RaceTypes.Terran.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitType.UnitTypes.Terran_Armory.getID(),
                3, RaceType.RaceTypes.Zerg.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitType.UnitTypes.Zerg_Evolution_Chamber.getID(),
                4, RaceType.RaceTypes.Zerg.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitType.UnitTypes.Zerg_Spire.getID(),
                5, RaceType.RaceTypes.Protoss.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitType.UnitTypes.Protoss_Forge.getID(),
                6, RaceType.RaceTypes.Protoss.getID(), 150, 75, 150, 75, 4000, 480, 3, UnitType.UnitTypes.Protoss_Cybernetics_Core.getID(),
                7, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitType.UnitTypes.Terran_Engineering_Bay.getID(),
                8, RaceType.RaceTypes.Terran.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitType.UnitTypes.Terran_Armory.getID(),
                9, RaceType.RaceTypes.Terran.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitType.UnitTypes.Terran_Armory.getID(),
                10, RaceType.RaceTypes.Zerg.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitType.UnitTypes.Zerg_Evolution_Chamber.getID(),
                11, RaceType.RaceTypes.Zerg.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitType.UnitTypes.Zerg_Evolution_Chamber.getID(),
                12, RaceType.RaceTypes.Zerg.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitType.UnitTypes.Zerg_Spire.getID(),
                13, RaceType.RaceTypes.Protoss.getID(), 100, 50, 100, 50, 4000, 480, 3, UnitType.UnitTypes.Protoss_Forge.getID(),
                14, RaceType.RaceTypes.Protoss.getID(), 100, 75, 100, 75, 4000, 480, 3, UnitType.UnitTypes.Protoss_Cybernetics_Core.getID(),
                15, RaceType.RaceTypes.Protoss.getID(), 200, 100, 200, 100, 4000, 480, 3, UnitType.UnitTypes.Protoss_Forge.getID(),
                16, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 1500, 0, 1, UnitType.UnitTypes.Terran_Academy.getID(),
                17, RaceType.RaceTypes.Terran.getID(), 100, 0, 100, 0, 1500, 0, 1, UnitType.UnitTypes.Terran_Machine_Shop.getID(),
                18, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                19, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Terran_Science_Facility.getID(),
                20, RaceType.RaceTypes.Terran.getID(), 100, 0, 100, 0, 2500, 0, 1, UnitType.UnitTypes.Terran_Covert_Ops.getID(),
                21, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Terran_Covert_Ops.getID(),
                22, RaceType.RaceTypes.Terran.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitType.UnitTypes.Terran_Control_Tower.getID(),
                23, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Terran_Physics_Lab.getID(),
                24, RaceType.RaceTypes.Zerg.getID(), 200, 0, 200, 0, 2400, 0, 1, UnitType.UnitTypes.Zerg_Lair.getID(),
                25, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitType.UnitTypes.Zerg_Lair.getID(),
                26, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitType.UnitTypes.Zerg_Lair.getID(),
                27, RaceType.RaceTypes.Zerg.getID(), 100, 0, 100, 0, 1500, 0, 1, UnitType.UnitTypes.Zerg_Spawning_Pool.getID(),
                28, RaceType.RaceTypes.Zerg.getID(), 200, 0, 200, 0, 1500, 0, 1, UnitType.UnitTypes.Zerg_Spawning_Pool.getID(),
                29, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 1500, 0, 1, UnitType.UnitTypes.Zerg_Hydralisk_Den.getID(),
                30, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 1500, 0, 1, UnitType.UnitTypes.Zerg_Hydralisk_Den.getID(),
                31, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Zerg_Queens_Nest.getID(),
                32, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Zerg_Defiler_Mound.getID(),
                33, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Cybernetics_Core.getID(),
                34, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitType.UnitTypes.Protoss_Citadel_of_Adun.getID(),
                35, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Robotics_Support_Bay.getID(),
                36, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Robotics_Support_Bay.getID(),
                37, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Robotics_Support_Bay.getID(),
                38, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitType.UnitTypes.Protoss_Observatory.getID(),
                39, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitType.UnitTypes.Protoss_Observatory.getID(),
                40, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Templar_Archives.getID(),
                41, RaceType.RaceTypes.Protoss.getID(), 100, 0, 100, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Fleet_Beacon.getID(),
                42, RaceType.RaceTypes.Protoss.getID(), 200, 0, 200, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Fleet_Beacon.getID(),
                43, RaceType.RaceTypes.Protoss.getID(), 100, 0, 100, 0, 1500, 0, 1, UnitType.UnitTypes.Protoss_Fleet_Beacon.getID(),
                44, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Arbiter_Tribunal.getID(),
                45, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                46, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                47, RaceType.RaceTypes.Protoss.getID(), 100, 0, 100, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Fleet_Beacon.getID(),
                48, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                49, RaceType.RaceTypes.Protoss.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Protoss_Templar_Archives.getID(),
                50, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                51, RaceType.RaceTypes.Terran.getID(), 150, 0, 150, 0, 2500, 0, 1, UnitType.UnitTypes.Terran_Academy.getID(),
                52, RaceType.RaceTypes.Zerg.getID(), 150, 0, 150, 0, 2000, 0, 1, UnitType.UnitTypes.Zerg_Ultralisk_Cavern.getID(),
                53, RaceType.RaceTypes.Zerg.getID(), 200, 0, 200, 0, 2000, 0, 1, UnitType.UnitTypes.Zerg_Ultralisk_Cavern.getID(),
                54, RaceType.RaceTypes.Terran.getID(), 100, 0, 100, 0, 2000, 0, 1, UnitType.UnitTypes.Terran_Machine_Shop.getID(),
                55, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                56, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                57, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                58, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                59, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                60, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                61, 0, 0, 0, 0, 0, 0, 0, 0, UnitType.UnitTypes.None.getID(),
                62, 0, 0, 0, 0, 0, 0, 0, 0, UnitType.UnitTypes.None.getID()


        };


        return data;
    }

    public static int[] getWeaponTypes() {


        int[] data = new int[]{
                0, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Marine.getID(), 6, 1, 15, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                1, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Jim_Raynor_Marine.getID(), 18, 1, 15, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                2, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Ghost.getID(), 10, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                3, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Sarah_Kerrigan.getID(), 30, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                112, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Samir_Duran.getID(), 25, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                113, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Infested_Duran.getID(), 25, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                116, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Alexei_Stukov.getID(), 30, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                4, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Vulture.getID(), 20, 2, 30, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                5, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Jim_Raynor_Vulture.getID(), 30, 2, 22, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                6, TechType.TechTypes.Spider_Mines.getID(), UnitType.UnitTypes.Terran_Vulture_Spider_Mine.getID(), 125, 0, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Radial_Splash.getID(), 0, 10, 50, 75, 100, 0, 1, 0, 0, 1, 0, 0, 0, 0,
                7, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Goliath.getID(), 12, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                9, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Alan_Schezar.getID(), 24, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                8, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Goliath.getID(), 10, 2, 22, 2, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                10, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Alan_Schezar.getID(), 20, 1, 22, 2, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                11, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Siege_Tank_Tank_Mode.getID(), 30, 3, 37, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                12, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Edmund_Duke_Tank_Mode.getID(), 70, 3, 37, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                13, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_SCV.getID(), 5, 1, 15, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 10, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                15, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Wraith.getID(), 20, 2, 22, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                17, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Tom_Kazansky.getID(), 40, 2, 22, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                16, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Wraith.getID(), 8, 1, 30, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                18, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Tom_Kazansky.getID(), 16, 1, 30, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                19, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Battlecruiser.getID(), 25, 3, 30, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                21, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Norad_II.getID(), 50, 3, 30, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                23, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Hyperion.getID(), 30, 3, 22, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                20, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Battlecruiser.getID(), 25, 3, 30, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                22, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Norad_II.getID(), 50, 3, 30, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                24, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Hyperion.getID(), 30, 3, 22, 1, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                25, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Firebat.getID(), 8, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Enemy_Splash.getID(), 0, 32, 15, 20, 25, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                26, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Firebat.getID(), 16, 1, 22, 1, UpgradeType.UpgradeTypes.Terran_Infantry_Weapons.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Enemy_Splash.getID(), 0, 32, 15, 20, 25, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                27, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Siege_Tank_Siege_Mode.getID(), 70, 5, 75, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Radial_Splash.getID(), 64, 384, 10, 25, 40, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                28, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Edmund_Duke_Siege_Mode.getID(), 150, 5, 75, 1, UpgradeType.UpgradeTypes.Terran_Vehicle_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Radial_Splash.getID(), 64, 384, 10, 25, 40, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                29, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Missile_Turret.getID(), 20, 0, 15, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                30, TechType.TechTypes.Yamato_Gun.getID(), UnitType.UnitTypes.Terran_Battlecruiser.getID(), 260, 0, 15, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Yamato_Gun.getID(), 0, 320, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                31, TechType.TechTypes.Nuclear_Strike.getID(), UnitType.UnitTypes.Terran_Ghost.getID(), 600, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Nuclear_Missile.getID(), 0, 3, 128, 192, 256, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                32, TechType.TechTypes.Lockdown.getID(), UnitType.UnitTypes.Terran_Ghost.getID(), 0, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.Lockdown.getID(), 0, 256, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0,
                33, TechType.TechTypes.EMP_Shockwave.getID(), UnitType.UnitTypes.Terran_Science_Vessel.getID(), 0, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Concussive.getID(), ExplosionType.ExplosionTypes.EMP_Shockwave.getID(), 0, 256, 64, 64, 64, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                34, TechType.TechTypes.Irradiate.getID(), UnitType.UnitTypes.Terran_Science_Vessel.getID(), 250, 0, 75, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Ignore_Armor.getID(), ExplosionType.ExplosionTypes.Irradiate.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                35, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Zergling.getID(), 5, 1, 8, 1, UpgradeType.UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                36, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Devouring_One.getID(), 10, 1, 8, 1, UpgradeType.UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                37, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Infested_Kerrigan.getID(), 50, 1, 15, 1, UpgradeType.UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                38, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Hydralisk.getID(), 10, 1, 15, 1, UpgradeType.UpgradeTypes.Zerg_Missile_Attacks.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                39, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Hunter_Killer.getID(), 20, 1, 15, 1, UpgradeType.UpgradeTypes.Zerg_Missile_Attacks.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                40, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Ultralisk.getID(), 20, 3, 15, 1, UpgradeType.UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                41, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Torrasque.getID(), 50, 3, 15, 1, UpgradeType.UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 25, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                42, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Broodling.getID(), 4, 1, 15, 1, UpgradeType.UpgradeTypes.Zerg_Melee_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                43, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Drone.getID(), 5, 0, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 32, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                46, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Guardian.getID(), 20, 2, 30, 1, UpgradeType.UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 256, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                47, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Kukulza_Guardian.getID(), 40, 2, 30, 1, UpgradeType.UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 256, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                48, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Mutalisk.getID(), 9, 1, 30, 1, UpgradeType.UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 96, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                49, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Kukulza_Mutalisk.getID(), 18, 1, 30, 1, UpgradeType.UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 96, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                52, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Spore_Colony.getID(), 15, 0, 15, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                53, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Sunken_Colony.getID(), 40, 0, 32, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                54, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Infested_Terran.getID(), 500, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Radial_Splash.getID(), 0, 3, 20, 40, 60, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                55, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Scourge.getID(), 110, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                56, TechType.TechTypes.Parasite.getID(), UnitType.UnitTypes.Zerg_Queen.getID(), 0, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Parasite.getID(), 0, 384, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0,
                57, TechType.TechTypes.Spawn_Broodlings.getID(), UnitType.UnitTypes.Zerg_Queen.getID(), 0, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Broodlings.getID(), 0, 288, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0,
                58, TechType.TechTypes.Ensnare.getID(), UnitType.UnitTypes.Zerg_Queen.getID(), 0, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Ensnare.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                59, TechType.TechTypes.Dark_Swarm.getID(), UnitType.UnitTypes.Zerg_Defiler.getID(), 0, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Dark_Swarm.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                60, TechType.TechTypes.Plague.getID(), UnitType.UnitTypes.Zerg_Defiler.getID(), 300, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Plague.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                61, TechType.TechTypes.Consume.getID(), UnitType.UnitTypes.Zerg_Defiler.getID(), 0, 0, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Consume.getID(), 0, 16, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1,
                62, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Probe.getID(), 5, 0, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 32, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                64, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Zealot.getID(), 8, 1, 22, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                65, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Fenix_Zealot.getID(), 20, 1, 22, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                66, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Dragoon.getID(), 20, 2, 30, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                67, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Fenix_Dragoon.getID(), 45, 2, 22, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                69, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Tassadar.getID(), 20, 1, 22, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 96, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                70, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Archon.getID(), 30, 3, 20, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Enemy_Splash.getID(), 0, 64, 3, 15, 30, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                71, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Tassadar_Zeratul_Archon.getID(), 60, 3, 20, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Enemy_Splash.getID(), 0, 64, 3, 15, 30, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                73, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Scout.getID(), 8, 1, 30, 1, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                75, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Mojo.getID(), 20, 1, 30, 1, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                114, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Artanis.getID(), 20, 1, 30, 1, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                74, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Scout.getID(), 14, 1, 22, 2, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                76, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Mojo.getID(), 28, 1, 22, 2, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                115, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Artanis.getID(), 28, 1, 22, 2, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                77, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Arbiter.getID(), 10, 1, 45, 1, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                78, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Danimoth.getID(), 20, 1, 45, 1, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 160, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                79, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Interceptor.getID(), 6, 1, 1, 1, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 128, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0,
                80, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Photon_Cannon.getID(), 20, 0, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                81, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Photon_Cannon.getID(), 20, 0, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 224, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                82, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Scarab.getID(), 100, 25, 1, 1, UpgradeType.UpgradeTypes.Scarab_Damage.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Enemy_Splash.getID(), 0, 128, 20, 40, 60, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                83, TechType.TechTypes.Stasis_Field.getID(), UnitType.UnitTypes.Protoss_Arbiter.getID(), 0, 1, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Stasis_Field.getID(), 0, 288, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                84, TechType.TechTypes.Psionic_Storm.getID(), UnitType.UnitTypes.Protoss_High_Templar.getID(), 14, 1, 45, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Ignore_Armor.getID(), ExplosionType.ExplosionTypes.Radial_Splash.getID(), 0, 288, 48, 48, 48, 1, 1, 0, 0, 1, 0, 1, 0, 0,
                100, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Corsair.getID(), 5, 1, 8, 1, UpgradeType.UpgradeTypes.Protoss_Air_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Air_Splash.getID(), 0, 160, 5, 50, 100, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                101, TechType.TechTypes.Disruption_Web.getID(), UnitType.UnitTypes.Protoss_Corsair.getID(), 0, 0, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Ignore_Armor.getID(), ExplosionType.ExplosionTypes.Disruption_Web.getID(), 0, 288, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                102, TechType.TechTypes.Restoration.getID(), UnitType.UnitTypes.Terran_Medic.getID(), 20, 0, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Ignore_Armor.getID(), ExplosionType.ExplosionTypes.Restoration.getID(), 0, 192, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                103, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Terran_Valkyrie.getID(), 6, 1, 64, 2, UpgradeType.UpgradeTypes.Terran_Ship_Weapons.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Air_Splash.getID(), 0, 192, 5, 50, 100, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                104, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Devourer.getID(), 25, 2, 100, 1, UpgradeType.UpgradeTypes.Zerg_Flyer_Attacks.getID(), DamageType.DamageTypes.Explosive.getID(), ExplosionType.ExplosionTypes.Corrosive_Acid.getID(), 0, 192, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                105, TechType.TechTypes.Mind_Control.getID(), UnitType.UnitTypes.Protoss_Dark_Archon.getID(), 8, 1, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Mind_Control.getID(), 0, 256, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                106, TechType.TechTypes.Feedback.getID(), UnitType.UnitTypes.Protoss_Dark_Archon.getID(), 8, 1, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Ignore_Armor.getID(), ExplosionType.ExplosionTypes.Feedback.getID(), 0, 320, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                107, TechType.TechTypes.Optical_Flare.getID(), UnitType.UnitTypes.Terran_Medic.getID(), 8, 1, 22, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Optical_Flare.getID(), 0, 288, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                108, TechType.TechTypes.Maelstrom.getID(), UnitType.UnitTypes.Protoss_Dark_Archon.getID(), 0, 1, 1, 1, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.Independent.getID(), ExplosionType.ExplosionTypes.Maelstrom.getID(), 0, 320, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0,
                109, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Zerg_Lurker.getID(), 20, 2, 37, 1, UpgradeType.UpgradeTypes.Zerg_Missile_Attacks.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Enemy_Splash.getID(), 0, 192, 20, 20, 20, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                111, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Protoss_Dark_Templar.getID(), 40, 3, 30, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                86, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Dark_Templar.getID(), 45, 1, 30, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                85, TechType.TechTypes.None.getID(), UnitType.UnitTypes.Hero_Zeratul.getID(), 100, 1, 22, 1, UpgradeType.UpgradeTypes.Protoss_Ground_Weapons.getID(), DamageType.DamageTypes.Normal.getID(), ExplosionType.ExplosionTypes.Normal.getID(), 0, 15, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                130, TechType.TechTypes.None.getID(), UnitType.UnitTypes.None.getID(), 0, 0, 0, 0, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.None.getID(), ExplosionType.ExplosionTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                131, TechType.TechTypes.None.getID(), UnitType.UnitTypes.None.getID(), 0, 0, 0, 0, UpgradeType.UpgradeTypes.None.getID(), DamageType.DamageTypes.None.getID(), ExplosionType.ExplosionTypes.None.getID(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0


        };
        return data;
    }

    public static int[] getUnitSizeTypes() {
        int[] data = new int[]{0, 1, 3, 4, 5};
        return data;
    }

    public static int[] getBulletTypes() {
        int[] data = new int[211];
        for (int i = 0; i < 211; i++) {
            data[i] = i;
        }
        return data;
    }

    public static int[] getDamageTypes() {
        int[] data = new int[]{0, 1, 3, 4, 5, 6};
        return data;
    }

    public static int[] getExplosionTypes() {
        int[] data = new int[26];
        for (int i = 0; i < 26; i++) {
            data[i] = i;
        }
        return data;
    }

    public static int[] getUnitCommandTypes() {
        int[] data = new int[45];
        for (int i = 0; i < 45; i++) {
            data[i] = i;
        }
        return data;
    }

    public static int[] getOrderTypes() {
        int[] data = new int[]{0, 1, 2, 3, 4, 5, 6, 10, 12, 13, 14, 15, 16, 17, 18, 20, 23, 24, 27, 29, 30, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43,
                44, 46, 47, 49, 50, 51, 55, 58, 63, 64, 65, 66, 67, 68, 69, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 94, 95, 96, 97, 98, 99, 101, 102, 103, 104, 105, 106, 107, 109, 110, 111, 112, 113, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124,
                125, 127, 128, 129, 131, 132, 133, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 152, 153, 154, 155, 156, 157, 158, 159,
                160, 161, 162, 163, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190
        };

        return data;
    }
}
