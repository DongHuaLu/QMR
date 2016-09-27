package com.game.gem.handler{

	import com.game.gem.message.ResGemErrorInfoMessage;
	import net.Handler;

	public class ResGemErrorInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGemErrorInfoMessage = ResGemErrorInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}