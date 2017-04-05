/**
 * This file is based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.sparcraft;

import jnibwapi.types.UnitType;
import jnibwapi.types.UnitType.UnitTypes;

public class AnimationFrameData {
    public static int[][] attackFrameData = new int[UnitTypes.getAllUnitTypes().size()][2];

    public static void Init() {
        // allocate the vector according to UnitType size
        for (int i = 0; i < UnitTypes.getAllUnitTypes().size(); i++) {
            attackFrameData[i] = new int[]{0, 0};
        }

        // Protoss Units
        attackFrameData[UnitTypes.Protoss_Probe.getID()] = new int[]{2, 2};
        attackFrameData[UnitTypes.Protoss_Zealot.getID()] = new int[]{8, 7};
        attackFrameData[UnitTypes.Protoss_Dragoon.getID()] = new int[]{7, 3};
        attackFrameData[UnitTypes.Protoss_Dark_Templar.getID()] = new int[]{9, 9};
        attackFrameData[UnitTypes.Protoss_Scout.getID()] = new int[]{2, 2};
        attackFrameData[UnitTypes.Protoss_Corsair.getID()] = new int[]{8, 8};
        attackFrameData[UnitTypes.Protoss_Arbiter.getID()] = new int[]{2, 2};

        // Terran Units
        attackFrameData[UnitTypes.Terran_SCV.getID()] = new int[]{2, 2};
        attackFrameData[UnitTypes.Terran_Marine.getID()] = new int[]{8, 6};
        attackFrameData[UnitTypes.Terran_Firebat.getID()] = new int[]{8, 8};
        attackFrameData[UnitTypes.Terran_Ghost.getID()] = new int[]{3, 2};
        attackFrameData[UnitTypes.Terran_Vulture.getID()] = new int[]{1, 1};
        attackFrameData[UnitTypes.Terran_Goliath.getID()] = new int[]{1, 1};
        attackFrameData[UnitTypes.Terran_Siege_Tank_Tank_Mode.getID()] = new int[]{1, 1};
        attackFrameData[UnitTypes.Terran_Siege_Tank_Siege_Mode.getID()] = new int[]{1, 1};
        attackFrameData[UnitTypes.Terran_Wraith.getID()] = new int[]{2, 2};
        attackFrameData[UnitTypes.Terran_Battlecruiser.getID()] = new int[]{2, 2};
        attackFrameData[UnitTypes.Terran_Valkyrie.getID()] = new int[]{40, 40};

        // Zerg Units
        attackFrameData[UnitTypes.Zerg_Drone.getID()] = new int[]{2, 2};
        attackFrameData[UnitTypes.Zerg_Zergling.getID()] = new int[]{5, 5};
        attackFrameData[UnitTypes.Zerg_Hydralisk.getID()] = new int[]{3, 2};
        attackFrameData[UnitTypes.Zerg_Lurker.getID()] = new int[]{2, 2};
        attackFrameData[UnitTypes.Zerg_Ultralisk.getID()] = new int[]{14, 14};
        attackFrameData[UnitTypes.Zerg_Mutalisk.getID()] = new int[]{1, 1};
        attackFrameData[UnitTypes.Zerg_Devourer.getID()] = new int[]{9, 9};
    }

    public static int[] getAttackFrames(UnitType type) {
        return attackFrameData[type.getID()];
    }
}


