package com.game.monster.handler{

	import com.game.monster.message.ResMonsterDieMessage;
	import net.Handler;

	public class ResMonsterDieHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterDieMessage = ResMonsterDieMessage(this.message);
			//TODO 添加消息处理
		}
	}
}