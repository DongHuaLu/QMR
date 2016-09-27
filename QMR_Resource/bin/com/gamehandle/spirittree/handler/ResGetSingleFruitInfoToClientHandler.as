package com.game.spirittree.handler{

	import com.game.spirittree.message.ResGetSingleFruitInfoToClientMessage;
	import net.Handler;

	public class ResGetSingleFruitInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetSingleFruitInfoToClientMessage = ResGetSingleFruitInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}