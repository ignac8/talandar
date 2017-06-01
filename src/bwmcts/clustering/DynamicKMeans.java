package bwmcts.clustering;

import bwmcts.sparcraft.Position;
import bwmcts.sparcraft.Unit;
import jnibwapi.types.UnitType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicKMeans implements ClusteringAlgorithm {

    private double minDistance;

    public DynamicKMeans(double minDistance) {
        super();
        this.minDistance = minDistance;
    }

    public List<List<Unit>> getClusters(Unit[] uarr, int k, double hp) {

        //List<Unit> units = new ArrayList<Unit>();
        List<Unit> units = getReadyUnits(uarr);
        /*
        for(Unit unit : uarr){
			if (unit==null)
				break;
			units.add(unit);
		}*/

        KMeans kmeans = new KMeans();
        Map<UnitType, List<Unit>> types = splitByType(units);

        List<List<Unit>> clusters = new ArrayList<>();
        for (UnitType type : types.keySet())
            clusters.add(types.get(type));

        int r = 1;
        while (refine(clusters, kmeans)) {
            r++;
        }

        return clusters;

    }

    private List<Unit> getReadyUnits(Unit[] units) {

        List<Unit> ready = new ArrayList<>();
        for (Unit unit : units) {
            if (unit == null)
                continue;
            if (unit.currentHP <= 0)
                continue;
            //if (unit.canAttackNow() || unit.canAttackNow() || unit.canHealNow()){
            ready.add(unit);
            //}
        }

        return ready;
    }

    private boolean refine(List<List<Unit>> clusters, KMeans kmeans) {
        List<List<Unit>> removedClusters = new ArrayList<>();
        List<List<Unit>> newClusters = new ArrayList<>();
        for (List<Unit> units : clusters) {
            if (getMaxDistance(new KMeansCluster(units)) > minDistance) {
                List<List<Unit>> split = kmeans.getClusters(units, 2, 0);
                removedClusters.add(units);
                newClusters.add(split.get(0));
                newClusters.add(split.get(1));
            }
        }

        if (removedClusters.isEmpty())
            return false;

        clusters.addAll(newClusters);
        clusters.removeAll(removedClusters);
        return true;

    }

    public double getAverageDistance(KMeansCluster cluster) {

        double distance = 0.0;
        for (Unit unit : cluster.getUnits())
            distance += distance(cluster.getMean(), unit.getPosition());

        return distance / (double) cluster.getUnits().size();
    }

    public double getMaxDistance(KMeansCluster cluster) {

        double maxDistance = Double.MIN_VALUE;
        for (Unit unit : cluster.getUnits()) {
            double dis = distance(cluster.getMean(), unit.getPosition());
            if (dis > maxDistance)
                maxDistance = dis;
        }

        return maxDistance;
    }

    private double distance(Position a, Position b) {

        double disX = a.getX() - b.getX();
        double disY = a.getY() - b.getY();

        double distance = Math.sqrt(disX * disX + disY * disY);

        return distance;
    }

    private Map<UnitType, List<Unit>> splitByType(List<Unit> units) {

        Map<UnitType, List<Unit>> splitted = new HashMap<>();

        for (Unit unit : units) {

            if (!splitted.containsKey(unit.getUnitType()))
                splitted.put(unit.getUnitType(), new ArrayList<>());

            splitted.get(unit.getUnitType()).add(unit);

        }

        return splitted;

    }

    @Override
    public String toString() {
        return "DynamicKMeans";
    }
}
