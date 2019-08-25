import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;

class RobotControl
 {
   private Robot r;
   
   public RobotControl(Robot r)
   {
       this.r = r;
   }

   public void control(int barHeights[], int blockHeights[], int required[], boolean ordered)
   {
	   
	   // The first part can be solved easily with out any arrays as the height of bars and blocks are fixed.
	   // Use the method r.up(), r.down(), r.extend(), r.contract(), r.raise(), r.lower(), r.pick(), r.drop()
	   // The code below will cause first arm to be moved up, the second arm to the right and the third to be lowered. 
	   
	   // Part B requires you to access the array barHeights passed as argument as the robot arm must move
	   // over the bars
	   
	   // The third part requires you to access the arrays barHeights and blockHeights 
	   // as the heights of bars and blocks are allowed to vary through command line arguments
	   
	   // The fourth part allows the user  to specify the order in which bars must 
	   // be placed in the target column. This will require you to use the use additional column
	   // which can hold temporary values
	   
	   // The last part requires you to write the code to move from source column to target column using
	   // an additional temporary column but without placing a larger block on top of a smaller block 
	   	 
	   
	 		int h = 2;
	 		int w = 1;
	 		int d = 0;
	 		
	 		int destCol = 0;	
	 		int tempCol=0;					//tempcol for part C/D
	 		
	 		ArrayList<Integer> tempArray=new ArrayList<Integer>() ;			//temp col array
	 		ArrayList<Integer> sourceArray=new ArrayList<Integer>() ;			//source col array
	 		
	 		//Init source array
	 		for(int i=0;i<blockHeights.length;i++)
	 			sourceArray.add(blockHeights[i]);
	 		
	 		int sourceCol = 10;
	 		int armHeight1 = 0;
	 		
	 		int currentBlock = blockHeights.length - 1;
	 		int currentOrderedBlock=0;
	 		
	 		//Finding the total source height
	 		int sourceHeight = 0;
	 		for (int currentht = 0; currentht < blockHeights.length; currentht++)	
	 		{
	 			sourceHeight += blockHeights[currentht];
	 		}
	 	
	 		while (tempArray.size() > 0 || sourceArray.size() > 0)
	 		{
	 			while (d > 0)				
	 			{
	 				r.raise();
	 				d--;
	 			}
	 			int blockht = blockHeights[currentBlock];		
	 			//Finding max of bars
	 			int barlarge = barHeights[0];					
	 			for(int i=1; i< barHeights.length; i++)
	 				{
	 					if(barHeights[i] > barlarge)     
	 						barlarge = barHeights[i];               
	 				}
	         
	 			//Finding max of blocks
	 			int blocklarge = blockHeights[0];				
	 			for(int j=1; j< blockHeights.length; j++)
	 				{
	 					if(blockHeights[j] > blocklarge)
	 						blocklarge = blockHeights[j];               
	 				}
	 			
	 			//Setting the height of 1st arm
	 			if (sourceHeight < (blocklarge + barlarge))		
	 				{
	 					armHeight1 = blocklarge + barlarge;
	 				}
	 			else 
	 				{
	 					armHeight1 = sourceHeight;
	 				}
	 		
	 			//Arm1 height increased 
	 			while (h - 1 < armHeight1)			
	 				{
	 					r.up();
	 					h++;
	 				}
	 			armHeight1 =- blockht;				
	 			
	 			if(sourceArray.size()>0 && required[currentOrderedBlock]==sourceArray.get(sourceArray.size()-1)) {
	 				//put to dest col and remove
	 				while (w < sourceCol)	
	 				{
	 					r.extend();
	 					w++;
	 				}
	 			//Arm 3 lowered
	 			while(h - d > sourceHeight +1)		
	 				{
	 					r.lower();
	 					d++;
	 				}
	 			r.pick();
	 				sourceHeight-=sourceArray.get(sourceArray.size()-1);			
		 			while(d > 0)				
		 			{
		 				r.raise();
		 				d--;
		 			}

		 			while (w > 1) 
		 						{
		 							r.contract();
		 							w--;
		 						}
		 					while ((h - 1) - d - sourceArray.get(sourceArray.size()-1) > destCol) 
		 						{
		 							r.lower();
		 							d++;
		 						}
					r.drop();
					destCol+=sourceArray.get(sourceArray.size()-1);
		 			currentOrderedBlock++;
		 			sourceArray.remove(sourceArray.size()-1);
	 			}

	 			else if(tempArray.size()>0 && required[currentOrderedBlock]==tempArray.get(tempArray.size()-1)) {
	 		 		//put in destcol and remove
	 					if(w==sourceCol)
	 					{
	 						r.contract();
	 						w--;
	 					}
	 					while (w < sourceCol-1)	
		 				{
		 					r.extend();
		 					w++;
		 				}
			 		//Arm 3 lowered
		 			while(h - d > tempCol +1)		
		 				{
		 					r.lower();
		 					d++;
		 				}
		 			r.pick();
	 				tempCol-=tempArray.get(tempArray.size()-1);			
		 			while(d > 0)				
		 			{
		 				r.raise();
		 				d--;
		 			}
		 			while (w > 1) 
		 						{
		 							r.contract();
		 							w--;
		 						}
		 					while ((h - 1) - d - tempArray.get(tempArray.size()-1) > destCol) 
		 						{		 		
		 							r.lower();
		 							d++;
		 						}
					r.drop();
					destCol+=tempArray.get(tempArray.size()-1);
					currentOrderedBlock++;
					tempArray.remove(tempArray.size()-1);
	 				}
	 			else {
	 				if(tempArray.size()>sourceArray.size()) {
	 					//pick from temp drop to source
	 					if(w==sourceCol)
	 					{
	 						r.contract();
	 						w--;
	 					}
	 					while (w < sourceCol-1)	
		 				{
		 					r.extend();
		 					w++;
		 				}
			 		//Arm 3 lowered
		 			while(h - d > tempCol +1)		
		 				{
		 					r.lower();
		 					d++;
		 				}
		 			r.pick();
	 					tempCol-=tempArray.get(tempArray.size()-1);			
			 			while(d > 0)				
			 			{
			 				r.raise();
			 				d--;
			 			}
			 			r.extend();
			 			w++;
			 			while ((h - 1) - d - tempArray.get(tempArray.size()-1) > sourceHeight) 
							{
								r.lower();
								d++;
							}
						r.drop();
						sourceHeight+=tempArray.get(tempArray.size()-1);
						sourceArray.add(tempArray.get(tempArray.size()-1));
						tempArray.remove(tempArray.size()-1);
	 				}
	 				else {
	 					//pick from source put to temp
	 					while (w < sourceCol)	
		 				{
		 					r.extend();
		 					w++;
		 				}
		 			
		 			//Arm 3 lowered
		 			while(h - d > sourceHeight +1)		
		 				{
		 					r.lower();
		 					d++;
		 				}
		 			r.pick();
	 					sourceHeight-=sourceArray.get(sourceArray.size()-1);			
			 			while(d > 0)				
			 			{
			 				r.raise();
			 				d--;
			 			}
			 			r.contract();
			 			w--;
			 			while ((h - 1) - d - sourceArray.get(sourceArray.size()-1) > tempCol) 
							{
								r.lower();
								d++;
							}
						r.drop();
						tempCol+=sourceArray.get(sourceArray.size()-1);
						tempArray.add(sourceArray.get(sourceArray.size()-1));
						sourceArray.remove(sourceArray.size()-1);
	 				}
	 			}		
	 		}  
   		}  
 	}

