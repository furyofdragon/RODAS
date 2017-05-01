package core;

import java.util.Vector;

public class FemModel {
	
	public static int nPoints;				// number of points
	public static int nRods;				// number of rods
	public static int nSections;			// number of sections
	public static int nMaterials;			// number of materials
	
	public static Vector<Node> vNodes = new Vector<Node>();;
	public static Vector<Rod> vRods = new Vector<Rod>();
	public static Vector<Section> vSections = new Vector<Section>();
	public static Vector<Material> vMaterials = new Vector<Material>();
	
	static public Vector<Node> getvNodes () {
		return vNodes;
	}
	
	static public Vector<Rod> getvRods () {
		return vRods;
	}

	public static Vector<Section> getvSections() {
		return vSections;
	}

	public static Vector<Material> getvMaterials() {
		return vMaterials;
	}
	
	static Node getNodeById (int id) {
		Node returnNode = null;
		for (int i = 0; i < vNodes.size(); i++) {
			if (vNodes.elementAt(i).id == id) {
				returnNode = vNodes.elementAt(i);
			}
		}
		return returnNode;
	}
	
	static Material getMaterialById (int id) {
		Material returnMaterial = null;
		for (int i = 0; i < vMaterials.size(); i++) {
			if (vMaterials.elementAt(i).id == id) {
				returnMaterial = vMaterials.elementAt(i);
			}
		}
		return returnMaterial;
	}
	
	static Section getSectionById (int id) {
		Section returnSection = null;
		for (int i = 0; i < vSections.size(); i++) {
			if (vSections.elementAt(i).id == id) {
				returnSection = vSections.elementAt(i);
			}
		}
		return returnSection;
	}

}
