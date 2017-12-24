package WhiteBlack.Interface;

import WhiteBlack.Team;

/**
 * @author chenhaoyu 全队buff接口
 */
public interface TeamBuff {

	/**
	 * @param team
	 *            目标team
	 * @param num
	 *            buff量
	 */
	public void teamAtkBuff(Team team, int num);

	/**
	 * @param team
	 *            目标team
	 * @param num
	 *            buff量
	 */
	public void teamDefBuff(Team team, int num);

}
