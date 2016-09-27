package com.game.monster.handler{

	import com.game.monster.message.ResMonsterMaxHpChangeMessage;
	import net.Handler;

	public class ResMonsterMaxHpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterMaxHpChangeMessage = ResMonsterMaxHpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}