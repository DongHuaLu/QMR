package com.game.fightpower.handler{

	import com.game.fightpower.message.ResFightPowerToClientMessage;
	import net.Handler;

	public class ResFightPowerToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResFightPowerToClientMessage = ResFightPowerToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}