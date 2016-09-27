package com.game.gem.handler{

	import com.game.gem.message.ResGemExtraExpMessage;
	import net.Handler;

	public class ResGemExtraExpHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGemExtraExpMessage = ResGemExtraExpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}