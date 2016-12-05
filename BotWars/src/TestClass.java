import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class TestClass {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] grid = new int[5][5];
		boolean flagPoint = false;
		boolean flagPoint1 = false;
		boolean flagPoint3 = false;
		String winningBlock = null;

		for (int i = 0; i < 5; i++) {
			String line[] = br.readLine().split(" ");

			for (int j = 0; j < 5; j++) {
				grid[i][j] = Integer.parseInt(line[j]);
				
				if (grid[i][j] == 14 || grid[i][j] == 13 || grid[i][j] == 11 || grid[i][j] == 7) {
					
					flagPoint = true;
				}
			}
		}
		String player= br.readLine();

		if (flagPoint) {
//			System.out.println("flagpoint");
			System.out.println(twoSideBlocked(grid));
		} else {
			
			// START -when no side is blocked
			String block=noWiningBlocked(grid);
			if(block!=null){
				flagPoint1=true;
				winningBlock=block;
			};
			// ENDS -when no side is blocked
		}
		
		if (flagPoint1 && !flagPoint) {
//			System.out.println("flagpoint2");
			System.out.println(winningBlock);
		} else if(!flagPoint && !flagPoint1) {
			
			//START -when 2 side is blocked
			String block=twoSideBlocked(grid);
			if(block!=null){
				flagPoint3=true;
				winningBlock=block;
			};
			//END -when 2 side is blocked
		}
		
		if (flagPoint3 && !flagPoint && !flagPoint1) {
//			System.out.println("flagpoint3");
			System.out.println(winningBlock);
		}

//		System.out.println("array:" + Arrays.deepToString(grid));
	}

	private static String twoSideBlocked(int[][] grid) {

		String winningBlock=null;
		int count=0;
		ArrayList<String> threeSide=new ArrayList<String>();
		ArrayList<Integer> twoSide=new ArrayList<Integer>();
		twoSide.add(3);
		twoSide.add(5);
		twoSide.add(6);
		twoSide.add(9);
		twoSide.add(10);
		twoSide.add(12);
		int [][] blockFlag=new int[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (grid[i][j] == 15 || grid[i][j] == 31) {
					blockFlag[i][j] = -1;
				}
			}
		}
		
		//forming initial blockflag
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (grid[i][j] == 15 || grid[i][j] == 31) {
					blockFlag[i][j] = -1;
				}else if(grid[i][j] == 7 || grid[i][j] == 11 || grid[i][j] == 13|| grid[i][j] == 14){
					blockFlag[i][j]=-2;
					threeSide.add(i+" "+j);
				} 
				else if (grid[i][j] == 3) {
					if(j!=0 && i!=4){
						if(blockFlag[i][j-1]>0 && blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
							mergeBlock(blockFlag,blockFlag[i][j-1],blockFlag[i+1][j]);
						}else if(blockFlag[i][j-1]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else if(blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==0 && i==4){
						blockFlag[i][j]=++count;
					}else if(j==0){
						if(blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==4){
						if(blockFlag[i][j-1]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 5){
					if(j!=0 && j!=4){
						if(blockFlag[i][j-1]>0 && blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
							mergeBlock(blockFlag,blockFlag[i][j-1],blockFlag[i][j+1]);
						}else if(blockFlag[i][j-1]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else if(blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==0){
						if(blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==4){
						if(blockFlag[i][j-1]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 6){
					if(i!=0 && j!=0){
						if(blockFlag[i-1][j]>0 && blockFlag[i][j-1]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
							mergeBlock(blockFlag,blockFlag[i-1][j],blockFlag[i][j-1]);
						}else if(blockFlag[i-1][j]>0){
							blockFlag[i][j]= blockFlag[i-1][j];
						}else if(blockFlag[i][j-1]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==0 && j==0){
						blockFlag[i][j]=++count;
					}else if(i==0){
						if(blockFlag[i][j-1]>0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==0){
						if(blockFlag[i-1][j]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 9){
					if(i!=4 && j!=4){
						if(blockFlag[i][j+1]>0 && blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i][j+1];
							mergeBlock(blockFlag,blockFlag[i][j+1],blockFlag[i+1][j]);
						}else if(blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else if(blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==4 && j==4){
						blockFlag[i][j]=++count;
					}else if(i==4){
						if(blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==4){
						if(blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 10){
					if(i!=0 && i!=4){
						if(blockFlag[i-1][j]>0 && blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
							mergeBlock(blockFlag, blockFlag[i-1][j], blockFlag[i+1][j]);
						}else if(blockFlag[i-1][j]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else if(blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==0){
						if(blockFlag[i+1][j]>0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==4){
						if(blockFlag[i-1][j]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 12){
					if(i!=0 && j!=4){
						if(blockFlag[i-1][j]>0 && blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
							mergeBlock(blockFlag, blockFlag[i-1][j], blockFlag[i][j+1]);
						}else if(blockFlag[i-1][j]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else if(blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==0 && j==4){
						blockFlag[i][j]=++count;
					}else if(i==0){
						if(blockFlag[i][j+1]>0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==4){
						if(blockFlag[i-1][j]>0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}
			}
		}
		
		
		System.out.println("array: before" + Arrays.deepToString(blockFlag));
		manipulateSelfLoop(blockFlag,grid);	
		System.out.println("array:" + Arrays.deepToString(blockFlag));
		
		int[] keyValue=new int[18];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(blockFlag[i][j]>0){
					keyValue[blockFlag[i][j]]=keyValue[blockFlag[i][j]]+1;
				}
			}
		}
		System.out.println(Arrays.toString(keyValue));
		
		//Start- Sorting
		int[][] values=new int[13][3];
		int[][] priorityArray =new int[13][3];
		int pointerCount=0;
		
		for(int i=0;i<keyValue.length;i++){
			if(keyValue[i]>0){
				values[pointerCount][0]=keyValue[i];
				values[pointerCount][1]=i;
				values[pointerCount][2]=isBorder(blockFlag,i,grid);
				pointerCount++;
			}
		}
		System.out.println("values:" + Arrays.deepToString(values));
		int priorityCounter=0;
		for(int i=1;i<26;i++){
			for(int j=0;j<values.length;j++){
				if(values[j][0]==i){
					priorityArray[priorityCounter][0]=values[j][0];
					priorityArray[priorityCounter][1]=values[j][1];
					priorityArray[priorityCounter][2]=values[j][2];
					priorityCounter++;
				}
			}
		}
		System.out.println("priority:" + Arrays.deepToString(priorityArray));
		
		if (threeSide.size() == 0) {
			int countOneTwo=0;
			int positionTwo=-1;
			for(int i=0;i<13;i++){
				if(priorityArray[i][0]>2){
					break;
				}
				if(priorityArray[i][0]==1 || priorityArray[i][0]==2){
					countOneTwo++;
					if(priorityArray[i][0]==2){
						positionTwo=priorityArray[i][1];
					}
				}
			}
			if(countOneTwo==2 || countOneTwo==4 || countOneTwo==6){
				if(positionTwo >= 0){
					int[] coord = fetchCoordinate(blockFlag, positionTwo);
					//TODO
					System.out.println("came here?");
					if(grid[coord[0]][coord[1]]==3){
						return coord[0] +" "+coord[1]+" "+ 3;
					}else if(grid[coord[0]][coord[1]]==5){
						return coord[0] +" "+coord[1]+" "+ 3;
					}else if(grid[coord[0]][coord[1]]==9){
						if(coord[1]!=4 && blockFlag[coord[0]][coord[1]+1]==positionTwo){
							return coord[0] +" "+coord[1]+" "+ 2;
						}else if(coord[0]!=4 && blockFlag[coord[0]+1][coord[1]]==positionTwo){
							return coord[0] +" "+coord[1]+" "+ 1;
						}
					}else if(grid[coord[0]][coord[1]]==10){
						return coord[0] +" "+coord[1]+" "+ 0;
					}else if(grid[coord[0]][coord[1]]==12){
						return coord[0] +" "+coord[1]+" "+ 0;
					}
				}
			}
			
			if(priorityArray[0][0] == 4){
				if(priorityArray[1][0]==4 && priorityArray[1][2]==0){
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							if(blockFlag[i][j]==priorityArray[1][1]){
								if (grid[i][j] == 6 || grid[i][j] == 10 || grid[i][j] == 12) {
									return i + " " + j + " " + 0;
								}else if(grid[i][j] == 5 || grid[i][j] == 9){
									return i + " " + j + " " + 1;
								}else if(grid[i][j] == 3){
									return i + " " + j + " " + 2;
								}
							}
						}
					}
				}else{
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							if(blockFlag[i][j]==priorityArray[0][1]){
								if (grid[i][j] == 6 || grid[i][j] == 10 || grid[i][j] == 12) {
									return i + " " + j + " " + 0;
								}else if(grid[i][j] == 5 || grid[i][j] == 9){
									return i + " " + j + " " + 1;
								}else if(grid[i][j] == 3){
									return i + " " + j + " " + 2;
								}
							}
						}
					}
				}
			}else if (priorityArray[0][0] == 2) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if(blockFlag[i][j]==priorityArray[0][1]){
							try{
								if(blockFlag[i][j-1]==priorityArray[0][1]){
									return i+" "+j+" "+3;
								}
							}catch(Exception e){}
							try{
								if(blockFlag[i-1][j]==priorityArray[0][1]){
									return i+" "+j+" "+0;
								}
							}catch(Exception e){}
							try{
								if(blockFlag[i][j+1]==priorityArray[0][1]){
									return i+" "+j+" "+1;
								}
							}catch(Exception e){}
							try{
								if(blockFlag[i+1][j]==priorityArray[0][1]){
									return i+" "+j+" "+2;
								}
							}catch(Exception e){}
						}
					}
				}
			}else {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if(blockFlag[i][j]==priorityArray[0][1]){
							if (grid[i][j] == 6 || grid[i][j] == 10 || grid[i][j] == 12) {
								return i + " " + j + " " + 0;
							}else if(grid[i][j] == 5 || grid[i][j] == 9){
								return i + " " + j + " " + 1;
							}else if(grid[i][j] == 3){
								return i + " " + j + " " + 2;
							}
						}
					}
				}
			}
		}else{
			if(threeSide.size()==1){
				String coord[]=threeSide.get(0).split(" ");
				int a=Integer.parseInt(coord[0]);
				int b=Integer.parseInt(coord[1]);
				System.out.println("+++++++++++++++++++++");
				System.out.println("blockflag: "+Arrays.deepToString(blockFlag));
				System.out.println("priority"+Arrays.deepToString(priorityArray));
				System.out.println("+++++++++++++++++++++");
				if(grid[a][b]==7 && b==0){
					return a+" "+b+" "+3;
				}else if(grid[a][b]==11 && a==4){
					return a+" "+b+" "+2;
				}else if(grid[a][b]==13 && b==4){
					return a+" "+b+" "+1;
				}else if(grid[a][b]==14 && a==0){
					return a+" "+b+" "+0;
				}else if(grid[a][b]==7){
					int adjacentValue=blockFlag[a][b-1];
					if(adjacentValue==0){
						return a+" "+b+" "+3;
					}else if(adjacentValue>0){
						int noOfBlock=countBlock(blockFlag,adjacentValue)+1;
						if(noOfBlock>2){
							return a+" "+b+" "+3;
						}else if(noOfBlock==2 && (priorityArray[1][0]==2 || priorityArray[2][0]==2|| priorityArray[3][0]==2||priorityArray[4][0]==2)){
							return a+" "+b+" "+3;
						}
						else if(noOfBlock==2 && priorityArray[1][0]>0){
							if(grid[a][b-1]==5){
								return a+" "+(b-1)+" "+3;
							}else if(grid[a][b-1]==9){
								return a+" "+(b-1)+" "+2;
							}else if(grid[a][b-1]==12){
								return a+" "+(b-1)+" "+0;
							}
						}else if(noOfBlock==2 && priorityArray[1][0]==0){
							return a+" "+b+" "+3;
						}
					}
				}else if(grid[a][b]==11){
					int adjacentValue=blockFlag[a+1][b];
					if(adjacentValue==0){
						return a+" "+b+" "+2;
					}else if(adjacentValue>0){
						int noOfBlock=countBlock(blockFlag,adjacentValue)+1;
						if(noOfBlock>2){
							return a+" "+b+" "+2;
						}else if(noOfBlock==2 && (priorityArray[1][0]==2 || priorityArray[2][0]==2|| priorityArray[3][0]==2||priorityArray[4][0]==2)){
							return a+" "+b+" "+2;
						}
						else if(noOfBlock==2 && priorityArray[1][0]>0){
							if(grid[a+1][b]==6){
								return (a+1)+" "+b+" "+3;
							}else if(grid[a+1][b]==10){
								return (a+1)+" "+b+" "+2;
							}else if(grid[a+1][b]==12){
								return (a+1)+" "+b+" "+1;
							}
						}else if(noOfBlock==2 && priorityArray[1][0]==0){
							return a+" "+b+" "+2;
						}
					}
				}else if(grid[a][b]==13){
					int adjacentValue=blockFlag[a][b+1];
					if(adjacentValue==0){
						return a+" "+b+" "+1;
					}else if(adjacentValue>0){
						int noOfBlock=countBlock(blockFlag,adjacentValue)+1;
						if(noOfBlock>2){
							return a+" "+b+" "+1;
						}else if(noOfBlock==2 && (priorityArray[1][0]==2 || priorityArray[2][0]==2|| priorityArray[3][0]==2||priorityArray[4][0]==2)){
							return a+" "+b+" "+1;
						}
						else if(noOfBlock==2 && priorityArray[1][0]>0){
							if(grid[a][b+1]==3){
								return a+" "+(b+1)+" "+2;
							}else if(grid[a][b+1]==5){
								return a+" "+(b+1)+" "+1;
							}else if(grid[a][b+1]==6){
								return a+" "+(b+1)+" "+0;
							}
						}else if(noOfBlock==2 && priorityArray[1][0]==0){
							return a+" "+b+" "+1;
						}
					}
				}else if(grid[a][b]==14){
					int adjacentValue=blockFlag[a-1][b];
					if(adjacentValue==0){
						return a+" "+b+" "+0;
					}else if(adjacentValue>0){
						int noOfBlock=countBlock(blockFlag,adjacentValue)+1;
						if(noOfBlock>2){
							return a+" "+b+" "+0;
						}else if(noOfBlock==2 && (priorityArray[1][0]==2 || priorityArray[2][0]==2|| priorityArray[3][0]==2||priorityArray[4][0]==2)){
							return a+" "+b+" "+0;
						}
						else if(noOfBlock==2 && priorityArray[1][0]>0){
							if(grid[a-1][b]==3){
								return (a-1)+" "+b+" "+3;
							}else if(grid[a-1][b]==9){
								return (a-1)+" "+b+" "+1;
							}else if(grid[a-1][b]==10){
								return (a-1)+" "+b+" "+0;
							}
						}else if(noOfBlock==2 && priorityArray[1][0]==0){
							return a+" "+b+" "+0;
						}
					}
				}
			}else if(threeSide.size()>1){
				String coord1[]=threeSide.get(0).split(" ");
				String coord2[]=threeSide.get(1).split(" ");
				
				int points[][]=new int[3][2];
				points[0][0]=Integer.parseInt(coord1[0]);
				points[0][1]=Integer.parseInt(coord1[1]);
				points[1][0]=Integer.parseInt(coord2[0]);
				points[1][1]=Integer.parseInt(coord2[1]);
				
				System.out.println("+++++++++++++++++++++");
				System.out.println("blockflag: "+Arrays.deepToString(blockFlag));
				System.out.println("priority"+Arrays.deepToString(priorityArray));
				System.out.println("co-ordiante: "+Arrays.deepToString(points));
				System.out.println("+++++++++++++++++++++");
				int[] noOfBlock=new int[2];
				int[] adjacentValue=new int[2];
				for(int i=0;i<2;i++){
					if(grid[points[i][0]][points[i][1]]==7 && points[i][1]==0){
						noOfBlock[i]=1;
					}else if(grid[points[i][0]][points[i][1]]==11 && points[i][0]==4){
						noOfBlock[i]=1;
					}else if(grid[points[i][0]][points[i][1]]==13 && points[i][1]==4){
						noOfBlock[i]=1;
					}else if(grid[points[i][0]][points[i][1]]==14 && points[i][0]==0){
						noOfBlock[i]=1;
					}else if(grid[points[i][0]][points[i][1]]==7){
						adjacentValue[i]=blockFlag[points[i][0]][points[i][1]-1];
						if(adjacentValue[i]==0){
							noOfBlock[i]=1;
						}else if(adjacentValue[i]>0){
							noOfBlock[i]=countBlock(blockFlag,adjacentValue[i])+1;
						}
					}else if(grid[points[i][0]][points[i][1]]==11){
						adjacentValue[i]=blockFlag[points[i][0]+1][points[i][1]];
						if(adjacentValue[i]==0){
							noOfBlock[i]=1;
						}else if(adjacentValue[i]>0){
							noOfBlock[i]=countBlock(blockFlag,adjacentValue[i])+1;
						}
					}else if(grid[points[i][0]][points[i][1]]==13){
						adjacentValue[i]=blockFlag[points[i][0]][points[i][1]+1];
						if(adjacentValue[i]==0){
							noOfBlock[i]=1;
						}else if(adjacentValue[i]>0){
							noOfBlock[i]=countBlock(blockFlag,adjacentValue[i])+1;
						}
					}else if(grid[points[i][0]][points[i][1]]==14){
						adjacentValue[i]=blockFlag[points[i][0]-1][points[i][1]];
						if(adjacentValue[i]==0){
							noOfBlock[i]=1;
						}else if(adjacentValue[i]>0){
							noOfBlock[i]=countBlock(blockFlag,adjacentValue[i])+1;
						}
					}
				}
				System.out.println("adjacent: "+Arrays.toString(adjacentValue));
				System.out.println("no of blocks: "+Arrays.toString(noOfBlock));
				if(adjacentValue[0]==-2 && adjacentValue[1]==-2){
					System.out.println("here -2");
					if(grid[points[0][0]][points[0][1]]==7){
						return points[0][0]+" "+points[0][1]+" "+3;
					}else if(grid[points[0][0]][points[0][1]]==11){
						return points[0][0]+" "+points[0][1]+" "+2;
					}else if(grid[points[0][0]][points[0][1]]==13){
						return points[0][0]+" "+points[0][1]+" "+1;
					}else if(grid[points[0][0]][points[0][1]]==14){
						return points[0][0]+" "+points[0][1]+" "+0;
					}
				}
				else if (adjacentValue[0] == 0 && adjacentValue[1] == 0) {
					//need to change 1. marking one box 2. in single -2 last block search for 2 block
					System.out.println("here 0");
					if (grid[points[0][0]][points[0][1]] == 7) {
						return points[0][0] + " " + points[0][1] + " " + 3;
					} else if (grid[points[0][0]][points[0][1]] == 11) {
						return points[0][0] + " " + points[0][1] + " " + 2;
					} else if (grid[points[0][0]][points[0][1]] == 13) {
						return points[0][0] + " " + points[0][1] + " " + 1;
					} else if (grid[points[0][0]][points[0][1]] == 14) {
						return points[0][0] + " " + points[0][1] + " " + 0;
					}

				}else if (adjacentValue[0] == adjacentValue[1] && adjacentValue[0]>0){
					int blockcount=countBlock(blockFlag, adjacentValue[0]);
					if(blockcount>2){
						if (grid[points[0][0]][points[0][1]] == 7) {
							return points[0][0] + " " + points[0][1] + " " + 3;
						} else if (grid[points[0][0]][points[0][1]] == 11) {
							return points[0][0] + " " + points[0][1] + " " + 2;
						} else if (grid[points[0][0]][points[0][1]] == 13) {
							return points[0][0] + " " + points[0][1] + " " + 1;
						} else if (grid[points[0][0]][points[0][1]] == 14) {
							return points[0][0] + " " + points[0][1] + " " + 0;
						}
					}else if(blockcount==2){
						if (grid[points[0][0]][points[0][1]] == 7) {
							if(grid[points[0][0]][points[0][1]-1]==5){
								return points[0][0]+" "+(points[0][1]-1)+" "+3;
							}else if(grid[points[0][0]][points[0][1]-1]==9){
								return points[0][0]+" "+(points[0][1]-1)+" "+2;
							}else if(grid[points[0][0]][points[0][1]-1]==12){
								return points[0][0]+" "+(points[0][1]-1)+" "+0;
							}
						} else if (grid[points[0][0]][points[0][1]] == 11) {
							if(grid[points[0][0]+1][points[0][1]]==6){
								return (points[0][0]+1)+" "+points[0][1]+" "+3;
							}else if(grid[points[0][0]+1][points[0][1]]==10){
								return (points[0][0]+1)+" "+points[0][1]+" "+2;
							}else if(grid[points[0][0]+1][points[0][1]]==12){
								return (points[0][0]+1)+" "+points[0][1]+" "+1;
							}
						} else if (grid[points[0][0]][points[0][1]] == 13) {
							if(grid[points[0][0]][points[0][1]+1]==3){
								return points[0][0]+" "+(points[0][1]+1)+" "+2;
							}else if(grid[points[0][0]][points[0][1]+1]==5){
								return points[0][0]+" "+(points[0][1]+1)+" "+1;
							}else if(grid[points[0][0]][points[0][1]+1]==6){
								return points[0][0]+" "+(points[0][1]+1)+" "+0;
							}
						} else if (grid[points[0][0]][points[0][1]] == 14) {
							if(grid[points[0][0]-1][points[0][1]]==3){
								return (points[0][0]-1)+" "+points[0][1]+" "+3;
							}else if(grid[points[0][0]-1][points[0][1]]==9){
								return (points[0][0]-1)+" "+points[0][1]+" "+1;
							}else if(grid[points[0][0]-1][points[0][1]]==10){
								return (points[0][0]-1)+" "+points[0][1]+" "+0;
							}
						}
					}
				}else if(noOfBlock[0]>1 || noOfBlock[1]>1){
					int a = 0;
					int b = 0;
					if(noOfBlock[0]<noOfBlock[1]){
						a=points[0][0];
						b=points[0][1];
					}else if(noOfBlock[0]>noOfBlock[1]){
						a=points[1][0];
						b=points[1][1];
					}else if(noOfBlock[0]==noOfBlock[1]){
						a=points[0][0];
						b=points[0][1];
					}
					
					if(grid[a][b]==7){
						return a+" "+b+" "+3;
					}else if(grid[a][b]==11){
						return a+" "+b+" "+2;
					}else if(grid[a][b]==13){
						return a+" "+b+" "+1;
					}else if(grid[a][b]==14){
						return a+" "+b+" "+0;
					}
				}else{
					if (grid[points[0][0]][points[0][1]] == 7) {
						return points[0][0] + " " + points[0][1] + " " + 3+"\n default 3S";
					} else if (grid[points[0][0]][points[0][1]] == 11) {
						return points[0][0] + " " + points[0][1] + " " + 2+"\n default 3S";
					} else if (grid[points[0][0]][points[0][1]] == 13) {
						return points[0][0] + " " + points[0][1] + " " + 1+"\n default 3S";
					} else if (grid[points[0][0]][points[0][1]] == 14) {
						return points[0][0] + " " + points[0][1] + " " + 0+"\n default 3S";
					}
				}
			}
		}
		
		//ENDS- Sorting
		
		int min=0;
		int tempVal=0;
		for (int i = 0; i < keyValue.length; i++) {
			if(keyValue[i]==0){
				continue;
			}
			if(min==0){
				min=i;
				tempVal=keyValue[i];
			}else{
				if(tempVal>keyValue[i]){
					min=i;
					tempVal=keyValue[i];
				}
			}
		}
		System.out.println(min);
		
		loop:
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(blockFlag[i][j]==min){
					if (grid[i][j] == 6 || grid[i][j] == 10 || grid[i][j] == 12) {
						winningBlock = i + " " + j + " " + 0;
					}else if(grid[i][j] == 5 || grid[i][j] == 9){
						winningBlock = i + " " + j + " " + 1;
					}else if(grid[i][j] == 3){
						winningBlock = i + " " + j + " " + 2;
					}
					break loop;
				}
			}
		}
		return winningBlock + "\n from here";
	}

	private static int countBlock(int[][] blockFlag, int adjacentValue) {
		int count=0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(blockFlag[i][j]==adjacentValue){
					count++;
				}
			}
		}
		return count;
	}

	private static int isBorder(int[][] blockFlag, int position, int[][] grid) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 0 || i == 4 || j == 0 || j == 1) {
					if(blockFlag[i][j]==position){
						if(i==0){
							if(grid[i][j]==6 || grid[i][j]==10 || grid[i][j]==12){
								return 1;
							}
						}
						if(i==4){
							if(grid[i][j]==3 || grid[i][j]==9 || grid[i][j]==10){
								return 1;
							}
						}
						if(j==0){
							if(grid[i][j]==3 || grid[i][j]==5 || grid[i][j]==6){
								return 1;
							}
						}
						if(j==4){
							if(grid[i][j]==5 || grid[i][j]==9 || grid[i][j]==12){
								return 1;
							}
						}
					}
				}
			}
		}
		return 0;
	}

	private static void mergeBlock(int[][] blockFlag, int value1, int value2) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (blockFlag[i][j] == value2) {
					blockFlag[i][j] = value1;
				}
			}
		}
	}

	private static void manipulateSelfLoop(int[][] blockFlag, int[][] grid) {
		int[][] blocktemp=new int[5][5];
		int[][] backup=new int[5][5];
		clone(blockFlag, blocktemp);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				clone(blocktemp, backup);
				try {
					if(blockFlag[i][j]==0){
						if(grid[i][j]==1){
							if (grid[i][j + 1] == 5 && grid[i + 1][j] == 12 && grid[i+1][j + 1]==5) {
								blocktemp[i + 1][j] = 0;
								blocktemp[i][j + 1] = 0;
								blocktemp[i + 1][j + 1] = 0;
								blocktemp[i][j + 2] = 0;
								blocktemp[i + 1][j + 2] = 0;
								if (grid[i][j + 2] == 5 && grid[i+1][j + 2] == 5) {
									blocktemp[i][j + 3] = 0;
									blocktemp[i + 1][j + 3] = 0;
								} 
							} else if (grid[i][j - 1] == 5 && grid[i + 1][j] == 6 && grid[i+1][j - 1] == 5) {
								blocktemp[i + 1][j] = 0;
								blocktemp[i][j - 1] = 0;
								blocktemp[i + 1][j - 1] = 0;
								blocktemp[i][j - 2] = 0;
								blocktemp[i + 1][j - 2] = 0;
								if (grid[i][j - 2] == 5 && grid[i+1][j - 2] == 5) {
									blocktemp[i][j - 3] = 0;
									blocktemp[i + 1][j - 3] = 0;
								} 
							}else if(grid[i + 1][j] == 12 && grid[i][j+1] == 3 && grid[i + 1][j + 1]==6){
								blocktemp[i + 1][j] = 0;
								blocktemp[i][j + 1] = 0;
								blocktemp[i + 1][j + 1] = 0;
							}else if(grid[i + 1][j] == 6 && grid[i][j-1] == 9 && grid[i + 1][j - 1]==12){
								blocktemp[i + 1][j] = 0;
								blocktemp[i][j - 1] = 0;
								blocktemp[i + 1][j - 1] = 0;
							}else if(grid[i+1][j]==10 && grid[i][j+1]==3 && grid[i+1][j+1]==10){
								blocktemp[i][j+1]=0;
								blocktemp[i+1][j]=0;
								blocktemp[i+1][j+1]=0;
								blocktemp[i+2][j]=0;
								blocktemp[i+2][j+1]=0;
								if(grid[i+2][j]==10 && grid[i+2][j+1]==10){
									blocktemp[i+3][j]=0;
									blocktemp[i+3][j+1]=0;
									if(grid[i+3][j]==10 && grid[i+3][j+1]==10){
										blocktemp[i+4][j]=0;
										blocktemp[i+4][j+1]=0;
									}
								}
							}else if(grid[i+1][j]==10 && grid[i][j-1]==9 && grid[i+1][j-1]==10){
								blocktemp[i][j-1]=0;
								blocktemp[i+1][j]=0;
								blocktemp[i+1][j-1]=0;
								blocktemp[i+2][j]=0;
								blocktemp[i+2][j-1]=0;
								if(grid[i+2][j]==10 && grid[i+2][j-1]==10){
									blocktemp[i+3][j]=0;
									blocktemp[i+3][j-1]=0;
									if(grid[i+3][j]==10 && grid[i+3][j-1]==10){
										blocktemp[i+4][j]=0;
										blocktemp[i+4][j-1]=0;
									}
								}
							}
						}else if(grid[i][j]==4){
							if (grid[i][j + 1] == 5 && grid[i - 1][j] == 9 && grid[i - 1][j + 1]==5) {
								blocktemp[i - 1][j] = 0;
								blocktemp[i][j + 1] = 0;
								blocktemp[i - 1][j + 1] = 0;
								blocktemp[i][j + 2] = 0;
								blocktemp[i - 1][j + 2] = 0;
								if (grid[i][j + 2] == 5 && grid[i - 1][j + 2]==5) {
									blocktemp[i][j + 3] = 0;
									blocktemp[i - 1][j + 3] = 0;
								} 
							} else if (grid[i][j - 1] == 5 && grid[i - 1][j] == 3 && grid[i - 1][j - 1]==5) {
								blocktemp[i - 1][j] = 0;
								blocktemp[i][j - 1] = 0;
								blocktemp[i - 1][j - 1] = 0;
								blocktemp[i][j - 2] = 0;//err
								blocktemp[i - 1][j - 2] = 0;
								if (grid[i][j - 2] == 5 && grid[i - 1][j - 2]==5) {
									blocktemp[i][j - 3] = 0;
									blocktemp[i - 1][j - 3] = 0;
								} 
							}else if(grid[i - 1][j] == 9 && grid[i][j+1] == 6 && grid[i - 1][j + 1]==3){
								blocktemp[i - 1][j] = 0;
								blocktemp[i][j + 1] = 0;
								blocktemp[i - 1][j + 1] = 0;
							}else if(grid[i - 1][j] == 3 && grid[i][j-1] == 12 && grid[i - 1][j - 1]==9){
								blocktemp[i - 1][j] = 0;
								blocktemp[i][j - 1] = 0;
								blocktemp[i - 1][j - 1] = 0;
							}else if(grid[i-1][j]==10 && grid[i][j+1]==6 && grid[i-1][j+1]==10){
								blocktemp[i][j+1]=0;
								blocktemp[i-1][j]=0;
								blocktemp[i-1][j+1]=0;
								blocktemp[i-2][j]=0;
								blocktemp[i-2][j+1]=0;
								if(grid[i-2][j]==10 && grid[i-2][j+1]==10){
									blocktemp[i-3][j]=0;
									blocktemp[i-3][j+1]=0;
									if(grid[i-3][j]==10 && grid[i-3][j+1]==10){
										blocktemp[i-4][j]=0;
										blocktemp[i-4][j+1]=0;
									}
								}
							}else if(grid[i-1][j]==10 && grid[i][j-1]==12 && grid[i-1][j-1]==10){
								blocktemp[i][j-1]=0;
								blocktemp[i-1][j]=0;
								blocktemp[i-1][j-1]=0;
								blocktemp[i-2][j]=0;
								blocktemp[i-2][j-1]=0;
								if(grid[i-2][j]==10 && grid[i-2][j-1]==10){
									blocktemp[i-3][j]=0;
									blocktemp[i-3][j-1]=0;
									if(grid[i-3][j]==10 && grid[i-3][j-1]==10){
										blocktemp[i-4][j]=0;
										blocktemp[i-4][j-1]=0;
									}
								}
							}
						}else if(grid[i][j]==2){
							if(grid[i][j-1]==5 && grid[i+1][j]==6 && grid[i+1][j-1]==5){
								blocktemp[i+1][j]=0;
								blocktemp[i][j-1]=0;
								blocktemp[i+1][j-1]=0;
								blocktemp[i][j-2]=0;
								blocktemp[i+1][j-2]=0;
								if(grid[i][j-2]==5 && grid[i+1][j-2]==5){
									blocktemp[i][j-3]=0;
									blocktemp[i+1][j-3]=0;
									if(grid[i][j-3]==5 && grid[i+1][j-3]==5){
										blocktemp[i][j-4]=0;
										blocktemp[i+1][j-4]=0;
									}
								}
							}else if(grid[i][j-1]==5 && grid[i-1][j]==3 && grid[i-1][j-1]==5){
								blocktemp[i-1][j]=0;
								blocktemp[i][j-1]=0;
								blocktemp[i-1][j-1]=0;
								blocktemp[i][j-2]=0;
								blocktemp[i-1][j-2]=0;
								if(grid[i][j-2]==5 && grid[i-1][j-2]==5){
									blocktemp[i][j-3]=0;
									blocktemp[i-1][j-3]=0;
									if(grid[i][j-3]==5 && grid[i-1][j-3]==5){
										blocktemp[i][j-4]=0;
										blocktemp[i-1][j-4]=0;
									}
								}
							}else if(grid[i][j-1]==9 && grid[i+1][j]==6 && grid[i+1][j-1]==12){
								blocktemp[i][j-1]=0;
								blocktemp[i+1][j]=0;
								blocktemp[i+1][j-1]=0;
							}else if(grid[i][j-1]==12 && grid[i-1][j]==3 && grid[i-1][j-1]==9){
								blocktemp[i][j-1]=0;
								blocktemp[i-1][j]=0;
								blocktemp[i-1][j-1]=0;
							}else if(grid[i+1][j]==10 && grid[i][j-1]==9 && grid[i+1][j-1]==10){
								blocktemp[i][j-1]=0;
								blocktemp[i+1][j]=0;
								blocktemp[i+1][j-1]=0;
								blocktemp[i+2][j]=0;
								blocktemp[i+2][j-1]=0;
								if(grid[i+2][j]==10 && grid[i+2][j-1]==10){
									blocktemp[i+3][j]=0;
									blocktemp[i+3][j-1]=0;
								}
							}else if(grid[i-1][j]==10 && grid[i][j-1]==12 && grid[i-1][j-1]==10){
								blocktemp[i][j-1]=0;
								blocktemp[i-1][j]=0;
								blocktemp[i-1][j-1]=0;
								blocktemp[i-2][j]=0;
								blocktemp[i-2][j-1]=0;
								if(grid[i-2][j]==10 && grid[i-2][j-1]==10){
									blocktemp[i+3][j]=0;
									blocktemp[i+3][j-1]=0;
								}
							}
						}else if(grid[i][j]==8){
							if(grid[i][j+1]==5 && grid[i+1][j]==12 && grid[i+1][j+1]==5){
								blocktemp[i+1][j]=0;
								blocktemp[i][j+1]=0;
								blocktemp[i+1][j+1]=0;
								blocktemp[i][j+2]=0;
								blocktemp[i+1][j+2]=0;
								if(grid[i][j+2]==5 && grid[i+1][j+2]==5){
									blocktemp[i][j+3]=0;
									blocktemp[i+1][j+3]=0;
									if(grid[i][j+3]==5 && grid[i+1][j+3]==5){
										blocktemp[i][j+4]=0;
										blocktemp[i+1][j+4]=0;
									}
								}
							}else if(grid[i][j+1]==5 && grid[i-1][j]==9 && grid[i-1][j+1]==5){
								blocktemp[i+1][j]=0;
								blocktemp[i][j+1]=0;
								blocktemp[i-1][j+1]=0;
								blocktemp[i][j+2]=0;
								blocktemp[i-1][j+2]=0;
								if(grid[i][j+2]==5 && grid[i-1][j+2]==5){
									blocktemp[i][j+3]=0;
									blocktemp[i-1][j+3]=0;
									if(grid[i][j+3]==5 && grid[i-1][j+3]==5){
										blocktemp[i][j+4]=0;
										blocktemp[i-1][j+4]=0;
									}
								}
							}else if(grid[i][j+1]==3 && grid[i+1][j]==12 && grid[i+1][j+1]==6){
								blocktemp[i][j+1]=0;
								blocktemp[i+1][j]=0;
								blocktemp[i+1][j+1]=0;
							}else if(grid[i][j+1]==6 && grid[i-1][j]==9 && grid[i-1][j+1]==3){
								blocktemp[i][j+1]=0;
								blocktemp[i-1][j]=0;
								blocktemp[i-1][j+1]=0;
							}else if(grid[i+1][j]==10 && grid[i][j+1]==3 && grid[i+1][j+1]==10){
								blocktemp[i][j+1]=0;
								blocktemp[i+1][j]=0;
								blocktemp[i+1][j+1]=0;
								blocktemp[i+2][j]=0;
								blocktemp[i+2][j+1]=0;
								if(grid[i+2][j]==10 && grid[i+2][j+1]==10){
									blocktemp[i+3][j]=0;
									blocktemp[i+3][j+1]=0;
								}
							}else if(grid[i-1][j]==10 && grid[i][j+1]==6 && grid[i-1][j+1]==10){
								blocktemp[i][j+1]=0;
								blocktemp[i-1][j]=0;
								blocktemp[i-1][j+1]=0;
								blocktemp[i-2][j]=0;
								blocktemp[i-2][j+1]=0;
								if(grid[i-2][j]==10 && grid[i-2][j+1]==10){
									blocktemp[i+3][j]=0;
									blocktemp[i+3][j+1]=0;
								}
							}
						}
					}
				} catch (Exception e) {
					clone(backup, blocktemp);
				}
			}
		}
		blockFlag=blocktemp;
	}
	
	private static String noWiningBlocked(int[][] grid) {
		ArrayList<String> blockList=new ArrayList<String>();
		Random rand = new Random();

		for (int i = 0; i < 5 ; i++) {
			for (int j = 0; j < 5; j++) {
				if(i==0 && j==0){
					if(grid[i][j]==0 || grid[i][j]==2 || grid[i][j]==4 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 0);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==4){
						blockList.add(i + " " + j + " " + 3);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						if(grid[i+1][j]!= 6 && grid[i+1][j]!= 10 && grid[i+1][j]!= 12 && grid[i+1][j]!= 14){
							blockList.add(i + " " + j + " " + 2);
						}
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						if(grid[i][j+1]!=3 && grid[i][j+1]!=5 && grid[i][j+1]!=6){
							blockList.add(i + " " + j + " " + 1);
						}
					}
				}else if(i==0 && j!=4){
					if(grid[i][j]==0 || grid[i][j]==2 || grid[i][j]==4 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 0);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						if(grid[i+1][j]!= 6 && grid[i+1][j]!= 10 && grid[i+1][j]!= 12 && grid[i+1][j]!= 14){
							blockList.add(i + " " + j + " " + 2);
						}
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						if(grid[i][j+1]!=3 && grid[i][j+1]!=5 && grid[i][j+1]!=6){
							blockList.add(i + " " + j + " " + 1);
						}
					}
				}else if(i==0 && j==4){
					if(grid[i][j]==0 || grid[i][j]==2 || grid[i][j]==4 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 0);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						if(grid[i+1][j]!= 6 && grid[i+1][j]!= 10 && grid[i+1][j]!= 12 && grid[i+1][j]!= 14){
							blockList.add(i + " " + j + " " + 2);
						}
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 1);
					}
				}else if(i!=4 && j==0){
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==4){
						blockList.add(i + " " + j + " " + 3);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						if(grid[i+1][j]!= 6 && grid[i+1][j]!= 10 && grid[i+1][j]!= 12 && grid[i+1][j]!= 14){
							blockList.add(i + " " + j + " " + 2);
						}
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						if(grid[i][j+1]!=3 && grid[i][j+1]!=5 && grid[i][j+1]!=6){
							blockList.add(i + " " + j + " " + 1);
						}
					}
				}else if(i==4 && j==0){
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==4){
						blockList.add(i + " " + j + " " + 3);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 2);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						if(grid[i][j+1]!=3 && grid[i][j+1]!=5 && grid[i][j+1]!=6){
							blockList.add(i + " " + j + " " + 1);
						}
					}
				}else if(i!=0 && j!=0 && i!=4 && j!=4 ){
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						if(grid[i+1][j]!= 6 && grid[i+1][j]!= 10 && grid[i+1][j]!= 12 && grid[i+1][j]!= 14){
							blockList.add(i + " " + j + " " + 2);
						}
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						if(grid[i][j+1]!=3 && grid[i][j+1]!=5 && grid[i][j+1]!=6){
							blockList.add(i + " " + j + " " + 1);
						}
					}
				}else if(i==4 && j!=0 && j!=4){
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 2);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						if(grid[i][j+1]!=3 && grid[i][j+1]!=5 && grid[i][j+1]!=6){
							blockList.add(i + " " + j + " " + 1);
						}
					}
				}
				else if(i!=0 && i!=4 && j==4){
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						if(grid[i+1][j]!= 6 && grid[i+1][j]!= 10 && grid[i+1][j]!= 12 && grid[i+1][j]!= 14){
							blockList.add(i + " " + j + " " + 2);
						}
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 1);
					}
				}else if(i==4 && j==4){
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==2 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 2);
					}
					if(grid[i][j]==0 || grid[i][j]==1 || grid[i][j]==4 || grid[i][j]==8){
						blockList.add(i + " " + j + " " + 1);
					}
				}
			}
		}
		System.out.println("available sides: "+blockList.size());
		
		System.out.println(blockList);
		if(blockList.size()==1){
			System.out.println( "two block"+twoSideBlocked(grid));
		}
		if(blockList.size()==0){
			return null;
		}else{
			return blockList.get(rand.nextInt(blockList.size()));
		}

	}
	
	private static void clone(int[][] copyFrom, int[][] copyTo){
		for(int i=0; i<copyTo.length; i++)
			  for(int j=0; j<copyTo[i].length; j++)
			    copyTo[i][j]=copyFrom[i][j];
	}
	
	private static int[] fetchCoordinate(int[][] blockFlag, int value) {
		int[] coord=new int[2];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(blockFlag[i][j]==value){
					coord[0]=i;
					coord[1]=j;
					return coord;
				}
			}
		}
		return null;
	}
}
