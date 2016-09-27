package com.game.longyuan.handler{

	import com.game.longyuan.message.ResLongYuanPosTipsMessage;
	import net.Handler;

	public class ResLongYuanPosTipsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLongYuanPosTipsMessage = ResLongYuanPosTipsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}