package com.game.classicbattle.handler{

	import com.game.classicbattle.message.ResClassicBattleSuccessToClientMessage;
	import net.Handler;

	public class ResClassicBattleSuccessToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResClassicBattleSuccessToClientMessage = ResClassicBattleSuccessToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}