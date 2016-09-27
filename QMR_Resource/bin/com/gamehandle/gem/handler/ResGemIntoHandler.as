package com.game.gem.handler{

	import com.game.gem.message.ResGemIntoMessage;
	import net.Handler;

	public class ResGemIntoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGemIntoMessage = ResGemIntoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}