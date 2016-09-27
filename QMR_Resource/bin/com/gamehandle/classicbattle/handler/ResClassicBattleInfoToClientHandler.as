package com.game.classicbattle.handler{

	import com.game.classicbattle.message.ResClassicBattleInfoToClientMessage;
	import net.Handler;

	public class ResClassicBattleInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResClassicBattleInfoToClientMessage = ResClassicBattleInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}