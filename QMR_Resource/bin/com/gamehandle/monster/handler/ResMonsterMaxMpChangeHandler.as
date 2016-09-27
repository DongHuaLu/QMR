package com.game.monster.handler{

	import com.game.monster.message.ResMonsterMaxMpChangeMessage;
	import net.Handler;

	public class ResMonsterMaxMpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterMaxMpChangeMessage = ResMonsterMaxMpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}