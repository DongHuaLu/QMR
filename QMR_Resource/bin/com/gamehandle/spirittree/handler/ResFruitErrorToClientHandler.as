package com.game.spirittree.handler{

	import com.game.spirittree.message.ResFruitErrorToClientMessage;
	import net.Handler;

	public class ResFruitErrorToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResFruitErrorToClientMessage = ResFruitErrorToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}