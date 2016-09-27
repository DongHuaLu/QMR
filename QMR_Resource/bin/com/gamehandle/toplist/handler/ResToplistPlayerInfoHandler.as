package com.game.toplist.handler{

	import com.game.toplist.message.ResToplistPlayerInfoMessage;
	import net.Handler;

	public class ResToplistPlayerInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResToplistPlayerInfoMessage = ResToplistPlayerInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}