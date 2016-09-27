package com.game.dazuo.handler{

	import com.game.dazuo.message.ResDazuoStateBroadCastMessage;
	import net.Handler;

	public class ResDazuoStateBroadCastHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDazuoStateBroadCastMessage = ResDazuoStateBroadCastMessage(this.message);
			//TODO 添加消息处理
		}
	}
}