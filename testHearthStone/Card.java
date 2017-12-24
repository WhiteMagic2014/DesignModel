package testHearthStone;

import java.awt.Color;
import java.awt.Image;

import testHearthStone.interfaces.Target;

public abstract class Card {
	int cost;// 费用
	int rareLevel;// 稀有度
	Boolean golden;// 是否为金卡
	Image photo;// 卡牌图片
	String occupation;// 职业
	String kind;// 属性 龙 元素 鱼人等。。
	Color bgColor;// 职业背景颜色
	String description;// 异能描述

	public abstract void init();
}

abstract class MagicCard extends Card {

}

abstract class WeaponCard extends Card {
	int atk;
	int durability;
}

abstract class MonsterCard extends Card implements Target{
	int atk;
	int hp;
}
