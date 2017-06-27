package core;

import java.util.Vector;

public class FemModel {
	
	public static int nPoints;				// number of points
	public static int nRods;				// number of rods
	public static int nSections;			// number of sections
	public static int nMaterials;			// number of materials
	
	public static double mass;
	
	public static double xmin, xmax, ymin, ymax, zmin, zmax;
	
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
	
	double getMass() {
		return mass;
	}
	
	public static void calcMass() {
		double cmass = 0;
		for (int i = 0; i < nRods; i++) {
			cmass = cmass + vRods.elementAt(i).mass;
		}
		mass = cmass;
		
		main.Main.statusBar.setText("Model mass = " + Double.toString(mass));
		
	}
	
	public static void calcBoundaries() {
		xmin = 0; xmax = 0; ymin = 0; ymax = 0; zmin = 0; zmax = 0;
		
		for (int i = 0; i < nPoints; i++) {
			xmin = Math.min(xmin, vNodes.elementAt(i).x);
			ymin = Math.min(ymin, vNodes.elementAt(i).y);
			zmin = Math.min(zmin, vNodes.elementAt(i).z);
			xmax = Math.max(xmax, vNodes.elementAt(i).x);
			ymax = Math.max(ymax, vNodes.elementAt(i).y);
			zmax = Math.max(zmax, vNodes.elementAt(i).z);
		}
	}
	
	public static void updateFemModel() {
		// update materials
		for (int i = 0; i < FemModel.vMaterials.size(); i++) {
			FemModel.vMaterials.elementAt(i).setG();
		}
		
		// update rods
		for (int i = 0; i < FemModel.vRods.size(); i++) {
			FemModel.vRods.elementAt(i).setLength();
			FemModel.vRods.elementAt(i).setMass();
			FemModel.vRods.elementAt(i).calcKlocal();
			FemModel.vRods.elementAt(i).calcTransl();
			FemModel.vRods.elementAt(i).calcKglobal();
		}
		
		// calc model mass
		FemModel.calcMass();
		
		FemModel.calcBoundaries();
	}

}
