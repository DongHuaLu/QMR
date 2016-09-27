package com.game.monster.handler{

	import com.game.monster.message.ResMonsterSayMessage;
	import net.Handler;

	public class ResMonsterSayHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterSayMessage = ResMonsterSayMessage(this.message);
			//TODO 添加消息处理
		}
	}
}