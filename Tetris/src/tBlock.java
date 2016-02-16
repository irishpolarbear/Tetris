
public class tBlock extends Block {
	public tBlock(){
		setX(2);
		setY(2);
		int newEx = 0 , newFx = 0 , newGx = 1, newHx = -1;
		int newEy = 1 , newFy = 0 , newGy = 0, newHy = 0;
		
		setEx(newEx);
		setFx(newFx);
		setGx(newGx);
		setHx(newHx);
		setEy(newEy);
		setFy(newFy);
		setGy(newGy);
		setHy(newHy);
		
		//setCoords({{-1,0},{0,0},{0,1},{-1,1}});
		System.out.println("T-block created.");
	}
}
