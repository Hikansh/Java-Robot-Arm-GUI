import java.lang.reflect.Array;
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
	   
	   
	   
	   /*
	    * w>same
	    * h>2
	    * 	1 ->++7(h)
	    * 		++6(d)
	    * 	2 -> --6(d)
	    * 		--2(h)
	    * 		++2(d)
	    * 	3 -> --2(d)
	    * 	4-> ++2(h)
	    * 		++6(d)
	    * 		--6(d)	
	    * */	
	   
	   
	   
	   
	   // The first part can be solved easily with out any arrays as the height of bars and blocks are fixed.
	   // Use the method r.up(), r.down(), r.extend(), r.contract(), r.raise(), r.lower(), r.pick(), r.drop()
	   // The code below will cause first arm to be moved up, the second arm to the right and the third to be lowered. 
	   
//	   //First block
//	   for(int i=0;i<7;i++)
//		   r.up();  			// move the first arm up by 8 units
//	   for(int i=0;i<9;i++)
//		   r.extend();			// move the second arm to the right by 9 units
//	   r.pick();				//picks up the 1st block
//	   for(int i=0;i<9;i++)
//		   r.contract();		//contracts the second arm by 9 units
//	   for(int i=0;i<6;i++)
//		   r.lower();			//lowers the third arm by 6 units
//	   r.drop();				//drops off the first block on the target
//	   
//	   
//	   //2nd Block
//	   for(int i=0;i<6;i++)
//		   r.raise();			//raises the third arm by 6 units
//	   for(int i=0;i<9;i++)
//		   r.extend();			//extends the second arm by 9 units
//	   for(int i=0;i<2;i++)
//		   r.down(); 			//lowers the height of first arm by 2 units
//	   r.pick();
//	   for(int i=0;i<9;i++)
//		   r.contract(); 		//contracts the 2nd arm by 9 units
//	   for(int i=0;i<2;i++)
//		   r.lower();
//	   r.drop();
//	   
//	     
//	   //3rd Block
//	   for(int i=0;i<9;i++)
//		   r.extend();
//	   r.pick();
//	   for(int i=0;i<2;i++)
//		   r.raise();
//	   for(int i=0;i<9;i++)
//		   r.contract();
//	   r.drop();
//	   
//	   
//	   //4th block
//	   for(int i=0;i<2;i++)
//		   r.up();
//	   for(int i=0;i<9;i++)
//		   r.extend();
//	   for(int i=0;i<6;i++)
//		   r.lower();
//	   r.pick();
//	   for(int i=0;i<6;i++)
//		   r.raise();
//	   for(int i=0;i<9;i++)
//		   r.contract();
//	   r.drop();
//	   
	  
	   // Part B requires you to access the array barHeights passed as argument as the robot arm must move
	   // over the bars
	   
	   
	   
	   // The third part requires you to access the arrays barHeights and blockHeights 
	   // as the heights of bars and blocks are allowed to vary through command line arguments
	   

	   
	   
	   // The fourth part allows the user  to specify the order in which bars must 
	   // be placed in the target column. This will require you to use the use additional column
	   // which can hold temporary values
	   

	   
	   
	   
	   // The last part requires you to write the code to move from source column to target column using
	   // an additional temporary column but without placing a larger block on top of a smaller block 
	   
	   for(int i=0;i<required.length;i++)
		   System.out.println(required[i]+"  "+blockHeights[i]);
	 
	   
	 		int h = 2;
	 		int w = 1;
	 		int d = 0;
	 		
	 		int destCol = 0;
	 		
	 		int tempCol=0;					//tempcol for part C
	 		ArrayList<Integer> tempArray=new ArrayList<Integer>() ;			//temp col array
	 		
	 		int sourceCol = 10;
	 		int armHeight1 = 0;
	 		
	 		int currentBlock = blockHeights.length - 1;
	 		int currentOrderedBlock=required.length-1;
	 		
	 		int sourceHeight = 0;
	 		for (int currentht = 0; currentht < blockHeights.length; currentht++)	
	 		{
	 			sourceHeight += blockHeights[currentht];
	 		}
	 		
	 		
	 		while (currentBlock >= 0)
	 		{
	 			while (d > 0)				
	 			{
	 				r.raise();
	 				d--;
	 			}
	 			int blockht = blockHeights[currentBlock];		
	 		
	 			int barlarge = barHeights[0];					
	 			for(int i=1; i< barHeights.length; i++)
	 				{
	 					if(barHeights[i] > barlarge)     
	 						barlarge = barHeights[i];               
	 				}
	         
	 			int blocklarge = blockHeights[0];				
	 			for(int j=1; j< blockHeights.length; j++)
	 				{
	 					if(blockHeights[j] > blocklarge)
	 						blocklarge = blockHeights[j];               
	 				}
	 			
	 			if (sourceHeight < (blocklarge + barlarge))		
	 				{
	 					armHeight1 = blocklarge + barlarge;
	 				}
	 			else 
	 				{
	 					armHeight1 = sourceHeight;
	 				}
	 		
	 			while (h - 1 < armHeight1)			
	 				{
	 					r.up();
	 					h++;
	 				}
	 			armHeight1 =- blockht;				
	 			while (w < sourceCol)	
	 				{
	 					r.extend();
	 					w++;
	 				}
	 			while(h - d > sourceHeight + 1)		
	 				{
	 					r.lower();
	 					d++;
	 				}
	 		
	 			r.pick();
	 			if(required[required.length-currentOrderedBlock-1]==blockHeights[currentBlock]){
	 				
	 				sourceHeight-=blockht;			
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
		 					while ((h - 1) - d - blockht > destCol) 
		 						{
		 							r.lower();
		 							d++;
		 						}
					r.drop();
					destCol+=blockHeights[currentBlock];
		 			
		 			currentBlock--;
		 			currentOrderedBlock--;

	 			}
	 			else {
	 				sourceHeight-=blockht;			
		 			while(d > 0)				
		 			{
		 				r.raise();
		 				d--;
		 			}
		 			r.contract();
		 			w--;
		 			while ((h - 1) - d - blockht > tempCol) 
						{
							r.lower();
							d++;
						}
					r.drop();
					tempCol+=blockHeights[currentBlock];
					tempArray.add(blockHeights[currentBlock]);
					currentBlock--;
					
	 			}
	 		}
	 		
	 		//temp col to traverse end of while
	 		//NOTE: MAYBE WE NEED TO MAKE TWO ARRAYS (TEMPCOL AND SOURCECOL) AND COMPARE TOPMOST ITEM EACH TIME
	 		
	 		while(tempCol>0) {
	 			currentBlock=(tempArray.size()-1);
	 			int blockht = blockHeights[currentBlock];		
	 			while (d > 0)				
	 			{
	 				r.raise();
	 				d--;
	 			}
	 			while (w < 9)	
 				{
 					r.extend();
 					w++;
 				}
	 			while(h - d > tempCol + 1)		
 				{
 					r.lower();
 					d++;
 				}
 		
	 			r.pick();
	 			System.out.println(currentOrderedBlock+"  "+currentBlock);
 				
	 			if(required[required.length-currentOrderedBlock-1]==blockHeights[currentBlock]){
	 				tempCol-=blockht;			
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
		 					while ((h - 1) - d - blockht > destCol) 
		 						{		 		
		 							r.lower();
		 							d++;
		 						}
					r.drop();
					destCol+=blockht;
					
					
					
//					destCol+=blockHeights[currentBlock];
//		 			currentBlock--;
//		 			currentOrderedBlock--;

		 			
		 			
	 			}
	 			else {
	 				tempCol-=blockht;			
		 			while(d > 0)				
		 			{
		 				r.raise();
		 				d--;
		 			}
		 			r.extend();
		 			w++;
		 			while ((h - 1) - d - blockht > sourceHeight+1) 
						{
							r.lower();
							d++;
						}
					r.drop();
					sourceHeight+=blockHeights[currentBlock];
					currentBlock--;
					
	 			}


	 		}
	 		
   }  
}  

