package com.game.wine.handler{

	import com.game.wine.message.ResWineInfoMessage;
	import net.Handler;

	public class ResWineInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWineInfoMessage = ResWineInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}