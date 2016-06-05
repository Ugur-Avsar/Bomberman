package combat;

import java.util.ArrayList;
import java.util.List;

import sound.SoundPlayer;

public final class BombMaster {
	private static List<Bomb> livingBombs = new ArrayList<Bomb>();
	private SoundPlayer soundPlayer;
	private static boolean soundPlaying= true;

	public static void update() {
		for (int i = 0; i < livingBombs.size(); i++)
			livingBombs.get(i).update();
	}

	public static boolean addBomb(Player p) {
		return livingBombs
				.add(new Bomb(p.getX() + p.getWidth() / 2, p.getY() + p.getHeight() / 2, 75, 100, 200, "bomb", p));
	}

	public static boolean destroyBomb(Bomb b) {
		b.getSource().decBombsSet();
		return livingBombs.remove(b);
	}

	public static List<Bomb> getBombs() {
		return new ArrayList<>(livingBombs);
	}
	
	/*public  void soundRe()
	{
		soundPlaying =true;
		
		if(!soundPlayer.isPlaying())
		{
			soundPlayer.restart();
		}
	}*/
}