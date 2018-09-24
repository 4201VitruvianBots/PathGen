import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.modifiers.TankModifier;

public class PathGen {
	public static double max_vel = 2;
	public static double max_accel = 2;
	public static double max_jerk = 10;
	public static int samples = Trajectory.Config.SAMPLES_HIGH;
	public static Trajectory.FitMethod fitMethod = FitMethod.HERMITE_CUBIC;
	public static double period = 0.01;
	
	static int index;
	
	static Map<String, Path> path = new HashMap<>();
	static TankModifier modifier;
	
	public static void main(String[] args) throws FileNotFoundException{
		long startTime = System.nanoTime();
		
		initializePaths();

		Trajectory trajectory;
		
		Set<String> keySet = path.keySet();
		Iterator<String> iterator = keySet.iterator();
		
		index = 0;
		File pathfinderDir = new File("Pathfinder");
		if(!pathfinderDir.exists() || !pathfinderDir.isDirectory()) {
			System.out.println("Pathfinder Directory doesn't exist. Making directory...");
			pathfinderDir.mkdir();
		}
		File pathNameDir = new File(pathfinderDir, "pathNames.txt");
		FileOutputStream fos = new FileOutputStream(pathNameDir);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		while(iterator.hasNext()) {
			String key = iterator.next();
			Path checkPath = null;

			boolean newPath = false;
			try {
				File memoryDir = new File("PathMemory/");
				if(!memoryDir.exists() || !memoryDir.isDirectory()) {
					System.out.println("PathMemory Directory doesn't exist. Making directory...");
					memoryDir.mkdir();
				}
				File pathMemory = new File(memoryDir, path.get(key).name + ".ser");
				if(!pathMemory.exists() || !pathMemory.isFile()) {
					System.out.println("No record of previous path.");
					newPath = true;
				} else {
					FileInputStream fileIn = new FileInputStream(pathMemory);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					checkPath = (Path) in.readObject();
					in.close();
					fileIn.close();
				}
			} catch(Exception e) {
				System.out.println("File Read Error: " + e.getMessage());
			}
			if(checkPath != null && path.get(key).equals(checkPath)) {
				System.out.println("Skipping Path Generation for path \"" + path.get(key).name + "\"");
			} else {
				System.out.println("Found Differences in Path \"" + path.get(key).name + "\"");
				newPath = true;
			}

			if(newPath) {
				
				System.out.println("Generating Path...");
				trajectory = Pathfinder.generate(path.get(key).path, path.get(key).config);
				System.out.println("Path Generated!");

				modifier = new TankModifier(trajectory).modify(0.7639);
				
				File pathDir = new File("Pathfinder/Paths");
				if(!pathDir.exists() || !pathDir.isDirectory()) {
					System.out.println("Pathfinder Path Directory doesn't exist. Making directory...");
					pathDir.mkdir();
				}
				File leftCsvFile = new File(pathDir, path.get(key).name + "_Left.csv");		// Remember to rename a successful file, otherwise it will be overwritten
				Pathfinder.writeToCSV(leftCsvFile, modifier.getLeftTrajectory());
				File rightCsvFile = new File(pathDir, path.get(key).name + "_Right.csv");		// Remember to rename a successful file, otherwise it will be overwritten
				Pathfinder.writeToCSV(rightCsvFile, modifier.getRightTrajectory());
				if(leftCsvFile.exists() && rightCsvFile.exists()) {
					System.out.println("Success! File \"" + path.get(key).name + "_Left.csv\" was generated!");
					System.out.println("Success! File \"" + path.get(key).name + "_Right.csv\" was generated!");
				} else {
					System.out.println("Error: File \"" + path.get(key).name + "_Left.csv\" was not created");
					System.out.println("Error: File \"" + path.get(key).name + "_Right.csv\" was not created");
				}
				try {
					FileOutputStream fileOut = new FileOutputStream("PathMemory/" + path.get(key).name + ".ser");         
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(path.get(key));
					out.close();
					fileOut.close();
					
					bw.write(path.get(key).name);
					bw.newLine();
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
				} catch (IOException e) {
					System.out.println("Error initializing stream: " + e.getMessage());
				}
				index++;
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Number of Paths Generated: " + index);
		long runTime = System.nanoTime() - startTime;
		if(runTime < 1e9)
			System.out.printf("\nRuntime: %.3f milliseconds", runTime / 1e6);
		else 
			System.out.printf("\nRuntime: %.3f seconds", runTime / 1e9);
	}
	
	static void initializePaths(){
		// Straight Autos
		path.put("driveStraight", new Path("driveStraight", Waypoints.driveStraight));
		path.put("driveCalibration", new Path("driveCalibration", Waypoints.driveCalibration));
		path.put("scaleToEdgeCubeLeft", new Path("scaleToEdgeCubeLeft", Waypoints.scaleToEdgeCubeLeft));												
		path.get("scaleToEdgeCubeLeft").setMaxVel(1.75);	
		path.get("scaleToEdgeCubeLeft").setMaxAccel(1.75);	
		
		// To Redo/Improve	
		path.put("scaleToEdgeCubeRight", new Path("scaleToEdgeCubeRight", Waypoints.scaleToEdgeCubeLeft));											
		path.get("scaleToEdgeCubeRight").setMaxVel(1.75);									
		path.get("scaleToEdgeCubeRight").setMaxAccel(1.75);						
		path.put("edgeCubeToScaleDrop", new Path("edgeCubeToScaleDrop", Waypoints.edgeCubeToScaleDrop));
		path.get("edgeCubeToScaleDrop").setMaxVel(4);									// To Redo/Improve
		path.get("edgeCubeToScaleDrop").setMaxAccel(4);									// To Redo/Improve	
		path.put("edgeCubeToScaleShoot", new Path("edgeCubeToScaleShoot", Waypoints.edgeCubeToScaleShoot));												// To Redo/Improve
		path.get("edgeCubeToScaleShoot").setMaxVel(4);									// To Redo/Improve
		path.get("edgeCubeToScaleShoot").setMaxAccel(4);			
		path.put("edgeCubeFarToScale", new Path("edgeCubeFarToScale", Waypoints.edgeCubeFarToScale));												// To Redo/Improve
		path.get("edgeCubeFarToScale").setMaxVel(4);									// To Redo/Improve
		path.get("edgeCubeFarToScale").setMaxAccel(4);									// To Redo/Improve	
		path.put("edgeCubeToSwitch", new Path("edgeCubeToSwitch", Waypoints.edgeCubeToSwitch));											// To Test (?)
		path.get("edgeCubeToSwitch").setMaxVel(4);									// To Redo/Improve
		path.get("edgeCubeToSwitch").setMaxAccel(4);									// To Redo/Improve	
		path.put("leftScaleToEdgeCubeFar", new Path("leftScaleToEdgeCubeFar", Waypoints.leftScaleToEdgeCubeFar));												// To Redo/Improve
		path.get("leftScaleToEdgeCubeFar").setMaxVel(1);									// To Redo/Improve
		path.get("leftScaleToEdgeCubeFar").setMaxAccel(1);			
		path.put("rightScaleToEdgeCubeFar", new Path("rightScaleToEdgeCubeFar", Waypoints.rightScaleToEdgeCubeFar));												// To Redo/Improve
		path.get("rightScaleToEdgeCubeFar").setMaxVel(1);									// To Redo/Improve
		path.get("rightScaleToEdgeCubeFar").setMaxAccel(1);									// To Redo/Improve	
		
		// Center Autos
		path.put("centerAutoLeft", new Path("centerAutoLeft", Waypoints.centerAutoLeft));
		path.get("centerAutoLeft").setMaxVel(1.5);									// To Redo/Improve
		path.get("centerAutoLeft").setMaxAccel(1.5);									// To Redo/Improve	
		path.put("centerAutoRight", new Path("centerAutoRight", Waypoints.centerAutoRight));
		path.get("centerAutoRight").setMaxVel(1.5);									// To Redo/Improve
		path.get("centerAutoRight").setMaxAccel(1.5);									// To Redo/Improve	
		path.put("switchBackLeft", new Path("switchBackLeft", Waypoints.switchBackLeft));
		path.get("switchBackLeft").setMaxVel(1.8);								
		path.get("switchBackLeft").setMaxAccel(1.8);
		path.put("switchBackRight", new Path("switchBackRight", Waypoints.switchBackRight));
		path.get("switchBackRight").setMaxVel(1.8);								
		path.get("switchBackRight").setMaxAccel(1.8);
		path.put("centerAutoGrabCube", new Path("centerAutoGrabCube", Waypoints.centerAutoGrabCube));								
		path.get("centerAutoGrabCube").setMaxVel(1.8);								
		path.get("centerAutoGrabCube").setMaxAccel(1.8);
		path.put("centerAutoGrabCubeReverse", new Path("centerAutoGrabCubeReverse", Waypoints.centerAutoGrabCube));								
		path.get("centerAutoGrabCubeReverse").setMaxVel(1.8);								
		path.get("centerAutoGrabCubeReverse").setMaxAccel(1.8);
		path.put("centerAutoLeftFaster", new Path("centerAutoLeftFaster", Waypoints.centerAutoLeft));
		path.get("centerAutoLeftFaster").setMaxVel(1.8);									// To Redo/Improve
		path.get("centerAutoLeftFaster").setMaxAccel(1.8);									// To Redo/Improve	
		path.put("centerAutoRightFaster", new Path("centerAutoRightFaster", Waypoints.centerAutoRight));
		path.get("centerAutoRightFaster").setMaxVel(1.8);									// To Redo/Improve
		path.get("centerAutoRightFaster").setMaxAccel(1.8);									// To Redo/Improve	
		
		// Right Autos
		path.put("rightStartToRightScale", new Path("rightStartToRightScale", Waypoints.rightStartToRightScale));								// To Redo/Improve
		path.put("rightStartToLeftScale", new Path("rightStartToLeftScale", Waypoints.rightStartToLeftScale));									// To Redo/Improve
		path.put("rightStartToRightSwitchReverseOne", new Path("rightStartToRightSwitchReverseOne", Waypoints.rightStartToRightSwitchReverseOne));			// To Test
		path.put("rightStartToRightSwitchReverseTwo", new Path("rightStartToRightSwitchReverseTwo", Waypoints.rightStartToRightSwitchReverseTwo));			// To Test
		path.put("rightStartToLeftSwitchNearReverseOne", new Path("rightStartToLeftSwitchNearReverseOne", Waypoints.rightStartToLeftSwitchNearReverseOne));	// To Test
		path.put("rightStartToLeftSwitchNearReverseTwo", new Path("rightStartToLeftSwitchNearReverseTwo", Waypoints.rightStartToLeftSwitchNearReverseTwo));	// To Test
		path.put("rightStartToLeftSwitchFarReverseOne", new Path("rightStartToLeftSwitchFarReverseOne", Waypoints.rightStartToLeftSwitchFarReverseOne));		// To Test
		path.put("rightStartToLeftSwitchFarReverseTwo", new Path("rightStartToLeftSwitchFarReverseTwo", Waypoints.rightStartToLeftSwitchFarReverseTwo));		// To Test
		path.put("rightScaleToCubeSix", new Path("rightScaleToCubeSix", Waypoints.rightScaleToCubeSix));
				
		// Left Autos
		path.put("leftStartToLeftScale", new Path("leftStartToLeftScale", Waypoints.leftStartToLeftScale));										// To Redo/Improve
		path.put("leftStartToRightScale", new Path("leftStartToRightScale", Waypoints.leftStartToRightScale));									// To Redo/Improve
		path.put("leftStartToLeftSwitchReverseOne", new Path("leftStartToLeftSwitchReverseOne", Waypoints.leftStartToLeftSwitchReverseOne));
		path.put("leftStartToLeftSwitchReverseTwo", new Path("leftStartToLeftSwitchReverseTwo", Waypoints.leftStartToLeftSwitchReverseTwo));
		path.put("leftStartToRightSwitchNearReverseOne", new Path("leftStartToRightSwitchNearReverseOne", Waypoints.leftStartToRightSwitchNearReverseOne));
		path.put("leftStartToRightSwitchNearReverseTwo", new Path("leftStartToRightSwitchNearReverseTwo", Waypoints.leftStartToRightSwitchNearReverseTwo));
		path.put("leftStartToRightSwitchFarReverseOne", new Path("leftStartToRightSwitchFarReverseOne", Waypoints.leftStartToRightSwitchFarReverseOne));
		path.put("leftStartToRightSwitchFarReverseTwo", new Path("leftStartToRightSwitchFarReverseTwo", Waypoints.leftStartToRightSwitchFarReverseTwo));
		path.put("leftScaleToCubeOne", new Path("leftScaleToCubeOne", Waypoints.leftScaleToCubeOne));
		//*/
	}
}
