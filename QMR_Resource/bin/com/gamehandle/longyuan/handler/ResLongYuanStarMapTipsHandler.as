package com.game.longyuan.handler{

	import com.game.longyuan.message.ResLongYuanStarMapTipsMessage;
	import net.Handler;

	public class ResLongYuanStarMapTipsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLongYuanStarMapTipsMessage = ResLongYuanStarMapTipsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}