package bwmcts.clustering;

import bwmcts.sparcraft.Unit;

import java.util.ArrayList;

public class KMedoidCluster {

    public ArrayList<Unit> ClusterMembers;
    public Unit Medoid;

    public KMedoidCluster(Unit medoid) {
        this.ClusterMembers = new ArrayList<>();
        this.Medoid = medoid;
    }

    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
        toPrintString += "Medoid: " + this.Medoid.toString() + System.getProperty("line.separator");
        /*
        for(Answer i : this.ClusterMembers)
		{
			toPrintString += i.toString() + System.getProperty("line.separator");
		}
		*/

        return toPrintString;
    }

}