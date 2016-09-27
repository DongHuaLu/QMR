package com.game.spirittree.handler{

	import com.game.spirittree.message.ResContinuousRipeningToClientMessage;
	import net.Handler;

	public class ResContinuousRipeningToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResContinuousRipeningToClientMessage = ResContinuousRipeningToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}