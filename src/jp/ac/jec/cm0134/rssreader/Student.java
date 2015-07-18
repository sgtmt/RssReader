package jp.ac.jec.cm0134.rssreader;

public class Student {
	private String id; // 学籍番号
	private String name; // 学生指名
	private int age; // 年齢
	private boolean hasMCPC; // MCPCの資格を保有しているか？
	private String like; // 趣味
	private String food; // 好きな食べ物
	private String game; // 好きなゲーム
	private String hp; // 自分のホームページ
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isHasMCPC() {
		return hasMCPC;
	}
	public void setHasMCPC(boolean hasMCPC) {
		this.hasMCPC = hasMCPC;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
}
