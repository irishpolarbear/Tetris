
public class lineBlock extends Block {
	public lineBlock(){
		setX(2);
		setY(2);
		
		int newEx = -1, newFx = 0, newGx = 1, newHx = 2;
		int newEy = 0, newFy = 0, newGy = 0, newHy = 0;
		
		setEx(newEx);
		setFx(newFx);
		setGx(newGx);
		setHx(newHx);
		setEy(newEy);
		setFy(newFy);
		setGy(newGy);
		setHy(newHy);
		
		//setCoords({{-1,0},{0,0},{0,1},{-1,1}});
		System.out.println("Line block created.");
	}
}
