package com.game.buff.handler{

	import com.game.buff.message.ResAddBuffMessage;
	import net.Handler;

	public class ResAddBuffHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAddBuffMessage = ResAddBuffMessage(this.message);
			//TODO 添加消息处理
		}
	}
}