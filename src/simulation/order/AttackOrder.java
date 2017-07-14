package simulation.order;

import jnibwapi.types.DamageType;
import jnibwapi.types.UnitSizeType;
import jnibwapi.types.UnitType;
import jnibwapi.types.WeaponType;
import simulation.GameState;
import simulation.Unit;

import static jnibwapi.types.DamageType.DamageTypes.Concussive;
import static jnibwapi.types.DamageType.DamageTypes.Explosive;
import static jnibwapi.types.UnitSizeType.UnitSizeTypes.Large;
import static jnibwapi.types.UnitSizeType.UnitSizeTypes.Medium;
import static jnibwapi.types.UnitSizeType.UnitSizeTypes.Small;

public class AttackOrder extends Order {
    private Unit unitToAttack;

    @Override
    public void execute(GameState currentGameState, GameState futureGameState) {
        Unit futureUnitToOrder = futureGameState.getUnits().get(unitToOrder);
        Unit futureUnitToAttack = futureGameState.getUnits().get(unitToAttack);

        WeaponType weaponType;
        int numberOfAttacks;
        UnitType unitToOrderUnitType = unitToOrder.getUnitType();
        UnitType unitToAttackUnitType = unitToAttack.getUnitType();
        if (unitToAttackUnitType.isFlyer()) {
            weaponType = unitToOrderUnitType.getAirWeapon();
            numberOfAttacks = unitToOrderUnitType.getMaxAirHits();
        } else {
            weaponType = unitToOrderUnitType.getGroundWeapon();
            numberOfAttacks = unitToOrderUnitType.getMaxGroundHits();
        }
        for (int counter = 0; counter < numberOfAttacks; counter++) {
            futureUnitToAttack.setCurrentShields(futureUnitToAttack.getCurrentShields() - weaponType.getDamageAmount());
            if (unitToAttack.getCurrentShields() < 0) {
                double leftoverDamage = -1 * futureUnitToAttack.getCurrentShields();
                futureUnitToAttack.setCurrentShields(0);
                leftoverDamage -= unitToAttackUnitType.getArmor();
                DamageType damageType = weaponType.getDamageType();
                UnitSizeType unitSizeType = unitToAttackUnitType.getUnitSizeType();
                if (damageType == Explosive) {
                    if (unitSizeType == Medium) {
                        leftoverDamage *= 0.75;
                    } else if (unitSizeType == Small) {
                        leftoverDamage *= 0.5;
                    }
                } else if (damageType == Concussive) {
                    if (unitSizeType == Large) {
                        leftoverDamage *= 0.25;
                    } else if (unitSizeType == Medium) {
                        leftoverDamage *= 0.5;
                    }
                }
                if (leftoverDamage < 0.5) {
                    leftoverDamage = 0.5;
                }
                futureUnitToAttack.setCurrentHitPoints(futureUnitToAttack.getCurrentHitPoints() - leftoverDamage);
            }
        }
    }
}
