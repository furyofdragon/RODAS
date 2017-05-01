package input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import core.FemModel;
import core.Material;
import core.Node;
import core.Rod;
import core.Section;

public class ReadFemModel {
	
	static int N = 0;				// number of lines in input file

	public static void ReadFemModelFile(String strFemModelFile) {
		
		String line = null;
		
		// read (cache) whole file
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
		FemModel.nPoints = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		//FemModel.vNodes = new Vector<Node>();
		for (int i = 0; i < FemModel.nPoints; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			double x = Double.parseDouble(token.nextToken().trim());
			double y = Double.parseDouble(token.nextToken().trim());
			double z = Double.parseDouble(token.nextToken().trim());
			FemModel.vNodes.add(new Node(id, x, y, z));
		}
		offset = offset + FemModel.nPoints;
		
		
		// read rods
		FemModel.nRods = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		//FemModel.vRods = new Vector<Rod>();
		for (int i = 0; i < FemModel.nRods; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			int psid = Integer.parseInt(token.nextToken().trim());
			int peid = Integer.parseInt(token.nextToken().trim());
			int sid = Integer.parseInt(token.nextToken().trim());
			int mid = Integer.parseInt(token.nextToken().trim());
			FemModel.vRods.add(new Rod(id, psid, peid, sid, mid));
		}
		offset = offset + FemModel.nRods;
		
		
		// read sections
		FemModel.nSections = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		//FemModel.vSections = new Vector<Section>();
		for (int i = 0; i < FemModel.nSections; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			double area = Double.parseDouble(token.nextToken().trim());
			double Ix = Double.parseDouble(token.nextToken().trim());
			double Iy = Double.parseDouble(token.nextToken().trim());
			double Wx = Double.parseDouble(token.nextToken().trim());
			double Wy = Double.parseDouble(token.nextToken().trim());
			FemModel.vSections.add(new Section(id, area, Ix, Iy, Wx, Wy));
		}
		offset = offset + FemModel.nSections;
		
		
		// read materials
		FemModel.nMaterials = Integer.parseInt(new StringTokenizer(al.get(offset), " ").nextToken().trim());
		offset = offset + 1;
		//FemModel.vMaterials = new Vector<Material>();
		for (int i = 0; i < FemModel.nMaterials; i++) {
			StringTokenizer token = new StringTokenizer(al.get(i+offset), " ");
			int id = Integer.parseInt(token.nextToken().trim());
			double E = Double.parseDouble(token.nextToken().trim());
			double mu = Double.parseDouble(token.nextToken().trim());
			double ro = Double.parseDouble(token.nextToken().trim());
			FemModel.vMaterials.add(new Material(id, E, mu, ro));
		}
		offset = offset + FemModel.nMaterials;
		
		// update rods
		for (int i = 0; i < FemModel.vRods.size(); i++) {
			FemModel.vRods.elementAt(i).setLength();
			FemModel.vRods.elementAt(i).setMass();
		}
		
	}
	
}
