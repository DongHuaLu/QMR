package com.game.marriage.handler{

	import com.game.marriage.message.ResGetSpouseLongYuanToClientMessage;
	import net.Handler;

	public class ResGetSpouseLongYuanToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetSpouseLongYuanToClientMessage = ResGetSpouseLongYuanToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}