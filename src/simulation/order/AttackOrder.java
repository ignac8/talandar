package simulation.order;

import jnibwapi.types.DamageType;
import jnibwapi.types.UnitSizeType;
import jnibwapi.types.UnitType;
import jnibwapi.types.WeaponType;
import simulation.SimulationState;
import simulation.Unit;

import static jnibwapi.types.DamageType.DamageTypes.Concussive;
import static jnibwapi.types.DamageType.DamageTypes.Explosive;
import static jnibwapi.types.UnitSizeType.UnitSizeTypes.Large;
import static jnibwapi.types.UnitSizeType.UnitSizeTypes.Medium;
import static jnibwapi.types.UnitSizeType.UnitSizeTypes.Small;

public class AttackOrder extends Order {
    private Unit unitToAttack;

    public AttackOrder(Unit unitToOrder, Unit unitToAttack) {
        super(unitToOrder);
        this.unitToAttack = unitToAttack;
    }

    public Unit getUnitToAttack() {
        return unitToAttack;
    }

    @Override
    public void execute(SimulationState currentSimulationState, SimulationState futureSimulationState,
                        double timeStep) {
        double time = currentSimulationState.getTime();
        if (unitToOrder.getHitPoints() > 0 && unitToOrder.getCooldownTime() <= time) {
            Unit futureUnitToAttack = futureSimulationState.getUnits().get(unitToAttack.getUnitId());
            Unit futureUnitToOrder = futureSimulationState.getUnits().get(unitToOrder.getUnitId());
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
            double distance = unitToOrder.getPosition().getDistance(unitToAttack.getPosition());
            if (distance <= weaponType.getMaxRange() && distance >= weaponType.getMinRange()) {
                for (int counter = 0; counter < numberOfAttacks; counter++) {
                    futureUnitToAttack.setShields(futureUnitToAttack.getShields() - weaponType.getDamageAmount());
                    if (futureUnitToAttack.getShields() < 0) {
                        double leftoverDamage = -1 * futureUnitToAttack.getShields();
                        futureUnitToAttack.setShields(0);
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
                        futureUnitToAttack.setHitPoints(futureUnitToAttack.getHitPoints() - leftoverDamage);
                        if (futureUnitToAttack.getHitPoints() < 0) {
                            futureUnitToAttack.setHitPoints(0);
                        }
                    }
                    futureUnitToOrder.setCooldownTime(time + weaponType.getDamageCooldown());
                    executed = true;
                }
            }

        }
    }
}
