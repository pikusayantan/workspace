import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class TestClass3 {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] grid = new int[5][5];
		boolean flagPoint = false;
		boolean flagPoint1 = false;
		boolean flagPoint2 = false;
		boolean flagPoint3 = false;
		String winningBlock = null;

		for (int i = 0; i < 5; i++) {
			String line[] = br.readLine().split(" ");

			for (int j = 0; j < 5; j++) {
				grid[i][j] = Integer.parseInt(line[j]);
				
				if (grid[i][j] == 14 || grid[i][j] == 13 || grid[i][j] == 11 || grid[i][j] == 7) {
					
					/*String block=conquerBlock(grid);
					if(block!=null){
						flagPoint=true;
						winningBlock=block;
					};*/
					
					flagPoint = true;
					switch (grid[i][j]) {
					case 14:
						winningBlock = i + " " + j + " " + 0;
						break;
					case 13:
						winningBlock = i + " " + j + " " + 1;
						break;
					case 11:
						winningBlock = i + " " + j + " " + 2;
						break;
					case 7:
						winningBlock = i + " " + j + " " + 3;
						break;
					}

				}

			}
		}
		String player= br.readLine();

		if (flagPoint) {
//			System.out.println("flagpoint");
			System.out.println(winningBlock);
		} else {
			
			// START -when no side is blocked
			String block=noSideBlocked(grid);
			if(block!=null){
				flagPoint1=true;
				winningBlock=block;
			};
			// ENDS -when no side is blocked
		}

		if (flagPoint1 && !flagPoint) {
//			System.out.println("flagpoint1");
			System.out.println(winningBlock);
		} else if(!flagPoint && !flagPoint1) {
			
			// START -when 1 side is blocked
			String block=oneSideBlocked(grid);
			if(block!=null){
				flagPoint2=true;
				winningBlock=block;
			};
			// END -when 1 side is blocked

		}
		
		if (flagPoint2 && !flagPoint && !flagPoint1) {
//			System.out.println("flagpoint2");
			System.out.println(winningBlock);
		} else if(!flagPoint && !flagPoint1 && !flagPoint2) {
			
			//START -when 2 side is blocked
			String block=twoSideBlocked(grid);
			if(block!=null){
				flagPoint3=true;
				winningBlock=block;
			};
			//END -when 2 side is blocked
		}
		
		if (flagPoint3 && !flagPoint && !flagPoint1 && !flagPoint2) {
//			System.out.println("flagpoint3");
			System.out.println(winningBlock);
		}

//		System.out.println("array:" + Arrays.deepToString(grid));
	}

	private static String conquerBlock(int[][] grid) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String twoSideBlocked(int[][] grid) {

		String winningBlock=null;
		int count=0;
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
				} else if (grid[i][j] == 3) {
					if(j!=0 && i!=4){
						if(blockFlag[i][j-1]!=0 && blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
							mergeBlock(blockFlag,blockFlag[i][j-1],blockFlag[i+1][j]);
						}else if(blockFlag[i][j-1]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else if(blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==0 && i==4){
						blockFlag[i][j]=++count;
					}else if(j==0){
						if(blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==4){
						if(blockFlag[i][j-1]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 5){
					if(j!=0 && j!=4){
						if(blockFlag[i][j-1]!=0 && blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
							mergeBlock(blockFlag,blockFlag[i][j-1],blockFlag[i][j+1]);
						}else if(blockFlag[i][j-1]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else if(blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==0){
						if(blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==4){
						if(blockFlag[i][j-1]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 6){
					if(i!=0 && j!=0){
						if(blockFlag[i-1][j]!=0 && blockFlag[i][j-1]!=0){
							blockFlag[i][j]=blockFlag[i-1][j];
							mergeBlock(blockFlag,blockFlag[i-1][j],blockFlag[i][j-1]);
						}else if(blockFlag[i-1][j]!=0){
							blockFlag[i][j]= blockFlag[i-1][j];
						}else if(blockFlag[i][j-1]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==0 && j==0){
						blockFlag[i][j]=++count;
					}else if(i==0){
						if(blockFlag[i][j-1]!=0){
							blockFlag[i][j]=blockFlag[i][j-1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==0){
						if(blockFlag[i-1][j]!=0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 9){
					if(i!=4 && j!=4){
						if(blockFlag[i][j+1]!=0 && blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i][j+1];
							mergeBlock(blockFlag,blockFlag[i][j+1],blockFlag[i+1][j]);
						}else if(blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else if(blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==4 && j==4){
						blockFlag[i][j]=++count;
					}else if(i==4){
						if(blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==4){
						if(blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 10){
					if(i!=0 && i!=4){
						if(blockFlag[i-1][j]!=0 && blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i-1][j];
							mergeBlock(blockFlag, blockFlag[i-1][j], blockFlag[i+1][j]);
						}else if(blockFlag[i-1][j]!=0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else if(blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==0){
						if(blockFlag[i+1][j]!=0){
							blockFlag[i][j]=blockFlag[i+1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==4){
						if(blockFlag[i-1][j]!=0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else{
							blockFlag[i][j]=++count;
						}
					}
				}else if(grid[i][j] == 12){
					if(i!=0 && j!=4){
						if(blockFlag[i-1][j]!=0 && blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i-1][j];
							mergeBlock(blockFlag, blockFlag[i-1][j], blockFlag[i][j+1]);
						}else if(blockFlag[i-1][j]!=0){
							blockFlag[i][j]=blockFlag[i-1][j];
						}else if(blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(i==0 && j==4){
						blockFlag[i][j]=++count;
					}else if(i==0){
						if(blockFlag[i][j+1]!=0){
							blockFlag[i][j]=blockFlag[i][j+1];
						}else{
							blockFlag[i][j]=++count;
						}
					}else if(j==4){
						if(blockFlag[i-1][j]!=0){
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
		return winningBlock;
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
		int[][] blocktemp=blockFlag.clone();
		int[][] backup=new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				backup=blocktemp.clone();
				System.out.println("start: "+Arrays.deepToString(backup));
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
								blocktemp[i][j - 2] = 0;//err
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
								blocktemp[i][j - 2] = 0;
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
					System.out.println("error");
					System.out.println("error: "+Arrays.deepToString(backup));
					blocktemp=backup.clone();
				}
				System.out.println("endss: "+Arrays.deepToString(blocktemp));
			}
		}
		blockFlag=blocktemp;
	}

	private static String oneSideBlocked(int[][] grid) {

		ArrayList<String> blockList=new ArrayList<String>();
		ArrayList<Integer> oneSide=new ArrayList<Integer>();
		oneSide.add(0);
		oneSide.add(1);
		oneSide.add(2);
		oneSide.add(4);
		oneSide.add(8);
		// START -when 1 side is blocked
		for (int i = 4; i >= 0; i--) {
			for (int j = 4; j >= 0; j--) {
				if (grid[i][j] == 1) {
					if(j==4){
						blockList.add(i + " " + j + " " + 1);
						if(i!=4){
							System.out.println("err: "+i+" "+j);
							if(oneSide.contains(grid[i+1][j])){
								blockList.add(i + " " + j + " " + 2);
							}
							if(oneSide.contains(grid[i][j-1])){
								blockList.add(i + " " + j + " " + 3);
							}
						}
					} if(j==0){
						blockList.add(i + " " + j + " " + 3);
						if(i!=4){
							if(oneSide.contains(grid[i][j+1])){
								blockList.add(i + " " + j + " " + 1);
							} if(oneSide.contains(grid[i+1][j])){
								blockList.add(i + " " + j + " " + 2);
							}
						}
					} if(i==4){
						blockList.add(i + " " + j + " " + 2);
						if(j!=0 && j!=4){
							if(oneSide.contains(grid[i][j+1])){
								blockList.add(i + " " + j + " " + 1);
							}
							if(oneSide.contains(grid[i][j-1])){
								blockList.add(i + " " + j + " " + 3);
							}
						}
					} if(j!=4 && j!=0 && i!=4){
						if(oneSide.contains(grid[i][j+1])){
							blockList.add(i + " " + j + " " + 1);
						} if(oneSide.contains(grid[i+1][j])){
							blockList.add(i + " " + j + " " + 2);
						} if(oneSide.contains(grid[i][j-1])){
							blockList.add(i + " " + j + " " + 3);
						}
					}

				}else if(grid[i][j] == 2){
					if(j==0){
						blockList.add(i + " " + j + " " + 3);
						if(i!=0 && i!=4){
							if(oneSide.contains(grid[i+1][j])){
								blockList.add(i + " " + j + " " + 2);
							}
							if(oneSide.contains(grid[i-1][j])){
								blockList.add(i + " " + j + " " + 0);
							}
						}
					}if(i==4){
						blockList.add(i + " " + j + " " + 2);
						if(j!=0){
							if(oneSide.contains(grid[i][j-1])){
								blockList.add(i + " " + j + " " + 3);
							}
							if(oneSide.contains(grid[i-1][j])){
								blockList.add(i + " " + j + " " + 0);
							}
						}
					}if(i==0){
						blockList.add(i + " " + j + " " + 0);
						if(j!=0){
							if(oneSide.contains(grid[i][j-1])){
								blockList.add(i + " " + j + " " + 3);
							}
							if(oneSide.contains(grid[i+1][j])){
								blockList.add(i + " " + j + " " + 2);
							}
						}
					}if(j!=0 && i!=4 && i!=0){
						if(oneSide.contains(grid[i+1][j])){
							blockList.add(i + " " + j + " " + 2);
						}if(oneSide.contains(grid[i][j-1])){
							blockList.add(i + " " + j + " " + 3);
						}if(oneSide.contains(grid[i-1][j])){
							blockList.add(i + " " + j + " " + 0);
						}
					}
				}else if(grid[i][j] == 4){
					
					if(j==0){
						blockList.add(i + " " + j + " " + 3);
						if(i!=0){
							if(oneSide.contains(grid[i-1][j])){
								blockList.add(i + " " + j + " " + 0);
							}if(oneSide.contains(grid[i][j+1])){
								blockList.add(i + " " + j + " " + 1);
							}
						}
					}if(j==4){
						blockList.add(i + " " + j + " " + 1);
						if(i!=0){
							if(oneSide.contains(grid[i][j-1])){
								blockList.add(i + " " + j + " " + 3);
							}if(oneSide.contains(grid[i-1][j])){
								blockList.add(i + " " + j + " " + 0);
							}
						}
					}if(i==0){
						blockList.add(i + " " + j + " " + 0);
						if(j!=0 && j!=4){
							if(oneSide.contains(grid[i][j-1])){
								blockList.add(i + " " + j + " " + 3);
							}if(oneSide.contains(grid[i][j+1])){
								blockList.add(i + " " + j + " " + 1);
							}
						}
					}if(j!=0 && j!=4 && i!=0){
						if(oneSide.contains(grid[i][j-1])){
							blockList.add(i + " " + j + " " + 3);
						}if(oneSide.contains(grid[i-1][j])){
							blockList.add(i + " " + j + " " + 0);
						}if(oneSide.contains(grid[i][j+1])){
							blockList.add(i + " " + j + " " + 1);
						}
					}
					
				}else if(grid[i][j] == 8){
					
					if(i==4){
						blockList.add(i + " " + j + " " + 2);
						if(j!=4){
							if(oneSide.contains(grid[i-1][j])){
								blockList.add(i + " " + j + " " + 0);
							}if(oneSide.contains(grid[i][j+1])){
								blockList.add(i + " " + j + " " + 1);
							}
						}
					}if(j==4){
						blockList.add(i + " " + j + " " + 1);
						if(i!=0 && i!=4){
							if(oneSide.contains(grid[i-1][j])){
								blockList.add(i + " " + j + " " + 0);
							}if(oneSide.contains(grid[i+1][j])){
								blockList.add(i + " " + j + " " + 2);
							}
						}
					}if(i==0){
						blockList.add(i + " " + j + " " + 0);
						if(j!=4){
							if(oneSide.contains(grid[i][j+1])){
								blockList.add(i + " " + j + " " + 1);
							}if(oneSide.contains(grid[i+1][j])){
								blockList.add(i + " " + j + " " + 2);
							}
						}
					}if(i!=4 && j!=4 && i!=0){
						if(oneSide.contains(grid[i-1][j])){
							blockList.add(i + " " + j + " " + 0);
						}if(oneSide.contains(grid[i][j+1])){
							blockList.add(i + " " + j + " " + 1);
						}if(oneSide.contains(grid[i+1][j])){
							blockList.add(i + " " + j + " " + 2);
						}
					}
					
				}

			}
		}
		// END -when 1 side is blocked
		Random rand = new Random();
		System.out.println("one side list size: "+blockList.size());
		
		System.out.println(blockList);
		if(blockList.size()==0){
			return null;
		}else{
			return blockList.get(rand.nextInt(blockList.size()));
		}
	}

	private static String noSideBlocked(int[][] grid) {
		ArrayList<String> blockList=new ArrayList<String>();
		Random rand = new Random();
		// START -when no side is blocked
		for (int i = 4; i >= 0; i--) {
			for (int j = 4; j >= 0; j--) {
				if (grid[i][j] == 0) {
					if (i == 0) {
						blockList.add( i + " " + j + " " + 0);
						if(grid[i+1][j]==0 || grid[i+1][j]==1 || grid[i+1][j]==2 || grid[i+1][j]==4 || grid[i+1][j]==8){
							blockList.add(i + " " + j + " " + 2);
						}
					} if (j == 4) {
						blockList.add(i + " " + j + " " + 1);
						if(grid[i][j-1]==0 || grid[i][j-1]==1 || grid[i][j-1]==2 || grid[i][j-1]==4 || grid[i][j-1]==8){
							blockList.add(i + " " + j + " " + 3);
						}
					} if(i==4){
						blockList.add(i + " " + j + " " + 2);
						if (grid[i - 1][j] == 0 || grid[i - 1][j] == 1 || grid[i - 1][j] == 2 || grid[i - 1][j] == 4 || grid[i - 1][j] == 8) {
							blockList.add(i + " " + j + " " + 0);
						}
					} if(j==0){
						blockList.add(i + " " + j + " " + 3);
						if(grid[i][j+1] == 0 || grid[i][j+1] == 1 ||grid[i][j+1] == 2 || grid[i][j+1] == 4 || grid[i][j+1] == 8){
							blockList.add(i + " " + j + " " + 1);
						}
					} if(i!=0 && i!=4 && j!=0 && j!=4){
						if (grid[i - 1][j] == 0 || grid[i - 1][j] == 1 || grid[i - 1][j] == 2 || grid[i - 1][j] == 4 || grid[i - 1][j] == 8) {
							blockList.add(i + " " + j + " " + 0);
						} if(grid[i][j+1] == 0 || grid[i][j+1] == 1 ||grid[i][j+1] == 2 || grid[i][j+1] == 4 || grid[i][j+1] == 8){
							blockList.add(i + " " + j + " " + 1);
						} if(grid[i+1][j]==0 || grid[i+1][j]==1 || grid[i+1][j]==2 || grid[i+1][j]==4 || grid[i+1][j]==8){
							blockList.add(i + " " + j + " " + 2);
						} if(grid[i][j-1]==0 || grid[i][j-1]==1 || grid[i][j-1]==2 || grid[i][j-1]==4 || grid[i][j-1]==8){
							blockList.add(i + " " + j + " " + 3);
						}
					}
				}
			}
		}

		System.out.println("No side list size: "+blockList.size());
		
		System.out.println(blockList);
		if(blockList.size()==0){
			return null;
		}else{
			return blockList.get(rand.nextInt(blockList.size()));
		}
		// ENDS -when no side is blocked	
	}
}