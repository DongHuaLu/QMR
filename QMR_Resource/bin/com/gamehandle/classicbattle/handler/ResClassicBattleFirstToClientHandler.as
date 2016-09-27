package com.game.classicbattle.handler{

	import com.game.classicbattle.message.ResClassicBattleFirstToClientMessage;
	import net.Handler;

	public class ResClassicBattleFirstToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResClassicBattleFirstToClientMessage = ResClassicBattleFirstToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}