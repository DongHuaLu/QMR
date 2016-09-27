package com.game.classicbattle.handler{

	import com.game.classicbattle.message.ResClassicBattleInfoTokenLackToClientMessage;
	import net.Handler;

	public class ResClassicBattleInfoTokenLackToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResClassicBattleInfoTokenLackToClientMessage = ResClassicBattleInfoTokenLackToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}