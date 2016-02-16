
public abstract class Block {
	private int x;
	private int y;
	private int gx, gy, hx, hy, ex, ey, fx, fy;
	
	
	//private int[][] coords;

	public int getX() {
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getHx() {
		return hx;
	}
	
	public int getHy(){
		return hy;
	}
	
	public int getGx() {
		return gx;
	}
	
	public int getGy(){
		return gy;
	}
	
	public int getEx() {
		return ex;
	}
	
	public int getEy(){
		return ey;
	}
	
	public int getFx() {
		return fx;
	}
	
	public int getFy(){
		return fy;
	}
	
	public void setHx(int hx){
		this.hx = hx;
	}
	
	public void setHy(int hy){
		this.hy = hy;
	}
	
	public void setGx(int gx){
		this.gx = gx;
	}
	
	public void setGy(int gy){
		this.gy = gy;
	}
	
	public void setEx(int ex){
		this.ex = ex;
	}
	
	public void setEy(int ey){
		this.ey = ey;
	}
	
	public void setFx(int fx){
		this.fx = fx;
	}
	
	public void setFy(int fy){
		this.fy = fy;
	}
	
	/*public int[][] getCoords(){
		return coords;
	}
	
	public void setCoords(int[][] coords){
		this.coords = coords;
	}*/
}
