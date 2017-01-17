package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import core.Material;
import core.Node;
import core.Rod;
import core.Section;

public class ReadFemModel {
	
	static int N = 0;				// number of lines in input file
	static int nPoints;				// number of points
	static int nRods;				// number of rods
	static int nSections;
	static int nMaterials;
	
	static Vector<Node> vPoints;
	static Vector<Rod> vRods;
	static Vector<Section> vSections;
	static Vector<Material> vMaterials;

	static void ReadFemModelFile(String strFemModelFile) {
		
		String line = null;
		
		// read (cache) all file
		ArrayList<String> al = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(strFemModelFile));
			while ((line = br.readLine()) != null) {
				N = N+1;
				al.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int offset = 0;
		
		
		// read points (nodes)
		nPoints = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		vPoints = new Vector<Node>();
		for (int i = 0; i < nPoints; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			double x = Double.parseDouble(token.nextToken().trim());
			double y = Double.parseDouble(token.nextToken().trim());
			double z = Double.parseDouble(token.nextToken().trim());
			vPoints.add(new Node(id, x, y, z));
		}
		offset = offset + nPoints;
		
		
		// read rods
		nRods = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		vRods = new Vector<Rod>();
		for (int i = 0; i < nRods; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			int psid = Integer.parseInt(token.nextToken().trim());
			int peid = Integer.parseInt(token.nextToken().trim());
			int sid = Integer.parseInt(token.nextToken().trim());
			int mid = Integer.parseInt(token.nextToken().trim());
			vRods.add(new Rod(id, psid, peid, sid, mid));
		}
		offset = offset + nRods;
		
		
		// read sections
		nSections = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		vSections = new Vector<Section>();
		for (int i = 0; i < nSections; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			double area = Double.parseDouble(token.nextToken().trim());
			double Ix = Double.parseDouble(token.nextToken().trim());
			double Iy = Double.parseDouble(token.nextToken().trim());
			double Wx = Double.parseDouble(token.nextToken().trim());
			double Wy = Double.parseDouble(token.nextToken().trim());
			vSections.add(new Section(id, area, Ix, Iy, Wx, Wy));
		}
		offset = offset + nSections;
		
		
		// read materials
		nMaterials = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		vMaterials = new Vector<Material>();
		for (int i = 0; i < nMaterials; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			double E = Double.parseDouble(token.nextToken().trim());
			double mu = Double.parseDouble(token.nextToken().trim());
			double ro = Double.parseDouble(token.nextToken().trim());
			vMaterials.add(new Material(id, E, mu, ro));
		}
		offset = offset + nMaterials;
		
	}
	
	static public Vector<Node> getvPoints () {
		return vPoints;
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
	
}
