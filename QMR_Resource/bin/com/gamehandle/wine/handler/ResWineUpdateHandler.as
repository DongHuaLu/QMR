package com.game.wine.handler{

	import com.game.wine.message.ResWineUpdateMessage;
	import net.Handler;

	public class ResWineUpdateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWineUpdateMessage = ResWineUpdateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}