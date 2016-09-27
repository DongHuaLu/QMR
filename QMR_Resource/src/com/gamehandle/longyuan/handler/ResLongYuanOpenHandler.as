package com.game.longyuan.handler{

	import com.game.longyuan.message.ResLongYuanOpenMessage;
	import net.Handler;

	public class ResLongYuanOpenHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLongYuanOpenMessage = ResLongYuanOpenMessage(this.message);
			//TODO 添加消息处理
		}
	}
}