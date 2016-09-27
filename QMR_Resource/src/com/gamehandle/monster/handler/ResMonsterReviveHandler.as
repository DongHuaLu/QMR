package com.game.monster.handler{

	import com.game.monster.message.ResMonsterReviveMessage;
	import net.Handler;

	public class ResMonsterReviveHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterReviveMessage = ResMonsterReviveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}