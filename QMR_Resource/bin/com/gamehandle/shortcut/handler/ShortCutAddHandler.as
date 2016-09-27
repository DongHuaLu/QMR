package com.game.shortcut.handler{

	import com.game.shortcut.message.ShortCutAddMessage;
	import net.Handler;

	public class ShortCutAddHandler extends Handler {
	
		public override function action(): void{
			var msg: ShortCutAddMessage = ShortCutAddMessage(this.message);
			//TODO 添加消息处理
		}
	}
}