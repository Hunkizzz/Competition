package Source;

public class Competition {
	private int id;
	private int id_member;
	private float stage1;
	private float stage2;
	private float stage3;
	private float stage4;
	private float stage5;
	private float stage6;
	private float stage7;
	private float stage8;
	

	
	public Competition(int id, int id_member, float stage1,float stage2,float stage3,float stage4,float stage5,float stage6,float stage7,float stage8) {
		this.id = id;
		this.id_member = id_member;
		this.stage1 = stage1;
		this.stage2 = stage2;
		this.stage3 = stage3;
		this.stage4 = stage4;
		this.stage5 = stage5;
		this.stage6 = stage6;
		this.stage7 = stage7;
		this.stage8 = stage8;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_member() {
		return id_member;
	}

	public void setId_member(int id_member) {
		this.id_member = id_member;
	}

	public float getStage1() {
		return stage1;
	}

	public void setStage1(float stage1) {
		this.stage1 = stage1;
	}

	public float getStage2() {
		return stage2;
	}

	public void setStage2(float stage2) {
		this.stage2 = stage2;
	}

	public float getStage4() {
		return stage4;
	}

	public void setStage4(float stage4) {
		this.stage4 = stage4;
	}

	public float getStage6() {
		return stage6;
	}

	public void setStage6(float stage6) {
		this.stage6 = stage6;
	}

	public float getStage7() {
		return stage7;
	}

	public void setStage7(float stage7) {
		this.stage7 = stage7;
	}

	public float getStage3() {
		return stage3;
	}

	public void setStage3(float stage3) {
		this.stage3 = stage3;
	}

	public float getStage5() {
		return stage5;
	}

	public void setStage5(float stage5) {
		this.stage5 = stage5;
	}

	public float getStage8() {
		return stage8;
	}

	public void setStage8(float stage8) {
		this.stage8 = stage8;
	}

	public float getFirst_res() {
		return stage1+stage2+stage3+stage4;
	}

	public float getSecond_res() {
		return stage5+stage6+stage7+stage8;
	}

	public float getTotal() {
		return stage1+stage2+stage3+stage4+stage5+stage6+stage7+stage8;
	}





	
	
	
	
	

}
