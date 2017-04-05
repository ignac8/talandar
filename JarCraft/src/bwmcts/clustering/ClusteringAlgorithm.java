package bwmcts.clustering;

import bwmcts.sparcraft.Unit;

import java.util.List;

public interface ClusteringAlgorithm {

    List<List<Unit>> getClusters(Unit[] units, int h, double hp);

    public String toString();
}
