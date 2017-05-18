/**
 * This file is an extension to code based on and translated from the open source project: Sparcraft
 * https://code.google.com/p/sparcraft/
 * author of the source: David Churchill
 **/
package bwmcts.uct.rguctcd;

import bwmcts.sparcraft.Unit;
import bwmcts.uct.NodeType;
import bwmcts.uct.UctNode;
import bwmcts.uct.UnitState;

import java.util.List;

public class RGuctNode extends UctNode {

    private List<UnitState> abstractMove;
    private List<List<Unit>> clusters;

    public RGuctNode(RGuctNode parent, NodeType type, List<UnitState> abstractMove, int movingPlayerIndex, String label) {
        super(parent, type, null, movingPlayerIndex, label);
        this.abstractMove = abstractMove;
    }

    @Override
    public String moveString() {
        StringBuilder moves = new StringBuilder();
        for (UnitState a : abstractMove) {
            moves.append(a.type).append(";");
        }
        return moves.toString();
    }

    public List<UnitState> getAbstractMove() {
        return abstractMove;
    }

    public void setAbstractMove(List<UnitState> abstractMove) {
        this.abstractMove = abstractMove;
    }

    public List<List<Unit>> getClusters() {
        return clusters;
    }

    public void setClusters(List<List<Unit>> clusters) {
        this.clusters = clusters;
    }

}