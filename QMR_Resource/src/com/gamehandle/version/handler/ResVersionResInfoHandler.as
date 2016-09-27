package com.game.version.handler{

	import com.game.version.message.ResVersionResInfoMessage;
	import net.Handler;

	public class ResVersionResInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResVersionResInfoMessage = ResVersionResInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}